package mainPackage
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import mainPackage.RoutesPack.deleteRoute.Deleter
//import mainPackage.RoutesPack.deleteRoute.Deleter
import mainPackage.RoutesPack.patchRoute.Patcher
import mainPackage.RoutesPack.postRoute.Poster
import spray.json.DefaultJsonProtocol

case class UserBase(id:Int,name:String,startTime:String,create_at:String,password:String)
trait coMagic extends DefaultJsonProtocol{
  implicit val done=jsonFormat5(UserBase)
}
import mainPackage.RoutesPack.getRoute._
object routes extends SprayJsonSupport with coMagic {
import mainPackage.Credentials._



  val reqHandler =
    pathPrefix("api" / "user") {
    Poster()~
        Patcher()~
          Deleter()~
                 Getter()
    }
}
