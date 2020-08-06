package com.parinherm.model

import com.parinherm.model.NavigationData.createExercises
import com.parinherm.model.datastructures.demoList

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

  val fds =
    """
      | it's a conventions to use xs, ys, as or bs as variable names
      | for  sequence of some sort and x, y, z, a or b as the name
      | for a single element of a sequence. Also h is for first element of a list
      | (head) and t for the remaining (tail), l is for the entire list
      |
      | variadic function has argument type followed by *
      | : _* is the splat operator, transforms a seq into individual arguments
      | pattern match: somevalue match {
      |   case somepattern => expression
      | }
      |
      | wildcard use _ in the pattern:
      | case _ => somevalue
      |
      | can also use wildcards anywhere in a pattern, say a constructor pattern
      | case Person(firstName, _) => firstName
      |
      | if no cases match, there will be a MatchError at runtime
      |
      | Companion Object
      | it is customary to supply one of these for a custom type
      | that takes a varargs parameter, to allow users to conveniently
      | construct instances of the type in tricky ways, for example
      | if you have a collection datastructure, and a user passes
      | two lists to a constructor, you might concatenate the two lists
      | into an instance of your type
      |
      |
      |""".stripMargin

  val exercises = ListBuffer.empty[Exercise]

  val topics = ListBuffer.empty[Topic]
  topics += new Topic("First Example", firstExample, ListBuffer.empty)
  topics += new Topic("Introducing Scala", introducingScala,  exercises)
  topics += new Topic("Strictness and Laziness", functionsHelp, exercises)
  topics += new Topic("Functional Data Structures", fds,
    createExercises(new Exercise("Demo",  demoList.run)))


}
