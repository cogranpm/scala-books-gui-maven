package com.parinherm.model

import com.parinherm.model.twitterguides.EffectiveScala
import com.parinherm.ui.{BrowserTest, ReferenceDocView, TopicsView}
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.{CTabFolder, CTabItem}
import org.eclipse.swt.events.{SelectionEvent, SelectionListener}
import org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter
import org.eclipse.swt.widgets.Composite

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class NavigationItem (title: String,  handler: SelectionListener)
case class NavigationHeader (title: String, items: List[NavigationItem])

object NavigationData {

  def create(parentTab: CTabFolder) : List[NavigationHeader] = {

    val headers: List[NavigationHeader] = List(createCheatSheets(parentTab),
    createFunctionalProgrammingInScala(parentTab),
    createScalaProgrammingLanguage(parentTab),
    createBrowserHack(parentTab),
    createTwitterGuides(parentTab))
    headers
  }

  def createTwitterGuides(parentTab: CTabFolder) = {
    val view = new TopicsView(parentTab, EffectiveScala.topics)
    val item = NavigationItem("Effective Scala", createTabHandler(parentTab, view, "Effective Scala"))
    NavigationHeader("Twitter Guides", List(item))
  }

  def createBrowserHack(parentTab: CTabFolder) : NavigationHeader = {
    val view = BrowserTest.create(parentTab)
    val item = NavigationItem("Browser Hacks", createTabHandler(parentTab, view, "Browser Hacks"))
    NavigationHeader("Utilities", List(item))
  }

 def createCheatSheets(parentTab: CTabFolder) : NavigationHeader = {
   /* cheatsheets */
   val view = new TopicsView(parentTab, CheatsheetNotes.topics)
   val item = NavigationItem("Jax", createTabHandler(parentTab, view, "Jax"))
   val header = NavigationHeader("Cheatsheets", List(item))
    header
 }

  def createScalaProgrammingLanguage(parentTab: CTabFolder) = {
    val topicsView = new TopicsView(parentTab, ScalableLanguageNotes.topics)
    val scalableLanguageDocument = new ScalableLanguageDocument("Chapter1.doc")
    val chap2Doc = new Chapter2Document("Chapter2.doc")
    val topic = NavigationItem("Topics", createTabHandler(parentTab, topicsView, "Topics"))
    val ref1 = NavigationItem("Scalable Language", createReferenceHandler(parentTab, scalableLanguageDocument, "Scalable Language"))
    val ref2 = NavigationItem("Chapter 2", createReferenceHandler(parentTab, chap2Doc, "Chapter 2"))
    val header = NavigationHeader("Programming in Scala", List(ref1, ref2, topic))
    header

  }


  def createFunctionalProgrammingInScala(parentTab: CTabFolder) = {
    val topicsView = new TopicsView(parentTab, FuncProgView.topics)
    val doc = new FuncProgScala("FuncProgScala.doc")

    val topics = NavigationItem("Topics", createTabHandler(parentTab, topicsView, "Topics"))
    val reference = NavigationItem("Reference", createReferenceHandler(parentTab, doc, "Document"))
    val funcProgHeader = NavigationHeader("Functional Programming in Scala", List(topics, reference))
    funcProgHeader

  }

  def createTabItem(parentTab: CTabFolder, title: String, view: Composite) = {
    val tabItem = new CTabItem(parentTab, SWT.CLOSE)
    tabItem.setText(title)
    tabItem.setControl(view)
    tabItem
  }

  def createReferenceHandler(parentTab: CTabFolder, doc: ReferenceDoc , title: String): SelectionListener = {
    val shelfItemHandler = widgetSelectedAdapter(
      (e: SelectionEvent) => {
        val docView: ReferenceDocView = new ReferenceDocView(parentTab, SWT.BORDER, doc)
        parentTab.setSelection(createTabItem(parentTab, title, docView))
        doc.run()
      })
    shelfItemHandler
  }

  def createTabHandler(parentTab: CTabFolder, view: Composite, title: String): SelectionListener = {
    val shelfItemHandler = widgetSelectedAdapter(
      (e: SelectionEvent) => {
        parentTab.setSelection(createTabItem(parentTab, title, view))
      })
    shelfItemHandler

  }

  def createExercises(exercise: Exercise*) : ListBuffer[Exercise] = {
    val list = ListBuffer.empty[Exercise]
    list.addAll(exercise)
    list
  }

  def addLine(line: String, buffer: mutable.StringBuilder) = buffer ++= line ++= "\n"

}
