package FunctionalProgrammingPrinciplesInScala.week2.assignment

object Main extends App {

  import FunSets._

  //test singletonSet
  println(contains(singletonSet(1), 1))
  println(contains(singletonSet(1), 2))

  //test union
  println(contains(union((x: Int) => x > 0, (x: Int) => x < 0), 4))
  println(contains(union((x: Int) => x > 0, (x: Int) => x < 0), 0))

  //test intersect
  println(contains(intersect((x: Int) => x >= 0, (x: Int) => x <= 0), 4))
  println(contains(intersect((x: Int) => x >= 0, (x: Int) => x <= 0), 0))

  //test diff
  println(contains(diff((x: Int) => x >= 0, (x: Int) => x <= 0), 4))
  println(contains(diff((x: Int) => x >= 0, (x: Int) => x <= 0), 0))
}
