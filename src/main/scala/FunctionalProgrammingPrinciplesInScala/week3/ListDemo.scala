package FunctionalProgrammingPrinciplesInScala.week3

/**
  * Created by T440P on 2016/3/30.
  */
object ListDemo {
  def main(args: Array[String]) {
    println(singleton[Int](3))
    val testList1 = new Cons[Int](
        1, new Cons[Int](2, new Cons[Int](3, new Cons[Int](4, new Nil[Int]))))
    println(testList1)
    println(nth(3, testList1))
    println("\n")
    println(nth(4, testList1))
  }

  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

  def nth[T](n: Int, xs: List[T]): T = {
    if (n < 0 || xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) xs.head
    else nth(n - 1, xs.tail)
  }
}

trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}

class Nil[T] extends List[T] {
  def isEmpty = true

  def head: Nothing = throw new NoSuchMethodException("Nil's head")

  def tail: Nothing = throw new NoSuchMethodException("Nil's tail")

  override def toString = "Nil"
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  //val can override def method
  def isEmpty = false

  override def toString = head + " - " + tail.toString
}
