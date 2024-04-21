import de._
object ThreadsNondeterminism extends App{
  val t= thread {log("New thread running")}
  log("...")
  log("...")
  t.join()
  log("New thread joined.")

  //Este no es determinista porque el SO llama a ejecucción a t pero
  //Lo ejecuta cuando el ve conveniente, por eso hace en cada ejecuccion
  //UNa cosa distinta
}
