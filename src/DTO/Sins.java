package DTO;

/**
 * 7대 죄악을 나타내는 클래스입니다.
 */
public class Sins {
    private int lust = 0;    // 정욕
    private int gluttony = 0; // 폭식
    private int greed = 0;   // 탐욕
    private int sloth = 0;   // 나태
    private int wrath = 0;   // 분노
    private int envy = 0;    // 시기
    private int pride = 0;   // 오만

    /**
     * 특정 죄악의 점수를 업데이트합니다.
     */
    public void updateScore(String sin) {
        switch(sin) {
            case "lust": lust++; break;
            case "gluttony": gluttony++; break;
            case "greed": greed++; break;
            case "sloth": sloth++; break;
            case "wrath": wrath++; break;
            case "envy": envy++; break;
            case "pride": pride++; break;
            default: System.out.println("잘못된 선택입니다.");
        }
    }

    // 각 죄악의 점수를 가져오는 메소드들
    public int getLust() { return lust; }
    public int getGluttony() { return gluttony; }
    public int getGreed() { return greed; }
    public int getSloth() { return sloth; }
    public int getWrath() { return wrath; }
    public int getEnvy() { return envy; }
    public int getPride() { return pride; }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("당신에게는 다음과 같은 죄악이 있습니다:\n\n");

        description.append(lust >= 1 ? "정욕 : 마음을 지배하는 욕망의 불길이 타오릅니다.\n" : "");
        description.append(gluttony >= 1 ? "폭식 : 적당함을 모르고, 끝없는 욕구에 사로잡혀 있습니다.\n" : "");
        description.append(greed >= 1 ? "탐욕 : 끝없는 욕심이 당신을 어둠 속으로 이끕니다.\n" : "");
        description.append(sloth >= 1 ? "나태 : 무기력한 태도가 당신의 발전을 가로막고 있습니다.\n" : "");
        description.append(wrath >= 1 ? "분노 : 작은 일에도 쉽게 화를 내며, 주변 사람들을 멀어지게 합니다.\n" : "");
        description.append(envy >= 1 ? "시기 : 다른 이의 행복을 보며 자신만의 불행을 탓합니다.\n" : "");
        description.append(pride >= 1 ? "오만 : 자만심이 높아, 자신의 한계를 인정하지 않습니다.\n" : "");

        if(description.toString().equals("당신에게는 다음과 같은 죄악이 있습니다:\n\n")) {
            description.append("비교적 죄악의 영향을 덜 받고 있습니다. 그러나 항상 자신을 되돌아보는 것이 중요합니다.");
        }

        return description.toString();
    }
}
