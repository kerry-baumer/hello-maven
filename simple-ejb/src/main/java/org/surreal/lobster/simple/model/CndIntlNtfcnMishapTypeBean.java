package org.surreal.lobster.simple.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.surreal.lobster.sharedcore.model.AbstractPropertyBean;

	/**
	 * @author kerry.baumer
	 *
	 */
	@Entity
	@Table(name="CND_INTL_NTFCN_MISHAP_TYPE", schema="WESS_DRAFT")
	public class CndIntlNtfcnMishapTypeBean extends AbstractPropertyBean {
		/**
		 * the serial version UID property
		 */
		private static final long serialVersionUID = 1L;

		/** the mishapTypeSerl property */
		@Id
		@Column (name="MISHAP_TYPE_SERL", length=32)
		@org.hibernate.annotations.GenericGenerator(name="system-uuid", strategy="uuid")	// Added by sed - 06/07/2013
		@GeneratedValue(generator="system-uuid")
		private String mishapTypeSerl;

		@ManyToOne
		@JoinColumn(name="INTL_NTFCN_SERL")
		private CndIntlNtfcnBean cndIntlNtfcnBean;

//		/** the mishapTypeC property */
//		@ManyToOne (fetch=FetchType.EAGER)
//		@JoinColumn (name="MISHAP_TYPE_C", columnDefinition="CHAR(2)")
//		private MishapTypeDecode mishapTypeC;

		/** the mishapTypeC property */
		@Column (name="MISHAP_TYPE_C", columnDefinition="CHAR(2)")
		private String mishapTypeC;


		public String getMishapTypeSerl() {
			return mishapTypeSerl;
		}

		public void setMishapTypeSerl(String newVal) {
			mishapTypeSerl = newVal;
		}

		public CndIntlNtfcnBean getCndIntlNtfcnBean() {
			return cndIntlNtfcnBean;
		}

		public void setCndIntlNtfcnBean(CndIntlNtfcnBean newValue) {
			this.cndIntlNtfcnBean = newValue;
		}
		
		public String getMishapTypeC() {
			return mishapTypeC;
		}

		public void setMishapTypeC(String newVal) {
			mishapTypeC = newVal;
		}
	
}
