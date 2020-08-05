package com.parinherm.model.twitterguides

import com.parinherm.model.{Exercise, Topic}

import scala.collection.mutable.ListBuffer

object EffectiveScala {

  val formattingNotes =
    """
      | to eliminate the ambiguity of the scala flexibility
      | indent 2 spaces
      | don't use reserved words as names (even though you can)
      | use active names for side effectful methods
      |   eg: user.activate() not user.setActive()
      | don't prefix getters with get
      |""".stripMargin

  val typesAndGenerics =
    """
      | avoid type level programming (higher kinded types?)
      | supply return type annotations (declare them) on public methods
      | use Invariants (generics and subtyping declarations)
      | Immutable collections should be covariant
      | Mutable collections should be invariant
      | do not alias types that are self explanatory eg:
      |   () => Int
      | is clearer then
      |   type IntMaker = () => Int
      | but
      | class ConcurrentPool[K, V] {
      |   type Queue = ConcurrentLinkedQueue[V]
      |   type Map = ConcurrentHashMap[K, Queue]
      | note is within class, enhances brevity and communicates purpose
      | don't use subclassing when an alias will do:
      |   trait SocketFactory extends (SocketAddress => Socket)
      | is better as a type alias
      |   type SocketFactory = SocketAddress => Socket
      | use package objects to bind type aliases to top level names
      |   package com.parinherm
      |   package object net {
      |     type SocketFactory = (SocketAddress) => Socket
      |   }
      |
      | use implicits sparingly,
      | they have complicated resolution rules
      | ok when:
      |   extending or adding scala style collection
      |   adapting or extending an object
      |   used to enhance type safety
      |   to provide type evidence
      |   for manifests
      |   don't use it for converting similar datatypes, these are better explicit
      |""".stripMargin

  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Formatting", formattingNotes, ListBuffer.empty[Exercise])
  topics += new Topic( "Types and Generics", typesAndGenerics, ListBuffer.empty)

}



