package six.rockets.services;

import six.rockets.models.Mission;
import six.rockets.models.Rocket;

public interface MissionPlanningService {
    void assignRocketsToMission(Mission mission, Rocket... rockets);
    String getMissionsSummary();
}
