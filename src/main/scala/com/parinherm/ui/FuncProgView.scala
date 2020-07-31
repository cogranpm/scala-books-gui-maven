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
import org.eclipse.jface.viewers.{TableViewer, TableViewerColumn}
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.{FillLayout, GridLayout}
import org.eclipse.swt.widgets.{Composite, Label, Text}

import scala.beans.BeanProperty
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

class Exercise (@BeanProperty var title: String)

class Topic (var title: String,
             var help: String,
             var exercises: ListBuffer[Exercise])
extends Object with BoundPropertyBean{

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

  var viewer: TableViewer = null

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
    dataBox.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create())
    val labelTitle = new Label(dataBox, SWT.NONE)
    labelTitle.setText("Title")
    GridDataFactory.swtDefaults().grab(false, false).applyTo(labelTitle)

    val textTitleContent = new Text(dataBox, SWT.BORDER | SWT.READ_ONLY)
    GridDataFactory.swtDefaults().grab(true, false).applyTo(textTitleContent)
    //val labelTitleContent = new Label(dataBox, SWT.NONE)
    //labelTitleContent.setText("test")
    //GridDataFactory.swtDefaults().grab(true, false).applyTo(labelTitleContent)

    val labelHelpTitle = new Label(dataBox, SWT.NONE)
    labelHelpTitle.setText("Help")
    GridDataFactory.swtDefaults().grab(false, false).applyTo(labelHelpTitle)
    val labelHelpContent = new Label(dataBox, SWT.NONE)
    labelHelpContent.setText("Help Contents")
    GridDataFactory.swtDefaults().grab(true, true).applyTo(labelHelpContent)

   //add a list of exercises right here
   // use master detail model binding

    val dbc = new DataBindingContext()
    val target = WidgetProperties.text(SWT.Modify).observe(textTitleContent)
    val selectedItem = ViewerProperties.singleSelection[TableViewer, Topic]().observe(viewer)
    val detailValue = BeanProperties.value("title").observeDetail(selectedItem)
    import org.eclipse.core.databinding.UpdateValueStrategy
    val widgetToModel = new UpdateValueStrategy[String, Nothing](UpdateValueStrategy.POLICY_NEVER)
    val modelToWidget = new UpdateValueStrategy[Nothing, String](UpdateValueStrategy.POLICY_UPDATE)
    dbc.bindValue(target, detailValue, widgetToModel, modelToWidget)


    dataBox
  }

  def getTopics(): util.Collection[Topic] = {
    topics.asJavaCollection
  }

  def getExercisesData(): util.Collection[Exercise] = {

    exercises.asJavaCollection
  }

}
