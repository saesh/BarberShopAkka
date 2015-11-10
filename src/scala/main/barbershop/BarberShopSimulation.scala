package barbershop

import akka.actor.{ActorSystem, Props}
import scala.util.Random

/**
 * Handles creation of actors and sends customers to the shop
 */
object BarberShopSimulation extends App {
  // barber shop akka system
  val system = ActorSystem("BarberShop")
  
  // random generator to use for simulating work
  var random = new Random
 
  // actors
  val barber = system.actorOf(Props[Barber], name = "barber") 
  val shop = system.actorOf(Props(new Shop(barber)), name = "shop")
 
  // create 100 customers and send them in a random interval
  // to the shop
  for (id <- 1 to 100) {
    val customer = system.actorOf(Props(new Customer(id)))
    Thread.sleep(random.nextInt(50))
    shop ! customer
  }
  
  // print out stats of the shop
  shop ! "stats"
  
  System.exit(0)
}