package TestFunction

import scala.collection.mutable

/**
  * Created by T440P on 2016/4/22.
  */
object ScalaMapTest {
  def main(args: Array[String]) {
    val stringMap = mutable.Map[String, Int]()
    stringMap += ("test"->3)
    println(stringMap)

  }
}
