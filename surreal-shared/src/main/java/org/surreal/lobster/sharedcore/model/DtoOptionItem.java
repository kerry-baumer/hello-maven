package org.surreal.lobster.sharedcore.model;

import java.io.Serializable;

import org.surreal.lobster.sharedcore.model.OptionItem;

public class DtoOptionItem implements OptionItem, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String decode;
	
	public DtoOptionItem() {}
	public DtoOptionItem(String arg0, String arg1) {
		code = arg0;
		decode = arg1;
	}
	
	@Override
	public String getText() {
		return code;
	}
	public void setText(String arg0) {
		code = arg0;
	}

	@Override
	public String getValue() {
		return decode;
	}
	public void setValue(String arg0) {
		decode = arg0;
	}

}
