package org.surreal.lobster.sharedcore.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.surreal.lobster.lantaru.usercache.reader.CachedUserAccount;
import org.surreal.lobster.lantaru.usercache.reader.search.CachedUserAccountIndexSearchExecutor;
import org.surreal.lobster.lantaru.usercache.reader.search.MultipleUserSearchCommand;
import org.surreal.lobster.lantaru.usercache.reader.search.SingleUserSearchCommand;
import org.surreal.lobster.lantaru.usercache.reader.search.context.SearchContextBuilder;
import org.surreal.lobster.lantaru.usercache.reader.search.context.UserAccountIndexReaderSearchContext;
import org.surreal.lobster.sharedcore.constants.AccountStatus;
import org.surreal.lobster.sharedcore.constants.AccountType;

/**
 * INFO:SEAN - IN THE FUTURE DO NOT USE THIS INTERFACE
 * USE THE USER ACCOUNT INDEX READER FACTORY FROM THE LANTARU JAR
 * DO NOT USE
 * DO NOT USE
 * DO NOT DO NOT DO NOT USE
 * PLEASE PLEASE DO NOT USE THIS
 * DON'T CONTINUE THE NASTINESS
 */
public class LantaruUserAccountCacheEntryLocator implements IUserAccountCacheEntryLocator {

	private final CachedUserAccountIndexSearchExecutor indexSearcher;

	public LantaruUserAccountCacheEntryLocator() {
		indexSearcher = new CachedUserAccountIndexSearchExecutor();
	}

	@Override
	public UserAccount findUserNullOk(final String ediPi) {
		final UserAccountIndexReaderSearchContext searchContext = new SearchContextBuilder().addEdiPi(ediPi).build();
		final CachedUserAccount cachedUserAccount = indexSearcher.searchIndex(new SingleUserSearchCommand(searchContext));
		if (cachedUserAccount == null) {
			return null;
		} else {
			return convert(cachedUserAccount);
		}
	}

	@Override
	public UserAccount findUser(final String ediPi) {
		final UserAccount userAccount = findUserNullOk(ediPi);
		if (userAccount == null) {
			throw new IllegalStateException("could not find user with ediPi " + ediPi);
		} else {
			return userAccount;
		}
	}
	
	public CachedUserAccount find(final String ediPi) {
		final UserAccountIndexReaderSearchContext searchContext = new SearchContextBuilder().addEdiPi(ediPi).build();
		final CachedUserAccount cachedUserAccount = indexSearcher.searchIndex(new SingleUserSearchCommand(searchContext));
		if (cachedUserAccount == null) {
			throw new IllegalStateException("could not find user with ediPi " + ediPi);
		}
		return cachedUserAccount;
	}

	@Override
	public UserAccount findUserFromEmail(final String emailAddress) {
		final UserAccountIndexReaderSearchContext searchContext = new SearchContextBuilder().addEmailAddress(emailAddress).build();
		final CachedUserAccount cachedUserAccount = indexSearcher.searchIndex(new SingleUserSearchCommand(searchContext));
		if (cachedUserAccount == null) {
			return null;
		} else {
			return convert(cachedUserAccount);
		}
	}

	@Override
	public List<UserAccount> findSafetyAuthorities(final String uic) {
		// create a search context with the given uic and the account type of SA since we're searching for safety authorities
		final UserAccountIndexReaderSearchContext searchContext =
				new SearchContextBuilder().addUic(uic).addAccountType(SearchContextBuilder.ACCOUNT_TYPE_SA).build();
		final List<CachedUserAccount> cachedUserAccounts = indexSearcher.searchIndex(new MultipleUserSearchCommand(searchContext));
		final List<UserAccount> userAccounts = new ArrayList<UserAccount>(cachedUserAccounts.size());
		for (final CachedUserAccount cachedUserAccount : cachedUserAccounts) {
			userAccounts.add(convert(cachedUserAccount));
		}
		return userAccounts;
	}

	private List<CachedUserAccount> getUserAccountsByPermission(String permission) {
		final UserAccountIndexReaderSearchContext searchContext = new SearchContextBuilder().addPermission(permission).build();
		final List<CachedUserAccount> cachedUserAccounts = indexSearcher.searchIndex(new MultipleUserSearchCommand(searchContext));
		return cachedUserAccounts;
	}
	
	@Override
	public List<String> getNotifiableEdiPiInPermission(String permission) {
		final List<CachedUserAccount> userAccounts = getUserAccountsByPermission(permission);
		final List<String> ediPis = new ArrayList<String>(userAccounts.size());
		for (final CachedUserAccount userAccount : userAccounts) {
			ediPis.add(userAccount.getEdiPi());
		}
		return ediPis;
	}
	
	@Override
	public List<String> getNotifiableEmailInPermission(final String permission) {
		final List<CachedUserAccount> cachedUserAccounts = getUserAccountsByPermission(permission);
		final List<String> emailAddresses = new ArrayList<String>(cachedUserAccounts.size());
		for (final CachedUserAccount cachedUserAccount : cachedUserAccounts) {
			emailAddresses.add(cachedUserAccount.getEmailAddress());
		}
		return emailAddresses;
	}

	@Override
	public List<String> getNotifiableEdiPiInPermissionAndUic(final String permission, final String uic) {
		final List<UserAccount> userAccounts = getUserAccountsInPermissionAndUic(permission, uic);
		final List<String> ediPis = new ArrayList<String>(userAccounts.size());
		for (final UserAccount userAccount : userAccounts) {
			ediPis.add(userAccount.getEdiPi());
		}
		return ediPis;
	}

	@Override
	public List<String> getNotifiableEmailInPermissionAndUic(final String permission, final String uic) {
		/*
		 * THE ONLY PLACE IT LOOKS LIKE THIS IS GETTING CALLED FROM IS THE CoiNotifierImpl CLASS.
		 * 
		 * CHANGING IT TO ONLY INCLUDE ACTIVE ACCOUNTS
		 */
		final UserAccountIndexReaderSearchContext searchContext =
				new SearchContextBuilder()
					.addAccountStatus(SearchContextBuilder.ACCOUNT_STATUS_ACTIVE)
					.addUic(uic)
					.addPermission(permission)
					.build();
		final List<CachedUserAccount> cachedUserAccounts = indexSearcher.searchIndex(new MultipleUserSearchCommand(searchContext));
		final List<String> emailAddresses = new ArrayList<String>(cachedUserAccounts.size());
		for (final CachedUserAccount cachedUserAccount : cachedUserAccounts) {
			emailAddresses.add(cachedUserAccount.getEmailAddress());
		}
		return emailAddresses;
	}

	@Override
	public List<UserAccount> getUserAccountsInPermissionAndUic(final String permission, final String uic) {
		final UserAccountIndexReaderSearchContext searchContext =
				new SearchContextBuilder().addUic(uic).addPermission(permission).build();
		final List<CachedUserAccount> cachedUserAccounts = indexSearcher.searchIndex(new MultipleUserSearchCommand(searchContext));
		final List<UserAccount> userAccounts = new ArrayList<UserAccount>(cachedUserAccounts.size());
		for (final CachedUserAccount cachedUserAccount : cachedUserAccounts) {
			userAccounts.add(convert(cachedUserAccount));
		}
		return userAccounts;
	}

	public List<UserAccount> getUsersByAccountType(final String query, final AccountType accountType) {
		final UserAccountIndexReaderSearchContext searchContext =
				new SearchContextBuilder().addLastName(query).addAccountType(accountType.toString()).build();
		final List<CachedUserAccount> cachedUserAccounts = indexSearcher.searchIndex(new MultipleUserSearchCommand(searchContext));
		final List<UserAccount> userAccounts = new ArrayList<UserAccount>();
		for (final CachedUserAccount cachedUserAccount : cachedUserAccounts) {
			userAccounts.add(convert(cachedUserAccount));
		}
		return userAccounts;
	}

	@Override
	public Set<String> getUserPermissions(final String ediPi) {
		final UserAccountIndexReaderSearchContext searchContext = new SearchContextBuilder().addEdiPi(ediPi).build();
		final CachedUserAccount cachedUserAccount = indexSearcher.searchIndex(new SingleUserSearchCommand(searchContext));
		final Set<String> permissions = new HashSet<String>();
		if (cachedUserAccount != null) {
			permissions.addAll(cachedUserAccount.getPermissions());
		}
		return permissions;
	}

	@Override
	public String getUserAccountType(final String ediPi) {
		final UserAccountIndexReaderSearchContext searchContext = new SearchContextBuilder().addEdiPi(ediPi).build();
		final CachedUserAccount cachedUserAccount = indexSearcher.searchIndex(new SingleUserSearchCommand(searchContext));
		return cachedUserAccount.getAccountType();
	}
	
	@Override
	public List<String> getNotifiableEdiPiInRole(String role) {
		final UserAccountIndexReaderSearchContext searchContext = 
				new SearchContextBuilder().addRole(role).build();
		final List<CachedUserAccount> cachedUserAccounts = indexSearcher.searchIndex(new MultipleUserSearchCommand(searchContext));
		final List<String> ediPis = new ArrayList<String>(cachedUserAccounts.size());
		for (final CachedUserAccount cachedUserAccount : cachedUserAccounts) {
			ediPis.add(cachedUserAccount.getEdiPi());
		}
		return ediPis;
	}
	
	@Override
	public List<String> getNotifiableEdiPiInRoleAndUic(String role, String uic) {
		final UserAccountIndexReaderSearchContext searchContext =
				new SearchContextBuilder().addRole(role).addUic(uic).build();
		final List<CachedUserAccount> cachedUserAccounts = indexSearcher.searchIndex(new MultipleUserSearchCommand(searchContext));
		final List<String> ediPis = new ArrayList<String>(cachedUserAccounts.size());
		for (final CachedUserAccount cachedUserAccount : cachedUserAccounts) {
			ediPis.add(cachedUserAccount.getEdiPi());
		}
		return ediPis;
	}
	
	@Override
	public List<String> getNotifiableEmailInRole(String role) {
		final UserAccountIndexReaderSearchContext searchContext = 
				new SearchContextBuilder().addRole(role).build();
		final List<CachedUserAccount> cachedUserAccounts = indexSearcher.searchIndex(new MultipleUserSearchCommand(searchContext));
		final List<String> emails = new ArrayList<String>(cachedUserAccounts.size());
		for (final CachedUserAccount cachedUserAccount : cachedUserAccounts) {
			emails.add(cachedUserAccount.getEmailAddress());
		}
		return emails;
	}
	
	@Override
	public List<String> getNotifiableEmailInRoleAndUic(String role, String uic) {
		final UserAccountIndexReaderSearchContext searchContext = 
				new SearchContextBuilder().addRole(role).addUic(uic).build();
		final List<CachedUserAccount> cachedUserAccounts = indexSearcher.searchIndex(new MultipleUserSearchCommand(searchContext));
		final List<String> emails = new ArrayList<String>(cachedUserAccounts.size());
		for (final CachedUserAccount cachedUserAccount : cachedUserAccounts) {
			emails.add(cachedUserAccount.getEmailAddress());
		}
		return emails;
	}

	private UserAccount convert(final CachedUserAccount cachedUserAccount) {
		final UserAccount userAccount = new UserAccountClientBean();
		userAccount.setAccountStatus(AccountStatus.valueOf(cachedUserAccount.getAccountStatus()));
		userAccount.setAccountType(AccountType.valueOf(cachedUserAccount.getAccountType()));
		userAccount.setCitizen(cachedUserAccount.getCitizen());
		userAccount.setEdiPi(cachedUserAccount.getEdiPi());
		userAccount.setEmail(cachedUserAccount.getEmailAddress());
		userAccount.setFirstName(cachedUserAccount.getFirstName());
		userAccount.setLastName(cachedUserAccount.getLastName());
		userAccount.setMiddleName(cachedUserAccount.getMiddleName());
		userAccount.setPhone(cachedUserAccount.getPhoneNumber());
		userAccount.setRank(cachedUserAccount.getRank());
		userAccount.setUic(cachedUserAccount.getUic());
		return userAccount;
	}
}
