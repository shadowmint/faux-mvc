package faux.mvc.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import faux.mvc.Context;
import faux.mvc.Controller;
import faux.mvc.View;

/** When invoked, runs a controller instance, renders the result and returns it. */
public class Action {
  
  private Controller _controller;
  
  private Method _method;
  
  private Context _context;
  
  public Action(Controller controller, Method method, Context context) {
    _controller = controller;
    _method = method;
    _context = context;
  }
  
  public String invoke() {
    String rtn = null;
    try {
      View v = null;
      try {
        v = (View) _method.invoke(_controller);
      }
      catch (InvocationTargetException x) {
        Throwable e = x.getCause();
        _context.handler.status().error("Error invoking action", e, Status.ERROR);
      }
      catch(Exception e) {
        _context.handler.status().error("Unable to invoke view action", e, Status.ERROR);
      }
      if (v != null) 
        rtn = v.render();
      else 
        _context.handler.status().error("Action did not return a view", Status.ERROR);
    }
    catch(Exception e) {
      _context.handler.status().error("Error rendering action", e, Status.ERROR);
    }
    return rtn;
  }
}
