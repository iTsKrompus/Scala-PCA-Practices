object ThreadsCreation extends App{

  //pg4 abajo
  class MyThread extends Thread {
    override def run(): Unit = { //Este override lo unico que hace es modificar Thread para que imprima eso cada vez que se crea un nuevo hilo
      println("New thread running.")
    }
  }

  val t2 = new MyThread
  t2.start() //Ejecuta en el thread 2 lo que está dentro del run
  t2.join() //Deja en espera el resto de hilos, en este caso el main, hasta que termine la ejecución del run de t2
  println("New thread joined.")

}
