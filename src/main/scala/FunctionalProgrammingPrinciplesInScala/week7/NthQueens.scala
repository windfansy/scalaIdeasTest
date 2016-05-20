package FunctionalProgrammingPrinciplesInScala.week7

/**
  * Created by T440P on 2016/4/14.
  */
object NthQueens {
  def main(args: Array[String]) {
    /*println(queens(4) map showImplByFor mkString "\n")
    println(queens(4) map showImplByMap mkString "\n")
    println(showAll(queens(4)))*/

    println(queens(8) take 3 map showImplByMap mkString "\n")
    println(showAll(queens(8), 3))
    println(showAll(queens(4), 3))
    //queens(1)
    // println(queens(8) map showImplByFor mkString "\n")

  }

  def queens(n: Int) = {
    def isSafe(col: Int, queens: List[Int]) = {
      (queens.length - 1 to 0 by -1) zip queens forall {
        case (r, c) => c != col && math.abs(col - c) != queens.length - r
      }
    }
    def placeQueens(k: Int): Set[List[Int]] = {//之所以写placeQueens而不是直接用queens去递归，并不是因为尾递归，而是col的取值永远是0 until n
      if (k == 0) Set(List())
      else for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
    }
    placeQueens(n)
  }

  /*def queens(n: Int): Set[List[Int]] = {

    def isSafe(col: Int, queensList: List[Int]) = {
      (queensList.length - 1 to 0 by -1) zip queensList forall {
        case (r, c) => c != col && math.abs(col - c) != queensList.length - r
      }
    }

    if (n == 0) Set(List())
    else for {
      queensList <- queens(n - 1)
      col <- 0 until n
      if isSafe(col, queensList)
    } yield col :: queensList

  }*/


  def showAll(queens: Set[List[Int]], showNumber: Int): String = {
    val queensDsp = queens take showNumber map {
      queen =>
        queen.reverse map (col =>
          Vector.fill(queen.length)("* ").updated(col, "X ").mkString) mkString "\n"
    }
    "\n" + (queensDsp mkString "\n\n")
  }

  def showAll(queens: Set[List[Int]]): String = {
    showAll(queens, queens.size)
  }

  def showImplByMap(queens: List[Int]): String = {
    val queensDsp = queens.reverse map {
      col =>
        Vector.fill(queens.length)("* ").updated(col, "X ").mkString
    } mkString "\n"
    "\n" + queensDsp
  }

  def showImplByFor(queens: List[Int]) = {
    val lines =
      for (col <- queens.reverse)
        yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
    "\n" + lines.mkString("\n")
  }


}
