package com.ibm

/**
 * Hello world!
 *
 */
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class HelloActor(name: String) extends Actor {
  def receive = {
    case "hello" => println(name + " :: hello back at you")
    case _       => println(name + " :: huh?")
  }
}

class RemoteActor extends Actor {
  def receive = {
    case msg: String =>
      println(s"RemoteActor received message '$msg'")
      sender ! "Hello from the RemoteActor"
  }
}

object App {
  def main(args : Array[String]) {
    val system = ActorSystem("HelloSystem")
    // default Actor constructor

    val helloActor = system.actorOf(Props(new HelloActor("joe")), name = "helloactor")

    val remoteActor = system.actorOf(Props[RemoteActor], name="remoteHelloActor")

    helloActor ! "hello"
    helloActor ! "buenos dias"

    //remoteActor ! "The RemoteActor is alive"

    //system.shutdown()
  }
}

