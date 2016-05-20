/**
 * 
 */
package org.surreal.lobster.simple.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.surreal.lobster.sharedcore.constants.PromotionState;
import org.surreal.lobster.sharedcore.constants.ReportType;
import org.surreal.lobster.sharedcore.constants.StatusState;
import org.surreal.lobster.sharedcore.model.AbstractPropertyBean;

/**
 * @author kerry.baumer
 *
 */

@NamedQueries({
	@NamedQuery(
			name="CndIntlNtfcnBean.findAll",
			query="select e from CndIntlNtfcnBean e"
	),
	@NamedQuery(
			name="CndIntlNtfcnBean.findAllUnsubmitted",
			query="select e from CndIntlNtfcnBean e where e.state <> :state"
	),
	@NamedQuery(
			name="CndIntlNtfcnBean.findAllSubmitted",
			query="select e from CndIntlNtfcnBean e where e.state = :state"
	),
	@NamedQuery(
			name="CndIntlNtfcnBean.findByHTId",
			query="select e from CndIntlNtfcnBean e where e.htId = :htId"
	),
	@NamedQuery(
			name="CndIntlNtfcnBean.findByGuid",
			query="select e from CndIntlNtfcnBean e where e.intlNtfcnSerl = :guid"
	)
})
@Entity
@Table(name="CND_INTL_NTFCN", schema="WESS_DRAFT")
public class CndIntlNtfcnBean extends AbstractPropertyBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** the invlvdEntitySerl property */
	@Id
	@Column (name="INTL_NTFCN_SERL", length=32)
	@GenericGenerator(name="system-uuid", strategy="uuid")	// Added by sed - 06/07/2013
	@GeneratedValue(generator="system-uuid")
	private String intlNtfcnSerl;
	
	@Column (name="notification_type", length=24)
	@Enumerated(EnumType.STRING)
	private ReportType notificationType;
	
	@Column (name="DOD_PROP_COST", length=9)
	private Double dodPropertyCost;

	@Column (name="DRAFTER_EDIPI", length=16)
	private String drafterEdiPi;
	
	@Column (name="POC_FIRST_NAME", length=20)
	private String pocFirstName;
	
	@Column (name="POC_LAST_NAME", length=20)
	private String pocLastName;
	
	@Column (name="POC_PHONE", length=20)
	private String pocPhone;
	
	@Column (name="POC_EMAIL_ADDR", length=50)
	private String pocEmail;
		
//	@Column (name="STATUS", length=1)
//	@Enumerated(EnumType.STRING)
	@Transient
	private StatusState status;
	
	@Column (name="STATE", length=32)
	@Enumerated(EnumType.STRING)
	private PromotionState state;
	
	@Column(name="ht_id", length=30)
	private String htId;
	
	@Column (name="MISHAP_LEVEL", length=9)
	private String mishapLevel;
	
	@Column (name="BODY_OF_WTR_C", columnDefinition="CHAR(1)")
	private String bodyOfWtrC;

	@Column (name="TOTAL_COST", length=9)
	private Integer totalCost;

	@Column (name="MISHAP_TYPE_C", columnDefinition="CHAR(2)")
	private String mishapType;

	@Column (name="MISHAP_INVLVD", length=9)
	private String mishapInvlvd;

	@Column (name="GMV_I", length=1)
	private String gmvI;
	
	@Column (name="NAVY_OPERN_I", length=1)
	private String navyOpernI;
	
	@Column (name="NO_OF_WHEELS", length=3)
	private String noOfWheels;

	@Column (name="MISHAP_DATE", length=9)
	private Date mishapDate;

	@Column (name="LOCAL_TIME", length=9)
	private String localTime;

	@Column (name="LOCAL_SERL_NO", length=9)
	private String localSerlNo;

    /** represents the cntryC value */
    @Column (name="CNTRY_C")
    private String cntryC;

    /** represents the stateProvC value */
    @Column (name="STATE_PROV_C")
    private String stateProvC;

	@Column (name="CITY", length=45)
	private String city;

	@Column (name="SHORT_NARR", length=140)
	private String shortNarr;

	@Column (name="INVLVD_UNIT_NAME", length=45)
	private String invlvdUnitName;

	@Column (name="VCTMS_UNIT_NAME", length=45)
	private String vctmsUnitName;

	@Column (name="RPRTG_UNIT_CODE", length=9)
	private String rprtgUnitCode;
	
	@Column (name="VCTMS_UNIT_CODE", length=9)
	private String vctmsUnitCode;
	
	@Column (name="VCTMS_ENVIR_C", length=9)
	private String vctmsEnvirC;

	@Column (name="INVLVD_UNIT_CODE", length=9)
	private String invlvdUnitCode;
	
	@Column (name="INVLVD_ENVIR_C", length=9)
	private String invlvdEnvirC;

	@Column (name="AT_FAULT_UNIT_CODE", length=9)
	private String atFaultUnitCode;
	
	@Column (name="NUM_NAVY_MIL_FATALITIES", length=10)
	private Integer numNavyMilFatalities;
	
	@Column (name="NUM_NAVY_MIL_PERM_DISB", length=10)
	private Integer numNavyMilPermanentDisability;

	@Column (name="NUM_NAVY_MIL_PART_DISB", length=10)
	private Integer numNavyMilPartialDisability;

	@Column (name="NUM_NAVY_MIL_HOSP", length=10)
	private Integer numNavyMilHospitalized;

	@Column (name="NUM_NAVY_CIV_FATALITIES", length=10)
	private Integer numNavyCivFatalities;
	
	@Column (name="NUM_NAVY_CIV_PERM_DISB", length=10)
	private Integer numNavyCivPermanentDisability;

	@Column (name="NUM_NAVY_CIV_PART_DISB", length=10)
	private Integer numNavyCivPartialDisability;

	@Column (name="NUM_NAVY_CIV_HOSP", length=10)
	private Integer numNavyCivHospitalized;
	
	@Column (name="NUM_USMC_MIL_FATALITIES", length=10)
	private Integer numUsmcMilFatalities;
	
	@Column (name="NUM_USMC_MIL_PERM_DISB", length=10)
	private Integer numUsmcMilPermanentDisability;

	@Column (name="NUM_USMC_MIL_PART_DISB", length=10)
	private Integer numUsmcMilPartialDisability;

	@Column (name="NUM_USMC_MIL_HOSP", length=10)
	private Integer numUsmcMilHospitalized;
	
	@Column (name="NUM_USMC_CIV_FATALITIES", length=10)
	private Integer numUsmcCivFatalities;
	
	@Column (name="NUM_USMC_CIV_PERM_DISB", length=10)
	private Integer numUsmcCivPermanentDisability;

	@Column (name="NUM_USMC_CIV_PART_DISB", length=10)
	private Integer numUsmcCivPartialDisability;

	@Column (name="NUM_USMC_CIV_HOSP", length=10)
	private Integer numUsmcCivHospitalized;
	
	@Column (name="NUM_OTHER_FATALITIES", length=10)
	private Integer numOtherFatalities;
	
	@Column (name="NUM_OTHER_PERM_DISB", length=10)
	private Integer numOtherPermanentDisability;

	@Column (name="NUM_OTHER_PART_DISB", length=10)
	private Integer numOtherPartialDisability;

	@Column (name="NUM_OTHER_HOSP", length=10)
	private Integer numOtherHospitalized;
	
	@Column (name="PORT_C", columnDefinition="CHAR(5)")
	private String portC;
	
	/** Navy & USMC installations + "Other" which should be displayed at top of list **/
	@Column (name="INSTALLN_C", columnDefinition="VARCHAR(48)")
	private String installnC;
	
	@Column (name="ON_BASE_I", columnDefinition="CHAR(1)")
	private String onBaseI;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column (name="EVENT_NARR", columnDefinition="clob")
	private String eventNarr;
	
	@OneToMany(cascade = (CascadeType.ALL), fetch = FetchType.EAGER,
			mappedBy="cndIntlNtfcnBean")
	private List<CndIntlNtfcnMishapTypeBean> mishapTypes = new ArrayList<CndIntlNtfcnMishapTypeBean>();
	
	public CndIntlNtfcnBean() {}
	
	/**
	 * @return the intlNtfcnSerl
	 */
	public String getIntlNtfcnSerl() {
		return intlNtfcnSerl;
	}
	/**
	 * @param intlNtfcnSerl the intlNtfcnSerl to set
	 */
	public void setIntlNtfcnSerl(String newValue) {
		this.intlNtfcnSerl = newValue;
	}
	/**
	 * @return the status
	 */
	public StatusState getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusState newValue) {
		this.status = newValue;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(PromotionState newValue) {
		this.state = newValue;
	}
	/**
	 * @return the state
	 */
	public PromotionState getState() {
		return state;
	}
	/**
	 * @return the mishapLevel
	 */
	public String getMishapLevel() {
		return mishapLevel;
	}
	/**
	 * @param mishapLevel the mishapLevel to set
	 */
	public void setMishapLevel(String newValue) {
		this.mishapLevel = newValue;
	}
    /**
     * Ste the body of water code
     * @param bodyOfWtr
     */
	public void setBodyOfWtrC(String bodyOfWtr) {
    	this.bodyOfWtrC = bodyOfWtr;
    }
    
    public String getBodyOfWtrC() {
    	return bodyOfWtrC;
    }
	
	/**
	 * @return the totalCost
	 */
	public Integer getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(Integer newValue) {
		this.totalCost = newValue;
	}
	/**
	 * @return the mishapType
	 */
	public String getMishapType() {
		return mishapType;
	}
	/**
	 * @param mishapType the mishapType to set
	 */
	public void setMishapType(String newValue) {
		this.mishapType = newValue;
	}
	/**
	 * @return the mishapInvlvd
	 */
	public String getMishapInvlvd() {
		return mishapInvlvd;
	}
	/**
	 * @param mishapInvlvd the mishapInvlvd to set
	 */
	public void setMishapInvlvd(String newValue) {
		this.mishapInvlvd = newValue;
	}
	/**
	 * @return the vehicleType
	 */
	public String getGmvI() {
		return gmvI;
	}
	/**
	 * @param vehicleBodyType the vehicleType to set
	 */
	public void setGmvI(String newValue) {
		this.gmvI = newValue;
	}
	/**
	 * @return the noOfWheels
	 */
	public String getNoOfWheels() {
		return noOfWheels;
	}
	/**
	 * @param newValue the noOfWheels to set
	 */
	public void setNoOfWheels(String newValue) {
		this.noOfWheels = newValue;
	}
	/**
	 * @return the mishapDate
	 */
	public Date getMishapDate() {
		return mishapDate;
	}
	/**
	 * @param newValue the mishapDate to set
	 */
	public void setMishapDate(Date newValue) {
		this.mishapDate = newValue;
	}
	/**
	 * @return the localTime
	 */
	public String getLocalTime() {
		return localTime;
	}
	/**
	 * @param localTime the localTime to set
	 */
	public void setLocalTime(String newValue) {
		this.localTime = newValue;
	}
	/**
	 * @return the localSerlNo
	 */
	public String getLocalSerlNo() {
		return localSerlNo;
	}
	/**
	 * @param localSerlNo the localSerlNo to set
	 */
	public void setLocalSerlNo(String newValue) {
		this.localSerlNo = newValue;
	}
	/**
	 * @return the location
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the location to set
	 */
	public void setCity(String newValue) {
		this.city = newValue;
	}
	/**
	 * @return the shortNarr
	 */
	public String getShortNarr() {
		return shortNarr;
	}
	/**
	 * @param shortNarr the shortNarr to set
	 */
	public void setShortNarr(String newValue) {
		this.shortNarr = newValue;
	}
	/**
	 * @return the invlvdUnitName
	 */
	public String getInvlvdUnitName() {
		return invlvdUnitName;
	}
	/**
	 * @param invlvdUnitName the invlvdActyName to set
	 */
	public void setInvlvdUnitName(String newValue) {
		this.invlvdUnitName = newValue;
	}
	/**
	 * @return the vctmsUnitName
	 */
	public String getVctmsUnitName() {
		return vctmsUnitName;
	}
	/**
	 * @param vctmsUnitName the invlvdActyName to set
	 */
	public void setVctmsUnitName(String newValue) {
		this.vctmsUnitName = newValue;
	}
	/**
	 * @return the rprtgUnitCode
	 */
	public String getRprtgUnitCode() {
		return rprtgUnitCode;
	}
	/**
	 * @param rprtgUnitCode the rprtgUnitCode to set
	 */
	public void setRprtgUnitCode(String newValue) {
		this.rprtgUnitCode = newValue;
	}
	/**
	 * @return the atFaultUnitCode
	 */
	public String getAtFaultUnitCode() {
		return atFaultUnitCode;
	}
	/**
	 * @param newVal the atFaultUnitCode to set
	 */
	public void setAtFaultUnitCode(String newVal) {
		atFaultUnitCode = newVal;
	}
	/**
	 * @return the invlvdUnitCode
	 */
	public String getInvlvdUnitCode() {
		return invlvdUnitCode;
	}
	/**
	 * @param newVal the invlvdUnitCode to set
	 */
	public void setInvlvdUnitCode(String newVal) {
		invlvdUnitCode = newVal;
	}
	
	/**
	 * @return the vctmsUnitCode
	 */
	public String getVctmsUnitCode() {
		return vctmsUnitCode;
	}
	/**
	 * @param vctmsUnitCode the vctmsUnitCode to set
	 */
	public void setVctmsUnitCode(String newValue) {
		this.vctmsUnitCode = newValue;
	}

	/**
	 * @return the numNavyMilFatalities
	 */
	public Integer getNumNavyMilFatalities() {
		return numNavyMilFatalities;
	}

	public void setNotificationType(ReportType ntfcnType) {
		this.notificationType = ntfcnType;
	}

	public ReportType getNotificationType() {
		return notificationType;
	}

	/**
	 * @param numNavyMilFatalities the numNavyMilFatalities to set
	 */
	public void setNumNavyMilFatalities(Integer newValue) {
		this.numNavyMilFatalities = newValue;
	}
	/**
	 * @return the numNavyMilPermanentDisability
	 */
	public Integer getNumNavyMilPermanentDisability() {
		return numNavyMilPermanentDisability;
	}
	/**
	 * @param numNavyMilPermanentDisability the numNavyMilPermanentDisability to set
	 */
	public void setNumNavyMilPermanentDisability(
			Integer newValue) {
		this.numNavyMilPermanentDisability = newValue;
	}
	/**
	 * @return the numNavyMilPartialDisability
	 */
	public Integer getNumNavyMilPartialDisability() {
		return numNavyMilPartialDisability;
	}
	/**
	 * @param numNavyMilPartialDisability the numNavyMilPartialDisability to set
	 */
	public void setNumNavyMilPartialDisability(Integer newValue) {
		this.numNavyMilPartialDisability = newValue;
	}
	/**
	 * @return the numNavyMilHospitalized
	 */
	public Integer getNumNavyMilHospitalized() {
		return numNavyMilHospitalized;
	}
	/**
	 * @param numNavyMilHospitalized the numNavyMilHospitalized to set
	 */
	public void setNumNavyMilHospitalized(Integer newValue) {
		this.numNavyMilHospitalized = newValue;
	}
	/**
	 * @return the numNavyCivFatalities
	 */
	public Integer getNumNavyCivFatalities() {
		return numNavyCivFatalities;
	}
	/**
	 * @param numNavyCivFatalities the numNavyCivFatalities to set
	 */
	public void setNumNavyCivFatalities(Integer newValue) {
		this.numNavyCivFatalities = newValue;
	}
	/**
	 * @return the numNavyCivPermanentDisability
	 */
	public Integer getNumNavyCivPermanentDisability() {
		return numNavyCivPermanentDisability;
	}
	/**
	 * @param numNavyCivPermanentDisability the numNavyCivPermanentDisability to set
	 */
	public void setNumNavyCivPermanentDisability(
			Integer newValue) {
		this.numNavyCivPermanentDisability = newValue;
	}
	/**
	 * @return the numNavyCivPartialDisability
	 */
	public Integer getNumNavyCivPartialDisability() {
		return numNavyCivPartialDisability;
	}
	/**
	 * @param numNavyCivPartialDisability the numNavyCivPartialDisability to set
	 */
	public void setNumNavyCivPartialDisability(Integer newValue) {
		this.numNavyCivPartialDisability = newValue;
	}
	/**
	 * @return the numNavyCivHospitalized
	 */
	public Integer getNumNavyCivHospitalized() {
		return numNavyCivHospitalized;
	}
	/**
	 * @param numNavyCivHospitalized the numNavyCivHospitalized to set
	 */
	public void setNumNavyCivHospitalized(Integer newValue) {
		this.numNavyCivHospitalized = newValue;
	}
	/**
	 * @return the numUsmcMilFatalities
	 */
	public Integer getNumUsmcMilFatalities() {
		return numUsmcMilFatalities;
	}
	/**
	 * @param numUsmcMilFatalities the numUsmcMilFatalities to set
	 */
	public void setNumUsmcMilFatalities(Integer newValue) {
		this.numUsmcMilFatalities = newValue;
	}
	/**
	 * @return the numUsmcMilPermanentDisability
	 */
	public Integer getNumUsmcMilPermanentDisability() {
		return numUsmcMilPermanentDisability;
	}
	/**
	 * @param numUsmcMilPermanentDisability the numUsmcMilPermanentDisability to set
	 */
	public void setNumUsmcMilPermanentDisability(
			Integer newValue) {
		this.numUsmcMilPermanentDisability = newValue;
	}
	/**
	 * @return the numUsmcMilPartialDisability
	 */
	public Integer getNumUsmcMilPartialDisability() {
		return numUsmcMilPartialDisability;
	}
	/**
	 * @param numUsmcMilPartialDisability the numUsmcMilPartialDisability to set
	 */
	public void setNumUsmcMilPartialDisability(Integer newValue) {
		this.numUsmcMilPartialDisability = newValue;
	}
	/**
	 * @return the numUsmcMilHospitalized
	 */
	public Integer getNumUsmcMilHospitalized() {
		return numUsmcMilHospitalized;
	}
	/**
	 * @param numUsmcMilHospitalized the numUsmcMilHospitalized to set
	 */
	public void setNumUsmcMilHospitalized(Integer newValue) {
		this.numUsmcMilHospitalized = newValue;
	}
	/**
	 * @return the numUsmcCivFatalities
	 */
	public Integer getNumUsmcCivFatalities() {
		return numUsmcCivFatalities;
	}
	/**
	 * @param numUsmcCivFatalities the numUsmcCivFatalities to set
	 */
	public void setNumUsmcCivFatalities(Integer newValue) {
		this.numUsmcCivFatalities = newValue;
	}
	/**
	 * @return the numUsmcCivPermanentDisability
	 */
	public Integer getNumUsmcCivPermanentDisability() {
		return numUsmcCivPermanentDisability;
	}
	/**
	 * @param numUsmcCivPermanentDisability the numUsmcCivPermanentDisability to set
	 */
	public void setNumUsmcCivPermanentDisability(Integer newValue) {
		this.numUsmcCivPermanentDisability = newValue;
	}
	/**
	 * @return the numUsmcCivPartialDisability
	 */
	public Integer getNumUsmcCivPartialDisability() {
		return numUsmcCivPartialDisability;
	}
	/**
	 * @param numUsmcCivPartialDisability the numUsmcCivPartialDisability to set
	 */
	public void setNumUsmcCivPartialDisability(Integer newValue) {
		this.numUsmcCivPartialDisability = newValue;
	}
	/**
	 * @return the numUsmcCivHospitalized
	 */
	public Integer getNumUsmcCivHospitalized() {
		return numUsmcCivHospitalized;
	}
	/**
	 * @param numUsmcCivHospitalized the numUsmcCivHospitalized to set
	 */
	public void setNumUsmcCivHospitalized(Integer newValue) {
		this.numUsmcCivHospitalized = newValue;
	}
	/**
	 * @return the numOtherFatalities
	 */
	public Integer getNumOtherFatalities() {
		return numOtherFatalities;
	}
	/**
	 * @param numOtherFatalities the numOtherFatalities to set
	 */
	public void setNumOtherFatalities(Integer newValue) {
		this.numOtherFatalities = newValue;
	}
	/**
	 * @return the numOtherPermanentDisability
	 */
	public Integer getNumOtherPermanentDisability() {
		return numOtherPermanentDisability;
	}
	/**
	 * @param numOtherPermanentDisability the numOtherPermanentDisability to set
	 */
	public void setNumOtherPermanentDisability(Integer newValue) {
		this.numOtherPermanentDisability = newValue;
	}
	/**
	 * @return the numOtherPartialDisability
	 */
	public Integer getNumOtherPartialDisability() {
		return numOtherPartialDisability;
	}
	/**
	 * @param numOtherPartialDisability the numOtherPartialDisability to set
	 */
	public void setNumOtherPartialDisability(Integer newValue) {
		this.numOtherPartialDisability = newValue;
	}
	/**
	 * @return the numOtherHospitalized
	 */
	public Integer getNumOtherHospitalized() {
		return numOtherHospitalized;
	}
	/**
	 * @param numOtherHospitalized the numOtherHospitalized to set
	 */
	public void setNumOtherHospitalized(Integer newValue) {
		this.numOtherHospitalized = newValue;
	}

	
	/**
	 * Accessor for instance field htId.
	 * @return The htId property value
	 */
	public String getHtId() {
		return htId;
	}

	/**
	 * Mutator for instance field htId.
	 * @param newValue The htId value to set
	 */
	public void setHtId(String newValue) {
		this.htId = newValue;
	}
	
	public String getDrafterEdiPi() {
		return drafterEdiPi;
	}
	
	public void setDrafterEdiPi(String newValue) {
		this.drafterEdiPi = newValue;
	}

	/**
	 * @return the dodPropertyCost
	 */
	public Double getDodPropertyCost() {
		return dodPropertyCost;
	}

	/**
	 * @param dodPropertyCost the dodPropertyCost to set
	 */
	public void setDodPropertyCost(Double dodPropertyCost) {
		this.dodPropertyCost = dodPropertyCost;
	}

	/**
	 * @return the pocFirstName
	 */
	public String getPocFirstName() {
		return pocFirstName;
	}
	/**
	 * @param pocFirstName the pocFirstName to set
	 */
	public void setPocFirstName(String newValue) {
		this.pocFirstName = newValue;
	}
	/**
	 * @return the pocLastName
	 */
	public String getPocLastName() {
		return pocLastName;
	}
	/**
	 * @param pocLastName the pocLastName to set
	 */
	public void setPocLastName(String newValue) {
		this.pocLastName = newValue;
	}
	/**
	 * @return the pocPhone
	 */
	public String getPocPhone() {
		return pocPhone;
	}
	/**
	 * @param pocPhone the pocPhone to set
	 */
	public void setPocPhone(String newValue) {
		this.pocPhone = newValue;
	}
	/**
	 * @return the pocEMail
	 */
	public String getPocEmail() {
		return pocEmail;
	}
	/**
	 * @param pocEmail the pocEMail to set
	 */
	public void setPocEmail(String newValue) {
		this.pocEmail = newValue;
	}

    /** The getter method for the cntryC property */
    public String getCntryC(){
        return cntryC;
    }
    /**
     * Set the cntryC property
     * @param cntryC The new value for the cntryC property
     */
    public void setCntryC(String cntryC){
        this.cntryC = cntryC;
    }
    /** The getter method for the stateProvC property */
    public String getStateProvC(){
        return stateProvC;
    }
    /**
     * Set the stateProvC property
     * @param stateProvC The new value for the stateProvC property
     */
    public void setStateProvC(String stateProvC){
        this.stateProvC = stateProvC;
    }
    
	public void setMishapTypes(List<CndIntlNtfcnMishapTypeBean> mishapTypes) {
		this.mishapTypes = mishapTypes;
	}

	public List<CndIntlNtfcnMishapTypeBean> getMishapTypes() {
		return mishapTypes;
	}

	public void addMishapType(CndIntlNtfcnMishapTypeBean t) {
		t.setCndIntlNtfcnBean(this);
		mishapTypes.add(t);
	}
	
//	public void removeMishapType(MishapTypeDecode decode) {
//		for(CndIntlNtfcnMishapTypeBean type : mishapTypes) {
//			if(decode.getCode().equals(type.getMishapTypeC().getCode())) {
//				removeMishapType(type);
//				break;
//			}
//		}
//		firePropertyChangeEvent(PROPERTY_REM_MISHAP_TYPE, null, decode);
//	}

	public void removeMishapType(String decode) {
		for(CndIntlNtfcnMishapTypeBean type : mishapTypes) {
			if(type.getMishapTypeC().equals(decode)) {
				removeMishapType(type);
				break;
			}
		}
	}
	
	public void removeMishapType(CndIntlNtfcnMishapTypeBean t) {
		mishapTypes.remove(t);
	}
	
//	@Override
//	public boolean hasIdentifier() {
//		return !(intlNtfcnSerl == null || intlNtfcnSerl.length() == 0);
//	}
	
	public String getEventNarr() {
		return eventNarr;
	}

	public void setEventNarr(String newVal) {
		eventNarr = newVal;
	}
	
	public String getPortC() {
		return portC;
	}

	public void setPortC(String newVal) {
		portC = newVal;
	}

	/**
	 * @return the installnC
	 */
	public String getInstallnC() {
		return installnC;
	}

	/**
	 * @param installnC the installnC to set
	 */
	public void setInstallnC(String installnC) {
		this.installnC = installnC;
	}

	/**
	 * @return the onBaseI
	 */
	public String getOnBaseI() {
		return onBaseI;
	}

	/**
	 * @param onBaseI the onBaseI to set
	 */
	public void setOnBaseI(String onBaseI) {
		this.onBaseI = onBaseI;
	}

	/**
	 * @return the vctmsEnvirC
	 */
	public String getVctmsEnvirC() {
		return vctmsEnvirC;
	}

	/**
	 * @param vctmsEnvirC the vctmsEnvirC to set
	 */
	public void setVctmsEnvirC(String vctmsEnvirC) {
		this.vctmsEnvirC = vctmsEnvirC;
	}

	/**
	 * @return the invlvdEnvirC
	 */
	public String getInvlvdEnvirC() {
		return invlvdEnvirC;
	}

	/**
	 * @param invlvdEnvirC the invlvdEnvirC to set
	 */
	public void setInvlvdEnvirC(String invlvdEnvirC) {
		this.invlvdEnvirC = invlvdEnvirC;
	}

	public String getNavyOpernI() {
		return navyOpernI;
	}

	public void setNavyOpernI(String navyOpernI) {
		this.navyOpernI = navyOpernI;
	}
}
