package model;

public class User implements Comparable {

    private String username;
    private int score;

    public User(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int compareTo(Object o) {
        User other = (User) o;
        return other.getScore() - this.getScore();
    }
}
