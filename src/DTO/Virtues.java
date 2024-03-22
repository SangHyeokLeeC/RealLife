package DTO;

/**
 * 7대 덕목을 나타내는 클래스입니다.
 */
public class Virtues {
    private int chastity = 0;  // 정절
    private int temperance = 0; // 절제
    private int charity = 0;    // 자선
    private int diligence = 0;  // 근면
    private int patience = 0;   // 인내
    private int kindness = 0;   // 친절
    private int humility = 0;   // 겸손

    /**
     * 특정 덕목의 점수를 업데이트합니다.
     */
    public void updateScore(String virtue) {
        switch (virtue) {
            case "chastity":
                chastity++;
                break;
            case "temperance":
                temperance++;
                break;
            case "charity":
                charity++;
                break;
            case "diligence":
                diligence++;
                break;
            case "patience":
                patience++;
                break;
            case "kindness":
                kindness++;
                break;
            case "humility":
                humility++;
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    // 각 덕목의 점수를 가져오는 메소드들
    public int getChastity() {
        return chastity;
    }

    public int getTemperance() {
        return temperance;
    }

    public int getCharity() {
        return charity;
    }

    public int getDiligence() {
        return diligence;
    }

    public int getPatience() {
        return patience;
    }

    public int getKindness() {
        return kindness;
    }

    public int getHumility() {
        return humility;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("당신은 ");

        description.append(chastity >= 1 ? "정결함, " : "더 정결해질 필요가 있음, ");
        description.append(temperance >= 1 ? "절제를 잘함, " : "절제가 필요함, ");
        description.append(charity >= 1 ? "자선심이 많음, " : "자선을 늘릴 필요가 있음, ");
        description.append(diligence >= 1 ? "근면함, " : "더 열심히 할 필요가 있음, ");
        description.append(patience >= 1 ? "인내심이 강함, " : "인내심을 키울 필요가 있음, ");
        description.append(kindness >= 1 ? "친절함, " : "더 친절해질 수 있음, ");
        description.append(humility >= 1 ? "겸손함." : "더 겸손해질 필요가 있음.");

        // 마지막 쉼표 제거
        if (description.lastIndexOf(", ") != -1) {
            description = new StringBuilder(description.substring(0, description.lastIndexOf(", ")));
        }

        description.append(".");

        return description.toString();
    }
}
