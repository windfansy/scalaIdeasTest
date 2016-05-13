package TestFunction

import scala.collection.mutable

/**
  * Created by T440P on 2016/5/13.
  */
object MultiMapStatisticTest {
  type logParserStatResult = mutable.Map[String, mutable.Map[String, mutable.Map[Int, Int]]]
  type logParserStatRecord = (String, String, Int, Int)

  def main(args: Array[String]) {
    // val profileIdMap = mutable.Map[Int, Int]().withDefaultValue(0)
    // val gsCommandMap = mutable.Map[String, mutable.Map[Int, Int]]().withDefaultValue(profileIdMap)
    // val dataTypeMap = mutable.Map[String, mutable.Map[String, mutable.Map[Int, Int]]]().withDefaultValue(gsCommandMap)
    val dataTypeMap = mutable.Map[String, mutable.Map[String, mutable.Map[Int, Int]]]()


    //    dataTypeMap.withDefaultValue(gsCommandMap)
    //    gsCommandMap.withDefaultValue(profileIdMap)
    // profileIdMap.withDefaultValue(0)
    val records: List[logParserStatRecord] = List(("TargetInfoDataObject", "spv", 544, 3), ("TargetInfoDataObject", "spv", 544, 5),
      ("WdPvDataObject", "spv", 433, 5), ("TargetInfoDataObject", "pv", 544, 3), ("TargetInfoDataObject", "spv", 722, 2))
    records.map(addAccumulator(dataTypeMap, _))
    //    addAccumulator(dataTypeMap, records(0))
    //    addAccumulator(dataTypeMap, records(1))
    //    addAccumulator(dataTypeMap, records(2))
    //    addAccumulator(dataTypeMap, records(3))
    //(dataTypeMap, records(4))
    //println(profileIdMap(3))
    showMultiMap(dataTypeMap)
  }

  def showMultiMap(dataTypeMap: logParserStatResult) = {
    for {
      dataType <- dataTypeMap.keys
      gsCommand <- dataTypeMap(dataType).keys
      profileId <- dataTypeMap(dataType)(gsCommand).keys
    } yield println(dataType, gsCommand, profileId, dataTypeMap(dataType)(gsCommand)(profileId))
  }

  def addAccumulator(dataTypeMap: logParserStatResult, record: logParserStatRecord) = {
    val (dataType, gsCommand, profileId, countNum) = record
    // val gsCommandMap = dataTypeMap(dataType)
    // val gsCommandMap = dataTypeMap(dataType)
    val gsCommandMap = dataTypeMap.getOrElse(dataType, new mutable.HashMap[String, mutable.Map[Int, Int]]())
    val profileIdMap = gsCommandMap.getOrElse(gsCommand, new mutable.HashMap[Int, Int]())
    val counterResult = profileIdMap.getOrElse(profileId, 0) + countNum
    profileIdMap += (profileId -> counterResult)
    gsCommandMap += (gsCommand -> profileIdMap)
    dataTypeMap += (dataType -> gsCommandMap)
  }
}


