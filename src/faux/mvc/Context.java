package faux.mvc;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Injector;

import faux.mvc.extend.RequestHandler;

public class Context {
  
  public HttpServletRequest request;
  
  public HttpServletResponse response;
  
  public RequestHandler handler;
  
  public Servlet servlet;
  
  public Injector injector;
  
  public Context(HttpServletRequest request, HttpServletResponse response, Servlet servlet, RequestHandler handler, Injector injector) {
    this.request = request;
    this.response = response;
    this.servlet = servlet;
    this.handler = handler;
    this.injector = injector;
  }
}
