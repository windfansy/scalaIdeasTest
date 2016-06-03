package FunctionalProgrammingPrinciplesInScala.week5

/**
  * Created by T440P on 2016/3/31.
  */
object ListDemo2 {
  def main(args: Array[String]) {
    val xs = new Cons[String]("head", Nil)
  }
}

trait List[+T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]

  // def prepend(elem: T): List[T] = new Cons(elem, this)  error for method parameter don't support covariant
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
}

object Nil extends List[Nothing] {
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
