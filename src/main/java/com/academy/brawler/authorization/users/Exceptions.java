package com.academy.brawler.authorization.users;

public class Exceptions {
    public static class LoginException extends Exception{
        LoginException(String s){
            super(s);
        }
    }

    public static class RegistrationException extends Exception{
        RegistrationException(String s){
            super(s);
        }
    }

    public static class JsonToUserException extends Exception{
        JsonToUserException(String s){
            super(s);
        }
    }

    public static class UserToJsonException extends Exception{
        UserToJsonException(String s){
            super(s);
        }
    }
}
