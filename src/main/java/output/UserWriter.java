package output;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserWriter extends CollectionWriter<User> {
    public void toFile (List<User> users) {
        ArrayList<String> usersOutput = new ArrayList<>();
        for (User user : users) {
            String userString = user.getName()+delimiter+user.getEmail()+delimiter+user.getPassword();
            usersOutput.add(userString);
        }
        writeFile("users", usersOutput);
    }
}
