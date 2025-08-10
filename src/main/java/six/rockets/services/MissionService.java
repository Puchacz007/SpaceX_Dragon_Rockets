package six.rockets.services;

import six.rockets.enums.MissionStatus;

import java.util.UUID;

public interface MissionService {
    UUID addMission();
    void changeMissionStatus(UUID missionId, MissionStatus status);
    void getMissionsSummary();
}
