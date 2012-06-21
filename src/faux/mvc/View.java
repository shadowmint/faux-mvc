package faux.mvc;

import java.io.InputStream;
import java.util.Properties;

import faux.mvc.extend.ITemplate;

public class View {

  private ITemplate _template;
  
  private Object _viewModel;
  
  private InputStream _templateStream;
  
  private Context _context;
  
  public View(InputStream templateStream, Object viewModel, ITemplate template, Context context) {
    _template = template;
    _viewModel = viewModel;
    _templateStream = templateStream;
    _context = context;
  }
  
  public String render() {
    String rtn = null;
    if (_templateStream != null) {
      Properties props = new Properties();
      if (_viewModel != null)
        props.put("Model", _viewModel);
      else
        props.put("Model", false);
      rtn = _template.render(props, _templateStream, _context);
    }
    return rtn;
  }
}
