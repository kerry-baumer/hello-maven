package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.navigation.Page;

public interface ValidationDataBroker {

	void validate(DataBrokerCallback<List<ValidationError>> callback);
	
	List<ValidationError> getErrorsFor(Page page, String xInfo); 
}
