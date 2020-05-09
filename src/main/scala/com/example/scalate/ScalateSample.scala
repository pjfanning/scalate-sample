package com.example.scalate

import java.util.UUID

import com.example.scalate.ScalateSample.template
import org.fusesource.scalate.{DefaultRenderContext, RenderContext, TemplateEngine}
import org.fusesource.scalate.support.StringTemplateSource
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
    val source = new StringTemplateSource(filename, input)
    val template = engine.compile(source)
    val ctx = new DefaultRenderContext(filename, engine)
    tokens.foreach { case (key, value) =>
      ctx.setAttribute(key, Some(value))
    }
    log.info(s"$ctx $template")
    ctx.capture(template)
  }
}
