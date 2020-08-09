package com.parinherm.services

import play.api.libs.json.{JsValue, Json}
import scalaj.http.{Http, HttpRequest}


import com.twitter.finagle.{Http => finagla, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}


object HttpClientService {

  System.setProperty("sun.net.http.allowRestrictedHeaders", "true")

  def getDataFromUrl(url: String): Option[JsValue] = {
    try {
      val request: HttpRequest = Http(url)
      //request.timeout(3000, 10000)
      val body = request.asString.body
      val json: JsValue = Json.parse(body)
      Some(json)
    } catch {
      case e: Exception => None
    }

  }
  //val requestUrl = "http://date.jsontest.com/"

  def getDataFromUrlAsync(url: String) : String = {
    val output: StringBuilder = new StringBuilder()
    val client: Service[http.Request, http.Response] = finagla.newService("www.scala-lang.org:80")
    val request = http.Request(http.Method.Get, "/")
    request.host = "www.scala-lang.org"
    val response: Future[http.Response] = client(request)
    Await.result(response.onSuccess {
      rep: http.Response => output.addAll("complete")
    })
    output.toString()
  }


}
