trait HasPrice {
  val priceInPence: Int
}
case class Apple(priceInPence: Int) extends HasPrice
case class Orange(priceInPence: Int) extends HasPrice

class Shop {
  def checkout(basket: List[HasPrice]) = basket.map(_.priceInPence).foldLeft(0)(_+_)
}
