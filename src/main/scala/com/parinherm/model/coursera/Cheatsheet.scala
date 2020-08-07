package com.parinherm.model.coursera

import com.parinherm.model.{Exercise, Topic}

import scala.collection.mutable.ListBuffer

object Cheatsheet {

  val notes =
    """
      | Evaluation Rules
      | def example = 2 //evaluated when called
      | val example = 2 //evaluated immediately
      | lazy val example = 2 //evaluated once when needed
      |
      | def square(x: Double) //call by value
      | def square(x: => Double) // call by name
      | def myFct(bindings: Int*) = { ... } //varargs
      |
      | Higher Order Functions
      | def sum( f: Int => Int): (Int, Int) => Int = {
      |   def sumf(a: Int, b: Int): Int = { ... }
      |   sumf
      | }
      |
      | // same as above
      | def sum(f: Int => Int) (a: Int, b: Int): Int = { ... }
      |
      | // called like this
      | sum((x: Int) => x * x * x)  // anonymous function
      | sum(x => x * x * x) // same with types inferred
      |
      | def cube(x: Int) = x * x * x
      | sum(x => x * x * x)(1, 10) // sum of cubes from 1 to 10
      | sum(cube)(1, 10)  // same as above
      |""".stripMargin
  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Notes", notes, ListBuffer.empty[Exercise])

}
