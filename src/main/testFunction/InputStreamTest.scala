import scala.io.Source

/**
  * Created by T440P on 2016/6/27.
  */
object InputStreamTest {
  def main(args: Array[String]) {
    val inputStream = this.getClass.getClassLoader.getResourceAsStream("dimIntervals.json")
    if (inputStream != null) {
      val source = Source.fromInputStream(inputStream)("UTF-8")
      val jsonString = source.getLines() mkString
    }


  }

}
