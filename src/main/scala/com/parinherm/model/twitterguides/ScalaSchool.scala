package com.parinherm.model.twitterguides

import com.parinherm.model.NavigationData.{addLine, createExercises, lineSplitter}

import com.parinherm.model.{Exercise, Topic}

import scala.collection.mutable.ListBuffer

object ScalaSchool{

  val basics =
    """
      | an anonymous function can be placed within curly braces 
      |""".stripMargin

  def basicsDemo(): String = {
   val output = new StringBuilder() 
    addLine("testing", output)
   output.toString
  }

  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Basics", basics, createExercises(new Exercise("Demo", basicsDemo)))
  topics += new Topic("Basics Continued", "", ListBuffer.empty)
  topics += new Topic("Collections", "", ListBuffer.empty)
  topics += new Topic("Pattern Matching & Functional Composition", "", ListBuffer.empty)
  topics += new Topic("Type and Polymorphism basics", "", ListBuffer.empty)
  topics += new Topic("Advanced types", "", ListBuffer.empty)
  topics += new Topic("Simple Build Tool", "", ListBuffer.empty)
  topics += new Topic("More Collections", "", ListBuffer.empty)
  topics += new Topic("Testing with specs", "", ListBuffer.empty)
  topics += new Topic("Concurrency", "", ListBuffer.empty)
  topics += new Topic("Java + Scala", "", ListBuffer.empty)
  topics += new Topic("Introduction to Finagle", "", ListBuffer.empty)
  topics += new Topic("Searchbird", "", ListBuffer.empty)
}


 
