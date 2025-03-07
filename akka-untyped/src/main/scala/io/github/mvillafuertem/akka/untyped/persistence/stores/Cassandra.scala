package io.github.mvillafuertem.akka.untyped.persistence.stores

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object Cassandra extends App {

  implicit val actorSystem: ActorSystem = ActorSystem("Cassandra", ConfigFactory.load().getConfig("cassandra"))
  val simplePersistentActor = actorSystem.actorOf(Props[SimplePersistentActor], "simplePersistentActor")

  for (i <- 1 to 10) {
    simplePersistentActor ! s"I love Akka [$i]"
  }

  simplePersistentActor ! "print"
  simplePersistentActor ! "snap"

  for (i <- 11 to 20) {
    simplePersistentActor ! s"I love Akka [$i]"
  }

}
