package faux.mvc.tests;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import static org.mockito.Mockito.*;

import faux.mvc.Actions;
import faux.mvc.Context;
import faux.mvc.extend.RequestHandler;
import faux.mvc.mock.MockActions;

public class ActionsTests {

  private Actions setup() {
    
  	HttpServletRequest request = mock(HttpServletRequest.class);
  	HttpServletResponse response = mock(HttpServletResponse.class);
  	Servlet servlet = mock(Servlet.class);
  	RequestHandler handler = mock(RequestHandler.class);
  	
  	Context context = new Context(request, response, servlet, handler, null);
    Actions rtn = new MockActions(context);
    
    return rtn;
  }
  
  @Test
  public void test_can_create_instance() {
    Actions instance = setup();
    assertNotNull("Unable to create instance", instance);
  }
  
  @Test
  public void test_can_map_view_model() {
	Actions instance = setup();
	
	Properties map = ((MockActions) instance).test(true, "KEY", 10, instance);
	assertNotNull("Unable to map view model");
	assertEquals("Missing truth value", true, map.get("valid"));
	assertEquals("Missing int value", 10, map.get("intValue"));
	assertEquals("Missing string value", "KEY", map.get("key"));
	assertEquals("Missing object value", instance, map.get("value"));
  }
}
