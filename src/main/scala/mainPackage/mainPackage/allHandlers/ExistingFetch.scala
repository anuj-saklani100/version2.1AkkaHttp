package mainPackage.allHandlers

import mainPackage.UserBase
import mainPackage.allHandlers.AllUserLister.getAllUsers
import mainPackage.allHandlers.ConnectorCon.getConnection

object ExistingFetch {
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
