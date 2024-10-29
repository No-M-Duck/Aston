package output;

import models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserWriter extends CollectionWriter<User> {
    public void toFile (List<User> users) throws IOException {
        ArrayList<String> usersOutput = new ArrayList<>();
        for (User user : users) {
            String userString = getString(user);
            usersOutput.add(userString);
        }
        writeCollection("users", usersOutput);
    }

    public void toFile (User user) throws IOException {
        String userString = getString(user);
        writeElement("users_found", userString);
    }

    private String getString (User user) {
        return user.getName()+delimiter+user.getEmail()+delimiter+user.getPassword();
    }
}
