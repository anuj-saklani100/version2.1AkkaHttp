package mainpackage.allhandlers

import mainpackage.DatabaseConnection.getConnection
import mainpackage.UserBase
import mainpackage.allhandlers.AllUserListerRequestHandler.getAllUsers


object GetUserAuthenticationRequestHandler {
  def existingUserFetch(username: String): List[UserBase] = {
    val connection = getConnection()
    val statement = connection.createStatement()
    val query = s"SELECT * FROM Person WHERE name = ?"
    val prepareStatement = connection.prepareStatement(query)
    prepareStatement.setString(1, username)
    val resultSet = prepareStatement.executeQuery()

    if (resultSet.next()) {
      println(resultSet)
      getAllUsers
    } else {
      List.empty[UserBase]
    }

  }

}
