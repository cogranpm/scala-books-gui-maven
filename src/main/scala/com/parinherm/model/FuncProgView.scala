package com.parinherm.model

import scala.collection.mutable.ListBuffer


object FuncProgView {

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

  val introducingScala =
    """
      | object - a singleton
      | as in:
      | object MyModule {
      |   def abs(n: Int):Int =
      |     if (n < 0) -n
      |     else n
      |
      |   private def formatAbs(x: Int) = {
      |     val msg = "blah %d %d"
      |     msg.format(x, abs(x))
      |   }
      |
      |   def main(args: Array[String]): Unit =
      |     println(formatAbs(-42))
      | }
      |""".stripMargin

  // there are many exercises per topic, associate via the topic key
  val exercises = ListBuffer.empty[Exercise]
  val testFunction: () => String = ()  => "running a function"
  val differentFunction: () => String = () => "running a diffferent function"
  exercises += new Exercise("Functions",  testFunction)
  exercises += new Exercise("Currying", differentFunction)


  val topics = ListBuffer.empty[Topic]
  topics += new Topic("First Example", firstExample, ListBuffer.empty)
  topics += new Topic("Introducing Scala", introducingScala,  exercises)
  topics += new Topic("Strictness and Laziness", functionsHelp, exercises)



}
