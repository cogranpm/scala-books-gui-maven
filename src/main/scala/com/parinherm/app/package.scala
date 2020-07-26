package com.parinherm

import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.resource.ImageRegistry
import org.eclipse.swt.graphics.Image


package object app {

  val imageRegistry: ImageRegistry = new ImageRegistry()
  val IMAGE_ACTVITY_SMALL = "activitysmall"
  val IMAGE_ACTIVITY_LARGE = "activitylarge"
  val IMAGE_STOCK_INFO = "stock_info"
  val IMAGE_STOCK_EXIT = "stock_exit"
  val IMAGE_GOUP = "goup"
  val IMAGES_PATH = "/images/"


  def setupImages(): Unit = {

  }

  def putImage(key: String, fileName: String): Unit = {

  }

  def getImage(name: String) : Image = {
    imageRegistry.get(name)
  }
}
