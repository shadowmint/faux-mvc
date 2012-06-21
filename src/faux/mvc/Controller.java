package faux.mvc;

import com.google.inject.*;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faux.mvc.extend.ITemplate;
import faux.mvc.extend.ITemplatePath;

/** Base for all controllers */
public abstract class Controller {

  private ITemplate _template;
  
  private ITemplatePath _templatePath;
  
  protected HttpServletRequest _request;
  
  protected HttpServletResponse _response;
  
  private Context _context;
  
  @Inject
  public Controller(ITemplate template, ITemplatePath templatePath) {
    _template = template;
    _templatePath = templatePath;
  }
  
  public void setContext(Context context) {
    _request = context.request;
    _response = context.response;
    _context = context;
  }
  
  private String controllerId() {
    String rtn = this.getClass().getSimpleName();
    rtn = rtn.replace("Controller", "");
    rtn = rtn.toLowerCase();
    return rtn;
  }
  
  private String viewInvoker() {
    StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    StackTraceElement e = stacktrace[3];
    String methodName = e.getMethodName();
    return methodName;
  }
  
  protected View view() {
    String method = viewInvoker();
    return view(method, null);
  }
  
  protected View view(String vid) {
    return view(vid, null);
  }
  
  protected View view(Object viewModel) {
    String method = viewInvoker();
    return view(method, viewModel);
  }
  
  protected View view(String vid, Object viewModel){
    String cid = controllerId();
    InputStream templateStream = _templatePath.resolvePath(cid, vid, _context);
    View rtn = new View(templateStream, viewModel, _template, _context);
    return rtn;
  }
}
