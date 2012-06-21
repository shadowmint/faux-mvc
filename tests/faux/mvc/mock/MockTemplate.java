package faux.mvc.mock;

import java.io.InputStream;
import java.util.Properties;

import faux.mvc.Context;
import faux.mvc.extend.ITemplate;

public class MockTemplate implements ITemplate {

  public String rendered = "";
  
  @Override
  public String render(Properties templateProperties, InputStream templateStream, Context context) {
    if ((templateProperties.get("Model") != null) && (templateStream != null)) 
      return rendered;
    else
      return null;
  }
}
