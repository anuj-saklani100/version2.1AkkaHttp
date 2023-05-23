package mainpackage

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import mainpackage.UserCRUDHandler._
object UserApi extends App{
  implicit val system=ActorSystem("NewSystem")
  implicit val materializer=ActorMaterializer()
  import system.dispatcher
Http().bindAndHandle(reqHandler,"localhost",3003)
}
