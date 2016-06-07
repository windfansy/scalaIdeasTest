package FunctionalProgrammingPrinciplesInScala.week1.assignment

/**
  * Created by T440P on 2016/5/17.
  */
object main {
  def main(args: Array[String]) {
   /* testPascal()
    testBalance()
    testCountChange()*/
    val longSeq = Seq[Long](4, 5, 6)
    val ele = longSeq.head

  }

  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r)
      1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  def testPascal() = {
    assert(pascal(0, 2) == 1)
    assert(pascal(1, 2) == 2)
    assert(pascal(1, 3) == 3)
    assert(pascal(2, 5) == 10)
    assert(pascal(0, 0) == 1)
  }

  def balance(chars: List[Char]): Boolean = {
    def innerBalance(chars: List[Char], preParenthese: Int): Boolean = {
      if (chars.isEmpty)
        if (preParenthese == 0) true else false
      else if (chars.head == '(') innerBalance(chars.tail, preParenthese + 1)
      else if (chars.head == ')')
        if (preParenthese - 1 < 0) false
        else innerBalance(chars.tail, preParenthese - 1)
      else innerBalance(chars.tail, preParenthese)
    }
    innerBalance(chars, 0)
  }

  def testBalance() = {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
    assert(
        balance(
            "I told him (that it’s not (yet) done). (But he wasn’t listening)".toList))
    assert(!balance(":-)".toList))
    assert(!balance("())(".toList))
    assert(!balance(")()()(".toList))
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    def innerCountChange(
        money: Int, coins: List[Int], usedCoins: List[Int]): Int = {
      if (money == 0) {
        disPlayCountChange(usedCoins)
        1
      } else if (money < 0 || coins.isEmpty) 0
      else if (money - coins.head >= 0)
        innerCountChange(money - coins.head, coins, coins.head :: usedCoins) +
        innerCountChange(money, coins.tail, usedCoins)
      else innerCountChange(money, coins.tail, usedCoins)
    }
    def disPlayCountChange(usedCoins: List[Int]) =
      println(usedCoins.mkString(","))
    if (money <= 0 || coins.isEmpty) 0
    else innerCountChange(money, coins, List[Int]())
  }

  def testCountChange() = {
    assert(countChange(4, List(1, 2)) == 3)
    assert(countChange(7, List(1, 3, 4)) == 5)
    assert(countChange(0, List(1, 3, 4)) == 0)
    assert(countChange(1, List(2, 3, 4)) == 0)
  }
}
