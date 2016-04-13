package FunctionalProgrammingPrinciplesInScala.week6

import scala.collection.immutable.List

/**
  * Created by T440P on 2016/4/5.
  */
object flattenImpl {
  def main(args: Array[String]) {
    println(doFlatten(List(List(1, 1), 2, List(), List(3, List(5, 8)))))
  }

  def doFlatten(xs: List[Any]): List[Any] = {
    xs match {
      case List() => xs
      case x :: xs1 => x match {
        case List() => doFlatten(xs1)
        case y :: ys => y :: doFlatten(ys) ++ doFlatten(xs1)
        case _ => x :: doFlatten(xs1)
      }
    }
  }
}
