package TestFunction

import java.text.SimpleDateFormat
import java.util.Date

/**
  * Created by windfansy on 2016/5/12.
  */
object DateFormatTest {
  def main(args: Array[String]) {
    val allDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    val timeFormat = new SimpleDateFormat("HH:mm")
    println(allDateFormat.format(new Date(1146656000000L)))
    println(timeFormat.format(new Date(1146656000000L)))
    System.currentTimeMillis()
  }
}
