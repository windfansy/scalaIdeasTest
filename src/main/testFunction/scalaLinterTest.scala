/**
  * Created by T440P on 2016/6/6.
  */
object scalaLinterTest {
  def main(args: Array[String]) {
    val a = 5
    val b = 4
    println(if (b > 4) (2, a) else (2, a))

    println(Integer.parseInt("86400000"))
    println(java.lang.Long.parseLong("-140061522534"))
    println("HasAccelerometer".toUpperCase)
    println("HasCompass".toUpperCase)
    println("HasGps".toUpperCase)
    println("CameraType".toUpperCase)
  }
}
