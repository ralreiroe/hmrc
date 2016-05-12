import scala.collection.immutable

trait HasPrice {
  val priceInPence: Int
}
case class Apple(priceInPence: Int) extends HasPrice
case class Orange(priceInPence: Int) extends HasPrice

class Shop(offers: Map[HasPrice, (List[HasPrice] => HasPrice => Int)]) {

  import PriceFunctions._
  def checkout(basket: List[HasPrice]) = {
    val totalPerProduct = offers.map{
      t => {
        val prod = t._1
        val func: (List[HasPrice]) => (HasPrice) => Int = t._2

        func(basket)(prod)
      }
    }

    totalPerProduct.foldLeft(0)(_+_)
  }

}

object PriceFunctions {

  def _priceXForY(count: Int, hasPrice: HasPrice, leftOfFor: Int, rightOfFor: Int) = (count / leftOfFor) * hasPrice.priceInPence * rightOfFor + (count % leftOfFor) * hasPrice.priceInPence

  def priceXForY(basket: List[HasPrice], hasPrice: HasPrice, leftOfFor: Int, rightOfFor: Int) = {
    _priceXForY(basket.count(_.equals(hasPrice)), hasPrice, leftOfFor, rightOfFor)
  }
}
