package com.company.sling.example.core;

import com.google.common.collect.ImmutableMap;
import com.google.common.net.MediaType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

/**
 * @link http://[host]:[port]/content/example.hello.json
 */
@Component(
  service = Servlet.class,
  property = {
    "sling.servlet.extensions=json",
    "sling.servlet.selectors=hello",
    "sling.servlet.resourceTypes=example/hello"
  }
)
public class HelloServlet extends SlingAllMethodsServlet {

  private static final Gson GSON = new GsonBuilder()
    .disableHtmlEscaping().serializeNulls().setPrettyPrinting()
    .create();

  @Override
  protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
    throws IOException {
    respond(response, "Hello world!");
  }

  private void respond(SlingHttpServletResponse response, String result) throws IOException {
    respond(response, ImmutableMap.of("result", result));
  }

  private void respond(SlingHttpServletResponse response, Object object) throws IOException {
    response.setContentType(MediaType.JSON_UTF_8.toString());
    response.getWriter().write(GSON.toJson(object));
  }

}
