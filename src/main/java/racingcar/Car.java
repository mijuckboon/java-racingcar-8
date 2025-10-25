package racingcar;

/**
 * 경주에 참가하는 자동차 객체
 * <p>
 * name: 이름 (주 식별자)
 * position: 위치
 * </p>
 */
public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MOVE_UNIT = 1;

    private final String name;
    private int position;

    public Car(String name) {
        if (name == null) {
            throw new IllegalArgumentException("이름은 필수입니다."); // 주 식별자 역할 필요 + trim으로 인한 NPE 방지
        }
        String trimmedName = name.trim();
        validateName(trimmedName);

        this.name = trimmedName;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move() {
        position += MOVE_UNIT; // 멀티 스레드 환경이면 동시성 문제 발생할 수 있음 (synchronized 혹은 AtomicInteger 사용)
        validatePosition(position);
    }

    private void validateName(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 %d글자 이내여야 합니다. (입력값: %s, 길이: %d)".formatted(
                    MAX_NAME_LENGTH, name, name.length()
            ));
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 빈 문자열일 수 없습니다.");
        }
    }

    private void validatePosition(int position) {
        if (position < RacingManager.MIN_POSITION) {
            throw new IllegalArgumentException("위치는 %d보다 작은 값일 수 없습니다. (입력값: %d)".formatted(
                    RacingManager.MIN_POSITION, position)
            );
        }
    }
}
