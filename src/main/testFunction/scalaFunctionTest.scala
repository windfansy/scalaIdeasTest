/**
  * Created by T440P on 2016/8/5.
  */
object scalaFunctionTest {
  def main(args: Array[String]) {
    val numList = Seq(1, 2, 3, 4, 5)
    val (left, right) = numList.span(_<)
  }
}
