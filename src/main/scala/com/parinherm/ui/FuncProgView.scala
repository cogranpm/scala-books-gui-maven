package com.parinherm.ui

import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.{FillLayout, GridLayout}
import org.eclipse.swt.widgets.{Composite, Label}

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
    listBox.setLayout(new FillLayout())
    val lblList = new Label(listBox, SWT.NONE).setText("List")
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
    val labelHelpTitle = new Label(dataBox, SWT.NONE).setText("Help")
    val labelHelpContent = new Label(dataBox, SWT.NONE).setText("Help Contents")
    dataBox
  }

}
