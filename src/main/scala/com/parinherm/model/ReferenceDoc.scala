package com.parinherm.model

import com.parinherm.files.DocumentIO
import org.eclipse.jface.text.Document
import org.eclipse.jface.text.IDocument


abstract class ReferenceDoc (var fileName: String) {
  val document: IDocument = new Document()
  val docWriter: DocumentIO = new DocumentIO()
  var dirtyFlag: Boolean = false


  def getDocument(): IDocument = document
  def addMessage(message: String): Unit = document.set(document.get() + message + "\n")
  def run(): Unit
  def save(): Unit
}
