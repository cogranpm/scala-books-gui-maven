package com.parinherm.model

import com.parinherm.model.NavigationData.{addLine, createExercises}

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, StringBuilder}


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



  val empty = ListBuffer.empty[Exercise]


  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Variables And Methods", variableAndMethodsHelp,
    createExercises(new Exercise("Demo",  demoVarsAndMethods)))
  topics += new Topic("Strings", strings_stuff, ListBuffer.empty[Exercise] )
  topics += new Topic("Regular Expressions", regex, ListBuffer.empty[Exercise])
  topics += new Topic("Classes", classes,
    createExercises(new Exercise("Demo", fnClasses)))


  def demoVarsAndMethods ():  String = {
    var output = new mutable.StringBuilder("Begin \n")
    lazy val z = "lazy"
    val (one, two) = ("one", 2)
    addLine( s"one: $one two: $two", output)
    addLine("Laziness lazy val z = 0", output)

    addLine("nested method and currying example", output)
    def add(n: Int)(m: Int): Int = n + m
    val adderTen = add(10)(_)
    addLine(s"${adderTen(5)}", output)


    output.toString()
  }

  def demoRegularExpressions(): String = {
    var output = new StringBuilder("Begin\n")

    output.toString()
  }


  def fnClasses(): String = {
    "classes and stuff"
  }
}
