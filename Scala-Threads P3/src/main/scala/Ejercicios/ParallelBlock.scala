package Ejercicios

import de._
object ParallelBlock extends App {
  def parallel[A, B](a: => A, b: => B): (A, B) = {
    var resultA: A = 1.asInstanceOf[A]
    var resultB: B = 1.asInstanceOf[B]
    val threadA = thread {
      resultA = a
    }
    val threadB = thread {
      resultB = b
    }
    threadA.join()
    threadB.join()
    (resultA, resultB)
  }
  val task1 = {3+4}
  val task2 = {3*1}
  println (parallel (task1, task2))
  log ("FIN")
}
