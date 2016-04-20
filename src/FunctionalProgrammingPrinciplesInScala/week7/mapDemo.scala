package FunctionalProgrammingPrinciplesInScala.week7

/**
  * Created by T440P on 2016/4/20.
  */
object mapDemo {
  def main(args: Array[String]) {
    val fruits = List("apple", "banana", "pear", "orange")
    println(fruits.sorted)
    println(fruits.sortWith(_.length < _.length))

    //Poly Demo
    println("Poly Demo")
    val poly1 = new Poly(Map[Int, Double](3 -> 3.0, 2 -> 4.1, 0 -> 5))
    val poly2 = new Poly((3, 2.0), (1, 3.5), (0, 6))
    val poly3 = poly1 + poly2
    println(poly1)
    println(poly2)
    println(poly3)

    println("Poly2 Demo")
    val poly2_1 = new Poly(Map[Int, Double](3 -> 3.0, 2 -> 4.1, 0 -> 5))
    val poly2_2 = new Poly((3, 2.0), (1, 3.5), (0, 6))
    val poly2_3 = poly2_1 + poly2_2
    println(poly2_1)
    println(poly2_2)
    println(poly2_3)

  }
}

class Poly(terms0: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = terms0 withDefaultValue 0.0

  def +(other: Poly): Poly = new Poly(terms ++ (other.terms map adjust))

  def adjust(term: (Int, Double)): (Int, Double) = {
    val (expo, coef) = term
    (expo, terms(expo) + coef)
  }

  override def toString = terms.toList.sorted.reverse.map { case (expo, coef) => coef + "x^" + expo }.mkString(" + ")
}

class Poly2(terms0: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = terms0 withDefaultValue 0.0

  def +(other: Poly): Poly = new Poly((other.terms foldLeft terms) (addTerm))

  def addTerm(result: Map[Int, Double], current: (Int, Double)): Map[Int, Double] = {
    val (expo, coef) = current
    result + (expo -> (result(expo) + coef))
  }

  override def toString = terms.toList.sorted.reverse.map { case (expo, coef) => coef + "x^" + expo }.mkString(" + ")
}
