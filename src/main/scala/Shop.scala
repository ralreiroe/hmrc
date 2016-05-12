trait HasPrice {
  val priceInPence: Int
}
case class Apple(priceInPence: Int) extends HasPrice
case class Orange(priceInPence: Int) extends HasPrice

class Shop {
  def checkout(basket: List[HasPrice]) = {
    priceXForY(basket.count(_.equals(Apple(60))), Apple(60),2,1) +
    priceXForY(basket.count(_.equals(Orange(25))), Orange(25),3,2)
  }

  def priceXForY(count: Int, hasPrice: HasPrice, leftOfFor: Int, rightOfFor: Int) = (count / leftOfFor) * hasPrice.priceInPence * rightOfFor + (count % leftOfFor) * hasPrice.priceInPence
}
