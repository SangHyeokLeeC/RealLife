package SERVICE;

import java.util.Scanner;

import static SERVICE.PrintUtility.printGameMainMenu;

public class MenuUtility {

    // 로그인 성공시 메뉴를 출력하는 메소드
    public static void gameMainMenu() {
        boolean keepRunning = true;
        Scanner sc = new Scanner(System.in);

        while (keepRunning) {

            printGameMainMenu();
            System.out.print(ColorsUtility.PURPLE + "선택한 번호를 입력하세요 >> " + ColorsUtility.RESET);
            int menu = sc.nextInt(); // 사용자로부터 메뉴 선택 입력 받기
            GameUtility game = new GameUtility();

            switch (menu) {
                case 1:
                    game.startGame();
                    break;

                case 2:
                    // 게임 불러오기 로직
                    break;

                case 3:
                    // 설정 변경 로직
                    game.GameSetting();
                    break;

                case 0:
                    // 로그아웃
                    keepRunning = false;
                    break;

                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }
}