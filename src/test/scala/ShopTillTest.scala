import org.scalatest._

class ShopTillTest extends FlatSpec with Matchers {

  def shop = new Shop()

  "Calculating the total cost" should "work" in {

    def a = Apple(60)
    def o = Orange(25)

    assert(shop.checkout(List(a)) == 60)
    assert(shop.checkout(List(o)) == 25)
    assert(shop.checkout(List(a, a)) == 60)
    assert(shop.checkout(List(a, o, a, a)) == 145)
  }

  "An empty basket" should "work" in {

    assert(shop.checkout(List()) == 0)
  }
  
  "Discount functions" should "work" in {

    def a = Apple(60)
    def o = Orange(25)

    assert(shop.priceXForY(List(o), o,3,2)==25)
    assert(shop.priceXForY(List.fill(2)(o), o,3,2)==50)
    assert(shop.priceXForY(List.fill(6)(o), o,3,2)==100)
    assert(shop.priceXForY(List.fill(16)(o), o,3,2)==275)
    assert(shop.priceXForY(List.fill(17)(o), o,3,2)==300)

    assert(shop.priceXForY(List.fill(1)(a), a,2,1)==60)
    assert(shop.priceXForY(List.fill(2)(a), a,2,1)==60)
    assert(shop.priceXForY(List.fill(3)(a), a,2,1)==120)
    assert(shop.priceXForY(List.fill(4)(a), a,2,1)==120)
    assert(shop.priceXForY(List.fill(5)(a), a,2,1)==180)
    assert(shop.priceXForY(List.fill(6)(a), a,2,1)==180)
    assert(shop.priceXForY(List.fill(16)(a), a,2,1)==480)
    assert(shop.priceXForY(List.fill(17)(a), a,2,1)==540)

  }
}
