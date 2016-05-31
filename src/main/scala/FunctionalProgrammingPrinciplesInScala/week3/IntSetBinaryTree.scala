package FunctionalProgrammingPrinciplesInScala.week3

/**
  * Created by T440P on 2016/3/28.
  */
object IntSetBinaryTree {
  def main(args: Array[String]): Unit = {
    val n1 = new NonEmpty(3, Empty, Empty)
    println(n1)
    val n2 = n1 incl 2 incl 5 incl 4
    val n3 = n1 incl 2 incl 1 incl 7 incl 6 incl 8
    val n4 = n2 union n3
    println(n2)
    println(n3)
    println(n4)
  }
}

abstract class IntSet {
  def contains(x: Int): Boolean

  def incl(x: Int): IntSet

  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def contains(x: Int) = false

  def incl(x: Int) = new NonEmpty(x, Empty, Empty)

  def union(other: IntSet) = other

  override def toString = "."
}

class NonEmpty(val ele: Int, val left: IntSet, val right: IntSet)
    extends IntSet {
  def contains(x: Int) = {
    if (ele > x) left contains x
    else if (ele < x) right contains x
    else true
  }

  def incl(x: Int): IntSet = {
    if (ele > x) new NonEmpty(ele, left incl x, right)
    else if (ele < x) new NonEmpty(ele, left, right incl x)
    else this
  }

  def union(other: IntSet) = {
    left union (right union (other incl ele))
  }

  override def toString = "{" + left + ele + right + "}"
}
