import org.scalatest._

class ShopTillTest extends FlatSpec with Matchers {

  "Calculating the total cost with discounts" should "work" in {

    def a = Apple(60)
    def o = Orange(25)

    val appleOffer2For1: List[HasPrice] => HasPrice => Int = basket => prod => {
      PriceFunctions._priceXForY(basket.count(_.equals(a)), prod, 2, 1)
    }
    val orangeOffer3For2: List[HasPrice] => HasPrice => Int = basket => prod => {
      PriceFunctions._priceXForY(basket.count(_.equals(o)), prod, 3, 2)
    }

    def shop = new Shop(Map(a -> appleOffer2For1, o -> orangeOffer3For2))

    assert(shop.checkout(List(a)) == 60)
    assert(shop.checkout(List(o)) == 25)
    assert(shop.checkout(List(a, a)) == 60)
    assert(shop.checkout(List(a, o, a, a)) == 145)
    assert(shop.checkout(List(a, o, o, o, a, a)) == 170)
  }

  "Calculating the total cost without discounts" should "work" in {

    def a = Apple(60)
    def o = Orange(25)

    val noAppleOffer: List[HasPrice] => HasPrice => Int = basket => prod => {
      basket.count(_.equals(a)) * prod.priceInPence
    }
    val noOrangeOffer: List[HasPrice] => HasPrice => Int = basket => prod => {
      PriceFunctions._priceXForY(basket.count(_.equals(o)), prod, 1, 1)
    }

    def shop = new Shop(Map(a -> noAppleOffer, o -> noOrangeOffer))

    assert(shop.checkout(List(a)) == 60)
    assert(shop.checkout(List(o)) == 25)
    assert(shop.checkout(List(a, a)) == 120)
    assert(shop.checkout(List(a, o, a, a)) == 205)
    assert(shop.checkout(List(a, o, o, o, a, a)) == 255)
  }

  "An empty basket" should "work" in {

    assert(new Shop(Map()).checkout(List()) == 0)
  }

  "Discount functions" should "work" in {

    def a = Apple(60)
    def o = Orange(25)

    assert(PriceFunctions.priceXForY(List(o), o,3,2)==25)
    assert(PriceFunctions.priceXForY(List.fill(2)(o), o,3,2)==50)
    assert(PriceFunctions.priceXForY(List.fill(6)(o), o,3,2)==100)
    assert(PriceFunctions.priceXForY(List.fill(16)(o), o,3,2)==275)
    assert(PriceFunctions.priceXForY(List.fill(17)(o), o,3,2)==300)

    assert(PriceFunctions.priceXForY(List.fill(1)(a), a,2,1)==60)
    assert(PriceFunctions.priceXForY(List.fill(2)(a), a,2,1)==60)
    assert(PriceFunctions.priceXForY(List.fill(3)(a), a,2,1)==120)
    assert(PriceFunctions.priceXForY(List.fill(4)(a), a,2,1)==120)
    assert(PriceFunctions.priceXForY(List.fill(5)(a), a,2,1)==180)
    assert(PriceFunctions.priceXForY(List.fill(6)(a), a,2,1)==180)
    assert(PriceFunctions.priceXForY(List.fill(16)(a), a,2,1)==480)
    assert(PriceFunctions.priceXForY(List.fill(17)(a), a,2,1)==540)

  }
}
