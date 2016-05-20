package FunctionalProgrammingPrinciplesInScala.week2

/**
  * Created by T440P on 2016/5/20.
  */
object CurryDemo {
  def main(args: Array[String]) {
    testSum()
    testProduct()
    testSum2()
    testProduct2()
  }

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f)(a + 1, b)
  }

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)
  }

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }

  def sum2(f: Int => Int)(a: Int, b: Int) = mapReduce(f, (x, y) => x + y, 0)(a, b)

  def product2(f: Int => Int)(a: Int, b: Int) = mapReduce(f, (x, y) => x * y, 1)(a, b)

  def testSum() = {
    println(sum(x => x * x)(3, 5))
    println(sum(x => x)(3, 5))
  }

  def testProduct() = {
    println(product(x => x * x)(3, 5))
    println(product(x => x)(3, 5))
  }

  def testSum2() = {
    println(sum2(x => x * x)(3, 5))
    println(sum2(x => x)(3, 5))
  }

  def testProduct2() = {
    println(product2(x => x * x)(3, 5))
    println(product2(x => x)(3, 5))
  }
}
