package mainpackage.allhandlers

import mainpackage.DatabaseConnection.{getConnection, password}
import mainpackage.ResultParserUtility.parseResultSet
import mainpackage.UserBase


object GetUserRequestHandler {
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


  def getAllUsers: List[UserBase] = {
    val connection = getConnection()
    val statement = connection.createStatement()
    val query = "SELECT * FROM Person"
    val resultSet = statement.executeQuery(query)
    val res_User = parseResultSet(resultSet)
    resultSet.close()
    statement.close()
    connection.close()
    res_User
  }

}
