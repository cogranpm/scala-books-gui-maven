package com.parinherm.ui

import java.io.IOException
import java.nio.file.{Files, Path, Paths}
import java.util.Base64

import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter
import org.eclipse.swt.layout.{GridLayout, RowLayout}
import org.eclipse.swt.widgets.{Button, Composite, Text}


object BrowserTest {

  import org.eclipse.swt.browser.{Browser, ProgressEvent, ProgressListener}
  //val newOne = "https://prekitt.lwtears.com/books/TGPKGSS1/2021/2"
  var browser: Browser = null
  var txtUrl: Text = null
  var buttonNext: Button = null
  var buttonRun: Button = null
  var iteration: Int = 0
  var folderName: String = "lwt-mybook"
  //val baseURL = "https://prekitt.lwtears.com/books/TGPKGSS1/2021"
  //val baseURL = "https://prekitt.lwtears.com/books/MFSB/2021"
  //val baseURL = "https://prekitt.lwtears.com/books/MFLB/2021"
  //val baseURL = "https://prekitt.lwtears.com/books/IKMN/2021"
  //val baseURL = "https://prekitt.lwtears.com/books/TGPKGSS/2021"
  val baseURL = "https://prekitt.lwtears.com/books/MB/2021"

  def processImages(current_iteration: Int): Unit = {
    /* runs a script within the browser page that is loaded in the browser control
    script will locate call canvas elements - which contain an image
    and will use the toDataURL method to get the binary data of the image within
    strip out the header text then return then in an array to the scala client code
    hosting the browser control

    the scala code will decode the data and save to a file
    using an index notation for the file names
    */
    val script =
      """ function getData() {
        | let items = document.querySelectorAll('canvas');
        | let finishedItems = [];
        | //let data = item[0].toDataURL('image/png').replace("image/png", "image/octet-stream");
        | for (let i = 0; i < items.length; i++) {
        |   let item = items[i];
        |   let itemData = item.toDataURL('image/png');
        |   let strippedItemData = itemData.replace(/^data:image\/(png|jpg);base64,/, "");
        |   finishedItems.push(strippedItemData);
        | }
        | return finishedItems;
        | }
        |
        | return getData();""".stripMargin
    val result: Array[Object] = browser.evaluate(script).asInstanceOf[Array[Object]]
    var index = 0
    for (item <- result){
      val imageData = Base64.getDecoder.decode(item.toString)
      val pageNo = index + current_iteration
      val imagePath: Path = Paths.get(s"${folderName}/page_${pageNo}.png")
      try{
        Files.write(imagePath,  imageData)
      } catch {
        case e: IOException => println(e.getMessage)
      }
      index += 1


    }
  }

  def create(parent: Composite) : Composite = {
    val composite = new Composite(parent, SWT.NONE)
    val buttonBar = new Composite(composite, SWT.NONE)
    val buttonLoad = new Button(buttonBar, SWT.PUSH)
    buttonLoad.setText("Load")

    txtUrl = new Text(buttonBar, SWT.SINGLE)
    txtUrl.setText(baseURL)

    buttonRun = new Button(buttonBar, SWT.PUSH)
    buttonRun.setText("&Run")
    buttonRun.setEnabled(false)

    buttonNext = new Button(buttonBar, SWT.PUSH)
    buttonNext.setText("Next")
    buttonRun.setEnabled(false)


    browser = new Browser(composite, SWT.NONE)
    browser.setJavascriptEnabled(true)
    //browser.setUrl("https://www.lwtears.com/mylwt")
    //browser.setUrl("https://prekitt.lwtears.com/books/TGPKGSS1/2021")

    browser.addProgressListener(new ProgressListener() {
      override def completed(event: ProgressEvent): Unit = {
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
        println(s"about to load ${txtUrl.getText()}")
        browser.setUrl(txtUrl.getText())
      }))

    buttonNext.addSelectionListener(widgetSelectedAdapter(
      (e: SelectionEvent) =>
      {
        txtUrl.setText(s"${baseURL}/${iteration}")
        println(s"about to load ${txtUrl.getText()}")
        browser.setUrl(txtUrl.getText())
      }))

    // run button will process images in current page then load the next page
    buttonRun.addSelectionListener(widgetSelectedAdapter(
      (e: SelectionEvent) =>
      {
        buttonRun.setEnabled(false)
        processImages(iteration)
        iteration += 2
        //load up next page
        txtUrl.setText(s"${baseURL}/${iteration}")
        println(s"about to load ${txtUrl.getText()}")
        browser.setUrl(txtUrl.getText())
      }
    ))

    composite
  }
}

