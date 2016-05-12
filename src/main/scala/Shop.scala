trait HasPrice {
  val priceInPence: Int
}
case class Apple(priceInPence: Int) extends HasPrice
case class Orange(priceInPence: Int) extends HasPrice

class Shop {
  def checkout(basket: List[HasPrice]) = basket.map(_.priceInPence).foldLeft(0)(_+_)

  def price3For2(count: Int, hasPrice: HasPrice) = (count / 3) * hasPrice.priceInPence * 2 + (count % 3) * hasPrice.priceInPence
  def price2For1(count: Int, hasPrice: HasPrice) = (count / 2) * hasPrice.priceInPence + (count % 2) * hasPrice.priceInPence

}
