import scala._
import org.eclipse.core.databinding.observable.Realm
import org.eclipse.jface.action.Action
import org.eclipse.jface.action.ActionContributionItem
import org.eclipse.jface.action.IAction
import org.eclipse.jface.action.MenuManager
import org.eclipse.jface.action.Separator
import org.eclipse.jface.action.StatusLineManager
import org.eclipse.jface.action.ToolBarManager
import org.eclipse.jface.window.ApplicationWindow
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.CTabFolder
import org.eclipse.swt.custom.CTabItem
import org.eclipse.swt.events.{SelectionAdapter, SelectionEvent, SelectionListener}
import org.eclipse.swt.events.SelectionListener._
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.graphics.Point
import org.eclipse.swt.layout.{FillLayout, GridData, GridLayout, RowLayout}
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Event
import org.eclipse.swt.widgets.Listener
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.ToolBar
import org.eclipse.swt.widgets.Text
import org.eclipse.swt.graphics.Point
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.widgets.Button
import org.eclipse.nebula.widgets.pshelf._
import DbFunctions._
import DBTests._
import com.parinherm.model.{Chapter2Document, ReferenceDoc, ScalableLanguageDocument, FuncProgScala}
import com.parinherm.ui.ReferenceDocView
import java.util.Base64



class MainWindow extends ApplicationWindow(null){

  addToolBar(SWT.FLAT | SWT.WRAP)
  addMenuBar()
  addStatusLine()

  var mainContainer: Composite = null
  var navReference:PShelfItem = null
  var navFuncProg: PShelfItem = null
  var folder: CTabFolder = null

  override def createContents(parent: Composite): Control = {
    val container = new Composite(parent, SWT.NONE)
    container.setLayout(new FillLayout)

    val sashForm: SashForm = new SashForm(container, SWT.HORIZONTAL)
    val weights: Array[Int] = Array[Int](1, 4)
    val navContainer: Composite = new Composite(sashForm, SWT.NONE)
    mainContainer = new Composite(sashForm, SWT.NONE)
    //this must be set under the code that assigns both side to sash form
    sashForm.setWeights(weights)

    navContainer.setLayout(new FillLayout(SWT.VERTICAL))
    mainContainer.setLayout(new FillLayout(SWT.VERTICAL))

    val navShelf: PShelf = new PShelf(navContainer, SWT.NONE)
    navShelf.setRenderer(new RedmondShelfRenderer)
    navReference = new PShelfItem(navShelf, SWT.NONE)

    navReference.setText("Reference")
    navReference.getBody.setLayout(new RowLayout(SWT.VERTICAL))
    createReferenceButtons()


    navFuncProg = new PShelfItem(navShelf, SWT.NONE)
    navFuncProg.setText("Functional Programming in Scala")
    navFuncProg.getBody.setLayout(new FillLayout(SWT.VERTICAL))


    folder = new CTabFolder(mainContainer, SWT.TOP | SWT.BORDER)
    val item = new CTabItem(folder, SWT.NONE)
    item.setText("&Getting Started")
    val masterPropertyTabItem = new CTabItem(folder, SWT.NONE)
    masterPropertyTabItem.setText("&Master Properties")
    container
  }

  private def clearComposite(composite: Composite) = {
    for (control <- composite.getChildren) {
      control.dispose
    }
    composite
  }


  override def createMenuManager(): MenuManager = {
    val win = this
    val menuManager = new MenuManager("Menu")
    val fileMenu = new MenuManager("&File")

    val quitAction = new Action("Quit") {
      override def run(): Unit = win.close()
    }
    quitAction.setAccelerator(SWT.MOD1 | 'Q')

    fileMenu.add(new Separator())
    fileMenu.add(quitAction)
    menuManager.add(fileMenu)
    menuManager
  }

  override def createToolBarManager(style: Int): ToolBarManager = {
    val toolBarManager = new ToolBarManager(SWT.NONE);
    toolBarManager.update(true)
    toolBarManager
  }

  override def createStatusLineManager(): StatusLineManager = new StatusLineManager

  override def configureShell(shell: Shell): Unit = {
    super.configureShell(shell)
    shell.setText("Kernai")

  }

  override def getInitialSize: Point = new Point(900, 900)

  def addReferenceButton(name: String, handler: SelectionListener): Unit = {
    val btnChapter1: Button = new Button(navReference.getBody, SWT.PUSH)
    btnChapter1.setText(name)
    btnChapter1.addSelectionListener(handler)
  }

  def addReferenceTab(name: String, referenceDoc: ReferenceDoc): Unit = {
    val docView: ReferenceDocView = new ReferenceDocView(folder, SWT.BORDER, referenceDoc)
    val tabItem = new CTabItem(folder, SWT.NONE)
    tabItem.setText(name)
    tabItem.setControl(docView)
    mainContainer.layout()
    folder.setSelection(tabItem)
    referenceDoc.run()
  }

  def addTab(name: String, view: Composite): Unit = {
    val tabItem = new CTabItem(folder, SWT.NONE)
    tabItem.setText(name)
    tabItem.setControl(view)
    mainContainer.layout()
    folder.setSelection(tabItem)
  }

  def createReferenceButtons(): Unit =
  {
    try {
      //this is a database test
      //DbFunctions.runQuery()
      //DbFunctions.insertBook("Neophytes guide to scala")
      //DbFunctions.close()

    } catch  {
      case ex: Exception => {
        println("error")
      }

    }

    val chapter1Desc = "Chapter 1"
    val handler: SelectionListener = widgetSelectedAdapter(
      (e: SelectionEvent) =>
      {
        val scalableLanguageDocument = new ScalableLanguageDocument("Chapter1.doc")
        addReferenceTab(chapter1Desc, scalableLanguageDocument)
      })

    addReferenceButton(chapter1Desc, handler)

    val chapter2Desc = "Chapter 2"
    val chapter2Handler = widgetSelectedAdapter (
      (e: SelectionEvent) =>
      {
        val chap2Doc = new Chapter2Document("Chapter2.doc")
        addReferenceTab(chapter2Desc, chap2Doc)
      })
    addReferenceButton(chapter2Desc, chapter2Handler)


    val funcScalaDesc = "Functional Prog with Scala"
    val funcScala = widgetSelectedAdapter (
      (e: SelectionEvent) =>
      {
        val funcScalaDoc = new FuncProgScala("FuncProgScala.doc")
        addReferenceTab(funcScalaDesc, funcScalaDoc)
      })
    addReferenceButton(funcScalaDesc, funcScala)

    val browserDesc = "Browser Test"
    val browserHandler = widgetSelectedAdapter(
      (e: SelectionEvent) =>
        {
          val browserView = BrowserTest.create(folder)
          addTab("Browser", browserView)
        }
    )
    addReferenceButton(browserDesc, browserHandler)
}



}


object FuncProg{

}

object BrowserTest {

  import org.eclipse.swt.browser.{Browser, ProgressEvent, ProgressListener}
  val newOne = "https://prekitt.lwtears.com/books/TGPKGSS1/2021/2"

  def create(parent: Composite) : Composite = {
    val composite = new Composite(parent, SWT.NONE)
    val buttonBar = new Composite(composite, SWT.NONE)
    val buttonLoad = new Button(buttonBar, SWT.PUSH)
    buttonLoad.setText("Load")

    val buttonRun = new Button(buttonBar, SWT.PUSH)
    buttonRun.setText("Run")
    buttonRun.setEnabled(false)


    val browser = new Browser(composite, SWT.NONE)
    browser.setJavascriptEnabled(true)
    //browser.setUrl("https://www.lwtears.com/mylwt")
    browser.setUrl("https://prekitt.lwtears.com/books/TGPKGSS1/2021")

    browser.addProgressListener(new ProgressListener() {
      override def completed(event: ProgressEvent): Unit = {
        println("completed")
        buttonRun.setEnabled(true)
      }

      override def changed(event: ProgressEvent): Unit = println("changed")

    })

    buttonBar.setLayout(new RowLayout())
    composite.setLayout(new GridLayout(1, false))
    GridDataFactory.fillDefaults.grab(true, false).applyTo(buttonBar)
    GridDataFactory.fillDefaults.grab(true, true).applyTo(browser)

    buttonLoad.addSelectionListener(widgetSelectedAdapter(
      (e: SelectionEvent) =>
      {
        buttonRun.setEnabled(false)
        browser.setUrl(newOne)
      }))

    buttonRun.addSelectionListener(widgetSelectedAdapter(
      (e: SelectionEvent) =>
        {
          val script =
            """ function getData() {
              | let item = document.querySelectorAll('canvas');
              | return item[0].toDataURL('image/png').replace("image/png", "image/octet-stream");
              | }
              |
              | //item.style.backgroundColor = 'green';
              | return getData();""".stripMargin
           val result = browser.evaluate(script).toString()
          println(result)
          // must remove the header bit first
          //val imageData = Base64.getDecoder.decode(result)

        }
    ))

    composite
  }
}


object KernaiApp extends App {

  import org.eclipse.core.databinding.observable.Realm
  import org.eclipse.jface.databinding.swt.DisplayRealm
  import org.eclipse.swt.widgets.Display

  val display: Display = Display.getDefault

  Realm.runWithDefault(DisplayRealm.getRealm(display), new Runnable() {
    override def run(): Unit = {
      try {
        val window = new MainWindow
        window.setBlockOnOpen(true)
        window.open
        Display.getCurrent.dispose()
      } catch {
        case e: Exception =>
          e.printStackTrace()
      }
    }
  })
}
