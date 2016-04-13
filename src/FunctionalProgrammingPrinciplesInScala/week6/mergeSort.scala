package FunctionalProgrammingPrinciplesInScala.week6

import scala.collection.immutable.List

/**
  * Created by T440P on 2016/4/5.
  */
object mergeSort {
  def main(args: Array[String]) {
    val nums = List(-4, 3, -9, 0, 89, -5, 4, 7, -9)
    println(mSort(nums))
  }

  def mSort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (leftList, rightList) = xs.splitAt(n)
      def merge(xs: List[Int], ys: List[Int]): List[Int] = {
        (xs, ys) match {
          case (List(), _) => ys
          case (_, List()) => xs
          case (x :: xs1, y :: ys1) =>
            if (x < y) x :: merge(xs1, ys)
            else y :: merge(xs, ys1)
        }
      }
      merge(mSort(leftList), mSort(rightList))
    }
  }
}
