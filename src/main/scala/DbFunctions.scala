import java.sql.Connection
import java.sql.DriverManager
import anorm._
import anorm.SqlParser._


case class Book (id: Long, subjectId: Long, name: String)
case class Subject(id: Long, name: String)
case class Chapter(id: Long, name: String)
case class Note(id: Long, name: String, body: String, script: String)

object DbFunctions {


  //initialize the database
  val user = "paulm"
  val url = "jdbc:postgresql://kronmintdesktop:5432/golangtest"
  val password = "reddingo"
  val driver = "org.postgresql.Driver"
  println ("initialize database functions")
  val connection: Connection = DriverManager.getConnection(url, user, password)
  implicit val con: java.sql.Connection = connection
  val parser: RowParser[Book] = Macro.namedParser[Book]

  def runQuery(): Unit =
  {
    val result: List[Book] = SQL(""" select id, name, subjectId from book where id = {bookId}""").on("bookId" -> 3).as(parser.*)
    result.foreach( book => println("anorm results: " + book.id + book.name))
  }

  def insertBook(name: String): Unit =
  {
    val id: Option[Long] =
      SQL("insert into book(name, subjectId) values ({name}, {subjectId} )")
      .on("name" ->  name, "subjectId" -> 10).executeInsert()

    id.foreach(x => println("inserted key was: " + x))
  }

  def close(): Unit = {
    connection.close()
  }
}
