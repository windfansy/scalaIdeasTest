package FunctionalProgrammingPrinciplesInScala.week2

/**
  * Created by T440P on 2016/5/20.
  */
object RationalDemo {
  def main(args: Array[String]) {
    testRational()
    testSimplifiedRational()
    testOperatorRational()
  }

  def testRational() = {
    val r1 = new Rational(3, 4)
    val r2 = new Rational(4, 7)
    println(r1.mkString)
    println(r1.neg.mkString)
    println(r1.add(r2).mkString)
    println(r1.sub(r2).mkString)
  }

  def testSimplifiedRational() = {
    val r1 = new Rational(3, 5)
    val r2 = new Rational(4, 6)
    println(r2.mkString)
    println(r2.neg.mkString)
    println(r1.add(r2).mkString)
    println(r1.sub(r2).mkString)
  }

  def testOperatorRational() = {
    val r1 = new Rational(3, 5)
    val r2 = new Rational(4, 6)
    println((r1 + r2).mkString)
    println((r1 - r2).mkString)
    println((r1 * r2).mkString)
    println((r1 / r2).mkString)
  }
}

class Rational(x: Int, y: Int) {
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  val numer = x / gcd(x, y)
  val denom = y / gcd(x, y)

  def add(that: Rational) = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def neg = new Rational(-numer, denom)

  def sub(that: Rational) = add(that.neg)

  def mkString = numer + "/" + denom

  def +(that: Rational) = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def -(that: Rational) = add(that.neg)

  def *(that: Rational) = {
    new Rational(numer * that.numer, denom * that.denom)
  }

  def /(that: Rational) = {
    new Rational(numer * that.denom, denom * that.numer)
  }

}



