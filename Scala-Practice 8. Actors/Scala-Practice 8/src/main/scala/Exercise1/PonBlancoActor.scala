package Exercise1
import akka.actor._
import scala.io._
import java.io._

class PonBlancoActor (fe: String, out: ActorRef) extends Actor {
  def receive = PartialFunction.empty // no recibe mensajes
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
  Thread.sleep (5000)
  println ("FIN")
  ourSystem.terminate
}
