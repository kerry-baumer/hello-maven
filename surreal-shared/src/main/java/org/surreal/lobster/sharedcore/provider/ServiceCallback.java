package org.surreal.lobster.sharedcore.provider;

public interface ServiceCallback<T>{

	void onSuccess(T result);
	
	void onFailure(Throwable caught); 

	abstract class Default<T> implements ServiceCallback<T> {
		
		@Override
		public void onFailure(Throwable caught) { 
		//empty
		}
	}

}
