package com.parinherm.model


import scala.collection.mutable.ListBuffer

object CheatsheetNotes {

  val topics = ListBuffer.empty[Topic]
  val exercises = ListBuffer.empty[Exercise]
  val note = """ collections """
  topics += new Topic("Collections", note, exercises)
}
