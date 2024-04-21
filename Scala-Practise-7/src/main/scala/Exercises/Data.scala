package Exercises
import de._
import scala.collection.mutable.ArrayBuffer
case class Data (val t:() => Unit, val periodo: Int, var cuantoQueda: Int)
class Monitor {
  var task = new ArrayBuffer[Data]()
  def alta (t:() => Unit, periodo: Int): Unit = synchronized{
    task.append((Data(t,periodo,periodo)))
  }
  def actualizaTiempos (cantidad: Int): Unit = synchronized {
    for(data<- task) {
      if(data.cuantoQueda>0) {
        data.cuantoQueda -= cantidad
      }
      else {
        data.cuantoQueda=data.periodo
        data.t()
      }
    }
  }
}
object TaskSchedulerMonitor extends App {
  val miMonitor = new Monitor
  val reloj = new Thread {
    var tiempoTranscurrido: Int = 0
    override def run() = { while (tiempoTranscurrido < 10) { // 10 segundos
      Thread.sleep (1000)
      tiempoTranscurrido+= 1
      println (s"tiempo actual:$tiempoTranscurrido")
      miMonitor.actualizaTiempos(1)
    }}
  }
  def task1 = println("Soy task1 y ejecuto cada 2 s")
  miMonitor.alta (() => task1, 2)
  def task2 = println("Soy task2 y ejecuto cada 3 s")
  miMonitor.alta (() => task2, 3)
  reloj.start()
  reloj.join()
  Thread.sleep (12000)
  log ("Fin")
}