package faux.mvc.extend;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import faux.mvc.Context;
import faux.mvc.internal.Action;
import faux.mvc.internal.Dispatcher;
import faux.mvc.internal.Status;

/** Handles incoming servlet requests; extend this in the servlet */
public abstract class RequestHandler {
  
  private Injector _injector = null;
  
  /** Public global logging api */
  private Status _status = null;
  
  /** If we're in debug mode. */
  private boolean _debug = false;
  
  public RequestHandler() {
    _injector = Guice.createInjector(configure());
  }
  
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, Servlet servlet) {
    _status = null; // Reset status on new request.
    Context c = new Context(request, response, servlet, this, _injector);
    Dispatcher d = new Dispatcher(c);
    
    status().trace("Created things");
    
    // Process request
    Action a = d.handleRequest(request.getRequestURI());
    Status s = status();
    
    s.trace("Resolved action");
    
    // An error?
    if (s.failed()) {
      s.trace("...but we failed.");
      try {
        if (_debug) {
          response.setContentType("text/html");
          response.getWriter().write("Error code " + s.code());
          response.getWriter().write(s.debug());
        }
        else
          response.sendError(s.code());
      }
      catch(IOException e) {
      }
    }
      
    // No error
    else {
      s.trace("...and it worked!");
      String result = a.invoke();
      s.trace("...and we invoked stuff ok");
      if (_debug) 
        result += status().debug();
      try {
        response.setContentType("text/html");
        response.getWriter().write(result);
      }
      catch(IOException e) {
      }
    }
  }
  
  /** Logging api */
  public Status status() {
    if (_status == null) 
      _status = _injector.getInstance(Status.class);
    return _status;
  }
  
  /** Enable debugging */
  public void debug() {
    _debug = true;
  }
  
  /** Configure any services that must be resolved here. */
  public abstract AbstractModule configure();
  
  /** Return the namespace for controllers here */
  public abstract String controllerNamespace();
  
  /** 
   * Return the global leader space for the app
   * <p>
   * ie. If this servlet lives under /home/myservlet/ and the path
   * /home/myservlet/home/blah should map to the action blah() on
   * the HomeController class, then pass "/home/myservlet" as the
   * response to this. Otherwise return "".
   */
  public abstract String applicationNamespace();
}
