package FunctionalProgrammingPrinciplesInScala.week8

import scala.collection.immutable

/**
  * Created by T440P on 2016/4/20.
  */
object streamDemo {
  def main(args: Array[String]) {
    //natural number
    println("natural number")
    def from(n: Int): Stream[Int] = n #:: from(n + 1)
    val nats = from(0)
    println(nats.take(10).toList)
    println(nats.take(10))

    //The Sieve of Eratosthenes in Code
    println("The Sieve of Eratosthenes in Code")
    def sieve(s: Stream[Int]): Stream[Int] =
      s.head #:: sieve(s.tail filter (_ % s.head != 0))
    val primes = sieve(from(2))
    println(primes.take(20).toList)

    //squareRoot by Stream
    println("squareRoot by Stream")
    def sqrtStream(x: Double): Stream[Double] = {
      def improve(guess: Double) = (guess + x / guess) / 2
      lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
      guesses
    }
    def isGoodEnough(x: Double, guess: Double): Boolean = if (math.abs(guess * guess - x) < 0.01 * x) true else false

    println(sqrtStream(4.0).filter(isGoodEnough(4, _)).take(10).toList)

    println("streamRange")
    def streamRange(lo: Int, hi: Int): Stream[Int] = {
      if (lo > hi) Stream.empty
      else lo #:: streamRange(lo + 1, hi)
    }
    println(streamRange(1000, 10000).take(20).toList)


  }
}
