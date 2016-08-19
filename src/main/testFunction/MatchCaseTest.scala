/**
  * Created by T440P on 2016/8/19.
  */
object MatchCaseTest {
  def main(args: Array[String]) {
    val e = new Employee
    e match {
      case obj: Person => println("Match Person")
      case obj: Employee => println("Match Employee")
    }
  }

}

class Person {
  def printClassName = "Person"
}

class Employee extends Person {
  override def printClassName = "Employee"
}
