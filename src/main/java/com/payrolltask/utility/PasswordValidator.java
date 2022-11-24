package com.payrolltask.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator 
{
	// digit + lowercase char + uppercase char + punctuation + symbol=no white space
    private static final String PASSWORD_PATTERN = "^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$";

    private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(final String password) {
        Matcher matcher = PATTERN.matcher(password);
        return matcher.matches();
    }
	private static final String MAIL_PATTERN = "^\\S+@\\S+\\.\\S+$";
	private static final Pattern mail = Pattern.compile(MAIL_PATTERN);

	public static boolean isValidforEmail(final String email) 
	{
         Matcher matcher = mail.matcher(email);
         return matcher.matches();
	}

}
