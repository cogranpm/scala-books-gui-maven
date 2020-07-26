/*
this class is a composite that sets up an outer container
for a list view and an edit view that supply their controls
in seperate classes
this one will respond to selections in the list and
set up bindings on the edit
should use jface model detail data binding and add selection listerner to the list
as examples will show on vogella etc
 */

package com.parinherm.ui


import org.eclipse.swt.widgets.Composite

/*
import com.parinherm.kernai.data.entity.ReferenceItem
import com.parinherm.kernai.databinding.LocalDateConverter
import com.parinherm.kernai.ui.viewModel.ReferenceItemViewModel
import org.eclipse.core.databinding.DataBindingContext
import org.eclipse.core.databinding.UpdateValueStrategy
//import org.eclipse.core.databinding.beans.PojoProperties
import org.eclipse.core.databinding.beans.typed.PojoProperties
import org.eclipse.core.databinding.conversion.IConverter
import org.eclipse.core.databinding.conversion.StringToNumberConverter
import org.eclipse.e4.core.di.annotations.Optional
import org.eclipse.e4.ui.di.Focus
import org.eclipse.e4.ui.di.Persist
import org.eclipse.e4.ui.model.application.ui.basic.MPart
import org.eclipse.e4.ui.services.IServiceConstants
import org.eclipse.jface.databinding.swt.typed.WidgetProperties
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.DateTime
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text
import java.time.LocalDate
import java.time.LocalDateTime
import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.inject.Named
import org.eclipse.core.databinding.conversion.Converter
import com.parinherm.kernai.databinding.DateTimeSelectionProperty
import org.eclipse.core.databinding.observable.value.IObservableValue
import org.eclipse.core.databinding.AggregateValidationStatus
import org.eclipse.core.databinding.Binding
import java.time.LocalTime
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Spinner
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport
import org.eclipse.core.databinding.conversion.NumberToStringConverter
import org.eclipse.core.databinding.validation.ValidationStatus

import kotlin.text.*
import org.eclipse.jface.viewers.ComboViewer
import org.eclipse.swt.widgets.Combo
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.LabelProvider
import com.parinherm.kernai.data.entity.LookupDetail
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider
import org.eclipse.jface.viewers.Viewer
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties
 */


class DataBindingView (parent: Composite, style: Int) extends Composite (parent, style) {

  //add all the view stuff here, labels and so on


  /*


		val target_body = WidgetProperties.text<Text>(SWT.Modify).observe(txtBody)
		val model_body = PojoProperties.value<ReferenceItem, String>("body").observeDetail<ReferenceItem>(model.selectedItem)

		//this is from the vogella tutorial on how to bind to java 8 LocalDate
		val dateTimeSelectionProperty: DateTimeSelectionProperty<DateTime, Any> = DateTimeSelectionProperty()
		val dateTimeObservableValue = dateTimeSelectionProperty.observe(dteCreated)
		val model_created = PojoProperties.value<ReferenceItem, LocalDate>("createdDate").observeDetail<ReferenceItem>(model.selectedItem)

		val timeProp: DateTimeSelectionProperty<DateTime, Any> = DateTimeSelectionProperty()
		val timeOb = timeProp.observe(dteCreatedTime)
		val model_time = PojoProperties.value<ReferenceItem, LocalTime>("createdTime").observeDetail<ReferenceItem>(model.selectedItem)


		val propBool = WidgetProperties.buttonSelection().observe(btnBool)
		val modelBool = PojoProperties.value<ReferenceItem, Boolean>("testBool").observeDetail<ReferenceItem>(model.selectedItem)

		val propInt = WidgetProperties.spinnerSelection().observe(spinInt)
		val modelInt = PojoProperties.value<ReferenceItem, Int>("testInt") .observeDetail<ReferenceItem>(model.selectedItem)

		val propLong = WidgetProperties.text<Text>(SWT.Modify).observe(txtLong)
		val modelLong = PojoProperties.value<ReferenceItem, Long>("testLong").observeDetail<ReferenceItem>(model.selectedItem)

		val propCombo = WidgetProperties.comboSelection().observe(cbvCombo.getCombo())
		val modelCombo = PojoProperties.value<ReferenceItem, String>("testCombo").observeDetail(model.selectedItem)


		val bindBody = ctx.bindValue(target_body, model_body)
		ctx.bindValue(dateTimeObservableValue, model_created)
		ctx.bindValue(timeOb, model_time)
		ctx.bindValue(propBool, modelBool)
		ctx.bindValue(propInt, modelInt)
		ctx.bindValue(propCombo, modelCombo)



		//long integer binding with validation and conversion
		//the regex allows leading sign symbol and comma's as the
		//StringToNumberConverter seems to want to put in commas
		val longConverter = StringToNumberConverter.toLong(true)
		val longConverterBack = NumberToStringConverter.fromLong(true)
		val longUpdate = UpdateValueStrategy.create(longConverter)
		longUpdate.setAfterGetValidator(
			{x ->
				val entry = x as String
				val regex = "^[+-]?(\\d+(,\\d{3})*)$".toRegex()
				if(regex.matches(entry)){
					ValidationStatus.ok()
				}else {
					ValidationStatus.error("Invalid number entered")
				}
			})

		val longUpdateBack = UpdateValueStrategy.create(longConverterBack)
		val bindLong = ctx.bindValue(propLong, modelLong, longUpdate, longUpdateBack)

		ControlDecorationSupport.create(bindLong, SWT.TOP or SWT.LEFT)

				// set a dirty state if one of the bindings is changed
		ctx.getBindings().forEach{ item ->
			var binding =  item
			binding.getTarget().addChangeListener{e ->
				if(!pauseDirtyListener) {
					part.setDirty(true)
				}
			}
		}


		val  errorObservable: IObservableValue<String> = WidgetProperties.text<Label>().observe(lblError)
		val allValidationBinding: Binding = ctx.bindValue(errorObservable, AggregateValidationStatus(ctx.getBindings(), AggregateValidationStatus.MAX_SEVERITY), null, null)



   */

}
