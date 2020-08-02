package com.parinherm.model

import scala.beans.BeanProperty
import scala.collection.mutable.ListBuffer

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
