package com.parinherm.model


import com.parinherm.model.FuncProgView.exercises

import scala.collection.mutable.ListBuffer

object CheatsheetNotes {



val variableAndMethodsHelp =
"""laziness: use lazy keyword in front of variable declaration
lazy val z = "lazy"

pattern matching: val (one, two) = ("one", 2)

simple method:
def add(n: Int, m: Int): Int = n + m

curried method:
def add(n: Int)(m: Int): Int = n + m

by name parameters (evaluates 'a' twice):
def twice[A](a: => A) = { a; a }

repeated parameters (varargs):
def many(ns: Int*): Seq[Int] = ns

calling a varargs method with a 'Seq'
> many(Seq(1, 2) :_*)

"""

  val demoVarsAndMethods: () => String = ()  => {
    var output: String = ""
    output += "Laziness lazy val z = 0"
    output
  }

  val strings_stuff =
    """ s for interpolation with $ around variables
      | $ { } for complex expressions
      | multiline in triple quotes " " "
      |""".stripMargin

  val regex =
    """
      | val time = ...
      |""".stripMargin


  val classes =
    """
      |
      |""".stripMargin

 val objects =
   """
     |
     |""".stripMargin

  val traits =
    """
      |
      |""".stripMargin

  val case_classes =
    """
      |
      |""".stripMargin

  val varsAndMethodsExercises = ListBuffer.empty[Exercise]
  varsAndMethodsExercises += new Exercise("Demo",  demoVarsAndMethods)

  val empty = ListBuffer.empty[Exercise]


  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Variables And Methods", variableAndMethodsHelp, varsAndMethodsExercises)
  topics += new Topic("Strings", strings_stuff, ListBuffer.empty[Exercise] )
  topics += new Topic("Regular Expressions", regex, ListBuffer.empty[Exercise])
  topics += new Topic("Classes", classes, empty)
}
