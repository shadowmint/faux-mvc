package faux.mvc.extend;

/** Extend this interface for logging purposes */
public interface ILog {

  /** Trace an error message */
  public void trace(String message);
  
  /** Fetch all 'debug' information to display (Eg. last error) */
  public String debug();
}
