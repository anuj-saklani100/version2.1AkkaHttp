package mainpackage
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import mainpackage.UserCRUDHandlers.{addUser, deleteById, existingUserFetch, getUserById, updateUser}
//import mainPackage.RoutesPack.deleteRoute.Deleter
import spray.json.DefaultJsonProtocol

case class UserBase(id:Int,name:String,startTime:String,create_at:String,password:String)
trait JSONSerialization extends DefaultJsonProtocol{
  implicit val userBaseJsonFormat=jsonFormat5(UserBase)
}

object UserCRUDHandler extends SprayJsonSupport with JSONSerialization {


  val reqHandler =
    pathPrefix("api" / "user") {
      post {
        entity(as[UserBase]) {
          (a) => {
            val res1 = addUser(a)
            if (res1 == None) complete(StatusCodes.NotImplemented)
            else complete(res1)
          }
        }
      }~
        patch {
          entity(as[UserBase]) {
            (a) => {
              parameter('id.as[Int]) {
                (b) => {
                  val res1 = updateUser(b, a)
                  if (res1) {
                    complete(StatusCodes.OK)
                  } else {
                    complete(StatusCodes.NotImplemented)
                  }
                }
              }
            }
          }
        }~
        delete {
          parameter('id.as[Int]) {
            (a) => {

              val res1 = deleteById(a)
              if (res1) {
                println("User deleted successfully")
                complete(StatusCodes.OK)
              } else {
                complete(StatusCodes.BadRequest)
              }
            }
          }
        }~
        get {
          parameter('id.as[Int]) {
            (a) => {
              val res1 = getUserById(a)
              complete(res1)
            }
          } ~
            parameter("username") {
              (a) =>
                get {
                  val res1 = existingUserFetch(a)
                  if (res1.isEmpty) {
                    complete(StatusCodes.NotAcceptable)
                  } else complete(res1)
                }
            }

        }
    }
}
