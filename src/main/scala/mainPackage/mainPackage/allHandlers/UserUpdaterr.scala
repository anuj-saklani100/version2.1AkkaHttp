package mainPackage.allHandlers

import mainPackage.UserBase
import mainPackage.Validations.isValidate
import mainPackage.allHandlers.ConnectorCon.getConnection

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object UserUpdaterr {
  def updateUser(ids: Int, info: UserBase): Boolean = {
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
      println("Password is invalid!")
      false
    }
  }
}
