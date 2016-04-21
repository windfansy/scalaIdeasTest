package FunctionalProgrammingPrinciplesInScala.week8

/**
  * Created by T440P on 2016/4/21.
  */
class WaterPouring(capacity: Vector[Int]) {
  // States
  type State = Vector[Int]
  val initialState = capacity map (_ => 0)

  // Moves
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    def change(state: State) = state updated(glass, 0)
  }

  case class Fill(glass: Int) extends Move {
    def change(state: State) = state updated(glass, capacity(glass))
  }

  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State) = {
      val amount = state(from) min (capacity(to) - state(to))
      state updated(to, state(to) + amount) updated(from, state(from) - amount)
    }
  }

  val glasses = 0 until capacity.length
  /*val moves = (glasses map (glass => Empty(glass))) ++ (glasses map (glass => Fill(glass))) ++
    (glasses flatMap { glass1 => glasses map {
      glass2 => if (glass1 != glass2) Pour(glass1, glass2)
    }
    })*/
  val moves = (for (glass <- glasses) yield Empty(glass)) ++ (for (glass <- glasses) yield Fill(glass)) ++
    (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))

  class Path(history: List[Move], val endState: State) {
    //def endState = (history foldRight initialState) (_ change _)

    def extend(move: Move) = new Path(move :: history, move.change(endState))

    override def toString = history.reverse.mkString(" ") + " --> " + endState
  }

  val initialPath = new Path(Nil, initialState)

  //key function
  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] = {
    if (paths.isEmpty)
      Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves map path.extend
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, (more map (_.endState)) ++ explored)
    }
    /* equal to for iteration
     */
  }

  val pathSets = from(Set(initialPath), Set(initialState))

  //key function
  def solutions(target: Int): Stream[Path] = {
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path
  }


}

object WaterPouring {
  def main(args: Array[String]) {
    val pourSolution = new WaterPouring(Vector(4, 7, 9))
    println(pourSolution.moves)
    //println(pourSolution.pathSets.take(3).toList)
    println(pourSolution.solutions(3))
  }
}
