/**
  * Created by T440P on 2016/8/15.
  */
object StringFunctionTest {
  def main(args: Array[String]) {
    val startsWithTest = "/targetpage /jipiaojiansuo"
    val regex = "/targetpage[\\s]*/"
    println(startsWithTest.startsWith(regex))
  }
}
