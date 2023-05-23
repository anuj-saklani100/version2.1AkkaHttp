package mainpackage

import scala.util.matching.Regex



trait ValidationTrait {
  // ------------------------- password validation mechanism --------------------
  def isValidate(pass: String): Boolean = {
    val minLength = 8
    val hasDigitRegex: Regex = ".*\\d.*".r
    val hasCharRegex: Regex = ".*[a-zA-Z].*".r
    val hasSpecialCharRegex: Regex = ".*[^a-zA-Z0-9].*".r

    (pass.length >= minLength) &
      (hasDigitRegex.findFirstMatchIn(pass).isDefined) &
      (hasCharRegex.findFirstMatchIn(pass).isDefined) &
      (hasSpecialCharRegex.findFirstMatchIn(pass).isDefined)
  }
}

object Validations extends ValidationTrait

