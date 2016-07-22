/**
  * Created by T440P on 2016/7/14.
  */
object LastOptionNoneTest {
  def main(args: Array[String]) {
    val seq1 = List(4, 4, 3, 1, 3, 3)
    val seq2 = seq1.flatMap { (ele: Int) => if (ele > 3) Some(ele) else None }
    println(seq2.lastOption.get)

    val test =
      s"""|${if (3 > 1) 2 else 1}
         |${if (1 < 0) 1 else 0}
      """.stripMargin.replaceAll("\n", " ")
    println(test)

  }
}
