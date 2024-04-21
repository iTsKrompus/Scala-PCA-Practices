object ThreadsMain extends App {

  //pg4 arriba
  val t:Thread = Thread.currentThread()
  val name = t.getName
  println(s"I am the thread $name") //La s sirve para indicar que es una cadena interpolada, en la cual va a haber tanto texto como variables

}
