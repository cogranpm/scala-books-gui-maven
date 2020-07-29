package com.parinherm.ui

import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.{Composite, Label}

object FuncProgView {

  def create(parent: Composite):  Composite ={
    val root = new Composite(parent, SWT.NONE)
    val mainSash = new SashForm(root, SWT.HORIZONTAL)
    mainSash.setLayout(new FillLayout())
    val listBox: Composite = new Composite(mainSash, SWT.NONE)
    listBox.setLayout(new FillLayout())
    val lblList = new Label(listBox, SWT.NONE).setText("List")
    val dataBox: Composite = new Composite(mainSash, SWT.NONE)
    dataBox.setLayout(new FillLayout())
    val lblData = new Label(dataBox, SWT.NONE).setText("Data")
    mainSash.setWeights(Array(20, 80))
    root.setLayout(new FillLayout())
    root
  }

}
