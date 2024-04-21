package Ejercicios

object Periodically extends App {
  def periodically(duration: Long, f: => Unit): Unit = {
    val worker = new Thread {
      override def run(): Unit = {
        while(true) {
          f
          Thread.sleep(duration)
        }
      }
    }
      worker.setName("Worker")
      worker.start()
    }
    periodically(1000, {println("ha pasado 1 segundo")})
  }
