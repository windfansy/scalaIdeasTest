package TestFunction

import java.text.SimpleDateFormat
import java.util.Date

/**
  * Created by T440P on 2016/5/4.
  */
object SystemCurrentTimeTest {
  def main(args: Array[String]) {
    val allDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    val timeFormat = new SimpleDateFormat("HH:mm")
    println(allDateFormat.format(new Date(1146656000000L)))
    println(timeFormat.format(new Date(1146656000000L)))
    //println("5ab33f4387134f4386cf00eff5e41f67".length)
  }
}
