import de._
import scala.collection._
object SynchronizedBadPool extends App { //ThreadPool son threads reutilizables para distintas tareas
  private val tasks = mutable.Queue[() => Unit]()
  val worker = new Thread {
    def poll(): Option[() => Unit] = tasks.synchronized { //Es como un hasmap, o devuelve un objeto unit o si no lo encuentra devuelve null. Unit es como void en java
      if (tasks.nonEmpty) Some(tasks.dequeue()) else None
    }
    override def run() = while (true) poll() match {
      case Some(task) => task()
      case None =>
    }
  }
  worker.setName("Worker")
  worker.setDaemon(true)  //Esto hace que el worker hilo sea un hilo demonio que, a la hora de acabar la ejecuccion de todo el programa, si solo queda este hilo pendiente el progama pueda acabar.
  worker.start()
  def asynchronous(body: =>Unit) = tasks.synchronized {
    tasks.enqueue(() => body)
  }
  asynchronous { log("Hello") }
  asynchronous { log(" world!")}
  Thread.sleep(5000)
}
