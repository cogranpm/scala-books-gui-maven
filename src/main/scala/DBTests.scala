import java.sql.Connection
import java.sql.DriverManager
import org.apache.commons.dbutils._
import scalikejdbc._



object DBTests {

case class Book (id: Long, subjectId: Long, name: String)
case class Subject(id: Long, name: String)
case class Chapter(id: Long, name: String)
case class Note(id: Long, name: String, body: String, script: String)


  def run() {
    /*val user = "paulm"
    val url = "jdbc:postgresql://kronmintdesktop:5432/golangtest"
    val password = "reddingo"
    val driver = "org.postgresql.Driver"
    println("going to run query")
    val connection: Connection = DriverManager.getConnection(url, user, password)
    val st = connection.prepareStatement("select id, name from book where id = ?")
    st.setInt(1, 3)
    var rs = st.executeQuery()

    while (rs.next()) {
      println(rs.getString(2))
    }
    rs.close()
    st.close()

    //dbutils method

    //val beanListHandler: MapListHandler = new MapListHandler()
    val queryRunner: QueryRunner = new QueryRunner()
    //val list = queryRunner.query(connection, "select id, name from book", null)


    //scalike
    ConnectionPool.singleton(url, user, password)
    ConnectionPool.add("golangtest", url, user, password)

/*
    val id = 3
    val book: Option[Book] = DB readOnly { implicit session =>
      sql"select id, name from book where id = ${id}"
        .map(rs => Book(rs.long("id"), rs.string("name"))).single.apply()
    }

    book match {
      case Some(b) => println(b.name)
      case _ => println("nothing")
    }

    //idiomatic way to treat option in scala is to use map, or flatMap, filter, foreach
    println("this is the idiomatic way: " + (book map {
      _.name
    }))
    book.foreach(a => println(a.name))
*/

    connection.close()

     */
  }
}
