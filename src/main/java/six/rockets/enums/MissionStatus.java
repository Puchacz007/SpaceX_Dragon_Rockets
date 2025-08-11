package six.rockets.enums;

import lombok.Getter;

@Getter
public enum MissionStatus {
    SCHEDULED("Scheduled"),
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    ENDED("Ended");

    private final String text;

    MissionStatus(String text) {
        this.text = text;
    }

}
