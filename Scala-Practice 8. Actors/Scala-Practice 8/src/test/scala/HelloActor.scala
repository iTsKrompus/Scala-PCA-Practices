import akka.actor._
import akka.event.Logging

class HelloActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case "hi" =>
      log.info("hi received")
    case msg =>
      log.info(s"Unexpected message '$msg'")
      context.stop(self)
  }
}

object ActorsCreate extends App {
  val ourSystem = ActorSystem("OurExampleSystem")
  val hiActor: ActorRef = ourSystem.actorOf(Props[HelloActor], name = "greeter")
  hiActor ! "hi"
  Thread.sleep(1000)
  hiActor ! "hola"
  Thread.sleep(1000)
  ourSystem.stop(hiActor)
}
