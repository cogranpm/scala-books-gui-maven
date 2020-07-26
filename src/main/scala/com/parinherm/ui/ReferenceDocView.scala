package com.parinherm.ui

import com.parinherm.model.ReferenceDoc
import org.eclipse.jface.text.source.AnnotationModel
import org.eclipse.jface.text.source.CompositeRuler
import org.eclipse.jface.text.source.LineNumberRulerColumn
import org.eclipse.jface.text.source.OverviewRuler
import org.eclipse.jface.text.source.SourceViewer
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.jface.text.source.SourceViewerConfiguration


class ReferenceDocView  (parent: Composite, style: Int, val document: ReferenceDoc) extends Composite (parent, style) {

  setLayout(new FillLayout(SWT.VERTICAL))

  // val document: IDocument = new Document()
  val VERTICAL_RULER_WIDTH = 12
  val overviewRuler = new OverviewRuler(null, VERTICAL_RULER_WIDTH, null)
  val ruler = new CompositeRuler(VERTICAL_RULER_WIDTH)
  val annotationModel = new AnnotationModel
  annotationModel.connect(document.getDocument())
  val txtBody = new SourceViewer(this, ruler, overviewRuler, false, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL)
  txtBody.configure(new SourceViewerConfiguration)
  txtBody.setDocument(document.getDocument(), annotationModel)
  ruler.addDecorator(0, new LineNumberRulerColumn)

  this.addDisposeListener( (e) => document.save())
}