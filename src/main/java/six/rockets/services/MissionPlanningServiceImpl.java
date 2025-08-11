package six.rockets.services;

import six.rockets.Exception.RocketException;
import six.rockets.enums.MissionStatus;
import six.rockets.enums.RocketStatus;
import six.rockets.models.Mission;
import six.rockets.models.Rocket;

import java.util.Comparator;
import java.util.List;

public class MissionPlanningServiceImpl implements MissionPlanningService {

    private final RocketService rocketService;
    private final MissionService missionService;

    public MissionPlanningServiceImpl(RocketService rocketService, MissionService missionService) {
        this.rocketService = rocketService;
        this.missionService = missionService;
    }

    @Override
    public void assignRocketsToMission(Mission mission, Rocket... rockets) {
        for(Rocket rocket : rockets) {
            if (rocket.getMission() == null) {
                missionService.changeMissionStatus(mission.getId(), MissionStatus.IN_PROGRESS);
                rocket.setMission(mission);
                rocketService.changeRocketStatus(rocket.getId(), RocketStatus.IN_SPACE);
                mission.getRockets().add(rocket);
            } else {
                throw new RocketException("Rocket with id " + rocket.getId() + "is already assigned to another mission with id " + rocket.getMission().getId());
            }
        }
    }

    @Override
    public String getMissionsSummary() {
        List<Mission> missions = missionService.getAllMissions();
        missions = missions.stream().sorted(Comparator.comparing((Mission mission) -> mission.getRockets().size()).reversed()
                .thenComparing(Mission::getName))
                .toList();
        return formatSummaryResponse(missions);
    }

    private String formatSummaryResponse(List<Mission> missions) {
        StringBuilder summary = new StringBuilder();
        for(Mission mission : missions) {
            summary.append(createSingleMissionSummary(mission))
                    .append("\n");
        }
        return summary.toString();
    }

    private String createSingleMissionSummary(Mission mission) {
        StringBuilder summary = new StringBuilder(
                "• " + mission.getName() +
                " – " + mission.getStatus().getText() +
                " – " + "Dragons: " + mission.getRockets().size());

        for (Rocket rocket : mission.getRockets()) {
            summary.append("\no ")
                    .append(rocket.getName())
                    .append(" – ")
                    .append(rocket.getStatus().getText());
        }


        return summary.toString();
    }

}