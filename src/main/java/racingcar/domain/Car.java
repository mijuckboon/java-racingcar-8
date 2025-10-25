package racingcar.domain;

/**
 * 경주에 참가하는 자동차 객체
 * <p>
 * name: 이름 (주 식별자)
 * </p>
 */
public class Car { // Result 안에 합병할 수도 있으나, 추후 확장성을 위해 유지
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public Car(String name) {
        if (name == null) {
            throw new IllegalArgumentException("이름은 필수입니다."); // 주 식별자 역할 필요 + trim으로 인한 NPE 방지
        }
        String trimmedName = name.trim();
        validateName(trimmedName);

        this.name = trimmedName;
    }

    public String getName() {
        return name;
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
}
