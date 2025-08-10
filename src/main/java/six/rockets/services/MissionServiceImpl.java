package six.rockets.services;


import six.rockets.enums.MissionStatus;
import six.rockets.models.Mission;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MissionServiceImpl implements MissionService {

    List<Mission> missions = new ArrayList<>();

    @Override
    public UUID addMission() {
        return null;
    }

    @Override
    public void changeMissionStatus(UUID missionId, MissionStatus status) {

    }

    @Override
    public void getMissionsSummary() {

    }
}