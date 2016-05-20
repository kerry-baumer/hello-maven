/**
 * Oct 9, 2012 3:14:18 PM
 */
package org.surreal.lobster.sharedcore.user;

import java.util.ArrayList;
import java.util.List;

import org.surreal.lobster.sam.model.Role;
import org.surreal.lobster.sharedcore.constants.AccountStatus;
import org.surreal.lobster.sharedcore.constants.AccountType;

/**
 * Client resolution bean for supporting Cassandra.
 * @author daniel.wilkin
 */
public class UserAccountClientBean implements PrivilegedUser {

	/** The serialVersionUID property */
	private static final long serialVersionUID = -4500813101100430942L;

	private String ediPi;
	private String firstName;
	private String middleName;
	private String lastName;
	private String rank;
	private String uic;
	private String phone;
	private String email;
	private String citizen;
	private AccountStatus accountStatus;
	private AccountType accountType;
	private List<Role> userRoles = new ArrayList<Role>();
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getEdiPi()
	 */
	@Override
	public String getEdiPi() {
		return ediPi;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setEdiPi(java.lang.String)
	 */
	@Override
	public void setEdiPi(String ediPi) {
		this.ediPi = ediPi;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getMiddleName()
	 */
	@Override
	public String getMiddleName() {
		return middleName;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setMiddleName(java.lang.String)
	 */
	@Override
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getRank()
	 */
	@Override
	public String getRank() {
		return rank;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setRank(java.lang.String)
	 */
	@Override
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getUic()
	 */
	@Override
	public String getUic() {
		return uic;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setUic(java.lang.String)
	 */
	@Override
	public void setUic(String uic) {
		this.uic = uic;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getPhone()
	 */
	@Override
	public String getPhone() {
		return phone;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setPhone(java.lang.String)
	 */
	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getCitizen()
	 */
	@Override
	public String getCitizen() {
		return citizen;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setCitizen(java.lang.String)
	 */
	@Override
	public void setCitizen(String citizen) {
		this.citizen = citizen;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getAccountStatus()
	 */
	@Override
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setAccountStatus(org.surreal.lobster.sharedcore.constants.AccountStatus)
	 */
	@Override
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getAccountType()
	 */
	@Override
	public AccountType getAccountType() {
		return accountType;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setAccountType(org.surreal.lobster.sharedcore.constants.AccountType)
	 */
	@Override
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#getUserRoles()
	 */
	@Override
	public List<Role> getUserRoles() {
		return userRoles;
	}

	/* (non-Javadoc)
	 * @see org.surreal.lobster.sharedcore.model.UserAccount#setUserRoles(java.util.List)
	 */
	@Override
	public void setUserRoles(List<Role> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "[" + getDetailString() + "]";
	}

	private String getDetailString() {
		return new StringBuilder()
				.append(getEdiPi()).append('-')
				.append(getAccountType()).append('-')
				.append(getUic()).append('-')
				.append(getAccountStatus()).append('-')
				.append(getEmail()).append('-')
				.append(getUserRoles())
				.toString();
	}
	
}