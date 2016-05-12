trait HasPrice {
  val priceInPence: Int
}
case class Apple(priceInPence: Int) extends HasPrice
case class Orange(priceInPence: Int) extends HasPrice

class Shop {
  def checkout(basket: List[HasPrice]) = {
    price2For1(basket.count(_.equals(Apple(60))), Apple(60)) +
    price3For2(basket.count(_.equals(Orange(25))), Orange(25))
  }

  def price3For2(count: Int, hasPrice: HasPrice) = (count / 3) * hasPrice.priceInPence * 2 + (count % 3) * hasPrice.priceInPence
  def price2For1(count: Int, hasPrice: HasPrice) = (count / 2) * hasPrice.priceInPence + (count % 2) * hasPrice.priceInPence

}
