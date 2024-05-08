package Exercise1
import akka.actor._
import scala.io._
import java.io._

class PonBlancoActor (fe: String, out: ActorRef) extends Actor {
  def receive = PartialFunction.empty // no recibe mensajes
  val contenido = Source.fromFile(fe) // siendo fe el nombre de fichero
      // Cambiar un carácter de cada 11 por un espacio en blanco
  val mensajeTransformado = contenido.sliding(11, 11).map(_.mkString + " ").mkString

  // para cerrar el fichero
 contenido.close()
  // Enviar el resultado al próximo actor
      out ! mensajeTransformado

  }

object PonBlancoActor {
  def props(fe: String, out: ActorRef) = Props(new PonBlancoActor(fe, out))
}
class PonFlechaActor (out: ActorRef) extends Actor{
  def receive = {
    case mensaje: String =>
      // Cambiar dos asteriscos seguidos por un ^
      val mensajeTransformado = mensaje.replaceAll("\\*\\*", "^")
      // Enviar el resultado al próximo actor
      out ! mensajeTransformado
  }
}
object PonFlechaActor {
  def props(out: ActorRef) = Props(new PonFlechaActor(out))
}
class PonCambioDeLineaActor (fs: String) extends Actor{
  def receive = {
    case mensaje: String =>
      // Escribir en un fichero de salida los caracteres resultantes y un cambio de línea cada 13 caracteres
      val writer = new PrintWriter(new File(fs))
      mensaje.grouped(13).foreach(line => writer.println(line))
      writer.close()
  }
}
object PonCambioDeLineaActor {
  def props(fs: String) = Props(new PonCambioDeLineaActor(fs))
}
object ExeActors801 extends App {
  val fe = "in.txt"
  val fs = "out"

  val ourSystem = ActorSystem("TransformaCaracteres")

  //Se crean los actores y referencias
  val pCL: ActorRef = ourSystem.actorOf(PonCambioDeLineaActor.props(fs))
  val pFl: ActorRef = ourSystem.actorOf (PonFlechaActor.props (pCL))
  val pBl: ActorRef = ourSystem.actorOf (PonBlancoActor.props (fe, pFl))




  Thread.sleep (1000)

  Thread.sleep (5000)
  println ("FIN")
  ourSystem.terminate
}