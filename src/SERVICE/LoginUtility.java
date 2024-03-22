package SERVICE;

import DAO.SQL;
import DTO.Users;
import java.util.Scanner;
import static SERVICE.PrintUtility.slowPrint;
import static SERVICE.ColorsUtility.*;

public class LoginUtility {

    // 로그인 메소드
    public int login() {

        SQL sql = new SQL();
        Scanner sc = new Scanner(System.in);
        sql.connect();

        slowPrint("\n[로그인]\n> 계속하려면 사용자 이름과 비밀번호를 입력하세요...\n", 20, GREEN);
        slowPrint(GREEN + "아이디: ", 20, RESET);
        String inputId = sc.next();
        slowPrint(GREEN + "비밀번호: ", 20, RESET);
        String inputPw = sc.next();

        return sql.login(inputId, inputPw);
    }

    // 로그인 시각화 메소드
    public void loginImpact() {
        String loginIn = "";
        for (int i = 0; i < 3; i++) {
            loginIn += ".";
            slowPrint("\r로그인 중" + loginIn, 10, YELLOW);
        }
        slowPrint("\n로그인 - 성공!\n", 30, GREEN);
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    // 회원가입 메소드
    public void UserJoin() {
        SQL sql = new SQL();
        sql.connect();
        Scanner sc = new Scanner(System.in);

        slowPrint(YELLOW + "이름: ", 20, RESET);
        String uName = sc.next();
        slowPrint(YELLOW + "아이디: ", 20, RESET);
        String userId = sc.next();
        slowPrint(YELLOW + "비밀번호: ", 20, RESET);
        String uPw = sc.next();
        slowPrint(YELLOW + "이메일: ", 20, RESET);
        String uEmail = sc.next();

        Users users = new Users(uName, userId, uPw, uEmail);
        sql.memberJoin(users);
    }

}
