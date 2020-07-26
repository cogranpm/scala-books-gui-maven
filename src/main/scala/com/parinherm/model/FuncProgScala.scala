package com.parinherm.model

import org.eclipse.jface.text.{DocumentEvent, IDocumentListener}


class FuncProgScala(fileName: String) extends ReferenceDoc (fileName ) {

  override def run(): Unit = {

    val contents = docWriter.readTextFile(fileName)
    addMessage(contents)

    document.addDocumentListener( new IDocumentListener {
      override def documentAboutToBeChanged(documentEvent: DocumentEvent): Unit = {

      }

      override def documentChanged(documentEvent: DocumentEvent): Unit = {
        dirtyFlag = true
      }
    })
  }


  override def save(): Unit = {
    if (dirtyFlag) docWriter.write(fileName, document.get())
  }
}

