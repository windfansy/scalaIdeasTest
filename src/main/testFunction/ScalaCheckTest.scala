import scala.util.Random

import org.scalacheck.Gen

/**
  * Created by T440P on 2016/7/6.
  */
object ScalaCheckTest {
  def main(args: Array[String]) {
//    val stringGen = for {
//      n <- Gen.choose(0, 20)
//    } yield (Random.alphanumeric take n) mkString
////      yield
//
//    println(stringGen)
//    println(stringGen.sample.get)
//    println((Random.alphanumeric take 10).mkString)

   println("9fd3c359-d7ad-4bee-beb5-6717fee58a9e".hashCode.toString.last)
   println(s"${"81aec5f3-5c21-4849-aa48-0c54dea9d18d".hashCode.toString.last}${2915}${"@"}${"81aec5f3-5c21-4849-aa48-0c54dea9d18d"}")
  }
}
