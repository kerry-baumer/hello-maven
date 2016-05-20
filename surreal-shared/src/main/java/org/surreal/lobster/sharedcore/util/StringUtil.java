package org.surreal.lobster.sharedcore.util;


public class StringUtil {

	public static String titleCase(String text) {
		final StringBuilder titleCaseResult = new StringBuilder();
		final String noUnderscores = text.replace('_', ' ');
		
		final String[] words = noUnderscores.split(" ");
		for (int i = 0; i < words.length; i++) {
			final String word = words[i];
			if (!word.trim().isEmpty()) {
				final String firstLetter = word.substring(0,1).toUpperCase();
				final String restOfTheWord = word.toLowerCase().substring(1,word.length());
				titleCaseResult.append(firstLetter);
				titleCaseResult.append(restOfTheWord);
				titleCaseResult.append(" ");
			}
		}
		return titleCaseResult.toString().trim();
	}
	
	/**
	 * @param c The character to evaluate
	 * @return <code>True</code> if the character is a hexadecimal digit, <code>false</code>
	 * otherwise
	 */
	public static boolean isHexCharacter(char c) {
		if (Character.isDigit(c)) {
			return true;
		}
		switch (Character.toUpperCase(c)) {
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
			return true;
		default:
		}
		return false;
	}

	/**
	 * Evaluates a string to determine if it is hex.
	 * @param str The potentially hex string
	 * @return <code>True</code> if the string is a hexadecimal value, <code>false</code> otherwise
	 */
	public static boolean isHex(String str) {
		String id = str.trim();
		if (id.startsWith("0x")) {
			id = id.substring(2);
		}
		final char[] chars = id.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!isHexCharacter(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param str The string to evaluate
	 * @return <code>True</code> if the string has alpha characters, <code>false</code> otherwise
	 */
	public static boolean hasAlpha(String str) {
		final String id = str.trim();
		final char[] chars = id.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isLetter(chars[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Converts the specified string to it's decimal equivalent if the string is
	 * hexadecimal.
	 * @param hex The hexidecimal string
	 * @return The equivalent decimal value of the hexadecimal string or <code>null</code>
	 * if it could not be converted
	 */
	public static Long convertHexToLong(final String hex) {
		final String hexClean = hex.trim();
		final Long result;
		if (hasAlpha(hexClean)) {
			result = isHex(hexClean) ? Long.parseLong(hexClean, 16) : null;
		} else {
			result = Long.parseLong(hexClean);
		}
		return result;
	}
	
	/**
	 * Determines if the given string is alpha-numeric
	 * @param s string to evaulate
	 * @return true if the string doesn't contain any special characters
	 */
	public static boolean isAlphaNumeric(String s) {
		if (s == null) { return false; } // fast fail null

		boolean result = true;
		char[] charArray = (s).toCharArray();
		for (char c : charArray) {
			int i = c;
			// 48 - 57 - numbers
			// 65 - 90 - upper case
			// 97 - 122 - lower case
			if (!((i >= 48 && i <= 57) ||
				(i >= 65 && i <= 90) ||
				(i >= 97 && i <= 122))) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	public static boolean isInteger(String str) {
		try  {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * @param word
	 * @return true if null or empty
	 */
	public static boolean isBlank(String word) {
		return word == null || word.isEmpty();
	}
	
}
