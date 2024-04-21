package Exercises

import de._
import scala.collection.mutable.ArrayBuffer
import java.util.{Timer, TimerTask}

//EJERCICIO 3
case class DataTimer (val t:() => Unit, val periodo: Int, var cuantoQueda: Int)
class MyTimerTask extends TimerTask {
  def run(): Unit = {

  }
}
class Monitor2 {
  var task = new ArrayBuffer[DataTimer]()
  def alta (t:() => Unit, periodo: Int): Unit = synchronized{
    task.append((DataTimer(t,periodo,periodo)))
  }

  def actualizaTiempos(cantidad: Int): Unit = synchronized {
    for (data <- task) {
      data.cuantoQueda -= cantidad
      if (data.cuantoQueda <= 0) {
        data.cuantoQueda = data.periodo
        data.t()
      }

    }
  }
}
object TaskSchedulerMonitor2 extends App {
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