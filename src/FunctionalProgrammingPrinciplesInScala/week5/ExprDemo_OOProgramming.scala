package FunctionalProgrammingPrinciplesInScala.week5

/**
  * Created by T440P on 2016/4/1.
  */
object ExprDemo_OOProgramming {
  def main(args: Array[String]) {
    val sumOfNumber = Sum_OO(Number_OO(4), Number_OO(33))
    println(sumOfNumber + " = " + sumOfNumber.eval)
    val productOfNumber = Prod_OO(Number_OO(4), Number_OO(33))
    println(productOfNumber + " = " + productOfNumber.eval)
    val compositeOpOfNumber = Prod_OO(Sum_OO(Number_OO(4), Number_OO(33)), Prod_OO(Number_OO(4), Number_OO(33)))
    println(compositeOpOfNumber + " = " + compositeOpOfNumber.eval)
    val compositeOpOfNumberAndVar = Prod_OO(Sum_OO(Number_OO(4), Var_OO("x")), Prod_OO(Var_OO("y"), Number_OO(33)))
    println(compositeOpOfNumberAndVar)

  }
}

trait Expr_OO {
  def eval: Int
}

case class Number_OO(n: Int) extends Expr_OO {

  override def toString = n.toString

  def eval = n
}

case class Sum_OO(expr1: Expr_OO, expr2: Expr_OO) extends Expr_OO {

  override def toString = "(" + expr1.toString + " + " + expr2.toString + ")"

  def eval = expr1.eval + expr2.eval
}

case class Prod_OO(expr1: Expr_OO, expr2: Expr_OO) extends Expr_OO {

  override def toString = "(" + expr1.toString + " * " + expr2.toString + ")"

  def eval = expr1.eval * expr2.eval
}

case class Var_OO(name: String) extends Expr_OO {
  def eval: Int = throw new Error("Var Not Support Calculation")
  override def toString = name
}

/** implicitly defined by case class

  * object Number {
  * def apply(n: Int) = new Number(n)
  * }

  * object Sum {
  * def apply(expr1: Expr, expr2: Expr) = new Sum(expr1, expr2)
  * }
  * */