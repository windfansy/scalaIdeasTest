/**
  * Created by T440P on 2016/8/5.
  */
object scalaFunctionTest {
  val targetpagePattern = """/[\s]*targetpage[\s]*/""".r

  def main(args: Array[String]) {
    val numList = Seq(1, 2, 3, 4, 5)
    val (left, right) = numList.span(_ < 6)
    println(left)
    println(right)

    val path1 = "/targetpage /jipiaojiansuo"
    val path2 = "/targetpage/jipiaojiansuo"
    val path3 = "/targetpage    /jipiaojiansuo"
    val path4 = "/targetpage    k/jipiaojiansuo"

    println(judgeVirtualPv(path1))
    println(judgeVirtualPv(path2))
    println(judgeVirtualPv(path3))
    println(judgeVirtualPv(path4))
  }

  def judgeVirtualPv(path: String) = {
    targetpagePattern.findPrefixOf(path) match{
      case None => false
      case _ => true
    }
  }
}
