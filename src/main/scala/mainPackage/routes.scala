package mainPackage
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import spray.json.DefaultJsonProtocol

case class UserBase(id:Int,name:String,startTime:String,create_at:String,password:String)
trait coMagic extends DefaultJsonProtocol{
  implicit val done=jsonFormat5(UserBase)
}
import mainPackage.handlers._
object routes extends SprayJsonSupport with coMagic {

  val url = "jdbc:mysql://localhost:3306/megaBase"
  val username = "root"
  val password = "Tellius@12345"

  import handlers._

  val mainUser = defHandlers(url, username, password)
  val reqHandler =
    pathPrefix("api" / "user") {

      post{
       entity(as[UserBase]){
         (a)=>{
           val res1=mainUser.addUser(a)
          if(res1 == None)complete(StatusCodes.NotImplemented)
          else complete(res1)
         }
       }
      }~
        patch{
        entity(as[UserBase]){
          (a)=>{
            parameter('id.as[Int]) {
              (b) => {
                val res1 = mainUser.updateUser(b,a)
                if(res1){
                  complete(StatusCodes.OK)
                }else{
                  complete(StatusCodes.NotImplemented)
                }
              }
            }
          }
        }
        }~
      get {
        parameter('id.as[Int]){
          (a)=>{
            val res1=mainUser.getUserById(a)
            complete(res1)
          }
        }~
        parameter("username"){
          (a)=>get{
         val res1=mainUser.existingUserFetch(a)
          if(res1.isEmpty){
            complete(StatusCodes.NotAcceptable)
          }else complete(res1)
          }
        }

      }

    }



}
