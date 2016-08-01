import java.util.Date

import org.joda.time.DateTime

/**
  * Created by T440P on 2016/6/16.
  */
object DateTimeCalcTest {
  def main(args: Array[String]) {
    val d1 = new Date(1466696077577L)
    val d2 = new DateTime(2016, 6, 23, 23, 34, 38)
    println(d1)
    println(d2.getMillis)
    println(d2.minusMonths(1))
    // val d1.getTime 1466047809526
    val delay = 1000

    val dt = new DateTime(d1)
    val month = dt.getMonthOfYear // where January is 1 and December is 12
    val year = dt.getYear
    val day = dt.getDayOfMonth

    println(year + " " + month + " " + day)

    val midNight = new DateTime(year, month, day, 0, 0)
    println(midNight.toDate)
    println(midNight.getMillis)
    println("Delta: " + (d1.getTime - midNight.getMillis))

    println("d41d8cd98f00b204e9800998ecf8427e6f338ae8".length)
    //println(midNight.toDate.getTime)
  }
}
