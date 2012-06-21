package faux.mvc.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import faux.mvc.Context;
import faux.mvc.extend.ITemplatePath;

public class MockTemplatePath implements ITemplatePath {

  public String basePath = "./";
  
  @Override
  public InputStream resolvePath(String controllerId, String viewId, Context context) {
    String cwd = cwd();
    String fullPath = cwd + basePath + controllerId + "/" + viewId + ".txt";
    InputStream rtn = null;
    try {
      rtn = new FileInputStream(fullPath); 
    }
    catch(Exception e) {
    }
    return rtn;
  }
  
  private String cwd() {
    File directory = new File (".");
    String rtn = "";
    try {
      rtn = directory.getAbsolutePath();
    }
    catch(Exception e) {
    }
    return rtn;
  }
}
