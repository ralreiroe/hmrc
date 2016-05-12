trait HasPrice {
  val priceInPence: Int
}
case class Apple(priceInPence: Int) extends HasPrice
case class Orange(priceInPence: Int) extends HasPrice

class Shop {
  def checkout(basket: List[HasPrice]) = {
    priceXForY(basket, Apple(60),2,1) +
    priceXForY(basket, Orange(25),3,2)
  }

  private def _priceXForY(count: Int, hasPrice: HasPrice, leftOfFor: Int, rightOfFor: Int) = (count / leftOfFor) * hasPrice.priceInPence * rightOfFor + (count % leftOfFor) * hasPrice.priceInPence

  def priceXForY(basket: List[HasPrice], hasPrice: HasPrice, leftOfFor: Int, rightOfFor: Int) = {
    _priceXForY(basket.count(_.equals(hasPrice)), hasPrice, leftOfFor, rightOfFor)
  }
}
