package SERVICE;

import DTO.Player;
import SERVICE.ColorsUtility;
import java.util.Scanner;

import static SERVICE.PrintUtility.*;

public class BuildingUtility {
    // 매점 구현
    public static void cafeteria(Player player) {
        Scanner scanner = new Scanner(System.in);

        boolean exitCafeteria = false; // 매점을 나갈지 여부를 결정하는 플래그

        while (!exitCafeteria) {
            printMenuBar();
            slowPrint(" 매점에 오신 것을 환영합니다! 무엇을 구매하시겠습니까?\n", 50, ColorsUtility.GREEN);
            slowPrint(ColorsUtility.CYAN + " 1: 맛스타 (2000원)\n",0,ColorsUtility.RESET);
            slowPrint(ColorsUtility.YELLOW + " 2: 만두 (2000원)\n",0,ColorsUtility.RESET);
            slowPrint(ColorsUtility.BLUE + " 3: 과자 (2000원)\n",0,ColorsUtility.RESET);
            slowPrint(ColorsUtility.PURPLE + " 4: 웰치스 두리안맛 (2000원)\n",0,ColorsUtility.RESET);
            slowPrint(ColorsUtility.CYAN + " 5: 물 (2000원)\n",0,ColorsUtility.RESET);
            slowPrint(ColorsUtility.RED + " 9: 나가기\n",0,ColorsUtility.RESET);
            slowPrint(ColorsUtility.WHITE + " 0 :플레이어 정보 조회\n",0,ColorsUtility.RESET);
            slowPrint(ColorsUtility.GREEN + " 선택 >> ",10,ColorsUtility.RESET);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    player.setMoney(player.getMoney() - 2000);
                    player.setHappiness(player.getHappiness() + 4); // 행복도
                    slowPrint(" 맛스타를 구매하셨습니다.\n", 50, ColorsUtility.CYAN);
                    slowPrint(" 행복 + 4 상승했습니다.\n", 50, ColorsUtility.GREEN);
                    break;
                case 2:
                    player.setMoney(player.getMoney() - 2500);
                    player.setHappiness(player.getHappiness() + 5);
                    slowPrint(" 만두를 구매하셨습니다.\n", 50, ColorsUtility.YELLOW);
                    slowPrint(" 행복 + 5 상승했습니다.\n", 50, ColorsUtility.GREEN);
                    break;
                case 3:
                    player.setMoney(player.getMoney() - 1500);
                    player.setHappiness(player.getHappiness() + 3);
                    slowPrint(" 과자를 구매하셨습니다.\n", 50, ColorsUtility.BLUE);
                    slowPrint(" 행복 + 3 상승했습니다.\n", 50, ColorsUtility.GREEN);
                    break;
                case 4:
                    player.setMoney(player.getMoney() - 1000);
                    player.setHappiness(player.getHappiness() + 2);
                    slowPrint(" 웰치스 포도맛을 구매하셨습니다.\n", 50, ColorsUtility.PURPLE);
                    slowPrint(" 행복 + 2 상승했습니다.\n", 50, ColorsUtility.GREEN);
                    break;
                case 5:
                    player.setHealth(player.getHealth() + 1);
                    player.setHappiness(player.getHappiness() + 1);
                    slowPrint(" 물을 구매하셨습니다.\n", 50, ColorsUtility.CYAN);
                    slowPrint(" 행복 + 1 상승했습니다.\n", 50, ColorsUtility.GREEN);
                    break;
                case 9:
                    slowPrint(" 매점을 나갑니다.\n", 50, ColorsUtility.RED);
                    exitCafeteria = true; // 루프 종료
                    break;
                case 0:
                    System.out.println("\n현재 플레이어 상태: " + player.toString());
                    break;
                default:
                    slowPrint(" 잘못된 선택입니다. 다시 선택해 주세요.\n", 50, ColorsUtility.RED);
                    break;
            }
        }
    }
}
