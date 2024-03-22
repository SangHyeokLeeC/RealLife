package SERVICE;

public class ColorsUtility {
    // ANSI 색상 코드 정의
    public static final String BLACK = "\u001B[30m"; // 검정색
    public static final String RED = "\u001B[31m";   // 빨간색
    public static final String GREEN = "\u001B[32m"; // 초록색
    public static final String YELLOW = "\u001B[33m"; // 노란색
    public static final String BLUE = "\u001B[34m";  // 파란색
    public static final String PURPLE = "\u001B[35m"; // 보라색
    public static final String CYAN = "\u001B[36m";  // 청록색
    public static final String WHITE = "\u001B[37m"; // 흰색

    // 색상 초기화 메소드. 이 코드는 색상 적용 후 다시 기본 색상으로 돌아가기 위해 사용됩니다.
    public static final String RESET = "\u001B[0m";

    /**
     * 특정 텍스트에 색상을 적용하여 반환하는 메소드
     * @param text      색상을 적용할 텍스트
     * @param colorCode 적용할 색상의 ANSI 코드
     * @return 색상이 적용된 텍스트 문자열을 반환
     */
    public static String colorize(String text, String colorCode) {
        // 지정된 색상 코드를 텍스트 앞에 추가하고, RESET 코드를 끝에 추가하여
        // 텍스트 출력 후 콘솔 색상을 기본값으로 리셋.
        // 이 메서드는 slowPrint와 이어집니다.
        return colorCode + text + RESET;
    }
}
