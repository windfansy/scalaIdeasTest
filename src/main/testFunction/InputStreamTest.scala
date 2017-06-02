import java.net.URI
import java.util.{Date, Calendar}



/**
  * Created by T440P on 2016/6/27.
  */
object InputStreamTest {
  def main(args: Array[String]) {
    //    val inputStream = this.getClass.getClassLoader.getResourceAsStream("dimIntervals.json")
    //    if (inputStream != null) {
    //      val source = Source.fromInputStream(inputStream)("UTF-8")
    //      val jsonString = source.getLines() mkString
    //    }
//    val url = "http://wd3.gridsumdissector.com/#/reportCenter?sid=5015&pid=5069&paramsid=838c41811926b2b07548c14c89bffdfd&ud=1492511427534"
//    val trimedUrl = url.trim
//    val uri = new URI(trimedUrl).normalize()
//    println(uri.getRawQuery)
//    println(uri.getRawFragment)
//    val CALENDAR = Calendar.getInstance()
//    CALENDAR.setTime(new Date(116, 0, 2))
//    CALENDAR.setMinimalDaysInFirstWeek(7)
//    println(CALENDAR.get(Calendar.WEEK_OF_YEAR))
    val e = new Exception("123")
    if (e.isInstanceOf[RuntimeException]) {
      System.out.println("Exception goes beyond the capability of log parser. Will rethrow it.", e)
      throw e
    } else
      System.out.println("not")


  }

  class AppNameResolveException(appKey:String) extends NullPointerException{
    override def getMessage:String ={
      s"AppName Can't be resolved for app key: $appKey"
    }
  }



}
