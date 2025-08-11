package six.rockets.services;

import six.rockets.models.Mission;
import six.rockets.models.Rocket;

import java.util.UUID;

public class MissionPlanningServiceImpl implements MissionPlanningService {

    private RocketService rocketService;
    private MissionService missionService;

    public MissionPlanningServiceImpl(RocketService rocketService, MissionService missionService) {
        this.rocketService = rocketService;
        this.missionService = missionService;
    }

    @Override
    public void assignRocketsToMission(Mission mission, Rocket... rockets) {

    }

    @Override
    public String getMissionsSummary() {
        return null;
    }
}