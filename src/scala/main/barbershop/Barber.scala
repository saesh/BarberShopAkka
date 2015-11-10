package barbershop

import akka.actor.Actor
import akka.actor.ActorRef
import scala.util.Random

class Barber extends Actor {
  val random = new Random
  
  def receive = {
    case customer: ActorRef =>
      println("[b] Starting to cut hair on customer!")
      
      Thread.sleep(random.nextInt(300))
      customer ! Haircut
      
      sender.tell(CuttingDoneMessage(customer), self)
  }
}