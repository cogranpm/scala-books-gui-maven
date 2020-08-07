package com.parinherm.model.coursera

import com.parinherm.model.{Exercise, Topic}

import scala.collection.mutable.ListBuffer

object Cheatsheet {

  val notesBasics =
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
      |
      | Currying
      | def f(a: Int, b: Int): Int //uncurried version (Int, Int) => Int
      | def f(a: Int)(b: Int): Int //curried version Int => Int => Int
      |""".stripMargin

  var notesOO =
    """
      | Classes
      |
      | class MyClass(x: Int, y: Int) {
      |
      |   //precondition, fires IllegalArgumentException if not met
      |   require(y > 0, "y must be positive")
      |
      |   def this (x: Int) = { ... } //auxillary constructor
      |
      |   def nb1 = x  //public method computed every time it's called
      |
      |   def nb2 = y
      |
      |   private def test(a: Int): Int = { ... }  //private method
      |
      |   val nb3 = x + y   //computed only once
      |
      |   override def toString =
      |     member1 + ", " + member2
      |
      | }
      |
      | new MyClass(1, 2)
      |
      | // design by contract includes require, assume, assert in scala.Predef
      |
      | Operators:
      | myObject myMethod 1 is same as myObject.myMethod(1)
      | function names can be symbolic
      | precedence is determined by first character
      | associtivity is determined by last character, if : then right associative otherwise left
      |
      | Class hierarchies:
      |
      | abstract class TopLevel {
      |   def method1(x: Int): Int
      |   def method2(x: Int): Int = { ... }
      | }
      |
      | class Level1 extends TopLevel {
      |   def method1(x: Int): Int = { ... }
      |   override def method2(x: Int): Int = { ... } //must override
      | }
      |
      | object MyObject extends TopLevel { ... } //singleton object
      |
      | to create runnable application:
      | object Hello {
      |   def main(args: Array[String]) = println("hello world")
      | }
      |
      | classes and object are organized into packages: package com.parinherm.stuff
      | import com.parinherm.stuff.MyClass
      | import com.parinherm.stuff._
      | import com.parinherm.stuff.{MyClass, MyOtherClass}
      | import com.parinherm.stuff.{MyClass => Alias}
      | //automatic imports
      | scala
      | java.lang
      | scala.Predef
      |
      | General Object Hierarchy
      | scala.Any
      | scala.AnyVal - base type of primitive types
      | scala.AnyRef - alias of java.lang.Object, Strings Lists etc
      | scala.Null - subtype of Anyref
      | scala.Nothing
      |
      | Type Parameters (Generics)
      | apply to traits, classes or functions
      |
      | class MyClass[T](arg1: T) { ... }
      | new MyClass[Int](1)
      | new MyClass(1) // type is inferred, based on value arguments
      |
      | Type restrictions:
      | def myFct[ T <: TopLevel ] ( arg: T ) : T = { ... } // derive from TopLevel or TopLevel
      |
      | def myFct[ T >: Level1 ] ( arg: T ) : T = { ... } // must be supertype of Level1
      |
      | def myFct[ T >: Level1 <: TopLevel ] ( arg: T ) : T = { ... }
      |
      | Variance :
      | given A <: B
      | if C[A] <: C[B], c is covariant
      | if C[A] >: C[B], c is contravariant
      | otherwise C is non variant
      |
      | class C[+A] { ... } // C is covariant
      | class C[-A] { ... } // C is contravariant
      | class C[A] { ... } // C is nonvariant
      |
      | functions must be contravariant in their argument types and covariant in result types
      | eg:
      | trait Function1[-T, +U] {
      |   def apply(x: T): U   //return is covariant
      | }
      |
      | class Array[+T] {
      |   def update(x: T)  // this fails, argument is covariant, not allowed
      | }
      |
      |""".stripMargin

  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Basics", notesBasics, ListBuffer.empty[Exercise])
  topics += new Topic("OO", notesOO, ListBuffer.empty)

}
