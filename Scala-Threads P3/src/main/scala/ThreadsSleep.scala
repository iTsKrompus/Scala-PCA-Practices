import de._
object ThreadsSleep extends App{
  val t = thread {
    Thread.sleep(1000)
    log("New thread running.")
    Thread.sleep(1000)
    log("Still running")
    Thread.sleep(1000)
    log("Completed.")
  }
  t.join() //Dejamos el main a la espera para que no se ejecute el main a la vezz que el hilo en el que hemos introducido el cuerpo
  log("New thread joined.")
  //Es determinista, dado que, dada una entrada cualquiera, siempre produce la misma salida independientemente de lo que quiera el SO
}
