package com.parinherm.model.coursera

import com.parinherm.model.NavigationData.{addLine, createExercises}
import com.parinherm.model.{Exercise, Topic}
import com.parinherm.services.HttpClientService

import scala.collection.mutable.ListBuffer



import play.api.libs.json._



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

  val notesPatterns =
    """
      | unknownObject match {
      |   case MyClass(n) => ...
      |   case MyClass2(a, b) => ...
      | }
      |
      | (someList: List[T]) match {
      |  case Nil => ...   //empty list
      |  case x :: Nil => ... // list with only one element
      |  case List(x) => ... // same as above
      |  case x :: xs => .. list with at least 1 element, x is bound to the head
      |  xs to the tail, xs could be Nil or some other list
      |  case 1 :: 2 :: cs => ... // list starts with 1 and then 2
      |  case (x, y) :: ps => ... //a list where head element is a pair
      |  case _ => ... // wildcard
      |
      |  Options:
      |  val myMap = Map("a" -> 42, "b" -> 43)
      |  def getMapValue(s: String): String = {
      |    myMap get s match {
      |      case Some(nb) => "value found: " + nb
      |      case None => "No value found"
      |    }
      |  }
      |
      |  getMapValue("a")
      |  getMapValue("c")
      |
      |  // easiest to just use getOrElse on an option value
      |  def getMapValue(s: String): String =
      |    // map.get returns an Option
      |    // Option is a container so you can use map on it
      |    // map takes a function parameter, so you can use a wildcard
      |    // in scala you can omit the left side of the lambda, so in this case
      |    // you see just the return value, a string
      |    // map on options, if value is present applies the function
      |    // and returns a Some with result of function
      |    // otherwise if None returns it and does not apply function
      |    myMap.get(s).map("Value found: " + _).getOrElse("No value found")
      |
      |  Pattern Matching in Anonymous Functions
      |  val pairs: List[(Char, Int)] = ('a', 2) :: ('b', 3) :: Nil
      |  val chars: List[Char] = pairs.map(p => p match {
      |     case (ch, num) => ch
      |     })
      |
      |   // can leave out the match bit
      |   val chars: List[Char] = pairs.map {
      |     case (ch, num) => ch
      |     }
      |
      |
      |
      |
      |
      |""".stripMargin

  val notesCollections =
    """
      | Collections:
      | Base classes:
      |   Iterable
      |   Seq
      |   Set
      |   Map
      |
      | Immutables:
      |   List - fast sequential
      |   Stream - tail evaluated only on demand
      |   Vector - fast random access
      |   Range
      |   String - can treat as Seq[Char]
      |   Map
      |   Set
      |
      | Mutables:
      |  Array - native java arrays
      |  Map
      |  Set
      |
      |  Examples:
      |
      |  val fruitList = List("apples", "oranges", "pears")
      |  val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))  //:: is right associative
      |  // which means that you push the right side argument to the left and call .::(leftside) on it
      |  eg: Nil.::("pears") // :: is a method on List
      |
      |  fruit.head
      |  fruit.tail // a list after first item
      |  val empty = List()
      |  val empty = Nil
      |
      |  val nums = Vector("Louis", "frank", "hiromi")
      |  nums(1)
      |  nums.updated(2, "helena") // returns new vector with changed value at index
      |
      |  val fruitSet = Set("apple", "banana", "pear", "banana")
      |  fruitSet.size //no duplicates
      |
      |  val r: Range = 1 until 5
      |  val s: Range = 1 to 5
      |  1 to 10 by 3
      |  6 to 1 by -2
      |  val s = (1 to 6).toSet
      |  s map (_ + 2) //adds 2 to each element in set
      |
      |  val s = "hello world"
      |  s filter (c => c.isupper) // returns HW
      |
      | Operations on Lists:
      |  val xs = List(...)
      |  xs.length
      |  xs.last
      |  xs.init  //all but last
      |  xs take n  //first n elements
      |  xs drop n  //rest are n elements
      |  xs(n)  // nth element
      |  xs ++ ys
      |  xs.reverse
      |  xs updated(n, x) // changes item at x and returns new list
      |  xs indexOf x
      |  xs contains x
      |  xs filter p
      |  xs filterNot p
      |  xs partition p
      |  xs takeWhile p
      |  xs dropWhile p
      |  xs span p  // same as (xs takeWhile p, xs dropWhile p)
      |
      |  List(x1, ..., xn) reduceLeft op  //applies op to each item pair in order
      |  List(x1, ..., xn).foldLeft(z)(op) // first applies op to z and first item, then to x2
      |  List(x1, ..., xn) reduceRight op // hmmmm
      |  List(x1, ..., xn).foldRight(z)(op) //
      |
      | xs exists p
      | xs forall p // true if p(item) true for all items
      | xs zip ys  // returns a list of pairs which groups elements with same index together
      | xs unzip  // returns a pair of two lists
      | xs.flatMap f //applies function to all elements and concatenates the result
      | xs.sum  //sum of element of numeric collection
      | xs.product
      | xs.max
      | xs.min
      | xs.flatten  //flattens collection of collection into single level
      | xs groupBy f // returns a map which points to a list of elementss
      | xs distinct  // removes duplicates
      |
      |   x +: xs // creates a new collection with leading element x
      |   xs :+ x // creates a new collection with a trailing element x
      |
      |   Operations on Maps:
      |   val myMap = Map("I" -> 1, "V" -> 5, "X" -> 10)
      |   myMap("I")
      |   myMap("A") // java.util.NoSuchElementException
      |   myMap get "A" // None
      |   myMap get "I" // Some(1)
      |   myMap.updated("V", 15)
      |
      |   Operatoins on Streams (note: streams a lazy lists):
      |   val xs = Stream(1, 2, 3)
      |   val xs = Stream.cons(1, Stream.cons(2, Stream.cons(3, Stream.empty))) // same as above
      |   (1 to 1000).toStream  // Stream(1, ?)
      |   x #:: xs // same as stream.cons
      |
      |   Pairs:
      |   val pair = ("answer", 42)  // type: (String, Int)
      |   val (label, value) = pair   // deconstruct
      |   pair._1
      |   pair._2
      |
      |   Ordering:
      |   import math.Ordering
      |
      |   def msort[T](xs: List[T])(implicit ord: Ordering) = { ... }
      |   msort(fruits)(Ordering.String)
      |   msort(fruits) // compiler figures out right ordering
      |
      | For Comprehensions:
      | syntactic sugar for map, flatMap and filter operations on collections
      | General Form: for (s) yield e
      |   for (x <- 1 to M; y <- 1 to N)
      |     yield (x, y)
      |
      | which is eqivalent to:
      |   (1 to M) flatMap (x => (1 to N) map (y => (x, y)))
      |
      | another example:
      |   for {
      |     i <- 1 until n
      |     j <- 1 until i
      |     if isPrime(i + j)
      |   } yield (i, j)
      |
      |
      |
      |""".stripMargin


  def testHuffingtonPost(): String = {
    val output = new StringBuilder()
    val requestBaseUrl = "https://elections.huffingtonpost.com/pollster/api/v2/"
    val requestUrl = requestBaseUrl + "questions.json"
    val json = HttpClientService.getDataFromUrl(requestUrl)
    json match {
      case Some(theData) => {
        val items = (theData \ "items").as[List[JsValue]]
        //items is a list of questions
        items.foreach({
          item => {
            val name = (item \ "name").as[String]
            addLine(name, output)
            val responses = (item \ "responses").as[List[JsValue]]
            responses.foreach({
              response => addLine((response \ "name").as[String], output)
            })
          }
        })
      }
      case _ => addLine("Error", output)
    }
    output.toString()

  }

  def collections() : String = {
    val output = new StringBuilder("")
    addLine("Collections", output)
    //addLine(testHuffingtonPost(), output)

    val healthDataUrl = "https://data.sfgov.org/resource/pyih-qa8i.json"
    //"https://services.arcgis.com/afSMGVsC7QlRK1kZ/arcgis/rest/services/Food_Inspections/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json"
    val hrep: Option[JsValue] = HttpClientService.getDataFromUrl(healthDataUrl)
    hrep match {
      case Some(theData) => {
        addLine(theData.toString(), output)
        /*
        val bizName = (theData \ "business_name").as[String]
        val vio = (theData \ "violation_description").as[String]
        addLine(s"Name: $bizName  Violation: $vio", output)

         */
      }
      case _ => addLine("Error", output)
    }
   /*
    val healthDataJson = HttpClientService.getDataFromUrl(healthDataUrl)
    addLine(healthDataJson.toString(), output)
     */
    //addLine(HttpClientService.getDataFromUrlAsync(healthDataUrl), output)


    //val jsonAst = JsonParser(body)
    //addLine(jsonAst.prettyPrint, output)


/*
 */

   val empty = List()

    val seqTest = Seq[Int](1, 2, 3)
    addLine(seqTest(0).toString, output)
    addLine(seqTest.last.toString, output)
    addLine("****************************************", output)
    output.toString()
  }

  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Basics", notesBasics, ListBuffer.empty[Exercise])
  topics += new Topic("OO", notesOO, ListBuffer.empty)
  topics += new Topic("Pattern Matching", notesPatterns, ListBuffer.empty)
  topics += new Topic( "Collections", notesCollections,
    createExercises(new Exercise("Demo", collections)) )

}
