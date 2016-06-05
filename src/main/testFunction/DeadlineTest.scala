package TestFunction

import java.io.File

import org.apache.commons.io.FileUtils
import org.apache.commons.io.LineIterator

/**
  * Created by T440P on 2016/4/6.
  */
object DeadlineTest {
  def main(args: Array[String]) {
   // val f = new File("DeadlineTest.log")
    //println(f.getAbsolutePath)
    val logFileNames = Array("DeadlineTest.log", "DeadlineTest2.log")
    val logExpectedLines = Array(130L, 129L)
    checkWeblogCompleted(logFileNames, logExpectedLines)
  }

  private def checkWeblogCompleted(logFileNames: Array[String], logExpectedLines: Array[Long]) = {
    import scala.concurrent.duration._
    val result = logFileNames zip logExpectedLines forall {
      case (logFileName, logExpectedLine) => {
        var isCheckPassed = false
        val deadline = 5.seconds.fromNow
        while (deadline.hasTimeLeft && !isCheckPassed){
          isCheckPassed = checkLogLineCompleted(logFileName, logExpectedLine)
          println(logFileName + ": " + System.currentTimeMillis() + ": " + isCheckPassed)
        }

        isCheckPassed
      }
    }
  }

  private def checkLogLineCompleted(logFileName: String, logExpectedLine: Long) = {
    val lineIterator = FileUtils.lineIterator(new File(logFileName))
    var logLines: Long = 0
    try {
      while (lineIterator.hasNext()) {
        logLines = logLines + 1
        lineIterator.nextLine()
      }
    } finally {
      LineIterator.closeQuietly(lineIterator)
    }
    if (logExpectedLine == logLines) true else false
  }
}
