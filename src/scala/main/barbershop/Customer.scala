package barbershop

import akka.actor.Actor
import akka.actor.ActorLogging

case object Haircut  

class Customer(id: Int) extends Actor {
  def receive = {
    case Haircut => 
      println("[c] Thank you (customer %s)".format(id))
  }
}