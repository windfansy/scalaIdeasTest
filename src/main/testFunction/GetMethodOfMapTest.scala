package TestFunction

import scala.collection.immutable.HashMap

/**
  * Created by T440P on 2016/3/28.
  */
object GetMethodOfMapTest {
  def main(args: Array[String]): Unit = {
    val testMap = HashMap[Int, String]((1, "fuck"), (10, "boy"))
    if (testMap.get(1) != None) println("equal") else println("unequal")
    if (testMap.get(10).isDefined) println("equal") else println("unequal")
    if (testMap.get(13).isDefined) println("equal") else println("unequal")
    if (testMap.get(13) == None) println("equal") else println("unequal")

    println(testMap.get(10).getOrElse("no value"))
    println(testMap.get(13).getOrElse("no value"))
  }
}
