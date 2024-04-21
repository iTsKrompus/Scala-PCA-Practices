import de._
object SynchronizedGuardedBlocks extends App {
  val lock = new AnyRef
  var message: Option[String] = None
  val greeter = thread {
    lock.synchronized {
      while (message == None) lock.wait()
      log(message.get)
    }
  }
  lock.synchronized {
    message = Some("Hello!")
    lock.notify()
  }
  greeter.join()
  log ("hola")
}

//Pg7. Solo se puede llamar a wait y notify si el thread esta en el objeto con el monitor. Cuando se haga un wait, se libera el monitor y se bloquea hasta que se haga un notify sobre el mismo objeto
//En Scala, no se crean varios procesos, sino que se suele trabajar en el mismo objeto y usar una variable para que los metodos se ejecuten en orden.

//WAITING(w) = E(esperando la entrada) < S(SIGNAL)
