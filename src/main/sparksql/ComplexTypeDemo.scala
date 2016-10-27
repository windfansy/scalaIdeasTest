/**
  * Created by T440P on 2016/10/25.
  */

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext,Column,DataFrame}
import org.apache.spark.sql.functions.{udf, lit}
import scala.util.Try


object ComplexTypeDemo {
  case class SubRecord(x: Int)
  case class ArrayElement(foo: String, bar: Int, vals: Array[Double])
  case class Record(an_array: Array[Int], a_map: Map[String, String],
                     a_struct: SubRecord, an_array_of_structs: Array[ArrayElement])
  case class MapTest(id: Int, map: Map[Int,Int])

  def main(args: Array[String]) {
    val sf = new SparkConf()
    val sc = new SparkContext(sf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._
    val df = sc.parallelize(Seq(
      Record(Array(1, 2, 3), Map("foo" -> "bar"), SubRecord(1),
        Array(
          ArrayElement("foo", 1, Array(1.0, 2.0)),
          ArrayElement("bar", 2, Array(3.0, 4.0)))),
      Record(Array(4, 5, 6), Map("foz" -> "baz"), SubRecord(2),
        Array(ArrayElement("foz", 3, Array(5.0, 6.0)),
          ArrayElement("baz", 4, Array(7.0, 8.0))))
    )).toDF
    df.registerTempTable("df")
    df.printSchema
    df.select($"a_map".getField("foo")).show
  }




}


