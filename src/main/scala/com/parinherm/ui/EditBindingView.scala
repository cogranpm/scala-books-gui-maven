/*
example of model data binding in jface, is the editing portion
will be mixed with a view portion as well
need to use list selection listener on the list control
parent composite should probably set it up
this class will just create the editing controls and make them
public or package private so parent composite set up the binding
 */

package com.parinherm.ui

class EditBindingView {


  /*
  	private lateinit var txtBody: Text
	private lateinit var dteCreated: DateTime
	private lateinit var dteCreatedTime: DateTime
	private lateinit var btnBool: Button
	private lateinit var spinInt: Spinner
	private lateinit var txtLong: Text
	private lateinit var cbvCombo: ComboViewer
		private lateinit var lblError: Label

	val model = ReferenceItemViewModel()
	val ctx = DataBindingContext()
	var pauseDirtyListener: Boolean = false

		setLayout(GridLayout(2, false))


		val lblBody = Label(parent, SWT.NONE)
		lblBody.text = "Body"
		txtBody = Text(parent, SWT.BORDER)
		txtBody.setMessage("Enter text to mark part as dirty.")
		//txtBody.addModifyListener({ _ -> part.setDirty(true) })
		txtBody.setLayoutData(GridData(GridData.FILL_HORIZONTAL))

		val lblDateCreated = Label(parent, SWT.NONE)
		lblDateCreated.text = "Created"
		dteCreated = DateTime(parent, SWT.DROP_DOWN or SWT.DATE)

		val lblTimeCreated = Label(parent, SWT.NONE)
		lblTimeCreated.text = "Time"
		dteCreatedTime= DateTime(parent, SWT.DROP_DOWN or SWT.TIME)

		var lblBool = Label(parent, SWT.NONE)
		lblBool.text = "Boolean"
		btnBool = Button(parent, SWT.CHECK)

		var lblInt = Label(parent, SWT.NONE)
		lblInt.text = "Integer"
		spinInt = Spinner(parent, SWT.NONE)
		spinInt.setMinimum(Int.MIN_VALUE)
		spinInt.setMaximum(Int.MAX_VALUE)

		var lblLong = Label(parent, SWT.NONE)
		lblLong.text = "Long"
		txtLong = Text(parent, SWT.SINGLE)
		txtLong.setLayoutData(GridData(GridData.FILL_HORIZONTAL))


		//combo box with lookup data
		var lblCombo = Label(parent, SWT.NONE)
		lblCombo.text = "Combo"
		cbvCombo = ComboViewer(parent, SWT.NONE)
		//cbvCombo.setContentProvider(ObservableListContentProvider<LookupDetail>())
		cbvCombo.setContentProvider(ArrayContentProvider.getInstance())
		cbvCombo.setLabelProvider(object: LabelProvider() {
			override fun getText(element: Any): String {
				val item: LookupDetail = element as LookupDetail
				return item.label
			}
		})
		//note: a LookupDetail entity is stored in the combo
		//can get it from a selection listener
		cbvCombo.setInput(model.comboLookups)

		lblError = Label(parent, SWT.NONE)
		val errLayout = GridData(GridData.FILL_HORIZONTAL);
		errLayout.horizontalSpan = 2
		lblError.setLayoutData(errLayout)
   */

}
