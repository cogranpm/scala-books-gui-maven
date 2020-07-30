package com.parinherm.ui

import org.eclipse.core.databinding.beans.typed.BeanProperties
import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.jface.databinding.viewers.ViewerSupport
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.jface.viewers.{TableViewer, TableViewerColumn}
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.{FillLayout, GridLayout}
import org.eclipse.swt.widgets.{Composite, Label}

import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

class Exercise (title: String)

object FuncProgView {

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
    val lblList = new Label(listBox, SWT.NONE).setText("List")
    val viewer = new TableViewer(listBox)
    val column = new TableViewerColumn(viewer, SWT.NONE)
    column.getColumn().setWidth(100)
    column.getColumn().setText("Title")
    viewer.getTable().setHeaderVisible(true)
    val input: WritableList[Exercise] = WritableList.withElementType(classOf[Exercise])
    //input.addAll(getExercisesData())
    input.add(new Exercise("Trying"))
    //val input = new WritableList[Exercise](getExercisesData(), classOf[Exercise])
    //ViewerSupport.bind[Object](viewer, input, BeanProperties.values("Title"))
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
    dataBox
  }

  def getExercisesData(): ListBuffer[Exercise] ={
    val exercises = ListBuffer.empty[Exercise]
    exercises += new Exercise("Functions")
    exercises
  }

}
