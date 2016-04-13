import collection.immutable.List
object listFun {
  val nums = List(4, 2, -9, 3, -10, 9, 0)
  nums.filter(x => x > 0)
  nums.filterNot(x => x > 0)
  nums.partition(x => x > 0)
  nums.takeWhile(x => x > 0)
  nums.dropWhile(x => x > 0)
  nums.span(x => x > 0)

  val data = List("a", "a", "a", "b", "c", "c", "a")

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case List() => List()
    case x :: xs1 =>
      val (first, rest) = xs.span(y => y == x)
      first :: pack(rest)
  }

  pack(data)

  def encode[T](xs: List[T]): List[(T, Int)] = {
    pack(xs).map(ys => (ys.head, ys.length))
  }
  encode(data)
  encode(pack(data))

  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]()) (f(_) :: _)

  mapFun[Int, String](nums, x => (x + 1).toString)

  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0) ((x, r) => 1 + r)
  lengthFun(nums)
  lengthFun(data)

  def reverse[T](xs: List[T]): List[T] =
    (xs foldLeft List[T]()) ((xs, x) => x :: xs)
  reverse(nums)
  reverse(data)
}