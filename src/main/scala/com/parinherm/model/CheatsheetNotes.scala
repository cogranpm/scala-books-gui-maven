package com.parinherm.model


import com.parinherm.model.FuncProgView.exercises

import scala.collection.mutable.ListBuffer

object CheatsheetNotes {



val variableAndMethodsHelp =
"""
laziness: use lazy keyword in front of variable declaration
pattern matching: val (one, two) = ("one", 2)
simple method:
def add(n: Int, m: Int): Int = n + m
curried method:
def add(n: Int)(m: Int): Int = n + m

"""

  val demoVarsAndMethods: () => String = ()  => {
    var output: String = ""
    output += "Laziness lazy val z = 0"
    output
  }

  val exercises = ListBuffer.empty[Exercise]
  exercises += new Exercise("Demo",  demoVarsAndMethods)

  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Variables And Methods", variableAndMethodsHelp, exercises)


}
