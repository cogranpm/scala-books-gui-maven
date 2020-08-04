package com.parinherm.model


import com.parinherm.model.FuncProgView.exercises

import scala.collection.mutable.ListBuffer

object CheatsheetNotes {



  val firstExample =
    """
case classes - immutable fields, new not required
return value object that represents a charge - as a case class
don't actually charge the card
pure functions allow for local reasoning
eliminate side effects

"""

  val functionsHelp =
    """
      |um, functions
      |""".stripMargin
  val testFunction: () => String = ()  => "running a function"
  val differentFunction: () => String = () => "running a diffferent function"

  val exercises = ListBuffer.empty[Exercise]
  exercises += new Exercise("Functions",  testFunction)
  exercises += new Exercise("Currying", differentFunction)

  val topics = ListBuffer.empty[Topic]
  topics += new Topic("First Example", firstExample, exercises)


}
