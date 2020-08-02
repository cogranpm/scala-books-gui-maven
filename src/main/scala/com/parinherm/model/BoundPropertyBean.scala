package com.parinherm.model

import java.beans.{PropertyChangeEvent, PropertyChangeListener, PropertyChangeSupport}


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

