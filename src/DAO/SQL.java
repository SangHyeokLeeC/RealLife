package DAO;

import DTO.NPC;
import DTO.Player;
import DTO.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {
    Connection con; // 데이터베이스 연결 객체
    PreparedStatement pstmt; // SQL문을 데이터베이스에 전송하기 위한 객체
    ResultSet rs; // SQL 쿼리의 결과를 저장하기 위한 객체

    /**
     * 데이터베이스 연결을 설정하는 메소드.
     */
    public void connect() {
        con = DBC.DBConnect(); // 데이터베이스 연결 설정
        pstmt = null; // PreparedStatement 객체 초기화
        rs = null; // ResultSet 객체 초기화
    }

    /**
     * 데이터베이스 연결을 해제하는 메소드.
     */
    public void conClose() {
        try {
            if (rs != null) rs.close(); // ResultSet 객체가 null이 아니면 닫기
            if (pstmt != null) pstmt.close(); // PreparedStatement 객체가 null이 아니면 닫기
            if (con != null) con.close(); // Connection 객체 닫기
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 발생시 예외 정보 출력
        }
    }

    /**
     * 사용자 회원가입을 처리하는 메소드.
     *
     * @param users 사용자 정보가 담긴 Users 객체
     */
    public void memberJoin(Users users) {
        // USERS 테이블에 데이터를 추가하는 SQL 쿼리
        String sql = "INSERT INTO USERS(UNAME, USERID, UPW, UEMAIL) VALUES(?,?,?,?)";

        // 자동 생성된 UCODE 값을 얻기 위한 설정
        String[] generatedKeys = {"UCODE"};

        try {
            // 사용자 정보 설정
            pstmt = con.prepareStatement(sql, generatedKeys);

            pstmt.setString(1, users.getuName());
            pstmt.setString(2, users.getUserId());
            pstmt.setString(3, users.getuPw());
            pstmt.setString(4, users.getuEmail());

            // USERS 테이블에 데이터 추가 실행
            int usersResult = pstmt.executeUpdate();

            if (usersResult > 0) {
                System.out.println("회원가입 성공");

                try (ResultSet KeysResult = pstmt.getGeneratedKeys()) {

                    if (KeysResult.next()) {
                        // 자동 생성된 UCODE 값 얻기
                        int ucode = KeysResult.getInt(1);

                        // PLAYERS 테이블에 사용자 기본 정보 삽입
                        // 게임 시작 시 사용자로부터 받은 정보를 기반으로 SQL 쿼리 실행
                        String sql1 = "INSERT INTO PLAYERS (UCODE) VALUES (?)";
                        try (PreparedStatement pstmt = con.prepareStatement(sql1)) {
                            // UCODE 설정
                            pstmt.setLong(1, ucode);

                            int result = pstmt.executeUpdate();
                            if (result > 0) {
                                System.out.println("새로운 플레이어 정보가 PLAYERS 테이블에 추가되었습니다.");
                            } else {
                                System.out.println("플레이어 정보 추가 실패.");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                System.out.println("회원가입 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 사용자 로그인을 처리하는 메소드.
     *
     * @param inputId 사용자 입력 ID
     * @param inputPw 사용자 입력 비밀번호
     * @return 로그인 성공 여부
     */
    public int login(String inputId, String inputPw) {
        connect();
        int playerId = -1; // 로그인 실패 시의 기본값. 로그인 성공 시, 사용자의 PLAYERID를 저장
        String userSql = "SELECT UCODE FROM USERS WHERE USERID = ? AND UPW = ?"; // USERID를 이용하여 UCODE를 가져오는 SQL 쿼리
        try {
            pstmt = con.prepareStatement(userSql); // PreparedStatement 객체 생성
            pstmt.setString(1, inputId); // 사용자 입력 ID 설정
            pstmt.setString(2, inputPw); // 사용자 입력 비밀번호 설정
            rs = pstmt.executeQuery(); // SQL 쿼리 실행 및 결과 저장

            if (rs.next()) {
                // 로그인 성공 처리, UCODE 가져오기
                int uCode = rs.getInt("UCODE");

                // PLAYERID 가져오기
                String playerSql = "SELECT PLAYERID FROM PLAYERS WHERE UCODE = ?";
                try (PreparedStatement pstmtPlayer = con.prepareStatement(playerSql)) {
                    pstmtPlayer.setInt(1, uCode);
                    try (ResultSet rsPlayer = pstmtPlayer.executeQuery()) {
                        if (rsPlayer.next()) {
                            playerId = rsPlayer.getInt("PLAYERID");
                            System.out.println("로그인 성공! \n플레이어 ID: " + playerId);
                        }
                    }
                }
            } else {
                // 로그인 실패 처리
                System.out.println("로그인 실패.."); // 로그인 실패 메시지 출력
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 발생시 예외 정보 출력
        }
        return playerId; // 로그인 성공 시 사용자의 PLAYERID 반환, 실패 시 -1 반환
    }


    public void savePlayer(Player player, int isLoggedIn)
    {
        // PLAYERID가 -1이 아니면 기존 플레이어 정보를 업데이트한다고 가정
        if (isLoggedIn != -1) {
            // PLAYERS 테이블에서 해당 PLAYERID에 해당하는 레코드를 업데이트
            String updateSql = "UPDATE PLAYERS SET NICKNAME = ?, GENDER = ?, JOB = ?, AGE = ?, HEALTH = ?, STUDY = ?, HOBBY = ?, HAPPINESS = ?, MONEY = ? WHERE PLAYERID = ?";
            try (PreparedStatement pstmt = con.prepareStatement(updateSql)) {
                pstmt.setString(1, player.getNickname());
                pstmt.setString(2, player.getGender());
                pstmt.setString(3, player.getJob());
                pstmt.setInt(4, player.getAge());
                pstmt.setInt(5, player.getHealth());
                pstmt.setInt(6, player.getStudy());
                pstmt.setInt(7, player.getHobby());
                pstmt.setInt(8, player.getHappiness());
                pstmt.setInt(9, player.getMoney());
                pstmt.setInt(10, isLoggedIn); // 로그인 시 받은 PLAYERID를 사용

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    System.out.println("플레이어 정보가 성공적으로 업데이트되었습니다.");
                } else {
                    System.out.println("플레이어 정보 업데이트 실패.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void npcPlayer(NPC npc, int isLoggedIn) {
        if (isLoggedIn != -1) {
            // NPC 테이블에 데이터를 추가하는 SQL 쿼리
            String insertSql = "INSERT INTO NPC (PLAYERID, NAME, GENDER, AFFECTION) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                pstmt.setInt(1, isLoggedIn); // 현재 로그인한 사용자의 PLAYERID
                pstmt.setString(2, npc.getName());
                pstmt.setString(3, npc.getGender());
                pstmt.setInt(4, npc.getAffection());

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    System.out.println("NPC 정보가 성공적으로 추가되었습니다.");
                } else {
                    System.out.println("NPC 정보 추가 실패.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
