object pairs {
  (1 until 7) map { i => (1 until i) map { j => (j, i) } }
  (1 until 7) map {
    _ + 1
  }

  val testMixVector = Vector(Vector(), 1, 2, Vector((1, 2)), Vector((1, 3), (2, 3)), Vector((1, 4), (2, 4), (3, 4)), Vector((1, 5), (2, 5), (3, 5), (4, 5)), Vector((1, 6), (2, 6), (3, 6), (4, 6), (5, 6)))
  val testOnlyVector = Vector(Vector(), Vector((1, 2)), Vector((1, 3), (2, 3)), Vector((1, 4), (2, 4), (3, 4)), Vector((1, 5), (2, 5), (3, 5), (4, 5)), Vector((1, 6), (2, 6), (3, 6), (4, 6), (5, 6)))

  //testMixVector.flatten
  testOnlyVector.flatten

  // 下面才是正片开始
  def isPrime(x: Int) = (2 until x) forall (i => x % i != 0)

  val n = 7
  (1 until n) flatMap (i =>
    (1 until i) map (j => (j, i))) filter (pair => isPrime(pair._1 + pair._2))

  for{
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (j, i)


  isPrime(2)
  isPrime(6)

}