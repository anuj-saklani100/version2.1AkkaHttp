package mainPackage

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import mainPackage.Routes._
object UserApi extends App{
  implicit val system=ActorSystem("NewSystem")
  implicit val materializer=ActorMaterializer()
  import system.dispatcher
Http().bindAndHandle(reqHandler,"localhost",3003)
}
