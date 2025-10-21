package racingcar;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;

    private String name;
    private int position;

    public Car(String name) {
        if (name == null) {
            throw new IllegalArgumentException(); // trim으로 인한 NPE 방지
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

    public void validateName(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 %d글자 이내여야 합니다.".formatted(MAX_NAME_LENGTH));
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 빈 문자열일 수 없습니다.");
        }
    }
}
