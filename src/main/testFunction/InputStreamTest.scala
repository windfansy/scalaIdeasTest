import java.net.URI

import scala.io.Source

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
    val url = "http://wd3.gridsumdissector.com/#/reportCenter?sid=5015&pid=5069&paramsid=838c41811926b2b07548c14c89bffdfd&ud=1492511427534"
    val trimedUrl = url.trim
    val uri = new URI(trimedUrl).normalize()
    println(uri.getRawQuery)
    println(uri.getRawFragment)


  }

}
