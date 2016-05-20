package org.surreal.lobster.sharedcore.databroker;

import org.surreal.lobster.sharedcore.navigation.Page;

public interface ValidationError {
	Page getPage();
	String getxInfo();
	String getErrorMessage();
}
