import de._
object ThreadsUnprotectedUid extends App{
  var uidCount = 0L
  def getUniqueId() = { //Si igualamos un metodo a this.shyncronyzed, lo que hará es convertir la función en atómica. quitándonos los problemas de que se puedan repetir
    val freshUid = uidCount + 1
    uidCount = freshUid
    freshUid
  }
  def printUniqueIds(n: Int): Unit = {
    val uids = for( i<-0 until n) yield getUniqueId()
    log(s"Generated uids: $uids")
  }
  val t = thread {printUniqueIds(5)}
  printUniqueIds(5)
  t.join()

}
