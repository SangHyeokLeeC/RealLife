package MAIN;

import DAO.SQL;
import SERVICE.ColorsUtility;
import SERVICE.GameUtility;
import SERVICE.LoginUtility;
import SERVICE.PrintUtility;

import java.util.InputMismatchException;
import java.util.Scanner;

import static SERVICE.PrintUtility.printGameMainMenu;

public class MBTISimulatorMain {
    public static void main(String[] args) {

        SQL sql = new SQL();
        Scanner sc = new Scanner(System.in);
        LoginUtility login = new LoginUtility();

        sql.connect(); // DB 연결
        // 화면 출력
        PrintUtility.printWelcomeMessage();

        boolean run = true;
        while (run) {
            try {
                // 화면 출력
                PrintUtility.printClear();;
                PrintUtility.printMainMenu();
                System.out.print(ColorsUtility.PURPLE + "선택한 번호를 입력하세요 >> " + ColorsUtility.RESET);
                int menu = sc.nextInt();

                switch (menu) {
                    case 1:
                        int isLoggedIn = login.login(); // 로그인 시도
                        if (isLoggedIn > 0) {
                            login.loginImpact(); // 로그인 성공 시 시각화 메소드 호출
                            boolean loginsus = true;

                            while (loginsus) {

                                printGameMainMenu();
                                System.out.print(ColorsUtility.PURPLE + "선택한 번호를 입력하세요 >> " + ColorsUtility.PURPLE);
                                int menu1 = sc.nextInt(); // 사용자로부터 메뉴 선택 입력 받기
                                GameUtility game = new GameUtility();

                                switch (menu1) {
                                    case 1:
                                        PrintUtility.printClear();
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
                                        loginsus = false;
                                        break;

                                    default:
                                        System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("회원정보가 일치하지 않습니다.");
                            // 로그인 실패 처리 로직
                            break;
                        }
                    case 2:
                        login.UserJoin();
                        break;
                    case 3:
                        PrintUtility.gameIntroduction();
                        break;
                    case 0:
                        PrintUtility.goodbyeMessage();
                        sql.conClose(); // DB 연결 해제
                        run = false;
                        break;
                    default:
                        PrintUtility.printInvalidOptionMessage();
                        break;
                }
            } catch (InputMismatchException e) {
                PrintUtility.printInputErrorMessage();
                sc.nextLine(); // 입력 버퍼 클리어
            }
        }
    }
}
