package org.surreal.lobster.sharedcore.databroker;

import java.util.List;

import org.surreal.lobster.sharedcore.model.OptionItemDetail;

public interface PersonLookupDataBroker extends DataBroker {

	void findPeople(String query, DataBrokerCallback<List<OptionItemDetail>> callback);
	
}
