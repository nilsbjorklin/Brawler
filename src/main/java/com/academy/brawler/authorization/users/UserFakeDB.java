package com.academy.brawler.authorization.users;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserFakeDB {
    private static final String FILE_LOCATION = "users.txt";
    private static final File USERS_FILE = new File(FILE_LOCATION);

    public static List<CustomUser> getAllUsers() throws SecurityException {
        try {
            List<CustomUser> allUsers = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(getFileReader());
            String userLine = bufferedReader.readLine();
            while (userLine != null) {
                allUsers.add(CustomUser.createUserJson(userLine));
                userLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            return allUsers;
        } catch (Exception e) {
            throw new SecurityException(String.format("public UserFakeDB getAllUsers: %s -> %s%n", e.getClass().getSimpleName(), e.getLocalizedMessage()));
        }
    }


    public static CustomUser getUser(final String email) throws SecurityException, IOException, Exceptions.JsonToUserException {
        BufferedReader bufferedReader = new BufferedReader(getFileReader());
        String userLine = bufferedReader.readLine();
        while (userLine != null) {
            CustomUser customUser = CustomUser.createUserJson(userLine);
            if (customUser.getEmail()
                    .equals(email)) {
                bufferedReader.close();
                return customUser;
            }
            userLine = bufferedReader.readLine();
        }
        bufferedReader.close();

        return null;
    }

    public static boolean addUser(final CustomUser user) throws SecurityException {
        try {
            FileWriter fileWriter = getFileWriter();
            fileWriter.write(user.formatForFile());
            fileWriter.close();

            return true;
        } catch (Exception e) {
            throw new SecurityException(String.format("UserFakeDB addUser: %s -> %s%n", e.getClass().getSimpleName(), e.getLocalizedMessage()));
        }
    }

    public static FileReader getFileReader() throws IOException {
        checkFile();
        return new FileReader(USERS_FILE);
    }


    public static FileWriter getFileWriter() throws IOException {
        checkFile();
        return new FileWriter(USERS_FILE, true);
    }

    private static boolean checkFile() throws IOException {
        if (USERS_FILE.exists() || USERS_FILE.createNewFile()) {
            return true;
        } else {
            throw new IOException("Could not read file");
        }
    }
}
