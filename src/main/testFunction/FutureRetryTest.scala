
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import akka.pattern.after
import akka.actor.Scheduler

object FutureRetryTest {
  def main(args: Array[String]) {


    /**
      * Given an operation that produces a T, returns a Future containing the result of T, unless an exception is thrown,
      * in which case the operation will be retried after _delay_ time, if there are more possible retries, which is configured through
      * the _retries_ parameter. If the operation does not succeed and there is no retries left, the resulting Future will contain the last failure.
      **/
    def retry[T](op: => T, delay: FiniteDuration, retries: Int)(implicit ec: ExecutionContext, s: Scheduler): Future[T] =
      Future(op) recoverWith { case _ if retries > 0 => after(delay, s)(retry(op, delay, retries - 1)) }
  }
}
