package faux.mvc.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import faux.mvc.View;
import faux.mvc.mock.MockController;
import faux.mvc.mock.MockTemplate;
import faux.mvc.mock.MockTemplatePath;

public class ViewTests {

  private View setup() {
    MockTemplate template = new MockTemplate();
    template.rendered = "Hello World";
    
    MockTemplatePath templatePath = new MockTemplatePath();
    templatePath.basePath = "/tests/faux/mvc/mock/assets/";
    
    MockController c = new MockController(template, templatePath);
    View rtn = c.home();
    
    return rtn;
  }
  
  @Test
  public void test_can_create_instance() {
    View instance = setup();
    assertNotNull("Unable to create instance", instance);
  }
  
  @Test
  public void test_can_render_valid_instance() {
    View instance = setup();
    String rtn = instance.render();
    assertNotNull("Unable to render valid instance", rtn);
  }
  
  @Test
  public void test_cannot_render_invalid_instance() {
    View instance = new View(null, this, null, null);
    String rtn = instance.render();
    assertNull("Able to render invalid instance", rtn);
  }
}
