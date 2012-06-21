package faux.mvc.internal;

import faux.mvc.extend.ILog;

import com.google.inject.*;

/** Request status tracking class */
public class Status {
  
  public static int SUCCESS = 200;
  
  public static int ERROR = 500;
  
  public static int NOT_FOUND = 404;
  
  private ILog _log;
  
  private boolean _failed;
  
  private int _code;
  
  @Inject
  public Status(ILog log) {
    _log = log;
    _failed = false;
    _code = SUCCESS;
  }
  
  /** Report an error occurred and stack trace */
  public void error(String message, Throwable e, int code) {
    error(message, code);
    _log.trace(e.toString());
    for (StackTraceElement s : e.getStackTrace()) {
      _log.trace(s.toString());
    }
  }
  
  /** Report an error occurred */
  public void error(String message, int code) {
    _log.trace("Request failed: CODE " + code);
    _log.trace(message);
    _code = code;
    if (_code != SUCCESS) {
      _failed = true;
    }
  }
  
  /** General debugging log message */
  public void trace(String message) {
    _log.trace(message);
  }
  
  /** If the request failed */
  public boolean failed() {
    return _failed;
  }
  
  /** Return log debug code */
  public String debug() {
    return _log.debug();
  }
  
  /** Status code */
  public int code() {
    return _code;
  }
}
