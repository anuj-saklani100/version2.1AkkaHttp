package mainPackage

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import mainPackage.routes._
object userApi extends App{
  implicit val system=ActorSystem("NewSystem")
  implicit val materializer=ActorMaterializer()
  import system.dispatcher
Http().bindAndHandle(reqHandler,"localhost",3003)
}
