package faux.mvc.extend;

import java.io.InputStream;
import java.util.Properties;

import faux.mvc.Context;

/** Implement this in the app for domain specific template rendering. */
public interface ITemplate {
  String render(Properties templateProperties, InputStream templateStream, Context context); 
}
