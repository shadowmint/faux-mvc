package faux.mvc.mock;

import faux.mvc.extend.ILog;

public class MockLog implements ILog {
  
  private String _last = null;

  @Override
  public void trace(String message) {
    _last = message;
  }

  @Override
  public String debug() {
    return _last;
  }
}
