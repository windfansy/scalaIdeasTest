package FunctionalProgrammingPrinciplesInScala.week5

/**
  * Created by T440P on 2016/4/1.
  */
object ExprDemo {
  def main(args: Array[String]) {
    val sumOfNumber = Sum(Number(4), Number(33))
    println(sumOfNumber + " = " + sumOfNumber.eval)
    val productOfNumber = Prod(Number(4), Number(33))
    println(productOfNumber + " = " + productOfNumber.eval)
    val compositeOpOfNumber = Prod(
        Sum(Number(4), Number(33)), Prod(Number(4), Number(33)))
    println(compositeOpOfNumber + " = " + compositeOpOfNumber.eval)
    val compositeOpOfNumberAndVar = Prod(
        Sum(Number(4), Var("x")), Prod(Var("y"), Number(33)))
    println(compositeOpOfNumberAndVar)
  }
}

trait Expr {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(expr1, expr2) => expr1.eval + expr2.eval
    case Prod(expr1, expr2) => expr1.eval * expr2.eval
    case _ => throw new Error("Not Supported Expr Eval Type")
  }

  override def toString = this match {
    case Number(n) => n.toString
    case Sum(expr1, expr2) =>
      "(" + expr1.toString + " + " + expr2.toString + ")"
    case Prod(expr1, expr2) =>
      "(" + expr1.toString + " * " + expr2.toString + ")"
    case Var(name) => name

    case _ => throw new Error("Not Supported Expr Type")
  }
}

case class Number(n: Int) extends Expr

case class Sum(expr1: Expr, expr2: Expr) extends Expr

case class Prod(expr1: Expr, expr2: Expr) extends Expr

case class Var(name: String) extends Expr

/** implicitly defined by case class

  * object Number {
  * def apply(n: Int) = new Number(n)
  * }

  * object Sum {
  * def apply(expr1: Expr, expr2: Expr) = new Sum(expr1, expr2)
  * }
  * */
