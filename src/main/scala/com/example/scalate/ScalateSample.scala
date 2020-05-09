package com.example.scalate

import org.fusesource.scalate.{DefaultRenderContext, TemplateEngine}
import org.slf4j.LoggerFactory

object ScalateSample extends App {
  val log = LoggerFactory.getLogger(ScalateSample.getClass)

  val template =
    """<%@ val testVal: String = "hello world" %>
      |${testVal}
      |""".stripMargin

  log.info(transform(template, Map.empty))

  def transform(input: String, tokens: Map[String, Any]): String = {
    val filename = "test.ssp"
    val engine = new TemplateEngine()
    val template = engine.compileSsp(input)
    val ctx = new DefaultRenderContext(filename, engine)
    tokens.foreach { case (key, value) =>
      ctx.setAttribute(key, Some(value))
    }
    ctx.capture(template)
  }
}
