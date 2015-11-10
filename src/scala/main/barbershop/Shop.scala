package barbershop

import akka.actor.ActorRef
import akka.actor.Actor
import scala.collection.mutable.ArrayBuffer

case class CuttingDoneMessage(customer: ActorRef)

class Shop(barber: ActorRef) extends Actor {
  var openSeats = 3
  val finishedCustomers = new ArrayBuffer[ActorRef]
  
  def handleCustomer(customer: ActorRef) = {
    if (openSeats > 0) {
      openSeats -= 1
      println("[s] Sending customer to barber, open seats: %s".format(openSeats))
      barber ! customer
    } else {
      println("[s] We are busy right now. Sorry.")
    }
  }
  
  def handleFinishedHaircut(customer: ActorRef) = {
    openSeats += 1
    finishedCustomers += customer
    println("[s] Thank you for your business")
  }
  
  def receive = {
    case customer: ActorRef => handleCustomer(customer)
    case CuttingDoneMessage(customer: ActorRef) => handleFinishedHaircut(customer)
    case "stats" => println("[s] >>> Total number of customers served: %s".format(finishedCustomers.size))
  }
}