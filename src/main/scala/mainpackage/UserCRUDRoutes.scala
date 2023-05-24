package mainpackage
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import mainpackage.UserCRUDHandlers._
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
            if (res1 == None) complete(StatusCodes.custom(404,"Invalid Password "," 1. Make Sure the Length of Your password is at least 8 \n      2. Should contain a Digit\n      3. Should contain a Character \n      4. Should contain a Special character")
            )
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
                    complete(StatusCodes.custom(200,"OK","User Updated Successfully!"))
                  } else {
                    complete(StatusCodes.custom(400,"Id not found","Please use the correct id"))
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
                complete(StatusCodes.custom(200,"OK","User Deleted Successfully"))
              } else {
                complete(StatusCodes.custom(400,"Id not found","Please use the correct id"))
              }
            }
          }
        }~
        get {
          parameter('id.as[Int]) {
            (a) => {
              val res1 = getUserById(a)
              res1 match{
                case Some(value)=> complete(value)
                case None =>{

                  complete(StatusCodes.custom(404,"UnAuthorized","Unauthorized Access, (Id Not Found)"))
                }
              }

            }
          } ~
            parameter("username") {
              (a) =>
                get {
                  val res1 = existingUserFetch(a)
                  if (res1.isEmpty) {
                    complete(StatusCodes.custom(404,"UnAuthorized","Unauthorized Access, (User Not Found)"))
                  } else complete(res1)
                }
            }

        }
    }
}
