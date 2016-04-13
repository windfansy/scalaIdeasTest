import scala.collection.immutable.List

object InsertSort {
  def iSort(xs: List[Int]): List[Int] = {
    xs match {
      case List() => List()
      case y :: ys => insert(y, iSort(ys))
    }
  }

  def insert(y: Int, ys: List[Int]): List[Int] = {
    ys match {
      case List() => List(y)
      case x :: xs => if (y <= x) y :: ys else x :: insert(y, xs)
    }
  }

  val testList = List(6, 5, 18, 32, 9)
  iSort(testList)
  val testList2 = List(6, 1, 3, 4, 18)
  iSort(testList2)

}