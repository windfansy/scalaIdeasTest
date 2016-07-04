import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

/**
  * Created by T440P on 2016/7/4.
  */
object FutureAndPromiseTest {
  def main(args: Array[String]) {
    val taxCutF = Government.redeemCampaignPledge
    println("Now that they're elected, let's see if they remember their promises...")
    taxCutF.onComplete {
      case Success(TaxCut(reduction)) =>
        println(s"A miracle! They really cut our taxes by $reduction percentage points!")
      case Failure(ex) =>
        println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
    }
    Thread.sleep(3000)

  }
}

case class TaxCut(reduction: Int)

object Government {
  def redeemCampaignPledge: Future[TaxCut] = {
    val p = Promise[TaxCut]()
    Future {
      println("Starting the new legislative period.")
      Thread.sleep(2000)
      p.success(TaxCut(20))
      println("We reduced the taxes! You must reelect us!!!!1111")
    }
    p.future
  }
}




