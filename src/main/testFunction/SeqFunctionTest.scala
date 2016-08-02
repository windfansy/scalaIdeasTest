/**
  * Created by T440P on 2016/8/2.
  */
object SeqFunctionTest {
  def main(args: Array[String]) {
    val numList = Seq(1,2,3,4,5,6)
    val groupedMap = numList.groupBy(_ % 3)
    groupedMap.keys.foreach(key => println (key + " : " + groupedMap.get(key)))
  }

}
