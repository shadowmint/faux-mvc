package faux.mvc.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import faux.mvc.Controller;
import faux.mvc.View;
import faux.mvc.mock.MockController;
import faux.mvc.mock.MockTemplate;
import faux.mvc.mock.MockTemplatePath;

public class ControllerTests {

  private MockController setup() {
    MockTemplate template = new MockTemplate();
    template.rendered = "Hello World";
    
    MockTemplatePath templatePath = new MockTemplatePath();
    templatePath.basePath = "/tests/faux/mvc/mock/assets/";
    
    MockController rtn = new MockController(template, templatePath);
    return rtn;
  }
  
  @Test
  public void test_can_create_instance() {
    Controller instance = setup();
    assertNotNull("Unable to create instance", instance);
  }
  
  @Test
  public void test_can_locate_view() {
    MockController instance = setup();
    View view = instance.home();
    assertNotNull("Unable to render 'home' view", view.render());
  }

  @Test
  public void test_can_locate_view_by_name() {
    MockController instance = setup();
    View view = instance.homeWithView();
    assertNotNull("Unable to render 'home' view by name", view.render());
  }

  @Test
  public void test_can_locate_view_with_model() {
    MockController instance = setup();
    View view = instance.homeWithModel();
    assertNotNull("Unable to render 'home' view with model", view.render());
  }

  @Test
  public void test_can_locate_view_by_name_with_model() {
    MockController instance = setup();
    View view = instance.homeWithViewAndModel();
    assertNotNull("Unable to render 'home' view by name with model", view.render());
  }

}
