/**
 * 
 */
package org.surreal.lobster.sharedcore.model;

import java.io.Serializable;

import org.surreal.lobster.sharedcore.model.OptionItemDetail;

/**
 * @author kerry.baumer
 *
 */
public class DtoOptionItemDetail implements OptionItemDetail, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String decode;
	private String additionalText;
	
	public DtoOptionItemDetail() {}
	/**
	 * 
	 * @param arg0 - code
	 * @param arg1 - decode
	 * @param arg2 - additional text
	 */
	public DtoOptionItemDetail(String arg0, String arg1, String arg2) {
		code = arg0;
		decode = arg1;
		additionalText = arg2;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.OptionItem#getText()
	 */
	@Override
	public String getText() {
		return decode;
	}
	public void setText(String arg0) {
		decode = arg0;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.OptionItem#getValue()
	 */
	@Override
	public String getValue() {
		return code;
	}
	public void setValue(String arg0) {
		code = arg0;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.OptionItemDetail#getAdditionalInfo()
	 */
	@Override
	public String getAdditionalInfo() {
		return additionalText;
	}
	public void setAdditionalInfo(String arg0) {
		additionalText = arg0;
	}

}
