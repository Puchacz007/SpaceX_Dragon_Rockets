package six.rockets.services;

import six.rockets.enums.MissionStatus;
import six.rockets.models.Mission;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MissionService {
    void addMissions(Mission... newMissions);
    void changeMissionStatus(UUID missionId, MissionStatus status);
    List<Mission> getAllMissions();
    Optional<Mission> getMissionById(UUID missionId);
}
