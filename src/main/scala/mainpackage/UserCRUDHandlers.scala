package mainpackage

import mainpackage.DatabaseConnection.{getConnection, password}
import mainpackage.ResultParserUtility.parseResultSet
import mainpackage.Validations.isValidate

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object UserCRUDHandlers {

  def addUser(user: UserBase): Option[UserBase] = {
 try{
   if (isValidate(user.password)) {
     val currentDateTime = LocalDateTime.now()
     val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
     val thisTime = currentDateTime.format(formatter)
     val connection = getConnection()
     val statement = connection.createStatement()
     val query = s"INSERT INTO Person (id, name,startTime, create_at, password) VALUES (?,?,?,?,?)"
     val prepareStatement = connection.prepareStatement(query)
     prepareStatement.setInt(1, user.id)
     prepareStatement.setString(2, user.name)
     prepareStatement.setString(3, thisTime)
     prepareStatement.setString(4, thisTime)
     prepareStatement.setString(5, user.password)


     val resultSet = prepareStatement.executeUpdate()
     if (resultSet > 0) {
       val id = user.id
       val name = user.name
       val startTime = thisTime
       val create_at = thisTime
       val pass = user.password
       val ns = UserBase(id, name, startTime, create_at, pass)
       statement.close()
       connection.close()
       Some(ns)
     } else {
       statement.close()
       connection.close()
       None
     }
   } else {
     None
   }
 } catch {
   case exe: Exception => println(s"The error message is: ${exe.getMessage}")
     None
 }
  }


  def deleteById(ids: Int): Boolean = {
    val connection = getConnection()
    val statement = connection.createStatement()
    val query = s"DELETE FROM Person WHERE id=?"
    val preparedStatement = connection.prepareStatement(query)
    preparedStatement.setInt(1, ids)
    val rowAffected = preparedStatement.executeUpdate()
    if (rowAffected > 0) {
      statement.close()
      connection.close()
      true
    } else {
      statement.close()
      connection.close()
      false
    }
  }


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


  def updateUser(ids: Int, info: UserBase): Boolean = {
 try{
   if (isValidate(info.password)) {
     val currentDateTime = LocalDateTime.now()
     val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
     val thisTime = currentDateTime.format(formatter)
     val connection = getConnection()
     val statement = connection.createStatement()
     val query = "UPDATE Person SET id=?, name=?, startTime=?, create_at=?, password=? WHERE id=?"
     val prepareStatement = connection.prepareStatement(query)
     prepareStatement.setInt(1, info.id)
     prepareStatement.setString(2, info.name)
     prepareStatement.setString(3, info.startTime)
     prepareStatement.setString(4, thisTime)
     prepareStatement.setString(5, info.password)
     prepareStatement.setInt(6, ids)
     val rowAffect = prepareStatement.executeUpdate()
     if (rowAffect > 0) {
       println("Row updated")
       true
     } else {
       println("Not updated!")
       false
     }
   } else {
     throw new Exception("Invalid Password")
   }
 }catch {
   case exe:Exception=> println(s"The error message is: ${exe.getMessage}")
     false
 }
  }
}
