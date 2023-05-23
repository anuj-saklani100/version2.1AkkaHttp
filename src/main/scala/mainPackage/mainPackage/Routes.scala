package mainPackage
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import mainPackage.routesPack.DeleteRoute.Deleter
//import mainPackage.RoutesPack.deleteRoute.Deleter
import mainPackage.routesPack.PatchRoute.Patcher
import mainPackage.routesPack.PostRoute.Poster
import spray.json.DefaultJsonProtocol

case class UserBase(id:Int,name:String,startTime:String,create_at:String,password:String)
trait CoMagic extends DefaultJsonProtocol{
  implicit val done=jsonFormat5(UserBase)
}
import mainPackage.routesPack.GetRoute._
object Routes extends SprayJsonSupport with CoMagic {
import mainPackage.Credentials._



  val reqHandler =
    pathPrefix("api" / "user") {
    Poster()~
        Patcher()~
          Deleter()~
                 Getter()
    }
}
