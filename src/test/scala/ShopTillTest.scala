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

    assert(shop.priceXForY(1, o,3,2)==25)
    assert(shop.priceXForY(2, o,3,2)==50)
    assert(shop.priceXForY(3, o,3,2)==50)
    assert(shop.priceXForY(4, o,3,2)==75)
    assert(shop.priceXForY(5, o,3,2)==100)
    assert(shop.priceXForY(6, o,3,2)==100)
    assert(shop.priceXForY(16, o,3,2)==275)
    assert(shop.priceXForY(17, o,3,2)==300)

    assert(shop.priceXForY(1, a,2,1)==60)
    assert(shop.priceXForY(2, a,2,1)==60)
    assert(shop.priceXForY(3, a,2,1)==120)
    assert(shop.priceXForY(4, a,2,1)==120)
    assert(shop.priceXForY(5, a,2,1)==180)
    assert(shop.priceXForY(6, a,2,1)==180)
    assert(shop.priceXForY(16, a,2,1)==480)
    assert(shop.priceXForY(17, a,2,1)==540)

  }
}
