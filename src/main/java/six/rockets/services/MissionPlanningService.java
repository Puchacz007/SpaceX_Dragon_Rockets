package six.rockets.services;

import java.util.UUID;

public interface MissionPlanningService {
    void assignRocketsToMission(UUID missionId, UUID... rocketIds);
}
