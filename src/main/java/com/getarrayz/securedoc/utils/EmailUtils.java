package com.getarrayz.securedoc.utils;

import static java.lang.StringTemplate.STR;

public class EmailUtils {

    //todo переделать это безумие, такие шаблоны - это не серьезно
    public static String getEmailMessage(
            String name,
            String host,
            String token
    ) {
        final var verificationUrl = getVerificationUrl(host, token);
        return String.format(
                """
                        Hello %s,
                        Your new account has been created. Please click on the link below to verify your account.
                        Click here: %s
                        Your Support Team.
                        """,
                name,
                verificationUrl

        );
    }

    public static String getVerificationUrl(String token, String host) {
        return String.format(
                """
                        %s/verify/account?token=%s
                        """,
                token,
                host
        );
    }


    //todo переделать это безумие, такие шаблоны - это не серьезно
    public static String getResetPasswordMessage(
            String name,
            String host,
            String token
    ) {
        final var verificationUrl = getResetPasswordUrl(host, token);
        return String.format(
                """
                        Hello %s,
                        Your password has been changed. Please click on the link below to verify your account.
                        Click here: %s
                        Your Support Team.
                        """,
                name,
                verificationUrl

        );
    }

    public static String getResetPasswordUrl(String token, String host) {
        return String.format(
                """
                        %s/verify/password?token=%s
                        """,
                token,
                host
        );
    }

}
