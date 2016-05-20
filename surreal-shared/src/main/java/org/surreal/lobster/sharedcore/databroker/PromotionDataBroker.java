package org.surreal.lobster.sharedcore.databroker;


public interface PromotionDataBroker extends DataBroker {

	void release(DataBrokerCallback<Void> callback);
	void reject(String reason, DataBrokerCallback<Void> callback);
	void accept(DataBrokerCallback<Void> callback);
	void delete(DataBrokerCallback<Void> callback);

}
