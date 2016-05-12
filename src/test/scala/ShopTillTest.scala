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

    assert(shop.price3For2(1, o)==25)
    assert(shop.price3For2(2, o)==50)
    assert(shop.price3For2(3, o)==50)
    assert(shop.price3For2(4, o)==75)
    assert(shop.price3For2(5, o)==100)
    assert(shop.price3For2(6, o)==100)
    assert(shop.price3For2(16, o)==275)
    assert(shop.price3For2(17, o)==300)

    assert(shop.price2For1(1, a)==60)
    assert(shop.price2For1(2, a)==60)
    assert(shop.price2For1(3, a)==120)
    assert(shop.price2For1(4, a)==120)
    assert(shop.price2For1(5, a)==180)
    assert(shop.price2For1(6, a)==180)
    assert(shop.price2For1(16, a)==480)
    assert(shop.price2For1(17, a)==540)

  }
}
