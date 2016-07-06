import scala.util.Random

import org.scalacheck.Gen

/**
  * Created by T440P on 2016/7/6.
  */
object ScalaCheckTest {
  def main(args: Array[String]) {
    val stringGen = for {
      n <- Gen.choose(0, 20)
    } yield (Random.alphanumeric take n) mkString
//      yield

    println(stringGen)
    println(stringGen.sample.get)
    println((Random.alphanumeric take 10).mkString)
  }
}
