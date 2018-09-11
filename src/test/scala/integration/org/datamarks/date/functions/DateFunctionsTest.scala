package integration.org.datamarks.date.functions

import java.sql.Timestamp
import org.datamarks.date.functions.DateFunctions

class DateFunctionsTest extends UnitSpec {

  private val timestampFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
  private val simpleDateFormat = "yyyy-MM-dd"
  private val defaultTimestamp = 1525748400000L
  private val defaultSaoPauloTimeStamp = 1525755780000L

  "An Empty Data" must "be none" in {
    val actual : Option[Timestamp] = DateFunctions.toTimestampFormatted("","")
    val expected : Option[Timestamp] = None
    assert(expected===actual)
  }

  "A data with wrong format" must "be none" in {
    val actual : Option[Timestamp] = DateFunctions.toTimestampFormatted("2018-08-05","YYYY-MM-DDD h:mm:ss.SSS")
    val expected : Option[Timestamp] = None
    assert(expected===actual)
  }

  s"2018-05-08 with pattern ${timestampFormat}" should s"be ${defaultTimestamp}" in {
    val actual : Option[Timestamp] = DateFunctions.toTimestampFormatted("2018-05-08T00:00:00.000Z",timestampFormat)
    val time = new Timestamp(defaultTimestamp)
    val expected : Option[Timestamp] = Option(time)
    assert(expected===actual)
  }

  "A date with format yyyy-MM-dd" should "be parsed" in {
    val actual : Option[Timestamp] = DateFunctions.toTimestampFormatted("2018-05-08",simpleDateFormat)
    val expected : Option[Timestamp] = Option(new Timestamp(defaultTimestamp))
    assert(expected===actual)
  }

  s"2018-05-08 with pattern ${timestampFormat}" should s"be ${defaultSaoPauloTimeStamp} from utc to SP " in {
    val actual : Option[Timestamp] = DateFunctions.toTimestampFormattedWithLocalZone("2018-05-08T05:03:00.000Z",timestampFormat)
    val time = new Timestamp(defaultSaoPauloTimeStamp)
    val expected : Option[Timestamp] = Option(time)
    assert(expected===actual)
  }

}