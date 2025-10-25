package racingcar;

/**
 * 한 라운드의 결과를 저장하는 객체
 */
public class RoundResult {
    private static final int MOVE_UNIT = 1;
    public static final int MIN_POSITION = 0;

    private final int roundIndex;
    private final int randomValue;
    private final boolean isMoved;
    private int position;

    public RoundResult(int roundIndex, int randomValue, boolean isMoved, int position) {
        this.roundIndex = roundIndex;
        this.randomValue = randomValue;
        this.isMoved = isMoved;
        this.position = position;
    }

    public int getRoundIndex() {
        return roundIndex;
    }

    public int getRandomValue() {
        return randomValue;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public int getPosition() {
        return position;
    }

    public void moveCar() { // 멀티 스레드 환경이면 동시성 문제 발생할 수 있음 (synchronized 혹은 AtomicInteger 사용)
        position += MOVE_UNIT;
        validatePosition(position);
    }


    private void validatePosition(int position) {
        if (position < MIN_POSITION) {
            throw new IllegalArgumentException("위치는 %d보다 작은 값일 수 없습니다. (입력값: %d)".formatted(
                    MIN_POSITION, position)
            );
        }
    }
}
