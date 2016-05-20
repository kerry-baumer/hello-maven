package org.surreal.lobster.sharedcore.databroker;

import org.surreal.lobster.sharedcore.databroker.DataBroker.HandlesError;


/**
 * Borrowed liberally from the GWT AsyncCallback interface.  Used to call back
 * invokers of the DataBroker in an asynchronous manner.
 * @author Jeffrey.Richley
 * @param <T> The type of data expected as the result.
 */
public interface DataBrokerCallback<T> {

	  /**
	   * Called when an asynchronous call fails to complete normally.
	   * or checked exceptions thrown by the service method are examples of the type
	   * of failures that can be passed to this method.
	   * 
	   * @param caught failure encountered while executing a remote procedure call
	   */
	  void onFailure(Throwable caught);

	  /**
	   * Called when an asynchronous call completes successfully.
	   * 
	   * @param result the return value of the remote produced call
	   */
	  void onSuccess(T result);
	  
	  /**
	   * Default implementation of a DataBrokerCallback.
	   * 
	   * @author christopher.d.grimes
	   *
	   * @param <T>
	   */
	  public abstract class Default<T> implements DataBrokerCallback<T> {
		  
		  private HandlesError handlesError;
		  
		  public Default() {
		  }
		 
		  /**
		   * Overload constructor takes a handles error strategy
		   */
		  public Default(HandlesError handlesError) {
			  this.handlesError = handlesError;
		  }
		  
		  @Override
		  public void onFailure(Throwable caught) {
			  if (handlesError != null) {
				  handlesError.handleError(caught);
			  } else {
				  caught.printStackTrace();
			  }
		  }
	  }
}
