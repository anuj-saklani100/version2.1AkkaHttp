package mainPackage.allHandlers

import mainPackage.Credentials.password
import mainPackage.RoutesPack._
import mainPackage.UserBase
import mainPackage.allHandlers.connectorCon.getConnection
object idFetcher {


  def getUserById(ids: Int): Option[UserBase] = {
    val connection = getConnection()
    val statement = connection.createStatement()
    val query = s"SELECT * FROM Person WHERE id=?"
    val prepareStatement = connection.prepareStatement(query)
    prepareStatement.setInt(1, ids)
    val resultSet = prepareStatement.executeQuery()
    if (resultSet.next()) {
      val id = resultSet.getInt("id")
      val name = resultSet.getString("name")
      val startTime = resultSet.getString("startTime")
      val create_at = resultSet.getString("create_at")
      val pass = resultSet.getString("password")
      val newUser = UserBase(id, name, startTime, create_at, password)
      resultSet.close()
      statement.close()
      connection.close()
      Some(newUser)
    } else {
      resultSet.close()
      statement.close()
      connection.close()
      None
    }
  }

}
