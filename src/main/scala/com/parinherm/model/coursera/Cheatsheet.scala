package com.parinherm.model.coursera

import com.parinherm.model.{Exercise, Topic}

import scala.collection.mutable.ListBuffer

object Cheatsheet {

  val notes =
    """
      | Evaluation Rules
      |
      |""".stripMargin
  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Notes", notes, ListBuffer.empty[Exercise])

}
