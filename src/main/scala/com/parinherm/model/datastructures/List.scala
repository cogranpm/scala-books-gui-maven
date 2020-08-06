package com.parinherm.model.datastructures

/*
example in functional programming in Scala
 */

/* sealed trait,
all implementations must be in this file
 */
sealed trait List[+A]
case object Nil extends List[Nothing]  //note, case object is a singleton case class
case class Cons[+A] (head: A, tail: List[A]) extends List[A]

object List {

  /* note the pattern in these two methods
  pattern matching and recursion is used to
  do a calculation on all items by sending the tail
  in on each iteration until the list is exhausted
  match deconstructs on constructor arguments
   */
  def sum(ints: List[Int]) : Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]) : Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  /*  the * means vargargs, comes in as an array
  *  the : _* is the splat operator, will split a sequence
  *  into a number of call arguments, for a variadic parameter
  * */
  def apply[A](item: A*): List[A] =
    if (item.isEmpty) Nil
    else Cons(item.head, apply(item.tail : _*))

}

object demoList {
  val ex1: List[Double] = Nil
  val ex2: List[Int] = Cons(1, Nil)
  val ex3: List[String] = Cons("a", Cons("b", Nil))

  def run() : String = {

    "I ran"
  }
}


