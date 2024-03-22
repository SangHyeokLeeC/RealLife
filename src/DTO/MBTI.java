package DTO;

public class MBTI {
    private int I = 0;
    private int E = 0;
    private int N = 0;
    private int S = 0;
    private int T = 0;
    private int F = 0;
    private int J = 0;
    private int P = 0;

    // 사용자의 선택에 따라 지표 값을 조정하는 메소드
    public void updateScore(String choice) {
        switch (choice) {
            case "I":
                I++;
                break;
            case "E":
                E++;
                break;
            case "N":
                N++;
                break;
            case "S":
                S++;
                break;
            case "T":
                T++;
                break;
            case "F":
                F++;
                break;
            case "J":
                J++;
                break;
            case "P":
                P++;
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    // 최종 MBTI 유형을 결정하는 메소드
    public String determineMBTI() {
        String mbti = "";
        mbti += (I >= E) ? "I" : "E";
        mbti += (N >= S) ? "N" : "S";
        mbti += (T >= F) ? "T" : "F";
        mbti += (J >= P) ? "J" : "P";
        return mbti;
    }

    // 각 MBTI 지표 점수 가져오기 메소드
    public int getI() {
        return I;
    }

    public int getE() {
        return E;
    }

    public int getN() {
        return N;
    }

    public int getS() {
        return S;
    }

    public int getT() {
        return T;
    }

    public int getF() {
        return F;
    }

    public int getJ() {
        return J;
    }

    public int getP() {
        return P;
    }


    @Override
    public String toString() {
        String mbti = determineMBTI();
        String description = "";

        switch (mbti) {
            case "ISTJ":
                description = "\n성실하고 계획적인 관리자형";
                description += "\n키워드 : 책임감 원탑 , 완벽주의 데이터";
                description += "\n혼자있는 시간을 소중하게 생각하는 독립왕";
                description += "\n감정보다는 논리!";
                description += "\n질서가 최고!뭐든 계획을 세워 순차적!으로 진행 ";
                description += "\n낯가림은 심한편으로 시간이 필요해!";
                description += "\n내 사전엔 실수는 없어!";
                description += "\n문제 해결은 나에게 맞겨!";
                description += "\n책임감하면 바로 ISTJ!";
                description += "\n약속 시간은 지켜줄래?";
                description += "\n한번 목표를 설정하면 끝까지해야해";
                description += "\n싫어요 : 강요하는 것, 훈수두는 것, 예의없는 것";
                break;


            case "ISFJ":
                description = "\n상냥하고 헌신적인 옹호자형";
                description += "\n키워드 : 유리멘탈 , 소확행 데이터";
                description += "\n솔직하며 빈말을 못함";
                description += "\n외유내강 그 잡채!";
                description += "\n끈기있고 성실하다? 바로 ISFJ";
                description += "\n내성적인 편이면서 외향적인거같아";
                description += "\n감정에 공감이 특기!";
                description += "\n새로운 환경은 무서워";
                description += "\n눈치는 빠르지만 모르는척하는 편";
                description += "\n싫어요 : 허세와 자만, 싸움과 갈등 상황 , 무언가 정하지 않고 함 ";
                break;


            case "INFJ":
                description = "\n이상적이고 성실한 제창자형";
                description += "\n키워드 : 지킬앤하이드, 시뮬레이션 데이터";
                description += "\n내 마음속 이야기를 듣기는 힘들지";
                description += "\n조용하지만 다들 날 봐주었으면 좋겠어";
                description += "\n나만의 가치관이 뚜렷하지";
                description += "\n감정적이면서 이성적이야";
                description += "\n번아웃은 무시못해";
                description += "\n자신에게는 엄격해야해!자아성찰이 취미";
                description += "\n생각이 너무 많아 힘들어";
                description += "\n할일을 미리 하는 편 - 계획세우기 좋아!";
                description += "\n싫어요: 무례한 것, 강요하는 것, 계획이 틀어지는 것 ";
                break;


            case "INTJ":
                description = "\n논리적이고 독창적인 건축가형";
                description += "\n키워드 : 츤데레, 원리원칙 데이터 ";
                description += "\n참신한 상상이 좋아";
                description += "\n독립적으로 행동하는게 좋아";
                description += "\n나도 유행은 알아 근데 굳이?";
                description += "\n나는 완벽하고, 목표지향적인게 좋아 ";
                description += "\n여러시선으로 해석하는거 좋아해";
                description += "\n항상 플랜B는 당연하지";
                description += "\n싫어요 : 감정적 호소, 효율성 떨어지는 것, 행동보다 말이 많은 것";
                break;


            case "ISTP":
                description = "\n호기심 많고 유연한 거장형";
                description += "\n 키워드 : 무표정 디폴트, 변수데이터 ";
                description += "\n굳이?? 내가?? 왜??";
                description += "\n이불밖은 위험해";
                description += "\n잔잔한 노래 < 신나는 노래";
                description += "\n눈치는 잘 안보는데 눈치는 빠른편이야";
                description += "\n하나에 꽂히면 죽을때까지";
                description += "\n호불호가 확실해";
                description += "\n의심이 많지만 성장은 하고싶어";
                description += "\n다른 사람에게 관심이 없어";
                description += "\n모든 사람은 공평해 ";
                description += "\n싫어요 : 모순된 행동, 수직적인 관계, 돌려서 말하는 것 ";
                break;

            case "ISFP":
                description = "\n예술적이고 모험을 즐기는 모험가형";
                description += "\n키워드 : 의문의 인싸, 게으름 데이터 ";
                description += "\n느긋하고 낙천적인게 최고야";
                description += "\n노는 것을 좋아하지만 너무 기빨려";
                description += "\n나 = 귀찮음";
                description += "\n할일을 끝까지 미루는게 계획이야";
                description += "\n싫어요 : 갈등상황, 경쟁하는 분위기, 불편한 부탁을 하는 것";
                break;


            case "INFP":
                description = "\n이상주의적이고 섬세한 중재자형";
                description += "\n키워드 : 귀여움 그 자체, 감정데이터 ";
                description += "\n좁고 깊은 관계가 더 좋아 ";
                description += "\n내면 열정만큼은 최고야";
                description += "\n마음의 상처.... 너무 많아";
                description += "\n자기애가 강하지만 자존감은 낮아ㅜ";
                description += "\n싫어요 : 재촉하는 것, 강요하는 것, 날카로운 비판";
                break;

            case "INTP":
                description = "\n논리적이고 호기심 많은 논리학자형";
                description += "\n키워드 : 관찰자 모드, 알쓸신잡 데이터 ";
                description += "\n내가 맞는 게 아니라 네가 틀린 거지";
                description += "\n왜 사람들은 솔직하지 못하지?";
                description += "\n관심 없는 분야에 상식 없음";
                description += "\n장르는 새롭고 신기한 판타지 유형 선호!";
                description += "\n선택적 아싸";
                description += "\n똑똑하고 상상력이 풍부한편이지";
                description += "\n궁금한 게 많아 ??????살인마";
                description += "\n가끔 너무 솔직할 때가 있다는 소리를 들어";
                description += "\n싫어요 : 강요하는 것, 귀찮게 구는 것, 지나치게 감정적인 것 ";
                break;
            case "ESTP":
                description = "\n대담하고 활동적인 기업가형";
                description += "\n키워드 : 붙임성 갑, 반항데이터 ";
                description += "\n현재를 즐김";
                description += "\n내가 이 구역의 내기왕!";
                description += "\n빨리!더 빨리! 성격이 급하고충독적임";
                description += "\n위기 상황은 임기응변으로 충분";
                description += "\n매우 솔직한 편이지만 악의는 없어";
                description += "싫어요 : \n거만한 것, 패션 센스 없는 것, 한없이 기다리는 것";
                break;

            case "ESFP":
                description = "\n사교적이고 활발한 엔터테이너형";
                description += "\n키워드 : 세상 무해한 인간, 노이즈 데이터";
                description += "\n사람 만나는 거 자체가 나의 행복";
                description += "\n우리 지금 당장 만나!";
                description += "\n신날때 데시벨 주의";
                description += "\n나랑 있으면 기 빨린다고?";
                description += "\n파티와 이벤트 최고";
                description += "\n분위기 메이커 스타일";
                description += "\n인생은 즐거워";
                description += "\n싫어요 : 비판하는 것, 혼자 있는 상황, 할 일이 쌓여 있는 상황";
                break;

            case "ENFP":
                description = "\n열정적이고 창의적인 활동가형";
                description += "\n키워드 : 호기심 천국, 빅토크 데이터 ";
                description += "\n장점 = 감정적, 단점 = 감정적";
                description += "\n나 빼고 놀 때 서운함 200%";
                description += "\n사람좋아 인간";
                description += "\n다같이 잘 놀고 혼자서도 잘 놀고";
                description += "\n하고싶은 말이 너무 많아";
                description += "\n어색한 분위기가 어색해";
                description += "\n싫어요 : 강요하는 것, 말을 날카롭게 하는 것, 무의미하고 반복적인 일";
                break;


            case "ESTJ":
                description = "\n실용적이고 효율적인 간부형";
                description += "\n키워드 : FM대로하는 권력자, 정리정돈 데이터";
                description += "\n사람들과 잘 어울리지만 매우 독립적";
                description += "\n친해지면 본모습 보여줄게";
                description += "\n그걸로 왜 싸워? 사소한 문제는 패스";
                description += "\n공감보다는 해결책!";
                description += "\n효율적인 삶을 지향";
                description += "\n치밀하고 계획적";
                description += "\n싫어요 : 충독적인 것, 무의미한 대화, 물건이 제자리에 없음";
                break;

            case "ESFJ":
                description = "\n친절하고 협조적인 영사관형";
                description += "\n키워드 : 우주최강 오지랖, 현실주의 데이터 ";
                description += "\n만남과 인연은 중요하지";
                description += "\n관심에 예민한 편이야";
                description += "\n현실적이고 안정적인게 최고지";
                description += "\n이 구역 배려왕";
                description += "\n공감능력이 내 장점";
                description += "\n처음 보는 사람도 내 친구";
                description += "\n싫어요 : 갈등 상황, 나를 비판하려는 사람, 호의를 당연하게 받음 ";
                break;

            case "ENFJ":
                description = "\n이타적이고 영감을 주는 주인공형";
                description += "\n키워드 : 웃음바이러스, 공감데이터 ";
                description += "\n친구사귀는게 제일 좋아";
                description += "\n상처주지마.. 계속 아파";
                description += "\n내가 다 맞춰줄게!";
                description += "\n의리!의리!";
                description += "\n오지랖 좀 그만부리라고 그러네..";
                description += "\n싫어요 : 질투와 소심함, 예의가 없는것, 너무 논리적이기만함 ";
                break;

            case "ENTJ":
                description = "\n대담하고 지도력 있는 지휘관형";
                description += "\n키워드 : 눈 떠보니 리더, 솔루션 데이터 ";
                description += "\n갈등 상황? 오히려 즐겨";
                description += "\n타고난 리더십! 이게 내 장점";
                description += "\n난 일하는게 제일 좋아";
                description += "\n나 화난거 아니야,,,";
                description += "\n지적인 욕구가 너무 넘쳐";
                description += "\n모두 완벽했으면 좋겠어";
                description += "\n계획이 없다고? 그게 무슨소리야?";
                description += "\n싫어요 : 무논리, 감정적인 것, 갑작스러운 주제 전환";
                break;

            default:
                description = "\n알 수 없는 유형\n";
        }
        return "MBTI 유형" + mbti + " - " + description;
    }
}