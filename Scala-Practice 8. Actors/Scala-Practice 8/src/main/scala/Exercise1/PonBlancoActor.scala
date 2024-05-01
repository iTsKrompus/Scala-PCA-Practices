package Exercise1
import akka.actor._
import scala.io._
import java.io._

class PonBlancoActor (fe: String, out: ActorRef) extends Actor {
  var conta: Integer =
  def receive = {
    case _ ⇒
      var conta
  }
  …
}
object PonBlancoActor {
  def props(fe: String, out: ActorRef) = Props(new PonBlancoActor(fe, out))
}
class PonFlechaActor (out: ActorRef) extends Actor{
  …
}
object PonFlechaActor {
  def props(out: ActorRef) = Props(new PonFlechaActor(out))
}
class PonCambioDeLineaActor (fs: String) extends Actor{
  …
}
object PonCambioDeLineaActor {
  def props(fs: String) = Props(new PonCambioDeLineaActor(fs))
}
object ExeActors801 extends App {
  val fe = "in.txt"
  val fs = "out"
  val ourSystem = ActorSystem("TransformaCaracteres")
  val pCL: ActorRef = ourSystem.actorOf(PonCambioDeLineaActor.props(fs))
  val pFl: ActorRef = ourSystem.actorOf (PonFlechaActor.props (pCL))
  val pBl: ActorRef = ourSystem.actorOf (PonBlancoActor.props (fe, pFl))
  val contenido = Source.fromFile(fe) // siendo fe el nombre de fichero
  var cont: Integer = 1
  var last: Char = 'p'
  for (c <- contenido) {
    //iteración
    pBl ! c

    }

  // para cerrar el fichero
  contenido.close
  // abrir un fichero para escritura y escribir un carácter
  val fo = new PrintWriter(new File(fs)) // fs es el nombre del fichero
  fo.print(c) // o para escribir cambio de línea fo.println
  // para cerrar el fichero
  fo.close
  Thread.sleep (5000)
  println ("FIN")
  ourSystem.terminate
}