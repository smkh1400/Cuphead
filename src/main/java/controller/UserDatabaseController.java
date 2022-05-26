package controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDatabaseController {

    private static UserDatabaseController instance;

    public static UserDatabaseController getInstance() {
        if (instance == null)
            instance = new UserDatabaseController();
        return instance;
    }

    private void updateDatabase(ArrayList<HashMap<String, String>> users) {
        try {
            FileWriter writer = new FileWriter("src/main/java/model/usersDatabase.json");
            writer.write(new Gson().toJson(users));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<HashMap<String, String>> loadDatabase() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/java/model/usersDatabase.json")));
            ArrayList<HashMap<String, String>> users;
            users = new Gson().fromJson(json, new TypeToken<List<HashMap<String, String>>>() {
            }.getType());
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean usernameExists(String username) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username))
                return true;
        }
        return false;
    }

    public String getPasswordWithUsername(String username) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                return users.get(i).get("password");
            }
        }
        return null;
    }

    public void addUser(String username, String password) { // add new user to database
        ArrayList<HashMap<String, String>> users = loadDatabase();
        HashMap<String, String> userNew = new HashMap<>();
        userNew.put("username", username);
        userNew.put("password", password);
        userNew.put("score", "0");
        users.add(userNew);
        this.updateDatabase(users);
    }

    public void removeUser(String username) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username))
                users.remove(i);
        }
        this.updateDatabase(users);
    }

    public int getScore(String username) {
        ArrayList<HashMap<String, String >> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                return Integer.parseInt(users.get(i).get("score"));
            }
        }
        return 0;
    }

    public void setScore(String username, int newScore) {
        ArrayList<HashMap<String , String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                users.get(i).put("score", String.valueOf(newScore));
            }
        }
    }

    public void changePassword(String username, String newPassword) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                users.get(i).put("password", newPassword);
            }
        }
        this.updateDatabase(users);
    }

    public void changeUsername(String oldUsername, String newUsername) {
        ArrayList<HashMap<String , String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(oldUsername))
                users.get(i).put("username", newUsername);
        }
        this.updateDatabase(users);
    }

//    public static User getUserByUsername(String username) {
//        ArrayList<HashMap<String, String>> users = loadDatabase();
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).get("username").equals(username)) {
//                HashMap<String, String> userData = users.get(i);
//                User user = new User(userData.get("username"), userData.get("nickname"), userData.get("password"));
//                return user;
//            }
//        }
//        return null;
//    }

//    public static User getUserByNickname(String nickname) {
//        ArrayList<HashMap<String, String>> users = loadDatabase();
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).get("nickname").equals(nickname)) {
//                HashMap<String, String> userData = users.get(i);
//                User user = new User(userData.get("username"), userData.get("nickname"), userData.get("password"));
//                return user;
//            }
//        }
//
//        return null;
//    }
}