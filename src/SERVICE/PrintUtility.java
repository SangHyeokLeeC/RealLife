package SERVICE;

import static SERVICE.ColorsUtility.*;

public class PrintUtility {
    // 슬로우 print
    public static void slowPrint(String message, long millisPerChar, String color) {
        message = ColorsUtility.colorize(message, color); // 색상 적용
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(millisPerChar);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    //

    // 화면 클리어 시각적 효과
    public static void printClear() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    // 화면 테마
    public static void printMenuBar(){
        slowPrint(ColorsUtility.GREEN + "════════════════════════════════════════════════════════════════\n", 5, ColorsUtility.RESET);
    }

    // [1] 게임 시작 시 환영 메시지를 출력하는 메소드.
    public static void printWelcomeMessage() {
        String[][] linesAndColors = {
                {"=================================================", ColorsUtility.CYAN},
                {"=      MBTI 시뮬레이터에 오신 것을 환영합니다!       =", ColorsUtility.GREEN},
                {"=   게임을 통해 당신의 MBTI 유형을 발견해보세요!     =", ColorsUtility.YELLOW},
                {"=================================================", ColorsUtility.CYAN}
        };

        System.out.println(RED + "   ===========================================" + RED);
        System.out.println(GREEN + "   =   M       M    BBBBBB   TTTTTTTT   III  =" + GREEN);
        System.out.println(YELLOW + "   =   MM     MM    BB  BB      TT       I   =" + YELLOW);
        System.out.println(BLUE + "   =   M M   M M    BBBBBB      TT       I   =" + BLUE);
        System.out.println(PURPLE + "   =   M  M M  M    BB  BB      TT       I   =" + PURPLE);
        System.out.println(CYAN + "   =   M   M   M    BBBBBB      TT      III  =" + CYAN);
        System.out.println(RED + "   ===========================================" + RED);

        for (String[] lineAndColor : linesAndColors) {
            String line = lineAndColor[0];
            String color = lineAndColor[1];
            slowPrint(line + "\n", 30, color);
        }
    }

    // [2] 로그인 회원가입 메뉴를 출력하는 메소드
    public static void printMainMenu() {
        slowPrint(ColorsUtility.CYAN + "\n｀、、｀ヽ｀ヽ｀、、ヽヽ、｀、ヽ｀ヽ｀ヽヽ｀ヽ｀ヽ｀ヽヽ｀ヽヽ｀ヽ｀ヽ｀ヽヽ｀\n ヽ｀、｀ヽ｀、ヽ｀｀、ヽ｀ヽ｀、ヽヽ｀ヽ、ヽヽ｀ヽ｀ヽヽ｀ヽヽ｀ヽ｀ヽ｀ヽヽ｀\n｀ヽ、ヽヽ｀ヽ｀、｀｀ヽ｀ヽ、ヽ、ヽ｀ヽ｀ヽヽ｀ヽ｀ヽヽ｀ヽヽ｀ヽ｀ヽ｀ヽヽ｀\n、ヽ｀ヽ｀、ヽヽ｀｀、ヽ｀、ヽヽ｀ヽ｀｀ヽ｀ヽ｀ヽヽ｀ヽヽ｀ヽ｀ヽ｀ヽ｀ヽ\n", 10, ColorsUtility.CYAN);
        System.out.println(CYAN + "╔══════════════════════════ MENU ══════════════════════════════╗" + CYAN);
        System.out.println(CYAN + "║                       WELCOME TO MBTI                        ║" + CYAN);
        System.out.println(GREEN + "    1. LOGIN                                                   " + GREEN);
        System.out.println(YELLOW + "    2. 회원가입                                                  " + YELLOW);
        System.out.println(BLUE + "    3. 게임소개                                                  " + BLUE);
        System.out.println(RED + "    0. 종료                                                     " + RED);
        System.out.println(CYAN + "╚══════════════════════════════════════════════════════════════╝" + CYAN);
        
    }

    // [2.1] 게임 소개 메시지를 출력하는 메소드
    public static void gameIntroduction() {
        slowPrint("\n[게임 소개]\n‘MBTI 시뮬레이터’는 다양한 시나리오를 통해 여러분의 MBTI 성격 유형을 알아볼 수 있는 시뮬레이션 게임입니다.\r여러분의 성격을 탐험하며 즐거운 시간을 보내세요!\n", 20, ColorsUtility.BLUE);
    }

    // [2.2] 프로그램 종료 메시지를 출력하는 메소드
    public static void goodbyeMessage() {
        slowPrint("\nMBTI 시뮬레이터를 이용해주셔서 감사합니다. 안녕히 가세요!\n", 20, ColorsUtility.RED);
    }

    // [2.3] 잘못된 입력에 대한 메시지를 출력하는 메소드
    public static void printInvalidOptionMessage() {
        slowPrint("\n잘못된 옵션입니다. 다시 입력해주세요.\n", 50, ColorsUtility.WHITE);
    }

    // [2.4] 사용자 입력 오류 메시지를 출력하는 메소드
    public static void printInputErrorMessage() {
        slowPrint("\n오류: 유효한 입력을 해주세요.\n", 50, ColorsUtility.RED);
    }
    
    // [3] StoryMainMenu 메인 메뉴
    public static void printGameMainMenu() {
        slowPrint(ColorsUtility.CYAN + "｀、、｀ヽ｀ヽ｀、、ヽヽ、｀、ヽ｀ヽ｀ヽヽ｀ヽ｀ヽ｀ヽヽ｀ヽヽ｀ヽ｀ヽ｀ヽヽ｀\n" +
                "ヽ｀、｀ヽ｀、ヽ｀｀、ヽ｀ヽ｀、ヽヽ｀ヽ、ヽヽ｀ヽ｀ヽヽ｀ヽヽ｀ヽ｀ヽ｀ヽヽ｀\n" +
                "｀ヽ、ヽヽ｀ヽ｀、｀｀ヽ｀ヽ、ヽ、ヽ｀ヽ｀ヽヽ｀ヽ｀ヽヽ｀ヽヽ｀ヽ｀ヽ｀ヽヽ｀\n" +
                "、ヽ｀ヽ｀、ヽヽ｀｀、ヽ｀、ヽヽ｀ヽ｀｀ヽ｀ヽ｀ヽヽ｀ヽヽ｀ヽ｀ヽ｀ヽヽ｀\n", 10, ColorsUtility.CYAN);
        System.out.println(CYAN + "╔══════════════════════════ MENU ══════════════════════════════╗" + CYAN);
        System.out.println(CYAN + "║                       WELCOME TO MBTI                        ║" + CYAN);
        System.out.println(GREEN + "    1. 새로운 게임                                                   " + GREEN);
        System.out.println(YELLOW + "    2. 불러오기                                                  " + YELLOW);
        System.out.println(BLUE + "    3. 설정                                                 " + BLUE);
        System.out.println(RED + "    0. 로그아웃                                                     " + RED);
        System.out.println(CYAN + "╚══════════════════════════════════════════════════════════════╝" + CYAN);

    }
}

