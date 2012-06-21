package faux.mvc;

import java.lang.reflect.Field;
import java.util.Properties;

/** Base for controller implementations */
public abstract class Actions {
  
  protected Context _context;
  
  public Actions(Context context) {
    _context = context;
  }
  
  /** Extracts top level public properties of the object and creates a property set */
  protected Properties viewModel(Object model) {
    Properties rtn = new Properties();
    Class<?> ctype = model.getClass();
    Field fields[] = ctype.getFields();
    for (Field f : fields) {
      String name = f.getName();
      try {
        Object value = f.get(model);
        rtn.put(name, value);
      }
      catch(IllegalAccessException e) {
      } // Ignore private fields.
    }
    return rtn;
  }
}
