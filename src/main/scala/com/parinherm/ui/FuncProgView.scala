package com.parinherm.ui

import java.beans.{PropertyChangeEvent, PropertyChangeListener, PropertyChangeSupport}
import java.util

import org.eclipse.core.databinding.DataBindingContext
import org.eclipse.core.databinding.beans.IBeanValueProperty
import org.eclipse.core.databinding.beans.typed.{BeanProperties, PojoProperties}
import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.core.databinding.property.value.IValueProperty
import org.eclipse.jface.databinding.swt.typed.WidgetProperties
import org.eclipse.jface.databinding.viewers.ViewerSupport
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties
import org.eclipse.jface.layout.{GridDataFactory, GridLayoutFactory}
import org.eclipse.jface.viewers.{ISelectionChangedListener, ListViewer, SelectionChangedEvent, TableViewer, TableViewerColumn}
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.graphics.Color
import org.eclipse.swt.layout.{FillLayout, GridLayout}
import org.eclipse.swt.widgets.{Composite, Control, Display, Label, Text}

import scala.beans.BeanProperty
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._
import org.eclipse.core.databinding.UpdateValueStrategy



trait BoundPropertyBean
{

  val pcs = new PropertyChangeSupport(this)

  def addPropertyChangeListener(pcl : PropertyChangeListener) =
    pcs.addPropertyChangeListener(pcl)

  def removePropertyChangeListener(pcl : PropertyChangeListener) =
    pcs.removePropertyChangeListener(pcl)

  def firePropertyChange(name : String, oldVal : Any, newVal : Any) : Unit =
    pcs.firePropertyChange(new PropertyChangeEvent(this, name, oldVal, newVal))
}

class Exercise (@BeanProperty var title: String, val func: () => String)
extends Object with BoundPropertyBean

// mucking about with databinding
// this is readonly display, so no propertychange fire required on set
// therefore use @BeanProperty to autogenerate default setters
class Topic (@BeanProperty val title: String,
             @BeanProperty val help: String,
             @BeanProperty val exercises: ListBuffer[Exercise])
extends Object with BoundPropertyBean
/*{

  def setTitle(newValue: String) = {
    val oldValue = title
    title = newValue
    firePropertyChange("title", oldValue, newValue)
  }

  def getTitle(): String = title

}

object PCL
  extends java.beans.PropertyChangeListener
{
  override def propertyChange(pce:java.beans.PropertyChangeEvent):Unit =
  {
    System.out.println("Bean changed its " + pce.getPropertyName() +
      " from " + pce.getOldValue() +
      " to " + pce.getNewValue())
  }
}
*/



object FuncProgView {


  // creating some data in code here
  // maybe move to database later
  val fdsHelp =
    """
      | um, some functional data structures here
      |""".stripMargin

  val functionsHelp =
    """
      |um, functions
      |""".stripMargin

  val curryHelp =
    """
      | um, currying
      |""".stripMargin

  // there are many exercises per topic, associate via the topic key
  val exercises = ListBuffer.empty[Exercise]
  val testFunction: () => String = ()  => "running a function"
  val differentFunction: () => String = () => "running a diffferent function"
  exercises += new Exercise("Functions",  testFunction)
  exercises += new Exercise("Currying", differentFunction)


  val topics = ListBuffer.empty[Topic]
  topics += new Topic("Functional Data Structures", fdsHelp, exercises)
  topics += new Topic("Handling Errors", curryHelp,  ListBuffer.empty)
  topics += new Topic("Strictness and Laziness", functionsHelp, ListBuffer.empty)


  var viewer: TableViewer = null
  var exerciseViewer: ListViewer = null
  var labelOutput: Text = null

  val dbc = new DataBindingContext()

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
    input.addAll(topic.exercises.asJavaCollection)
    ViewerSupport.bind(exerciseViewer, input, BeanProperties.value(classOf[Exercise], "title"))

  }

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
          val selections = viewer.getStructuredSelection
          val firstElement = selections.getFirstElement.asInstanceOf[Topic]
          bindExercises(firstElement)
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
    GridDataFactory.fillDefaults().grab(false, true).applyTo(exerciseViewer.getList)
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
    topics.asJavaCollection
  }

  def getExercisesData(): util.Collection[Exercise] = {

    exercises.asJavaCollection
  }

}
