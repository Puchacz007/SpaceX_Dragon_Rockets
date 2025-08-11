package six.rockets.services;


import six.rockets.enums.MissionStatus;
import six.rockets.models.Mission;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MissionServiceImpl implements MissionService {

    List<Mission> missions = new ArrayList<>();

    @Override
    public UUID addMissions(Mission... missions) {
        return null;
    }

    @Override
    public void changeMissionStatus(UUID missionId, MissionStatus status) {

    }

    @Override
    public List<Mission> getAllMissions() {
        return List.of();
    }

    @Override
    public Mission getMissionById(UUID missionId) {
        return null;
    }
}