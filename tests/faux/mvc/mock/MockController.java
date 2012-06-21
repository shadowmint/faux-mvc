package faux.mvc.mock;

import faux.mvc.Controller;
import faux.mvc.View;
import faux.mvc.extend.ITemplate;
import faux.mvc.extend.ITemplatePath;

import com.google.inject.*;

public class MockController extends Controller {
  
  @Inject
  public MockController(ITemplate template, ITemplatePath templatePath) {
    super(template, templatePath);
  }

  public View home() {
    return view();
  }
  
  public View homeWithModel() {
    return view(this);
  }
  
  public View homeWithView() {
    return view("hello");
  }
  
  public View homeWithViewAndModel() {
    return view("hello", this);
  }
}
