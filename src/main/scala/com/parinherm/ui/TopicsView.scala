package com.parinherm.ui

import java.util

import com.parinherm.model.{Exercise, Topic}
import com.parinherm.model.FuncProgView.{exercises, topics}
import org.eclipse.core.databinding.{DataBindingContext, UpdateValueStrategy}
import org.eclipse.core.databinding.beans.typed.BeanProperties
import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.jface.databinding.swt.typed.WidgetProperties
import org.eclipse.jface.databinding.viewers.ViewerSupport
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties
import org.eclipse.jface.layout.{GridDataFactory, GridLayoutFactory}
import org.eclipse.jface.viewers.{ISelectionChangedListener, ListViewer, SelectionChangedEvent, TableViewer, TableViewerColumn}
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.{Composite, Control, Label, Text}

import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

object TopicsView {

  var topics: ListBuffer[Topic] = null
  var viewer: TableViewer = null
  var exerciseViewer: ListViewer = null
  var labelOutput: Text = null

  val dbc = new DataBindingContext()

  def create(parent: Composite, topics: ListBuffer[Topic]):  Composite ={
    this.topics = topics
    val root = new Composite(parent, SWT.NONE)
    val mainSash = new SashForm(root, SWT.HORIZONTAL)
    mainSash.setLayout(new FillLayout())

    val listBox = createListBox(mainSash)
    val dataBox = createDataBox(mainSash)

    mainSash.setWeights(Array(20, 80))
    root.setLayout(new FillLayout())
    root
  }


  def createReadOnlyLabel(parent: Composite) = new Text(parent, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI)

  def createBox(parent: Composite) = {
    val box = new Composite(parent, SWT.BORDER)
    box.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create())
    box
  }

  def createWidgetBinding(widget: Control, propertyName: String) = {
    val target = WidgetProperties.text(SWT.Modify).observe(widget)
    val selectedItem = ViewerProperties.singleSelection[TableViewer, Topic]().observe(viewer)
    val detailValue = BeanProperties.value(propertyName).observeDetail(selectedItem)

    val widgetToModel = new UpdateValueStrategy[String, Nothing](UpdateValueStrategy.POLICY_NEVER)
    val modelToWidget = new UpdateValueStrategy[Nothing, String](UpdateValueStrategy.POLICY_UPDATE)
    dbc.bindValue(target, detailValue, widgetToModel, modelToWidget)
  }

  def bindExercises(topic: Topic) = {
    val input: WritableList[Exercise] = WritableList.withElementType(classOf[Exercise])
    if (!topic.exercises.isEmpty) {
      input.addAll(topic.exercises.asJavaCollection)
    }
    ViewerSupport.bind(exerciseViewer, input, BeanProperties.value(classOf[Exercise], "title"))

  }



  def createListBox(parent: Composite) : Composite = {
    val listBox: Composite = new Composite(parent, SWT.NONE)
    listBox.setLayout(new FillLayout(SWT.VERTICAL))
    viewer = new TableViewer(listBox)
    val column = new TableViewerColumn(viewer, SWT.NONE)
    column.getColumn().setWidth(100)
    column.getColumn().setText("Title")
    viewer.getTable().setHeaderVisible(true)
    val input: WritableList[Topic] = WritableList.withElementType(classOf[Topic])
    //input.add(new Exercise("Trying"))
    input.addAll(getTopics())
    ViewerSupport.bind(viewer, input, BeanProperties.value(classOf[Topic], "title"))


    viewer.addSelectionChangedListener(new ISelectionChangedListener {
      override def selectionChanged(selectionChangedEvent: SelectionChangedEvent): Unit = {
        if (!viewer.getStructuredSelection.isEmpty) {
          val selections = viewer.getStructuredSelection
          val firstElement = selections.getFirstElement.asInstanceOf[Topic]
          bindExercises(firstElement)
        }
      }
    })

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
    //dataBox.setBackground(Display.getCurrent.getSystemColor(SWT.COLOR_BLUE))
    dataBox.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create())
    val labelTitle = new Label(dataBox, SWT.NONE)
    labelTitle.setText("Title")
    GridDataFactory.fillDefaults().grab(false, false).applyTo(labelTitle)

    val textTitleContent = createReadOnlyLabel(dataBox)
    GridDataFactory.fillDefaults().grab(true, false).applyTo(textTitleContent)

    val labelHelpTitle = new Label(dataBox, SWT.NONE)
    labelHelpTitle.setText("Help")
    GridDataFactory.fillDefaults().grab(false, false).applyTo(labelHelpTitle)

    val labelHelpContent = createReadOnlyLabel(dataBox)
    labelHelpContent.setText("Help Contents")
    GridDataFactory.fillDefaults().grab(true, true).applyTo(labelHelpContent)

    /* a box containing exercises stuff */
    val exerciseBox = createBox(dataBox)
    GridDataFactory.fillDefaults().span(2, 1).grab(true, true).applyTo(exerciseBox)

    val labelExercise = new Label(exerciseBox, SWT.NONE)
    labelExercise.setText("Exercises")
    GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(labelExercise)

    exerciseViewer = new ListViewer(exerciseBox)
    GridDataFactory.fillDefaults().grab(false, true).hint(350, SWT.DEFAULT).applyTo(exerciseViewer.getList)
    exerciseViewer.addSelectionChangedListener(new ISelectionChangedListener {
      override def selectionChanged(selectionChangedEvent: SelectionChangedEvent): Unit = {
        //run the code of this thing
        val selections = exerciseViewer.getStructuredSelection
        val selectedExercise = selections.getFirstElement.asInstanceOf[Exercise]
        labelOutput.setText(selectedExercise.func.apply)

      }
    })

    labelOutput = createReadOnlyLabel(exerciseBox)
    GridDataFactory.fillDefaults().grab(true, true).applyTo(labelOutput)


    createWidgetBinding(textTitleContent, "title")
    createWidgetBinding(labelHelpContent, "help")

    dataBox
  }

  def getTopics(): util.Collection[Topic] = {
    this.topics.asJavaCollection
  }



}
