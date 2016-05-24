/**
  * Created by T440P on 2016/5/24.
  */
object ValDefTest {
  def main(args: Array[String]) {
    testVal
    testDef
    testLazyVal
    testVal
    testDef
    testLazyVal
  }

  val testVal = {
    println("val calc only once")
    "val"
  }

  def testDef = {
    println("def calc every time when calling")
    "def"
  }

  lazy val testLazyVal = {
    println("lazy val calc only once")
    "lazy val"
  }
}
