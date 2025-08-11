package six.rockets.services;

import six.rockets.enums.MissionStatus;
import six.rockets.models.Mission;
import java.util.List;
import java.util.UUID;

public interface MissionService {
    UUID addMissions(Mission... mission);
    void changeMissionStatus(UUID missionId, MissionStatus status);
    List<Mission> getAllMissions();
    Mission getMissionById(UUID missionId);
}
