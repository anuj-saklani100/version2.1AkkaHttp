package mainPackage.allHandlers

import mainPackage.UserBase
import mainPackage.Validations.isValidate
import mainPackage.allHandlers.ConnectorCon.getConnection

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object UserAdder {
  def addUser(user: UserBase): Option[UserBase] = {
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
  }
}
