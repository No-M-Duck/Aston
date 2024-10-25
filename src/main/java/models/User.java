package models;

public class User {
    private String name;
    private String password;
    private String email;

    private User(UserBuilder userBuilder) {
        this.name = userBuilder.name;
        this.password = userBuilder.password;
        this.email = userBuilder.email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name  + ", email=" + email + ", password=" + password + '}';
    }

    public static class UserBuilder{
        String name;
        String password;
        String email;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
