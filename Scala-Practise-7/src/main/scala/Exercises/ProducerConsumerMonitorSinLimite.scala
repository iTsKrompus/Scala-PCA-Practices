package Exercises

import de._
import scala.collection._
class ProducerConsumerMonitorSinLimite [T] {
  private val buffer = mutable.Queue[T]()
  def append (v: T): Unit = {
    buffer.synchronized {
      buffer.enqueue(v)
      buffer.notify()
    }
  }
  def take (): T = {
    buffer.synchronized{
      while (buffer.isEmpty) buffer.wait()
        buffer.dequeue()
    }

  }
}
object Ejemplo1 extends App {
  var b = new ProducerConsumerMonitorSinLimite [Int]
  val productor1 = thread {for (i<-1 to 15) b.append (i)}
  val productor2 = thread {for (i<-16 to 30) b.append (i)}
  val consumidor1 = thread {for (i<-1 to 15) println (b.take())}
  val consumidor2 = thread {for (i<-16 to 30) println (b.take())}
  productor1.join(); productor2.join(); consumidor1.join(); consumidor2.join();
  log ("Fin")
}

//1. Programa las funciones append y take
//Listo
//2. Prueba a poner dos consumidores cada uno de los cuales consume 15 items
//Listo
//3. ¿Qué ocurriría si uno de los consumidores intenta consumir 16 items, en lugar de 15?
//Pues que se quedara en waiting para siempre el hilo que le falte un dato por tomar
//4. ¿Tiene sentido en este ejemplo (dos productores y un consumidor) utilizar la sentencia  notifyAll en lugar de notify?
//No, porque solo se va a poder quedar un proceso waitng, por lo tanto el notify y notify all harian exactamente lo mismo, despertar un unico proceso
