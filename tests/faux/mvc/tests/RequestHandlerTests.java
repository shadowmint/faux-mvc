package faux.mvc.tests;

import static org.junit.Assert.*;

import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.google.inject.AbstractModule;

import static org.mockito.Mockito.*;

import faux.mvc.extend.ILog;
import faux.mvc.extend.ITemplate;
import faux.mvc.extend.ITemplatePath;
import faux.mvc.extend.RequestHandler;
import faux.mvc.internal.Status;
import faux.mvc.mock.MockLog;
import faux.mvc.mock.MockTemplate;
import faux.mvc.mock.MockTemplatePath;

public class RequestHandlerTests {

  private RequestHandler setup() {
    RequestHandler h = new RequestHandler() {
      @Override
      public AbstractModule configure() {
        AbstractModule rtn = new AbstractModule() {
          @Override
          protected void configure() {
            bind(ILog.class).to(MockLog.class);
            bind(ITemplate.class).to(MockTemplate.class);
            bind(ITemplatePath.class).to(MockTemplatePath.class);
          }
        };
        return rtn;
      }

      @Override
      public String controllerNamespace() {
        return "faux.mvc.mock";
      }

      @Override
      public String applicationNamespace() {
        return "";
      }
    };
    return h;
  }
  
  @Test
  public void test_can_create_instance() {
    RequestHandler instance = setup();
    assertNotNull("Unable to create instance", instance);
  }
  
  @Test
  public void test_can_handle_requests_with_trailing_slash() {
    
  	HttpServletRequest request = mock(HttpServletRequest.class);
  	HttpServletResponse response = mock(HttpServletResponse.class);
  	Servlet servlet = mock(Servlet.class);
  	PrintWriter mockOutput = mock(PrintWriter.class);
  	
  	try {
    	when(request.getRequestURI()).thenReturn("/mock/home/");
    	when(response.getWriter()).thenReturn(mockOutput);
  	}
  	catch(Exception e) {
  	}
  	
	RequestHandler instance = setup();
	instance.handleRequest(request, response, servlet);
	
	Status s = instance.status();
	assertFalse(s.failed());
  }
  
  @Test
  public void test_cannot_handle_bad_action_requests() {
    
  	HttpServletRequest request = mock(HttpServletRequest.class);
  	HttpServletResponse response = mock(HttpServletResponse.class);
  	PrintWriter mockOutput = mock(PrintWriter.class);
  	Servlet servlet = mock(Servlet.class);
  	
  	try {
    	when(request.getRequestURI()).thenReturn("/mock/noSuchAction");
    	when(response.getWriter()).thenReturn(mockOutput);
  	}
  	catch(Exception e) {
  	}
  	
	RequestHandler instance = setup();
	instance.handleRequest(request, response, servlet);
	
	Status s = instance.status();
	assertTrue(s.failed());
	assertEquals(s.code(), Status.NOT_FOUND);
  }
  
  @Test
  public void test_cannot_handle_bad_controller_requests() {
    
  	HttpServletRequest request = mock(HttpServletRequest.class);
  	HttpServletResponse response = mock(HttpServletResponse.class);
  	PrintWriter mockOutput = mock(PrintWriter.class);
  	Servlet servlet = mock(Servlet.class);
  	
  	try {
    	when(request.getRequestURI()).thenReturn("/bad/home");
    	when(response.getWriter()).thenReturn(mockOutput);
  	}
  	catch(Exception e) {
  	}
  	
	RequestHandler instance = setup();
	instance.handleRequest(request, response, servlet);
	
	Status s = instance.status();
	assertTrue(s.failed());
	assertEquals(s.code(), Status.NOT_FOUND);
  }
}
