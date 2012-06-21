package faux.mvc.mock;

import java.util.Properties;

import faux.mvc.Actions;
import faux.mvc.Context;

import com.google.inject.*;

public class MockActions extends Actions {
  
  @Inject
  public MockActions(Context context) {
    super(context);
  }

  public Properties test(boolean valid, String name, int intValue, Object obj) {
    MockViewModel m = new MockViewModel();
    m.valid = valid;
    m.key = name;
    m.intValue = intValue;
    m.value = obj;
    return viewModel(m);
  }
}
