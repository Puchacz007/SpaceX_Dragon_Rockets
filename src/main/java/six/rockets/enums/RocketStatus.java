package six.rockets.enums;

import lombok.Getter;

@Getter
public enum RocketStatus {
    ON_GROUND("On ground"),
    IN_SPACE("In space"),
    IN_REPAIR("In repair");

    private final String text;

    RocketStatus(String text) {
        this.text = text;
    }
}
