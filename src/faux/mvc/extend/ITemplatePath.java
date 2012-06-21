package faux.mvc.extend;

import java.io.InputStream;

import faux.mvc.Context;

/** Implement this in the app for domain specific template path resolution. */
public interface ITemplatePath {
  InputStream resolvePath(String controllerId, String viewId, Context context);
}
