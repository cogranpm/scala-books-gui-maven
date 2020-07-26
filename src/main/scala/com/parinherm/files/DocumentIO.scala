package com.parinherm.files

import java.io.IOException
import java.nio.file.{Files, Paths, StandardOpenOption}
import java.util.Optional
import java.util.stream.Stream

class DocumentIO {

  def readTextFile(fileName: String) : String = {
    var content = ""
    val theFile = Paths.get(fileName)
    if (!Files.exists(theFile)){
      Files.createFile(theFile)
    }
    else {
      content = new String(Files.readAllBytes(theFile))
    }
    content
  }

  def write(fileName: String, content: String): Unit = {
    try {
      Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE)
    } catch {
      case ex: IOException => println (ex.getMessage)
    }
  }
}
