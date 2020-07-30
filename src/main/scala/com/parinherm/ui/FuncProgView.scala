package com.parinherm.ui

import java.util

import org.eclipse.core.databinding.beans.IBeanValueProperty
import org.eclipse.core.databinding.beans.typed.BeanProperties
import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.core.databinding.property.value.IValueProperty
import org.eclipse.jface.databinding.viewers.ViewerSupport
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.jface.viewers.{TableViewer, TableViewerColumn}
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.{FillLayout, GridLayout}
import org.eclipse.swt.widgets.{Composite, Label}

import scala.beans.BeanProperty
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

class Exercise (@BeanProperty var title: String)

class Topic (@BeanProperty var title: String, @BeanProperty var help: String, @BeanProperty var exercises: ListBuffer[Exercise])

object FuncProgView {

  val fdsHelp =
    """
      | um, some functional data structures here
      |""".stripMargin

  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Functional Data Structures", fdsHelp,  ListBuffer.empty)
  topics += new Topic("Handling Errors", fdsHelp,  ListBuffer.empty)
  topics += new Topic("Strictness and Laziness", fdsHelp, ListBuffer.empty)

  // there are many exercises per topic, associate via the topic key
  val exercises = ListBuffer.empty[Exercise]
  exercises += new Exercise("Functions")
  exercises += new Exercise("Currying")

  def create(parent: Composite):  Composite ={
    val root = new Composite(parent, SWT.NONE)
    val mainSash = new SashForm(root, SWT.HORIZONTAL)
    mainSash.setLayout(new FillLayout())

    val listBox = createListBox(mainSash)
    val dataBox = createDataBox(mainSash)

    mainSash.setWeights(Array(20, 80))
    root.setLayout(new FillLayout())
    root
  }


  def createListBox(parent: Composite) : Composite = {
    val listBox: Composite = new Composite(parent, SWT.NONE)
    listBox.setLayout(new FillLayout(SWT.VERTICAL))
    val viewer = new TableViewer(listBox)
    val column = new TableViewerColumn(viewer, SWT.NONE)
    column.getColumn().setWidth(100)
    column.getColumn().setText("Title")
    viewer.getTable().setHeaderVisible(true)
    val input: WritableList[Topic] = WritableList.withElementType(classOf[Topic])
    //input.add(new Exercise("Trying"))
    input.addAll(getTopics())
    ViewerSupport.bind(viewer, input, BeanProperties.value(classOf[Topic], "title"))
    listBox
  }

  def createDataBox(parent: Composite): Composite ={
    /*
    form that has:
      label for displaying title
      label for displaying notes
      list of buttons for code
      multiline label for code output
    */
    val dataBox: Composite = new Composite(parent, SWT.NONE)
    dataBox.setLayout(new GridLayout(2, false))
    val labelTitle = new Label(dataBox, SWT.NONE)
    labelTitle.setText("Title")
    GridDataFactory.swtDefaults().grab(true, false).applyTo(labelTitle)
    val labelTitleContent = new Label(dataBox, SWT.NONE)
    GridDataFactory.swtDefaults().grab(true, false).applyTo(labelTitleContent)
    val labelHelpTitle = new Label(dataBox, SWT.NONE)
    labelHelpTitle.setText("Help")
    GridDataFactory.swtDefaults().grab(true, false).applyTo(labelHelpTitle)
    val labelHelpContent = new Label(dataBox, SWT.NONE)
    labelHelpContent.setText("Help Contents")
    GridDataFactory.swtDefaults().grab(true, true).applyTo(labelHelpContent)

   //add a list of exercises right here
   // use master detail model binding

    dataBox
  }

  def getTopics(): util.Collection[Topic] = {
    topics.asJavaCollection
  }

  def getExercisesData(): util.Collection[Exercise] = {

    exercises.asJavaCollection
  }

}
