package SERVICE;

import DAO.SQL;
import DTO.*;

import java.util.Scanner;

import static SERVICE.PrintUtility.*;
import static SERVICE.PrintUtility.slowPrint;

public class GameUtility {

    private int s = 50;

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    static Player player = new Player();
    static NPC npc = new NPC();
    static Scanner sc = new Scanner(System.in);
    static SQL sql = new SQL();
    static MBTI mbti = new MBTI(); // MBTIResult 인스턴스 생성
    static Sins sins = new Sins(); // Sins 인스턴스 생성
    static Virtues virtues = new Virtues(); // Virtues 인스턴스 생성

    // 게임 속도 조절
    public int GameSetting() {
        boolean setting = true;
        Scanner sc = new Scanner(System.in);

        printClear();
        printMenuBar();
        slowPrint(" [설정]\n", 10, ColorsUtility.RED);
        slowPrint(" 1. 빠름\n", 10, ColorsUtility.BLUE);
        slowPrint(" 2. 보통\n", 10, ColorsUtility.YELLOW);
        slowPrint(" 3. 느림\n", 10, ColorsUtility.PURPLE);
        System.out.print("선택 >> ");
        int choice = sc.nextInt();

        while (setting) {
            switch (choice) {
                case 1:
                    slowPrint(" 속도를 빠름으로 설정했습니다..\n", 10, ColorsUtility.BLUE);
                    setS(50);
                    setting = false;
                    break;
                case 2:
                    slowPrint(" 속도를 보통으로 설정했습니다..\n", 50, ColorsUtility.GREEN);
                    setS(100);
                    setting = false;
                    break;
                case 3:
                    slowPrint(" 속도를 느림으로 설정했습니다..\n", 100, ColorsUtility.RED);
                    setS(150);
                    setting = false;
                    break;
                case 4:
                    setting = false;
                default:
                    System.out.println("잘못 선택하셨습니다.");
            }
        }
        return s;
    }

    // 게임 케릭터 생성...
    public void createAndSavePlayer() {
        sql.connect();

        LoginUtility login = new LoginUtility();

        int isLoggedIn = login.login();

        boolean playerProduced = false;
        String nickname, gender, job, genderNPC, friendName;
        int age, health, study, hobby, happiness, money, affection;
        System.out.println("플레이어 생성을 시작합니다.");

        while (!playerProduced) {
            System.out.print("닉네임을 입력하세요: >> ");
            nickname = sc.nextLine().trim();
            System.out.print("성별을 입력하세요 (남성/여성): >> ");
            gender = sc.nextLine().trim().toUpperCase();
            job = "학생";
            age = 17;
            health = 100;
            study = 30;
            hobby = 30;
            happiness = 70;
            money = 20000;

            // Player 객체 생성
            player = new Player(nickname, gender, job, age, health, study, hobby, happiness, money, isLoggedIn);

            // 플레이어 정보 데이터베이스에 저장
            sql.savePlayer(player, isLoggedIn);  // savePlayer 메소드 구현 필요

            System.out.println(nickname + " 플레이어가 생성되었습니다.");
            playerProduced = true;

            slowPrint("\n여기 이 친구의 이름은 무엇인가? \n", 30, ColorsUtility.YELLOW);
            slowPrint("소꿉친구의 이름을 입력하세요: >> ", 30, ColorsUtility.YELLOW);
            friendName = sc.next();
            System.out.print("성별을 입력하세요 (남성/여성): >> ");
            genderNPC = sc.next();
            affection = 30;
            npc = new NPC(isLoggedIn, friendName, genderNPC, affection);
            sql.npcPlayer(npc, isLoggedIn);

            slowPrint(npc.getName() + " (" + npc.getGender() + ") 이(가) 생성 되었습니다.", 30, ColorsUtility.PURPLE);

        }
    }


    public void startGame() {
        Scanner sc = new Scanner(System.in);

        printMenuBar();
        slowPrint(" 흐음!! 잘 왔다!\n MBIT 세계에 온걸 환영한다...\n", 44, ColorsUtility.YELLOW);

        slowPrint("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣶⣶⣶⣄⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢠⣤⣄⣾⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠈⣻⣿⣿⣿⣿⣿⣿⣿⣶⣶⡄⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣠⠟⠁⠀⠀⠉⠉⠛⠛⠛⢻⡏⠁⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢰⠏⠀⠀⢰⣷⠀⠀⣤⡄⠀⠀⢹⡄⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⡿⠀⠀⠀⠀⣴⠶⣦⡉⠁⠀⠀⠀⣷⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢸⡇⠀⠀⢀⡀⢷⣤⣼⠇⢠⡀⠀⠀⣿⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣰⠞⠃⠀⠀⣨⡿⢶⣤⣤⡶⠏⠀⠀⢀⡟⠀⠀⠀⠀\n" +
                "⠀⠀⢀⡾⠁⠀⠀⠀⣰⠏⠀⠀⣿⠀⠀⠀⠀⠀⠈⠻⡄⠀⠀⠀\n" +
                "⠀⠀⡾⠁⠀⠀⠀⣰⠟⠀⠀⢀⡟⠀⠀⠀⠀⣀⠀⠀⢻⠀⠀⠀\n" +
                "⠀⢸⡇⠀⠀⠲⠛⠁⠀⠀⢀⡾⠁⠀⠀⠀⠀⢸⡆⠀⢸⡇⠀⠀\n" +
                "⠀⠘⣇⠀⠀⠀⠀⠀⠀⣠⡞⠁⠀⠀⠀⠀⠀⢸⡇⠀⢸⡇⠀⠀\n" +
                "⠀⠀⠙⠷⣤⣤⣤⣤⠾⠋⠀⠀⠀⠀⠀⠀⠀⢸⠃⠀⢸⡇⠀\n", 10, ColorsUtility.BLUE);

        slowPrint("\r 나는 INTJ 박사라고 한다! " +
                "\r 모두가 나를 인티제이 박사님이라고 부르고 있지... \r 여기는 'This is MBTI' 세계 " +
                "\r 줄여서 'MBTI 세계' 라고 하지. " +
                "\r 일단 이름부터 시작하지... " +
                "\r 너의 이름은 무엇인가?? \r", 44, ColorsUtility.YELLOW);

        // Player 객체 생성 부분
        createAndSavePlayer();

        String A = " " + player.getNickname() + " : ";
        String B = " " + npc.getName() + " : ";
        String P = player.getNickname(); // 플레이어 닉네임
        String N = npc.getName(); // 소꿉친구 닉네임

        // 게임 시작
        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바
        int currentAge = player.getAge(); // 플레이어 객체에서 나이 정보 가져오기
        slowPrint(" Date : 2017-03-04 [월요일] \n" + " 나이: " + currentAge + "살\n", s, ColorsUtility.GREEN);
        slowPrint(" 현재시각 : 오전 08 : 00 \n", s, ColorsUtility.GREEN);
        slowPrint(" 위치 : " + player.getNickname() + "의 방\n", s, ColorsUtility.CYAN);
        printMenuBar(); // 화면 메뉴바
        slowPrint(A + "뭐야 아침인가??\n", s, ColorsUtility.CYAN);
        slowPrint(A + "헉 지금이 몇시지?\n", s, ColorsUtility.CYAN);
        slowPrint(" 알람시계 - AM 08 : 07\n", s, ColorsUtility.RED);
        slowPrint(A + "늦었잖아?!\n", s, ColorsUtility.CYAN);
        printClear(); // 화면 클리어


        boolean storyChap01_1 = true; // 게임이 실행 중인지 나타내는 플래그

        while (storyChap01_1) {
            printMenuBar(); // 화면 메뉴바
            slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
            slowPrint(" 1: 늦었다. 빨리가자!\n", 0, ColorsUtility.PURPLE);
            slowPrint(" 2: 안되겠어...천천히 가자...\n", 0, ColorsUtility.RED);
            slowPrint(" 0: 현재 스테이터스 출력\n", 0, ColorsUtility.WHITE);
            slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // ENFP - 모험 선택 시 지표 상승
                    slowPrint(" 빠르게 움직여서 건강을 조금 소모했지만, 지각은 면했습니다..\n", 30, ColorsUtility.GREEN);
                    slowPrint(" 건강 - 10 감소했습니다., ", s, ColorsUtility.RED);
                    slowPrint(" 행복 + 10 상승했습니다.\n", s, ColorsUtility.GREEN);
                    virtues.updateScore("diligence"); // 근면 상승
                    mbti.updateScore("E");  // 외향성
                    mbti.updateScore("N");  // 직관
                    mbti.updateScore("P");  // 인식
                    player.setHealth(player.getHealth() - 10); // 건강 -10
                    player.setHappiness(player.getHappiness() + 5); // 행복 +5
                    storyChap01_1 = false;
                    break;
                case 2:
                    // ISTJ - 신중 선택 시 지표 상승
                    slowPrint(" 천천히 움직여서 건강은 유지했지만, 학교에 지각 했습니다.\n", 30, ColorsUtility.YELLOW);
                    slowPrint(" 행복 - 5 감소했습니다.\n", s, ColorsUtility.RED);
                    mbti.updateScore("I");
                    mbti.updateScore("S");
                    mbti.updateScore("J");
                    sins.updateScore("sloth"); // 나태 상승
                    sins.updateScore("pride"); // 오만 상승
                    player.setHappiness(player.getHappiness() - 5); // 행복 -5
                    storyChap01_1 = false;
                    break;
                case 0:
                    System.out.println("\n현재 플레이어 상태: " + player.toString());
                    break;
                default:
                    System.out.println(" 잘못된 선택입니다. 올바른 번호를 입력해 주세요.");
                    break;
            }
        }
        // 17살 방황의 시기 스토리 시작
        //chapter[1]
        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바
        slowPrint(" Chap_01\n", 60, ColorsUtility.RED);
        slowPrint(" 17살 방황의 시기\n", s, ColorsUtility.RED);
        slowPrint(" Date : 2017-03-04 [월요일] \n" + " 나이: " + currentAge + "살\n", s, ColorsUtility.GREEN);
        slowPrint(" 현재시각 : 오전 08 : 20 \n", s, ColorsUtility.GREEN);
        slowPrint(" 위치 : 인하대부속고등학교 정문앞 \n", s, ColorsUtility.GREEN);
        printMenuBar(); // 화면 메뉴바

        slowPrint(" 학교 가는 길...\r 학교 가는 길... \r" +
                " 학교 가는 길... \n", s, ColorsUtility.GREEN);
        slowPrint(" ??? : " + player.getNickname() + "!! (누군가 당신의 어깨를 치며 말합니다.)\n", s, ColorsUtility.PURPLE);
        slowPrint(A + "뭐냐?\n", s, ColorsUtility.CYAN);
        slowPrint(B + "같이 가자~\n", s, ColorsUtility.PURPLE);

        boolean storyChap01_2 = true; // 스토리 진행 플래그

        printClear(); // 화면 클리어
        while (storyChap01_2) {
            printMenuBar(); // 화면 메뉴바
            slowPrint(" [소꿉친구와 같이 등교 할까?]\n", s, ColorsUtility.PURPLE);
            slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
            slowPrint(" 1: 같이 간다.\n", 0, ColorsUtility.GREEN);
            slowPrint(" 2: 혼자 간다.\n", 0, ColorsUtility.RED);
            slowPrint(" 9: 소꿉치구 스테이터스 출력\n", 0, ColorsUtility.PURPLE);
            slowPrint(" 0: 현재 스테이터스 출력\n", 0, ColorsUtility.WHITE);
            slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(B + "너가 왠일로 학교를 빨리가냐??\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "오늘 주번이라서~ 넌 왜 이 시간에 가?\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "그냥~? 너 가는거 보이길래 따라 나왔지\n", s, ColorsUtility.PURPLE);
                    slowPrint(" 소꿉친구의 호감도가 조금 상승했습니다 + 10\n", 60, ColorsUtility.GREEN);

                    npc.increaseAffection(10); // 소꿉친구와의 호감도 10 증가
                    mbti.updateScore("E"); // 외향성 증가
                    mbti.updateScore("F"); // 감정 증가
                    mbti.updateScore("P"); // 인식 증가
                    virtues.updateScore("kindness"); // 친절 덕목 증가
                    storyChap01_2 = false; // 스토리 루프 종료 조건 (필요에 따라 조정)
                    break;

                case 2:
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "뭘 같이 가.. 따로 가 그냥\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "말을 해도 그 따구로 하냐\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "먼저 간다...\n", s, ColorsUtility.CYAN);
                    slowPrint(" " + N + "와의 호감도가 많이 하락했습니다 - 5\n", 60, ColorsUtility.RED);
                    npc.decreaseAffection(-5); // 소꿉친구와의 호감도 5 감소
                    mbti.updateScore("I"); // 내향성 증가
                    mbti.updateScore("T"); // 사고 증가
                    mbti.updateScore("J"); // 판단 증가
                    sins.updateScore("pride"); // 오만 죄악 증가
                    storyChap01_2 = false; // 스토리 루프 종료 조건 (필요에 따라 조정)
                    break;

                case 9:
                    System.out.println("\n소꿉친구와의 관계: " + npc.toString());
                    break;

                case 0:
                    System.out.println("\n현재 플레이어 상태: " + player.toString());
                    break;

                default:
                    System.out.println(" 잘못된 선택입니다. 다시 선택해 주세요.\n");
                    break;
            }
        }

        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바
        slowPrint(" 5살 때 같은 아파트에 살며 알게된 소꿉친구인 " + npc.getName() + "... \n" +
                " 어렸을적엔 같이 놀기도 하고 장난도 많이 쳤지만 초,중학교 졸업하며 바뀐 환경 탓인지\n" +
                " 아님 내가 어울리질 못하는 건지 대화하는 것도 어색해 졌다.\n" +
                " 그 애는 아무렇지 않은 것 같은데 왜인지 쉽게 다가가기 어려워 졌달까\n" +
                " 아무데나 있는 [잡초]같이 흔해빠진 난 너와 어울리지 않는 것만 같았다\n", s, ColorsUtility.CYAN);

        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바
        slowPrint(" Date : 2017-05-04 [월요일] \n" + " 나이: " + currentAge + "살\n", s, ColorsUtility.GREEN);
        slowPrint(" 현재시각 : 오전 08 : 35 \n", s, ColorsUtility.GREEN);
        slowPrint(" 위치 : 인하대부속고등학교 1학년 B반 \n", s, ColorsUtility.GREEN);
        printMenuBar(); // 화면 메뉴바

        boolean storyChap01_3 = true;

        while (storyChap01_3) {
            // 학교 도착 후 선택지
            slowPrint(" 학교에 도착했습니다. 당신의 선택은?\n", 0, ColorsUtility.CYAN);
            slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
            slowPrint(" 1. 자습한다.\n", 0, ColorsUtility.GREEN);
            slowPrint(" 2. 그림을 그린다.\n", 0, ColorsUtility.PURPLE);
            slowPrint(" 3. 엎드려 잔다.\n", 0, ColorsUtility.YELLOW);
            slowPrint(" 4. 매점에 간다.\n", 0, ColorsUtility.RED);
            slowPrint(" 0. 플레이어 정보 조회\n", 0, ColorsUtility.WHITE);
            slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
            int schoolChoice = sc.nextInt();
            System.out.println();

            switch (schoolChoice) {
                case 1:
                    // 공부 수치 +1 로직 추가 예정
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(" 공부 + 10 상승했습니다.\n", 60, ColorsUtility.GREEN);
                    slowPrint(" 행복 - 20 하락했습니다.\n", 60, ColorsUtility.RED);
                    mbti.updateScore("J");
                    mbti.updateScore("T");
                    virtues.updateScore("diligence");
                    player.setStudy(player.getStudy() + 10); // 공부 수치 증가
                    player.setHappiness(player.getHappiness() - 20); // 행복 수치 감소로 스트레스 증가 효과
                    storyChap01_3 = false;
                    break;
                case 2:
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(" 취미 + 1 상승했습니다.\n", 60, ColorsUtility.GREEN);
                    slowPrint(" 행복 + 5 상승했습니다.\n", 60, ColorsUtility.GREEN);
                    mbti.updateScore("P");
                    mbti.updateScore("F");
                    virtues.updateScore("kindness"); // 또는 humility
                    player.setHobby(player.getHobby() + 10); // 취미 수치 증가
                    player.setHappiness(player.getHappiness() + 5); // 행복 수치 증가로 스트레스 감소 효과
                    storyChap01_3 = false;
                    break;
                case 3:
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(" 행복 + 5 상승했습니다.\n", 60, ColorsUtility.RED);
                    slowPrint(" 건강 + 5 상승했습니다.\n", 60, ColorsUtility.RED);
                    mbti.updateScore("I");
                    sins.updateScore("sloth");
                    player.setHealth(player.getHealth() + 5); // 건강 수치 증가
                    player.setHappiness(player.getHappiness() + 5); // 행복 수치 증가로 스트레스 감소 효과
                    storyChap01_3 = false;
                    break;
                case 4:
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "음... 뭐 먹지.. 간단하게 만두나 먹을까?\n", s, ColorsUtility.CYAN);
                    slowPrint(" " + npc.getName() + " : 야! \n", s, ColorsUtility.PURPLE);
                    slowPrint(A + ": 뭐야 넌 또 왜 여기 있는거야..?\n", s, ColorsUtility.CYAN);
                    slowPrint(" " + npc.getName() + " : 너 따라 빨리 나오느라 아침도 못먹어서 왔다. 왜?\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "아니 뭐.. 갑지기 만나니까 당황스러워서\n", s, ColorsUtility.CYAN);
                    slowPrint(" " + npc.getName() + " : 당황스러울까지야 어차피 같은 학교고 마주칠 수도 있지\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "...\n", s, ColorsUtility.CYAN);
                    slowPrint(" " + npc.getName() + " : 너 오늘 학교 끝나고 시간있어?\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "어? 시간? 갑지기 왜?\n", s, ColorsUtility.CYAN);
                    slowPrint(" " + npc.getName() + " : 아니~ 그냥 같이 가자고\n", s, ColorsUtility.PURPLE);
                    BuildingUtility.cafeteria(player); // 매점 가는 로직
                    break;
                case 0:
                    System.out.println("\n현재 플레이어 상태: " + player.toString());
                    break;
                default:
                    System.out.println(" 잘못된 선택입니다.");
                    break;
            }
        }

        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바
        slowPrint(" Date : 2017-06-04 [월요일] \n" + " 나이: " + currentAge + "살\n", s, ColorsUtility.GREEN);
        slowPrint(" 현재시각 : 오후 04 : 50 \n", s, ColorsUtility.GREEN);
        slowPrint(" 위치 : 인하대부속고등학교 체육관 \n", s, ColorsUtility.GREEN);
        printMenuBar(); // 화면 메뉴바
        slowPrint(" 방과 후 하교 시간\n", s, ColorsUtility.CYAN);
        slowPrint(" 선생님 : 그럼 오늘 종례는 여기서 마치고 집에 일찍들 들어가라~\n", s, ColorsUtility.BLUE);

        slowPrint(B + "야! 가자~ 빨리 나와~\n", s, ColorsUtility.PURPLE);
        slowPrint(A + "(뭔일인데 빨리 가자고..) 오키..\n", s, ColorsUtility.CYAN);
        slowPrint(A + "근데 무슨 일인데 같이 가자는 거야?\n", s, ColorsUtility.CYAN);
        slowPrint(B + "가보면 알아~ 어차피 내일 할거 미리 하는 거니까\n", s, ColorsUtility.PURPLE);
        slowPrint(A + "내일? 내일은 금요일이잖아? 불금 준비라도 하냐?\n", s, ColorsUtility.CYAN);
        slowPrint(B + "아니~ 내일 오후엔 동아리 선택한다 했잖아. 너네반은 그런 얘기 없었어?\n", s, ColorsUtility.PURPLE);
        slowPrint(A + "아~ 깜빡했네.. (동아리 같이 할 친구 없어서 까맣게 잊고 있었다...)\n", s, ColorsUtility.CYAN);
        slowPrint(B + "너 같이 할 친구 없는거 같아서 나 들어가려는 동아리 미리 가입해 놓게\n", s, ColorsUtility.PURPLE);
        slowPrint(A + "(무당이냐..? 부끄럽네...) 아.. 그래?\n", s, ColorsUtility.CYAN);
        slowPrint(B + "짠! 여기야. 내가 들어가려는 동아리\n", s, ColorsUtility.PURPLE);
        slowPrint(" (그곳은 학교 방송실 앞이였다.)\n", s, ColorsUtility.CYAN);
        slowPrint(B + "너도 방송부 해보자~ 어때?\n", s, ColorsUtility.PURPLE);
        printClear(); // 콘솔 클리어
        printMenuBar(); // 화면 메뉴바

        boolean storyChap01_4 = true;

        while (storyChap01_4) {
            slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
            slowPrint(" 1: 방송부에 들어간다.\n", 0, ColorsUtility.GREEN);
            slowPrint(" 2: 거절한다.\n", 0, ColorsUtility.PURPLE);
            slowPrint(" 0: 플레이어 정보조회\n", 0, ColorsUtility.WHITE);
            slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
            int clubchoice = sc.nextInt();
            System.out.println();

            switch (clubchoice) {
                case 1:
                    printClear();
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "내가 방송부에? 잘 안될거 같은데...\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "그건 해보기 전엔 모르지 면접이라도 한번 봐보자~\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "괜찮을까? 나 준비도 못했는데\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "괜찮아~ 들어가자\n", s, ColorsUtility.PURPLE);
                    slowPrint(" 방송부 선배 : 안녕~ 난 방송부 부장 방시혁 선배야 반가워~\n", s, ColorsUtility.BLUE);
                    slowPrint(" " + P + " & " + N + " : 안녕하세요~..\n", s, ColorsUtility.PURPLE);
                    System.out.println(" ");
                    slowPrint(" [ 그 때는 마치 나의 고등학교 학창 시절, 찬란한 봄바람이 불어도는 듯 했다 ]\n", s, ColorsUtility.CYAN);
                    sins.updateScore("pride");
                    slowPrint(" 취미 + 10\n", s, ColorsUtility.GREEN);
                    slowPrint(" 행복 + 10\n", s, ColorsUtility.GREEN);
                    mbti.updateScore("E");
                    mbti.updateScore("F");
                    mbti.updateScore("P");
                    player.setHobby(player.getHobby() + 10); // 취미 수치 상당히 증가
                    player.setHappiness(player.getHappiness() + 10); // 행복 수치 상당히 증가
                    storyChap01_4 = false;
                    break;

                case 2:
                    printClear();
                    printMenuBar(); // 화면 메뉴바
                    mbti.updateScore("I");
                    slowPrint(A + "아 나 안될 거 같아... 내가 무슨 방송부를...\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "괜찮아~ 별거 없어\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "아 그래도... 나랑은 별로 안어울릴 거 같아\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "그래..? 알겠어.. 미안해~ 갑자기 데려와서\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "... 먼저 갈께 내일 보자~\n", s, ColorsUtility.CYAN);
                    // 거절 후 다음날
                    printClear();
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(" 다음날...\n", 90, ColorsUtility.RED);
                    slowPrint(" 선생님: 이제 금요일 오후마다 너네가 할 동아리 시간에 하고 싶은 동아리 선택해라~\n", 40, ColorsUtility.YELLOW);
                    slowPrint(A + " (어떤 동아리에 들어가지?) \n", s, ColorsUtility.CYAN);
                    // 동아리 선택지
                    boolean validChoice = true;
                    while (validChoice) {
                        printClear();
                        printMenuBar(); // 화면 메뉴바
                        slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
                        slowPrint(" 1: 축구부\n", 0, ColorsUtility.GREEN);
                        slowPrint(" 2: 미술부\n", 0, ColorsUtility.PURPLE);
                        slowPrint(" 3: 컴퓨터부\n", 0, ColorsUtility.YELLOW);
                        slowPrint(" 0: 플레이어 정보 조회\n", 0, ColorsUtility.WHITE);
                        slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
                        clubchoice = sc.nextInt();
                        System.out.println();

                        switch (clubchoice) {
                            case 1:
                                printClear();
                                printMenuBar(); // 화면 메뉴바
                                slowPrint(A + "그래. 남자 답게 축구부에 들어가보자\n", s, ColorsUtility.BLUE);
                                mbti.updateScore("E");
                                mbti.updateScore("S");
                                player.setHealth(player.getHealth() + 10); // 건강 수치 증가
                                player.setHobby(player.getHobby() + 5); // 취미 수치 증가
                                storyChap01_4 = false;
                                validChoice = false;
                                break;
                            case 2:
                                printClear();
                                printMenuBar(); // 화면 메뉴바
                                slowPrint(A + "점잖게 미술부에 가서 시간이나 때워야겠다.\n", s, ColorsUtility.YELLOW);
                                mbti.updateScore("P");
                                mbti.updateScore("F");
                                player.setHobby(player.getHobby() + 10); // 취미 수치 상당히 증가
                                storyChap01_4 = false;
                                validChoice = false;
                                break;
                            case 3:
                                printClear();
                                printMenuBar(); // 화면 메뉴바
                                slowPrint(A + "저기 들어가면 게임할 수 있나..?\n", s, ColorsUtility.CYAN);
                                mbti.updateScore("T");
                                mbti.updateScore("N");
                                player.setStudy(player.getStudy() + 15); // 공부 수치 증가
                                storyChap01_4 = false;
                                validChoice = false;
                                break;
                            case 0:
                                System.out.println("\n현재 플레이어 상태: " + player.toString());
                                break;
                            default:
                                System.out.println("잘못된 선택입니다.");
                                break;
                        }
                    }
                    break;
                case 0:
                    System.out.println("\n현재 플레이어 상태: " + player.toString());
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
                    break;
            }

        }


        //chpater[2]
        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바
        slowPrint(" Chap_02\n", s, ColorsUtility.RED);
        slowPrint(" 여름방학이 지나고...\n", s, ColorsUtility.RED);
        slowPrint(" Date : 2011-08-04 [월요일] \n" + " 나이: " + currentAge + "살\n", s, ColorsUtility.GREEN);
        slowPrint(" 현재시각 : 오후 02: 00 \n", s, ColorsUtility.GREEN);
        slowPrint(" 위치 : 인하대부속고등학교 1학년 B반 \n", s, ColorsUtility.GREEN);
        printMenuBar(); // 화면 메뉴바

        slowPrint(" (2학기를 맞아 방송부에 들어가 보았다)\n", s, ColorsUtility.CYAN);
        slowPrint(B + player.getNickname() + "~ 왔어?\n", s, ColorsUtility.PURPLE);
        slowPrint(A + "여기있었네? 뭐하고 있었어?\n", s, ColorsUtility.CYAN);
        slowPrint(B + "이제 2학기니까 2학년 준비 해야지~\n", s, ColorsUtility.PURPLE);
        slowPrint(A + "2학년을 왜 준비함?ㅋㅋ\n", s, ColorsUtility.CYAN);
        slowPrint(B + "대학 안갈꺼야? 2학년이 제일 중요해 내신 볼때 2학년 내신은 무적권 보니까.\n", s, ColorsUtility.PURPLE);
        slowPrint(A + "아 그래? (그런줄 몰랐다.)\n", s, ColorsUtility.CYAN);
        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바

        boolean storyChap02_1 = true;

        // 방송부 대화 후 선택지
        while (storyChap02_1) {
            slowPrint(" (아 이제 어떡하지.. 공부 해야하나.. 취미생활을 통해 꿈을 찾을까..)\n", s, ColorsUtility.CYAN);
            slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
            slowPrint(" 1: 공부한다.\n", 0, ColorsUtility.GREEN);
            slowPrint(" 2: 취미생활에 몰두 한다.\n", 0, ColorsUtility.PURPLE);
            slowPrint(" 3: 다 필요없고 게임이나 하러 간다.\n", 0, ColorsUtility.YELLOW);
            slowPrint(" 4: 매정에 간다.\n", 0, ColorsUtility.RED);
            slowPrint(" 0: 플레이어 정보 조회.\n", 0, ColorsUtility.WHITE);
            slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
            int storyChap02_1_menu = sc.nextInt();

            switch (storyChap02_1_menu) {
                case 1:
                    printClear();
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "그래 나도 공부해서 대학에 진학해야겠다. 너도 대학진학이지?\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "당연하지~ 난 인하대 조랑말포경학과에 진학할 꺼야~ 넌 정했어?\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "나는... 아직 잘 모르겠어. 일단 너랑 같은 대학 가는 걸 목표로 해볼게\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "음... 그래\n", s, ColorsUtility.PURPLE);
                    slowPrint(" 공부 + 15 상승했습니다\n", s, ColorsUtility.GREEN);
                    slowPrint(" 행복 - 5 감소했습니다.\n", s, ColorsUtility.RED);
                    virtues.updateScore("diligence");
                    mbti.updateScore("J");
                    mbti.updateScore("T");
                    player.setStudy(player.getStudy() + 15); // 공부 수치 상당히 증가
                    player.setHappiness(player.getHappiness() - 5); // 단기적 행복 감소
                    storyChap02_1 = false;
                    break;
                case 2:
                    printClear();
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "기왕에 들어온 방송분데 여기에서 내 꿈 찾아볼래\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "오올~ 왜? 방송국에 취직이라도 하려고?\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "뭐~ 돼면~\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "거봐! 내가 추천해 주니까 좋지?\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "... 그러게\n", s, ColorsUtility.CYAN);
                    System.out.println();
                    slowPrint(" (여름방학 전 소꿉친구와 대화)\n", s, ColorsUtility.BLUE);
                    slowPrint(A + "[근데 넌 왜 방송부에 들어가려 한거야?]\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "[기억안나? 나 방송에 꿈이 있었잖아~]\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "[진짜? 나 왜 몰랐지?]\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "[헐~ 너무한다... (속삭이듯)누구 때문인데...]\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "[어? 뭐라고 했어?]\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "[아무것도 아냐~]\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "[???]\n", 50, ColorsUtility.CYAN);
                    virtues.updateScore("kindness");
                    slowPrint(" 취미 + 10 상승했습니다.\n", s, ColorsUtility.GREEN);
                    slowPrint(" 행복 - 5 감소했습니다.\n", s, ColorsUtility.RED);
                    mbti.updateScore("P");
                    mbti.updateScore("F");
                    player.setHobby(player.getHobby() + 15); // 취미 수치 상당히 증가
                    player.setHappiness(player.getHappiness() + 10); // 행복 수치 증가
                    storyChap02_1 = false;
                    break;
                case 3:
                    printClear();
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "모르겠다~ 게임이나 하러 갈꺼야\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "엥? 같이 공부하러 도서관에 가자~\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "혼자가~ 난 공부랑 안맞는거 같아\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "...\n", s, ColorsUtility.PURPLE);
                    slowPrint(" {그저 지금 당장의 즐거움을 위해 PC방으로 향하는 주인공}\n", s, ColorsUtility.CYAN);
                    slowPrint(" 행복 + 5\n", s, ColorsUtility.GREEN);
                    sins.updateScore("sloth");
                    mbti.updateScore("I");
                    player.setHappiness(player.getHappiness() + 5); // 단기적 행복 증가
                    // 장기적으로 공부나 취미에 부정적
                    storyChap02_1 = false;
                    break;
                case 4:
                    printClear();
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "매점 갈래?\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "그래~\n", s, ColorsUtility.PURPLE);
                    sins.updateScore("gluttony");
                    storyChap02_1 = false;
                    break;
                case 0:
                    System.out.println("\n 현재 플레이어 상태: " + player.toString());
                    break;
                default:
                    System.out.println(" 잘못된 선택입니다.");
                    break;
            }
        }

        //chapter[3]
        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바
        slowPrint(" Chap_03\n", s, ColorsUtility.RED);
        slowPrint(" 찬란할 것만 같았던 열여덟의 순간...\n", s, ColorsUtility.RED);
        slowPrint(" Date : 2018-09-11 [월요일] \n" + " 나이: " + "18살" + "\n", s, ColorsUtility.GREEN);
        slowPrint(" 현재시각 : 오전 08 : 40 \n", s, ColorsUtility.GREEN);
        slowPrint(" 위치 : 인하대부속고등학교 2학년 D반 \n", s, ColorsUtility.GREEN);
        printMenuBar(); // 화면 메뉴바
        player.setAge(player.getAge() + 1);

        slowPrint(A + "아니 무슨 2학년 되자 마자 모의 고사야~?\n", s, ColorsUtility.CYAN);
        slowPrint(B + "야~ " + player.getNickname() + "! 모의고사 준비 잘했냐?\n", s, ColorsUtility.PURPLE);

        boolean chapter3_1 = true;
        while (chapter3_1) {
            slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
            slowPrint(" 1: 밤새서 공부했다도 한다.\n", 0, ColorsUtility.GREEN);
            slowPrint(" 2: 공부 하나도 안했다고 한다.\n", 0, ColorsUtility.PURPLE);
            slowPrint(" 9: 소꿉친구 스테이터스 출력\n", 0, ColorsUtility.YELLOW);
            slowPrint(" 0: 플레이어 정보 조회\n", 0, ColorsUtility.WHITE);
            slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
            int chapter3_1_menu = sc.nextInt();

            switch (chapter3_1_menu) {
                case 1:
                    printClear();
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "어제 밤새서 공부해서 그런가 개피곤하네\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "게임하다 밤샌건 아니고?\n", s, ColorsUtility.PURPLE);
                    slowPrint(" [찬란할건만 같았던 2학년은 공부와 모의고사만을 위해 사는 기계와 같았다]\n", s, ColorsUtility.GREEN);
                    mbti.updateScore("J");
                    virtues.updateScore("diligence");
                    player.setStudy(player.getStudy() + 5); // 공부 수치 상당히 증가
                    player.setHappiness(player.getHappiness() - 2); // 행복 수치 약간 감소
                    player.setHealth(player.getHealth() - 5); // 건강 수치 감소
                    chapter3_1 = false;
                    break;

                case 2:
                    printClear();
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "아니 공부 하나도 못했어\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "에이~ 그래놓고 나보다 잘 보면 죽어\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "그럴일 없을꺼야~\n", s, ColorsUtility.CYAN);
                    slowPrint("[2학년이 되어도 공부를 안하니 대책없이 사는거 같았다]\n", s, ColorsUtility.RED);
                    mbti.updateScore("S");
                    sins.updateScore("sloth");
                    player.setHappiness(player.getHappiness() + 2); // 행복 수치 약간 증가
                    chapter3_1 = false;
                    break;
                case 9:
                    System.out.println("\n소꿉친구와의 관계: " + npc.toString());
                    break;

                case 0:
                    System.out.println("\n현재 플레이어 상태: " + player.toString());
                    break;

                default:
                    System.out.println(" 잘못 입력하셨습니다.");
            }
        }

        //chapter[4]
        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바
        slowPrint(" Chap_04\n", s, ColorsUtility.RED);
        slowPrint(" 이야기의 마지막 10대\n", s, ColorsUtility.RED);
        slowPrint(" Date : 2019-11-17 [수요일] \n" + " 나이: " + (currentAge + 2) + "살\n", s, ColorsUtility.GREEN);
        slowPrint(" 현재시각 : 오후 9 : 40 \n", s, ColorsUtility.GREEN);
        slowPrint(" 위치 : " + player.getNickname() + "의 방\n", s, ColorsUtility.CYAN);
        printMenuBar(); // 화면 메뉴바

        slowPrint(" 찬란할 것만 같았던 고2가 지나고, 고3이 되었다.\n", s, ColorsUtility.GREEN);
        slowPrint(" 우리는 각자의 공부를 위해 조심스러웠고 마추질 일도 적어졌다.\n", s, ColorsUtility.GREEN);
        slowPrint(" 마치 만화의 한페이지 처럼 휘리릭 넘어가 듯 우리의 이야기가 마침표를 찍을 것만 같았다.\n", s, ColorsUtility.GREEN);
        slowPrint(" 그리고.. 수능날이 다가왔다.\n", s, ColorsUtility.RED);
        player.setAge(player.getAge() + 1);
        slowPrint(A + "드디어.. 수능까지 D-1 마지막까지 최선을 다해 보자\n", s, ColorsUtility.CYAN);

        boolean chapter4_1 = true;
        while (chapter4_1) {
            slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
            slowPrint(" 1: 공부한다.\n", 0, ColorsUtility.GREEN);
            slowPrint(" 2: 피곤한데 잔다.\n", 0, ColorsUtility.PURPLE);
            slowPrint(" 3: 신께 기도 한다.\n", 0, ColorsUtility.YELLOW);
            slowPrint(" 0: 플레이어 정보 조회\n", 0, ColorsUtility.WHITE);
            slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
            int chapter4_2_menu = sc.nextInt();
            switch (chapter4_2_menu) {
                case 1:
                    slowPrint(" 공부 수치 + 25\n", s, ColorsUtility.GREEN);
                    slowPrint(" 건강 수치 -25\n", s, ColorsUtility.RED);
                    printClear();
                    printMenuBar();
                    virtues.updateScore("diligence");
                    mbti.updateScore("J");
                    mbti.updateScore("T");
                    player.setStudy(player.getStudy() + 25);
                    player.setHealth(player.getHealth() - 25);
                    chapter4_1 = false;
                    break;

                case 2:
                    slowPrint(" 건강 수치 + 5\n", s, ColorsUtility.GREEN);
                    slowPrint(" 행복 수치 - 3\n", s, ColorsUtility.RED);
                    printClear();
                    printMenuBar();
                    sins.updateScore("sloth");
                    mbti.updateScore("I");
                    player.setHealth(player.getHealth() + 5); // 건강 수치 증가
                    player.setHappiness(player.getHappiness() + 3); // 행복 수치 약간 증가
                    chapter4_1 = false;
                    break;

                case 3:
                    slowPrint(" 행복도 + 5\n", s, ColorsUtility.GREEN);
                    printClear();
                    printMenuBar();
                    virtues.updateScore("charity");
                    mbti.updateScore("F");
                    player.setHappiness(player.getHappiness() + 5); // 행복 수치 증가
                    chapter4_1 = false;
                    break;
                case 0:
                    System.out.println("\n현재 플레이어 상태: " + player.toString());
                    break;
                default:
                    System.out.println(" 잘못 선택하셧습니다. 다시 입력해주세요.");
            }
        }

        slowPrint(" 그 때 소꿉친구로부터 문자가 왔다\n", s, ColorsUtility.YELLOW);
        slowPrint(B + player.getNickname() + "! 내일 수능이네~ 우리 둘 다 화이팅하자!! 내일 끝나고 같이 놀래??\n", s, ColorsUtility.RED);
        slowPrint(" 내일?? 어떡하지?? 나 아무런 준비도 못했는데\n", s, ColorsUtility.CYAN);
        slowPrint(" 순간 내일 소꿉친구와 만날 수도 있다는 생각에 허둥되는 자신을 보며\n", s, ColorsUtility.CYAN);
        slowPrint(" 내가 왜 허둥되는거야... 평소엔 아무 생각도 없었으면서\n", s, ColorsUtility.CYAN);

        boolean chapter4_2 = true;
        while (chapter4_2) {
            printClear();
            printMenuBar();
            slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
            slowPrint(" 1: 만난다.\n", 0, ColorsUtility.GREEN);
            slowPrint(" 2: 거절한다.\n", 0, ColorsUtility.PURPLE);
            slowPrint(" 0: 플레이어 정보 조회\n", 0, ColorsUtility.WHITE);
            slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
            int chapter4_2_menu = sc.nextInt();
            switch (chapter4_2_menu) {
                case 1:
                    slowPrint(A + "그래~ 내일 수능 끝나고 연락할께\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "알겠어~ 내일 끝나고 연락해~\n", s, ColorsUtility.PURPLE);

                    boolean chapter4_2_1 = true;
                    printClear();
                    slowPrint(" 다음날\n", s, ColorsUtility.YELLOW);
                    slowPrint(" 약속시간 보다 일찍나온 주인공, 소꿉친구에게 문자를 하는데\n", s, ColorsUtility.YELLOW);

                    slowPrint(A + "어디야? 나 CGV 앞임\n", s, ColorsUtility.CYAN);
                    slowPrint(B + player.getNickname() + "! 왤케 빨리왔어?!\n", s, ColorsUtility.PURPLE);

                    slowPrint(" 평소 머리를 묶고 다니는 친구의 처음 보는 머리 푼 모습과 한껏 꾸민듯한 모습을 보며\n", s, ColorsUtility.YELLOW);
                    slowPrint(A + "너.. 머리 푼거 처음 보는거 같다..\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "그래? 너가 하도 숨어 다니니까 그렇지~\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "그런가..? 딱히 숨어다니진 않았는데..\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "ㅋㅋ 오늘 수능도 끝났고 수험표 있으니까 영화 할인해서 보자~\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "그래~ 뭐 보고 싶은거 있어?\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "음.. 글쎄 넌?\n", s, ColorsUtility.PURPLE);

                    npc.increaseAffection(5); // 소꿉친구와의 호감도 증가
                    player.setHappiness(player.getHappiness() + 5); // 행복 수치 증가
                    virtues.updateScore("kindness");
                    mbti.updateScore("E"); // 외향성 증가

                    printClear(); // 화면 클리어
                    printMenuBar(); // 화면 메뉴바

                    while (chapter4_2_1) {
                        slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
                        slowPrint(" 1: 은밀하게 위대하게\n", 0, ColorsUtility.RED);
                        slowPrint(" 2: 컨저링\n", 0, ColorsUtility.BLACK);
                        slowPrint(" 3: 어바웃타임\n", 0, ColorsUtility.YELLOW);
                        slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
                        int chapter4_2_1_menu = sc.nextInt();

                        switch (chapter4_2_1_menu) {
                            case 1:
                                slowPrint(A + "은밀하게 위대하게 보자. 이거 재밌대\n", s, ColorsUtility.CYAN);
                                slowPrint(B + "그래~\n", s, ColorsUtility.PURPLE);
                                npc.increaseAffection(15); // 소꿉친구와의 호감도 증가
                                chapter4_2_1 = false;
                                break;
                            case 2:
                                slowPrint(A + "컨저링 보자. 완전 무섭겠다.ㅋㅋ\n", s, ColorsUtility.CYAN);
                                slowPrint(B + "아,, 무서울거 같은데\n", s, ColorsUtility.PURPLE);
                                npc.increaseAffection(-3); // 소꿉친구와의 호감도 증가
                                chapter4_2_1 = false;
                                break;
                            case 3:
                                slowPrint(A + "어바웃타임 볼래? 로맨스코미디 영화네\n", s, ColorsUtility.CYAN);
                                slowPrint(B + "그랭!\n", s, ColorsUtility.PURPLE);
                                npc.increaseAffection(10); // 소꿉친구와의 호감도 증가
                                chapter4_2_1 = false;
                                break;
                            default:
                                System.out.println(" 잘못된 선택입니다.");
                                // [1선택 후]호감도 + 10 / [2선택 후]호감도 - 5 / [3선택 후]호감도 +20
                        }
                    }
                    printClear(); // 화면 클리어
                    printMenuBar(); // 화면 메뉴바
                    slowPrint(A + "의외로 볼만 하네~\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "그러게\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "... 너 대학교는 전에 말했었던 인하대학교 진학할꺼야?\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "엉 그래보려고 너는?\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "난 수능성적보고 원서 넣어봐야지\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "너랑 같이 있었으면 좋겠다...\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "어? 뭐를?\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "너랑 같이 학교 다니면 좋겠다구~\n", s, ColorsUtility.PURPLE);
                    slowPrint(A + "아... 나도...\n", s, ColorsUtility.CYAN);
                    chapter4_2 = false;
                    break;

                case 2:
                    chapter4_2 = false;
                    slowPrint(A + "아,, 내일은 좀 그런데\n", s, ColorsUtility.CYAN);
                    slowPrint(B + "알겠어,, 담에 시간있을 때 보자\n", s, ColorsUtility.PURPLE);
                    npc.decreaseAffection(2); // 소꿉친구와의 호감도 감소
                    // 행복 수치에 미묘한 변화
                    sins.updateScore("envy");
                    mbti.updateScore("I"); // 내향성 증가
                    break;
                case 9:
                    System.out.println("\n소꿉친구와의 관계: " + npc.toString());
                    break;
                case 0:
                    System.out.println("\n현재 플레이어 상태: " + player.toString());
                    break;
            }
        }

        printClear(); // 화면 클리어
        printMenuBar(); // 화면 메뉴바

        boolean chapter5 = true;

        while (chapter5) {
            System.out.println(" 공부: " + player.getStudy());
            System.out.println(" NPC의 애정도: " + npc.getAffection());

            slowPrint(" [선택지]\n", 0, ColorsUtility.BLUE);
            slowPrint(" 1: 공부 수치 70이상 : 인하대\n", 0, ColorsUtility.PURPLE);
            slowPrint(" 2: 공부 수치 70이하 : 인하공전\n", 0, ColorsUtility.YELLOW);
            slowPrint(" 3: 재수\n", 0, ColorsUtility.RED);
            slowPrint(" 0: 플레이어 정보 조회\n", 0, ColorsUtility.WHITE);
            slowPrint(" 선택 >> ", 0, ColorsUtility.GREEN);
            int chapter5_menu = sc.nextInt();

            switch (chapter5_menu) {
                case 1:
                    if (player.getStudy() >= 70) {
                        if (npc.getAffection() >= 50) {
                            if (player.getGender() == "남성") {
                                player.setAge(player.getAge() + 3);
                                printMenuBar(); // 화면 메뉴바
                                slowPrint(" (어영부영 대학에 진학하고 20살이 되자마자 나는 군대에 입대하게 되었다.)\n", s, ColorsUtility.RED);
                                slowPrint(" (어떨떨 하게 군대를 제대하고 난 후, 처음 캠퍼스를 돌아다니던 그 때)\n", s, ColorsUtility.RED);
                                slowPrint(B + "야! " + player.getNickname() + "!!\n", s, ColorsUtility.PURPLE);
                                slowPrint(" 어디선가 들려오는 익숙한 목소리\n", s, ColorsUtility.RED);
                                slowPrint(B + "이씨~ 너 왜 연락 안했냐?\n", s, ColorsUtility.PURPLE);
                                slowPrint(A + "아니 뭐... 그냥~\n", 70, ColorsUtility.CYAN);
                                slowPrint(" {너를 좋아하는 마음 들킬까봐}\n", s, ColorsUtility.CYAN);
                                System.out.println();
                                slowPrint(B + "군대 갔다고 연락도 안하고\n", s, ColorsUtility.PURPLE);
                                slowPrint(" {보고싶을까봐}\n", s, ColorsUtility.CYAN);
                                System.out.println();
                                slowPrint(B + "... 너 걱정 많이했어...\n", s, ColorsUtility.PURPLE);
                                slowPrint(A + "... 왜?\n", s, ColorsUtility.CYAN);
                                slowPrint(B + "좋아..하니까...\n", s, ColorsUtility.PURPLE);
                                System.out.println();
                                slowPrint(" = 눈이 녹아 꽃이 피듯, [꽃]옆에는 언제나 [잡초]가 있듯, 그 아이 곁을 지켜주고 싶었다. =\n", s, ColorsUtility.CYAN);
                                chapter5 = false;
                            } else {
                                printMenuBar(); // 화면 메뉴바
                                player.setAge(player.getAge() + 1);
                                slowPrint(B + "야! " + player.getNickname() + "!!\n", s, ColorsUtility.PURPLE);
                                slowPrint(" 어디선가 들려오는 익숙한 목소리\n", s, ColorsUtility.RED);
                                slowPrint(B + "이씨~ 너 왜 연락 안했냐?\n", s, ColorsUtility.PURPLE);
                                slowPrint(A + "아니 뭐... 그냥~\n", s, ColorsUtility.CYAN);
                                slowPrint(" {너를 좋아하는 마음 들킬까봐}\n", s, ColorsUtility.CYAN);
                                System.out.println();
                                slowPrint(B + "군대 갔다고 연락도 안하고\n", s, ColorsUtility.PURPLE);
                                slowPrint(" {보고싶을까봐}\n", s, ColorsUtility.CYAN);
                                System.out.println();
                                slowPrint(B + "... 너 걱정 많이했어...\n", s, ColorsUtility.PURPLE);
                                slowPrint(A + "... 왜?\n", 80, ColorsUtility.CYAN);
                                slowPrint(B + "좋아..하니까...\n", 70, ColorsUtility.PURPLE);
                                System.out.println();
                                slowPrint(" = 눈이 녹아 꽃이 피듯, [꽃]옆에는 언제나 [잡초]가 있듯, 그 아이 곁을 지켜주고 싶었다. =\n", 90, ColorsUtility.CYAN);
                                chapter5 = false;
                            }
                        } else {
                            printMenuBar(); // 화면 메뉴바
                            player.setAge(player.getAge() + 10);
                            slowPrint(" " + N + "의 결혼을 축하해주세요~\n", s, ColorsUtility.RED);
                            slowPrint(" 장소: 서울 \n", s, ColorsUtility.RED);
                            slowPrint(" 시간 : 14:00 \n", s, ColorsUtility.RED);
                            printMenuBar(); // 화면 메뉴바

                            slowPrint(A + "...결혼하는구나\n", s, ColorsUtility.CYAN);
                            slowPrint(" 친구1 : 누구??\n", s, ColorsUtility.BLUE);
                            slowPrint(A + "내 친구.. 좀 오래 알고 지낸 친군데 이번에 결혼한다네\n", s, ColorsUtility.CYAN);
                            slowPrint(" 친구1 : 그럼 갔다와야겠네.\n", s, ColorsUtility.BLUE);
                            slowPrint(A + "....\n", s, ColorsUtility.CYAN);
                            slowPrint(" 친구1 : 왜? 안가게?\n", s, ColorsUtility.BLUE);
                            slowPrint(A + "글쎄.. 너무 오랜만이라..\n", s, ColorsUtility.CYAN);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);

                            slowPrint(" ??? : 지금부터 결혼식이 진행될 예정이오니 귀빈 여러분들께선 자리에 착석해 주시기 바랍니다.\n", s, ColorsUtility.BLUE);
                            slowPrint(" {결국 나는 또 아무것도 하지못하고 그저 우두커니 서있을 뿐이었다.}\n", s, ColorsUtility.CYAN);
                            slowPrint(" 딴다다단~~~~~\n", s, ColorsUtility.YELLOW);


                            slowPrint(" 하객1 : 워후~~잘어울린다!!\n", s, ColorsUtility.GREEN);
                            slowPrint(" 하객2 : 신부 이쁘네~\n", s, ColorsUtility.BLUE);
                            slowPrint(" 하객3 : 언제 끝나? 뷔페 자리 없을 거 같은데..\n", s, ColorsUtility.RED);
                            slowPrint(" 사회자 : 지금부터 사진촬영이 있겠습니다.\n", s, ColorsUtility.BLUE);
                            slowPrint(" 사회자 : 내빈 여러분들께서는 호명되는 순서에 맞게 차례대로 서주시기를 바랍니다.\n", s, ColorsUtility.BLUE);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);

                            slowPrint(" {그 애 뒤에 서는게 부끄러워 차마 사진은 찍지 못했다.}\n", s, ColorsUtility.CYAN);
                            slowPrint(" (밖으로 이동후)\n", s, ColorsUtility.YELLOW);
                            slowPrint(A + "후.. 하루가 기냐..\n", s, ColorsUtility.CYAN);
                            slowPrint(" ??? : " + player.getNickname() + "!!\n", s, ColorsUtility.PURPLE);
                            slowPrint(" {뒤를 돌아본 나는 깜짝 놀랄 수 밖에 없었다.}\n", s, ColorsUtility.CYAN);
                            slowPrint(B + "왔으면 왔다고 인사라도 해주지 왜 밥도 안먹고 그냥 가냐~?\n", s, ColorsUtility.PURPLE);
                            slowPrint(A + "아니 뭐.. 너 결혼하는 거 봤으니까..뭐....\n", s, ColorsUtility.CYAN);
                            slowPrint(B + ".....\n", s, ColorsUtility.PURPLE);
                            slowPrint(A + "아! 야... 나 친구랑 만나기로 해서 이만 가봐야겠다. \n", s, ColorsUtility.CYAN);
                            slowPrint(A + "갈게~ 결혼 축하해~ \n", s, ColorsUtility.CYAN);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);

                            slowPrint(B + "잠깐만!!\n", s, ColorsUtility.PURPLE);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);
                            slowPrint(B + "오늘 와줘서 고마워.잘지내~\n", s, ColorsUtility.PURPLE);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);
                            slowPrint(A + "어...그래....너도 잘지내~ \n", s, ColorsUtility.CYAN);
                            System.out.println();

                            slowPrint(" {계절은 지나 다시 돌아온다..하지만 봄에 핀 한송이의 꽃이 다시 돌아오지 않는 것처럼...}\n", s, ColorsUtility.CYAN);
                            slowPrint(" {우리의 소중한 기억과 추억으로만 간직해야겠다.}\n", s, ColorsUtility.CYAN);
                            chapter5 = false;
                        }
                    } else {
                        System.out.println("공부수치가 70미만입니다.");
                    }
                    break;
                case 2:
                    // 공부 수치 70이하 인하공전
                    if (player.getStudy() >= 50 && player.getStudy() < 70) {
                        if (npc.getAffection() >= 50) {
                            if (player.getGender() == "남성") {
                                player.setAge(player.getAge() + 3);
                                printMenuBar(); // 화면 메뉴바
                                slowPrint(" (어영부영 대학에 진학하고 20살이 되자마자 나는 군대에 입대하게 되었다.)\n", s, ColorsUtility.RED);
                                slowPrint(" (어떨떨 하게 군대를 제대하고 난 후, 처음 캠퍼스를 돌아다니던 그 때)\n", s, ColorsUtility.RED);
                                slowPrint(B + "야! " + player.getNickname() + "!!\n", s, ColorsUtility.PURPLE);
                                slowPrint(" 어디선가 들려오는 익숙한 목소리\n", s, ColorsUtility.RED);
                                slowPrint(B + "이씨~ 너 왜 연락 안했냐?\n", s, ColorsUtility.PURPLE);
                                slowPrint(A + "아니 뭐... 그냥~\n", 70, ColorsUtility.CYAN);
                                slowPrint(" {너를 좋아하는 마음 들킬까봐}\n", s, ColorsUtility.CYAN);
                                System.out.println();
                                slowPrint(B + "군대 갔다고 연락도 안하고\n", s, ColorsUtility.PURPLE);
                                slowPrint(" {보고싶을까봐}\n", s, ColorsUtility.CYAN);
                                System.out.println();
                                slowPrint(B + "... 너 걱정 많이했어...\n", s, ColorsUtility.PURPLE);
                                slowPrint(A + "... 왜?\n", s, ColorsUtility.CYAN);
                                slowPrint(B + "좋아..하니까...\n", s, ColorsUtility.PURPLE);
                                System.out.println();
                                slowPrint("= 눈이 녹아 꽃이 피듯, [꽃]옆에는 언제나 [잡초]가 있듯, 그 아이 곁을 지켜주고 싶었다. =\n", s, ColorsUtility.CYAN);
                                chapter5 = false;

                            } else if (player.getGender() == "여성") {
                                player.setAge(player.getAge() + 1);
                                printMenuBar(); // 화면 메뉴바
                                slowPrint(B + "야! " + player.getNickname() + "!!\n", s, ColorsUtility.PURPLE);
                                slowPrint(" 어디선가 들려오는 익숙한 목소리\n", s, ColorsUtility.RED);
                                slowPrint(B + "이씨~ 너 왜 연락 안했냐?\n", s, ColorsUtility.PURPLE);
                                slowPrint(A + "아니 뭐... 그냥~\n", s, ColorsUtility.CYAN);
                                slowPrint(" {너를 좋아하는 마음 들킬까봐}\n", s, ColorsUtility.CYAN);
                                System.out.println();
                                slowPrint(B + "군대 갔다고 연락도 안하고\n", s, ColorsUtility.PURPLE);
                                slowPrint(" {보고싶을까봐}\n", s, ColorsUtility.CYAN);
                                System.out.println();
                                slowPrint(B + "... 너 걱정 많이했어...\n", s, ColorsUtility.PURPLE);
                                slowPrint(A + "... 왜?\n", 80, ColorsUtility.CYAN);
                                slowPrint(B + "좋아..하니까...\n", 70, ColorsUtility.PURPLE);
                                System.out.println();
                                slowPrint(" = 눈이 녹아 꽃이 피듯, [꽃]옆에는 언제나 [잡초]가 있듯, 그 아이 곁을 지켜주고 싶었다. =\n", 90, ColorsUtility.CYAN);
                                chapter5 = false;

                            }
                        } else {
                            printMenuBar(); // 화면 메뉴바
                            player.setAge(player.getAge() + 10);
                            slowPrint(" " + N + "의 결혼을 축하해주세요~\n", s, ColorsUtility.RED);
                            slowPrint(" 장소: 서울 \n", s, ColorsUtility.RED);
                            slowPrint(" 시간 : 14:00 \n", s, ColorsUtility.RED);
                            printMenuBar(); // 화면 메뉴바

                            slowPrint(A + "...결혼하는구나\n", s, ColorsUtility.CYAN);
                            slowPrint(" 친구1 : 누구??\n", s, ColorsUtility.BLUE);
                            slowPrint(A + "내 친구.. 좀 오래 알고 지낸 친군데 이번에 결혼한다네\n", s, ColorsUtility.CYAN);
                            slowPrint(" 친구1 : 그럼 갔다와야겠네.\n", s, ColorsUtility.BLUE);
                            slowPrint(A + "....\n", s, ColorsUtility.CYAN);
                            slowPrint(" 친구1 : 왜? 안가게?\n", s, ColorsUtility.BLUE);
                            slowPrint(A + "글쎄.. 너무 오랜만이라..\n", s, ColorsUtility.CYAN);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);

                            slowPrint(" ??? : 지금부터 결혼식이 진행될 예정이오니 귀빈 여러분들께선 자리에 착석해 주시기 바랍니다.\n", s, ColorsUtility.BLUE);
                            slowPrint(" {결국 나는 또 아무것도 하지못하고 그저 우두커니 서있을 뿐이었다.}\n", s, ColorsUtility.CYAN);
                            slowPrint(" 딴다다단~~~~~\n", s, ColorsUtility.YELLOW);


                            slowPrint(" 하객1 : 워후~~잘어울린다!!\n", s, ColorsUtility.GREEN);
                            slowPrint(" 하객2 : 신부 이쁘네~\n", s, ColorsUtility.BLUE);
                            slowPrint(" 하객3 : 언제 끝나? 뷔페 자리 없을 거 같은데..\n", s, ColorsUtility.RED);
                            slowPrint(" 사회자 : 지금부터 사진촬영이 있겠습니다.\n", s, ColorsUtility.BLUE);
                            slowPrint(" 사회자 : 내빈 여러분들께서는 호명되는 순서에 맞게 차례대로 서주시기를 바랍니다.\n", s, ColorsUtility.BLUE);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);

                            slowPrint(" {그 애 뒤에 서는게 부끄러워 차마 사진은 찍지 못했다.}\n", s, ColorsUtility.CYAN);
                            slowPrint(" (밖으로 이동후)\n", s, ColorsUtility.YELLOW);
                            slowPrint(A + "후.. 하루가 기냐..\n", s, ColorsUtility.CYAN);
                            slowPrint(" ??? : " + player.getNickname() + "!!\n", s, ColorsUtility.PURPLE);
                            slowPrint(" {뒤를 돌아본 나는 깜짝 놀랄 수 밖에 없었다.}\n", s, ColorsUtility.CYAN);
                            slowPrint(B + "왔으면 왔다고 인사라도 해주지 왜 밥도 안먹고 그냥 가냐~?\n", s, ColorsUtility.PURPLE);
                            slowPrint(A + "아니 뭐.. 너 결혼하는 거 봤으니까..뭐....\n", s, ColorsUtility.CYAN);
                            slowPrint(B + ".....\n", s, ColorsUtility.PURPLE);
                            slowPrint(A + "아! 야... 나 친구랑 만나기로 해서 이만 가봐야겠다. \n", s, ColorsUtility.CYAN);
                            slowPrint(A + "갈게~ 결혼 축하해~ \n", s, ColorsUtility.CYAN);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);

                            slowPrint(B + "잠깐만!!\n", s, ColorsUtility.PURPLE);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);
                            slowPrint(B + "오늘 와줘서 고마워.잘지내~\n", s, ColorsUtility.PURPLE);
                            slowPrint(" ....\n", s, ColorsUtility.YELLOW);
                            slowPrint(A + "어...그래....너도 잘지내~ \n", s, ColorsUtility.CYAN);
                            System.out.println();

                            slowPrint(" {계절은 지나 다시 돌아온다..하지만 봄에 핀 한송이의 꽃이 다시 돌아오지 않는 것처럼...}\n", s, ColorsUtility.CYAN);
                            slowPrint(" {우리의 소중한 기억과 추억으로만 간직해야겠다.}\n", s, ColorsUtility.CYAN);
                            chapter5 = false;

                        }
                    } else {
                        System.out.println("인하공전도 못갈 성적입니다. 재수를 선택해주세요...");
                    }
                    break;

                case 3:
                    // 그 외 재수
                    if (npc.getAffection() >= 50) {
                        if (player.getGender() == "남성") {
                            printMenuBar(); // 화면 메뉴바
                            player.setAge(player.getAge() + 3);
                            slowPrint(" (어영부영 대학에 진학하고 20살이 되자마자 나는 군대에 입대하게 되었다.)\n", s, ColorsUtility.RED);
                            slowPrint(" (어떨떨 하게 군대를 제대하고 난 후, 처음 캠퍼스를 돌아다니던 그 때)\n", s, ColorsUtility.RED);
                            slowPrint(B + "야! " + player.getNickname() + "!!\n", s, ColorsUtility.PURPLE);
                            slowPrint(" 어디선가 들려오는 익숙한 목소리\n", s, ColorsUtility.RED);
                            slowPrint(B + "이씨~ 너 왜 연락 안했냐?\n", s, ColorsUtility.PURPLE);
                            slowPrint(A + "아니 뭐... 그냥~\n", 70, ColorsUtility.CYAN);
                            slowPrint(" {너를 좋아하는 마음 들킬까봐}\n", s, ColorsUtility.CYAN);
                            System.out.println();
                            slowPrint(B + "군대 갔다고 연락도 안하고\n", s, ColorsUtility.PURPLE);
                            slowPrint(" {보고싶을까봐}\n", s, ColorsUtility.CYAN);
                            System.out.println();
                            slowPrint(B + "... 너 걱정 많이했어...\n", s, ColorsUtility.PURPLE);
                            slowPrint(A + "... 왜?\n", s, ColorsUtility.CYAN);
                            slowPrint(B + "좋아..하니까...\n", s, ColorsUtility.PURPLE);
                            System.out.println();
                            slowPrint(" = 눈이 녹아 꽃이 피듯, [꽃]옆에는 언제나 [잡초]가 있듯, 그 아이 곁을 지켜주고 싶었다. =\n", s, ColorsUtility.CYAN);
                            chapter5 = false;

                        } else if (player.getGender() == "여성") {
                            player.setAge(player.getAge() + 1);
                            printMenuBar(); // 화면 메뉴바
                            slowPrint(B + "야! " + player.getNickname() + "!!\n", s, ColorsUtility.PURPLE);
                            slowPrint(" 어디선가 들려오는 익숙한 목소리\n", s, ColorsUtility.RED);
                            slowPrint(B + "이씨~ 너 왜 연락 안했냐?\n", s, ColorsUtility.PURPLE);
                            slowPrint(A + "아니 뭐... 그냥~\n", s, ColorsUtility.CYAN);
                            slowPrint(" {너를 좋아하는 마음 들킬까봐}\n", s, ColorsUtility.CYAN);
                            System.out.println();
                            slowPrint(B + "군대 갔다고 연락도 안하고\n", s, ColorsUtility.PURPLE);
                            slowPrint(" {보고싶을까봐}\n", s, ColorsUtility.CYAN);
                            System.out.println();
                            slowPrint(B + "... 너 걱정 많이했어...\n", s, ColorsUtility.PURPLE);
                            slowPrint(A + "... 왜?\n", 80, ColorsUtility.CYAN);
                            slowPrint(B + "좋아..하니까...\n", 70, ColorsUtility.PURPLE);
                            System.out.println();
                            slowPrint(" = 눈이 녹아 꽃이 피듯, [꽃]옆에는 언제나 [잡초]가 있듯, 그 아이 곁을 지켜주고 싶었다. =\n", 90, ColorsUtility.CYAN);
                            chapter5 = false;
                        }
                    } else {
                        printMenuBar(); // 화면 메뉴바
                        player.setAge(player.getAge() + 10);
                        slowPrint(" " + N + "의 결혼을 축하해주세요~\n", s, ColorsUtility.RED);
                        slowPrint(" 장소: 서울 \n", s, ColorsUtility.RED);
                        slowPrint(" 시간 : 14:00 \n", s, ColorsUtility.RED);
                        printMenuBar(); // 화면 메뉴바

                        slowPrint(A + "...결혼하는구나\n", s, ColorsUtility.CYAN);
                        slowPrint(" 친구1 : 누구??\n", s, ColorsUtility.BLUE);
                        slowPrint(A + "내 친구.. 좀 오래 알고 지낸 친군데 이번에 결혼한다네\n", s, ColorsUtility.CYAN);
                        slowPrint(" 친구1 : 그럼 갔다와야겠네.\n", s, ColorsUtility.BLUE);
                        slowPrint(A + "....\n", s, ColorsUtility.CYAN);
                        slowPrint(" 친구1 : 왜? 안가게?\n", s, ColorsUtility.BLUE);
                        slowPrint(A + "글쎄.. 너무 오랜만이라..\n", s, ColorsUtility.CYAN);
                        slowPrint(" ....\n", s, ColorsUtility.YELLOW);

                        slowPrint(" ??? : 지금부터 결혼식이 진행될 예정이오니 귀빈 여러분들께선 자리에 착석해 주시기 바랍니다.\n", s, ColorsUtility.BLUE);
                        slowPrint(" {결국 나는 또 아무것도 하지못하고 그저 우두커니 서있을 뿐이었다.}\n", s, ColorsUtility.CYAN);
                        slowPrint(" 딴다다단~~~~~\n", s, ColorsUtility.YELLOW);


                        slowPrint(" 하객1 : 워후~~잘어울린다!!\n", s, ColorsUtility.GREEN);
                        slowPrint(" 하객2 : 신부 이쁘네~\n", s, ColorsUtility.BLUE);
                        slowPrint(" 하객3 : 언제 끝나? 뷔페 자리 없을 거 같은데..\n", s, ColorsUtility.RED);
                        slowPrint(" 사회자 : 지금부터 사진촬영이 있겠습니다.\n", s, ColorsUtility.BLUE);
                        slowPrint(" 사회자 : 내빈 여러분들께서는 호명되는 순서에 맞게 차례대로 서주시기를 바랍니다.\n", s, ColorsUtility.BLUE);
                        slowPrint(" ....\n", s, ColorsUtility.YELLOW);

                        slowPrint(" {그 애 뒤에 서는게 부끄러워 차마 사진은 찍지 못했다.}\n", s, ColorsUtility.CYAN);
                        slowPrint(" (밖으로 이동후)\n", s, ColorsUtility.YELLOW);
                        slowPrint(A + "후.. 하루가 기냐..\n", s, ColorsUtility.CYAN);
                        slowPrint(" ??? : " + player.getNickname() + "!!\n", s, ColorsUtility.PURPLE);
                        slowPrint(" {뒤를 돌아본 나는 깜짝 놀랄 수 밖에 없었다.}\n", s, ColorsUtility.CYAN);
                        slowPrint(B + "왔으면 왔다고 인사라도 해주지 왜 밥도 안먹고 그냥 가냐~?\n", s, ColorsUtility.PURPLE);
                        slowPrint(A + "아니 뭐.. 너 결혼하는 거 봤으니까..뭐....\n", s, ColorsUtility.CYAN);
                        slowPrint(B + ".....\n", s, ColorsUtility.PURPLE);
                        slowPrint(A + "아! 야... 나 친구랑 만나기로 해서 이만 가봐야겠다. \n", s, ColorsUtility.CYAN);
                        slowPrint(A + "갈게~ 결혼 축하해~ \n", s, ColorsUtility.CYAN);
                        slowPrint(" ....\n", s, ColorsUtility.YELLOW);

                        slowPrint(B + "잠깐만!!\n", s, ColorsUtility.PURPLE);
                        slowPrint(" ....\n", s, ColorsUtility.YELLOW);
                        slowPrint(B + "오늘 와줘서 고마워.잘지내~\n", s, ColorsUtility.PURPLE);
                        slowPrint(" ....\n", s, ColorsUtility.YELLOW);
                        slowPrint(A + "어...그래....너도 잘지내~ \n", s, ColorsUtility.CYAN);
                        System.out.println();

                        slowPrint(" {계절은 지나 다시 돌아온다..하지만 봄에 핀 한송이의 꽃이 다시 돌아오지 않는 것처럼...}\n", s, ColorsUtility.CYAN);
                        slowPrint(" {우리의 소중한 기억과 추억으로만 간직해야겠다.}\n", s, ColorsUtility.CYAN);
                        chapter5 = false;
                    }
                    break;
                case 0:
                    System.out.println("\n현재 플레이어 상태: " + player.toString());
                    break;
                default:
                    System.out.println(" 잘못된 선택입니다.");
                    break;
            }
        }

        printClear();
        slowPrint(" THE END\n", 300, ColorsUtility.CYAN);

        boolean storyEnding = true;

        while (storyEnding) {

            slowPrint("\n심리검사 선택지 \n", 10, ColorsUtility.CYAN);
            slowPrint(" 1. MBTI 출력 \n", 10, ColorsUtility.BLUE);
            slowPrint(" 2. 선 성향 확인하기 \n", 10, ColorsUtility.GREEN);
            slowPrint(" 3. 악 성향 확인하기\n", 10, ColorsUtility.RED);
            slowPrint(" 4. 프로그램 종료\n", 10, ColorsUtility.YELLOW);
            System.out.print("선택 >> ");
            int storyEndingMenu = sc.nextInt();

            switch (storyEndingMenu) {
                case 1:
                    mbti.toString();
                    System.out.println(mbti.toString());
                    break;
                case 2:
                    virtues.toString();
                    System.out.println(virtues.toString());
                    break;
                case 3:
                    sins.toString();
                    System.out.println(sins.toString());
                    break;
                case 4:
                    slowPrint(" ⠀⠀⠀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⢠⡾⠛⠉⠙⢻⣦⣤⣴⠶⠶⢶⣤⣴⡿⠋⠉⠙⢿⣆⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⢿⡇⠀⢀⣴⠟⠋⠁⠀⠀⠀⠀⠀⠉⠛⢷⣄⠀⠀⣿⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠘⢷⣴⡟⠁⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣦⡾⠋⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⣿⠁⠀⠀⣿⡿⠀⠀⠀⠀⢸⣿⠇⠀⠀⢻⡆⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠘⣿⠀⠀⠀⢀⡤⠖⣒⣒⡒⠦⣄⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⢻⣆⠀⢠⠏⠀⠘⢿⡿⠋⠀⠈⣇⠀⢀⣿⠁⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⣀⣴⠿⣦⡘⡆⠰⢤⣀⣇⣠⠔⢀⡏⣠⡾⢷⣄⡀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⢀⣴⠿⠋⠀⢀⣼⠿⢿⣦⣄⣀⣀⣠⣴⣿⠿⢿⣄⠀⠉⠻⣦⡄⠀⠀⠀\n" +
                            "⠀⣠⡿⠁⠀⠀⣠⡿⠁⠀⠀⠀⠉⠉⠉⠉⠀⠀⠀⠀⠻⣦⠀⠀⠈⠻⣦⠀⠀\n" +
                            "⢰⡟⠀⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣧⠀⠀⠀⠹⣧⠀\n" +
                            "⣿⡇⠀⠀⠀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠀⠀⠀⣿⠄\n" +
                            "⠘⢷⣤⣤⡾⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡷⣦⣤⣴⠟⠀\n" +
                            "⠀⠀⠀⣠⡶⠟⠛⠻⢶⣄⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⠾⠛⠛⠷⣦⡀⠀⠀⠀\n" +
                            "⠀⠀⣸⠏⠀⠀⠀⠀⠀⠙⣷⡀⠀⠀⠀⠀⠀⣼⠟⠀⠀⠀⠀⠀⠘⣷⡀⠀⠀\n" +
                            "⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⢸⣇⠀⠀⠀⠀⢰⣿⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀\n" +
                            "⠀⠀⠹⣧⠀⠀⠀⠀⠀⠀⣸⡿⣦⣤⣤⣤⡾⣿⠀⠀⠀⠀⠀⠀⣠⡿⠀⠀⠀\n" +
                            "⠀⠀⠀⠙⢷⣤⣀⣀⣀⣴⠟⠁⠀⠀⠀⠀⠀⠙⢷⣄⣀⣀⣠⣴⠟⠁⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠈⠉⣉⡉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⢉⡉⠉⠀⠀⠀⠀⠀⠀\n", 10, ColorsUtility.YELLOW);
                    slowPrint(" 플레이 해주셔서 감사합니다...\n", 250, ColorsUtility.CYAN);
                    storyEnding = false;
                    sql.conClose();
                    System.exit(0);
                default:
                    System.out.println(" 잘못 입력하셨습니다.");
                    break;
            }
        }
    }
}