package DTO;

public class Player {
    private int playerId; // 사용자 고유식별자, 자동증가
    private String nickname; // 게임 내 사용될 닉네임
    private String gender; // 성별
    private String job; // 직업 유형
    private int age; // 나이
    private int health; // 건강
    private int study; // 공부
    private int hobby; // 취미
    private int happiness; // 행복
    private int money;
    private int uCode; // USERS 테이블의 UCODE 참조

    public Player() {
    }

    public Player(String nickname, String gender, String job, int age, int health, int study, int hobby, int happiness, int money, int isLoggedIn) {
        this.playerId = isLoggedIn;
        this.nickname = nickname;
        this.gender = gender;
        this.job = job;
        this.age = age;
        this.health = health; // 초기 건강 값
        this.study = study; // 초기 공부 값
        this.hobby = hobby; // 초기 취미 값
        this.happiness = happiness; // 초기 행복 값
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStudy() {
        return study;
    }

    public void setStudy(int study) {
        this.study = study;
    }

    public int getHobby() {
        return hobby;
    }

    public void setHobby(int hobby) {
        this.hobby = hobby;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getuCode() {
        return uCode;
    }

    public void setuCode(int uCode) {
        this.uCode = uCode;
    }
    // 여기서 나머지 getter와 setter 메소드 생략...

    @Override
    public String toString() {
        StringBuilder playerInfo = new StringBuilder();
        playerInfo.append("\n🌟 플레이어 정보 🌟")
                .append("\n🆔 고유 번호: ").append(playerId)
                .append("\n🔖 닉네임: ").append(nickname)
                .append("\n🔹 성별: ").append(gender)
                .append("\n💼 직업: ").append(job)
                .append("\n🔹 나이: ").append(age).append("세")
                .append("\n🩺 건강: ").append(health).append(health < 50 ? " (아프다)" : "")
                .append("\n📚 공부: ").append(study)
                .append("\n🎨 취미: ").append(hobby)
                .append("\n💵 머니: ").append(money)
                .append("\n😊 행복: ").append(happiness).append(happiness <= 50 ? " (정신에 이상이 있다)" : "");

        // 조건에 따른 게임 속 모험 권장 메시지 추가
        if (health < 50) {
            playerInfo.append("\n🚑 건강이 좋지 않습니다. 모험 중에 건강을 돌보세요!");
        }
        if (happiness <= 50) {
            playerInfo.append("\n😢 행복 수치가 낮습니다. 게임 속에서 기쁨을 찾으세요!");
        }

        // 모든 상태가 양호할 경우의 메시지
        if (health >= 50 && happiness > 50) {
            playerInfo.append("\n✨ 게임 속에서 자신의 삶을 즐기세요! ✨");
        }
        return playerInfo.toString();
    }

}
