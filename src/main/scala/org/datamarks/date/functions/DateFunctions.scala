package org.datamarks.date.functions

import java.sql.Timestamp
import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

import org.apache.commons.lang3.StringUtils.isBlank

object DateFunctions {

  def toTimestampFormatted(date: String, format: String) : Option[Timestamp] = {
    if (date != null) {
      try {
        val formatter = DateTimeFormatter.ofPattern(format)
        val localDateTime: LocalDateTime = {
          if (format.contains("HH")||format.contains("ss")) LocalDateTime.parse(date, formatter)
          else LocalDate.parse(date,formatter).atStartOfDay()
        }
        Option(Timestamp.valueOf(localDateTime))
      }
      catch {
        case e: DateTimeParseException => {
          println(s"Cannot parse to timestamp VALUE:[${date}] FORMAT: [${format}] \n${e.getMessage}")
          None
        }
        case exArg: IllegalArgumentException => {
          println(s"Cannot parse to timestamp VALUE:[${date}] FORMAT: [${format}] \n${exArg.getMessage}")
          None
        }
      }
    } else {
      None
    }
  }

  def transformToTimestamp(value: String, format: String = "yyyy-MM-dd'T'HH:mm:ssXXX") = {
    if (isBlank(value) || (value.equals("00000000")) || (value.equals("0")))
      toTimestampFormatted(null, format)
    else
      toTimestampFormatted(value, format)
  }


}