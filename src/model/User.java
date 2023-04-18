package model;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String recoveryQuestion;
    private String slogan;
    private int recoveryQuestionNumber;
    private int highScore;
    private int rank;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public void setRecoveryQuestion(String recoveryQuestion) {
        this.recoveryQuestion = recoveryQuestion;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getRecoveryQuestionNumber() {
        return recoveryQuestionNumber;
    }

    public void setRecoveryQuestionNumber(int recoveryQuestionNumber) {
        this.recoveryQuestionNumber = recoveryQuestionNumber;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
