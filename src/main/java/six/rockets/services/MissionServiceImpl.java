package six.rockets.services;


import six.rockets.enums.MissionStatus;
import six.rockets.enums.RocketStatus;
import six.rockets.models.Mission;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MissionServiceImpl implements MissionService {

    List<Mission> missions = new ArrayList<>();

    @Override
    public void addMissions(Mission... newMissions) {
        missions.addAll(List.of(newMissions));
    }

    @Override
    public void changeMissionStatus(UUID missionId, MissionStatus status) {
        Optional<Mission> optionalMission = getMissionById(missionId);
        optionalMission.ifPresent(mission -> {
            if (MissionStatus.ENDED == status) {
                mission.getRockets().forEach(rocket -> {
                    if (rocket.getStatus() == RocketStatus.IN_SPACE) {
                        rocket.setStatus(RocketStatus.ON_GROUND);
                    }
                    rocket.setMission(null);
                });
                mission.getRockets().clear();
            } else if (MissionStatus.IN_PROGRESS == status && isMissionPending(mission)) {
                mission.setStatus(MissionStatus.PENDING);
                return;
            }
            mission.setStatus(status);

        });
    }

    @Override
    public List<Mission> getAllMissions() {
        return missions;
    }

    @Override
    public Optional<Mission> getMissionById(UUID missionId) {
        return missions.stream()
                .filter(mission -> mission.getId().equals(missionId))
                .findAny();
    }

    private boolean isMissionPending(Mission mission) {
        return mission.getRockets().stream()
                .anyMatch(rocket -> rocket.getStatus() == RocketStatus.IN_REPAIR);
    }
}