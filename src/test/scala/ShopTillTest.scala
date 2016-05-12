import org.scalatest._

class ShopTillTest extends FlatSpec with Matchers {

  def shop = new Shop()

  "Calculating the total cost" should "work" in {

    def a = Apple(60)
    def o = Orange(25)

    assert(shop.checkout(List(a)) == 60)
    assert(shop.checkout(List(o)) == 25)
    assert(shop.checkout(List(a, a)) == 120)
    assert(shop.checkout(List(a, o, a, a)) == 205)
  }
}
