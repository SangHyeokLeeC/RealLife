package DTO;

import java.util.Scanner;

public class NPC {
    private String name;
    private String gender;
    private int affection;

    public NPC(int isLoggedIn, String friendName, String genderNPC, int affection) {
        this.name = friendName;
        this.gender = genderNPC;
        this.affection = affection;
    }

    public NPC() {

    }

    // 호감도 증가 메소드
    public void increaseAffection(int amount) {
        this.affection = Math.min(100, this.affection + amount);
    }

    // 호감도 감소 메소드
    public void decreaseAffection(int amount) {
        this.affection = Math.max(0, this.affection - amount);
    }

    // NPC 정보 출력 메소드
    @Override
    public String toString() {
        String affectionDescription;
        if (affection >= 0 && affection <= 9) {
            affectionDescription = "당신을 죽이고 싶어합니다..";
        } else if (affection >= 10 && affection <= 19) {
            affectionDescription = "당신을 혐오하고 있습니다.";
        } else if (affection >= 20 && affection <= 30) {
            affectionDescription = "당신을 친구라고 생각하고 있습니다.";
        } else if (affection >= 31 && affection <= 50) {
            affectionDescription = "당신을 신뢰하고 있습니다.";
        } else if (affection >= 51 && affection <= 70) {
            affectionDescription = "당신에게 호감이 있습니다.";
        } else if (affection >= 71 && affection <= 100) {
            affectionDescription = "당신을 사랑하고 있습니다.";
        } else {
            affectionDescription = "정의되지 않은 상태입니다.";
        }

        return "\n" + " 🌟" + name + "의 정보 🌟" +
                "\n🔹 이름: " + name +
                "\n🔹 성별: " + gender +
                // 나이 정보는 제거하거나, 나이 필드가 있다면 그 값을 포함시키세요.
                "\n🔹 호감도: " + affection + "\n" + " (" + affectionDescription + ")";
    }

    // Getter 메소드
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAffection() {
        return affection;
    }
}
