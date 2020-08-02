package com.parinherm.model

import scala.beans.BeanProperty


class Exercise (@BeanProperty var title: String, val func: () => String)
  extends Object with BoundPropertyBean

