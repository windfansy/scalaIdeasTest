object session {
  1 + 2

  def abs(x: Double) = if (x < 0) -x else x

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  def isGoodEnough(guess: Double, x: Double) =
    abs(guess * guess - x) / x < 0.001

  def improve(guess: Double, x: Double) =
    (guess + x / guess) / 2

  def sqrt(x: Double) = sqrtIter(1.0, x)

  sqrt(2.0)
  sqrt(4)
  sqrt(1e-6)
  sqrt(1e60)

  val emptySeq = Seq()
  emptySeq.map(ele => (ele, ele.toString))

  val intList = List(4, 3, 2, 1)
  val int2List = List(1, 2, 3, 4)
  intList zip int2List
}
