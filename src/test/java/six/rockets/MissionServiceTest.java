package six.rockets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import six.rockets.enums.MissionStatus;
import six.rockets.enums.RocketStatus;
import six.rockets.models.Mission;
import six.rockets.models.Rocket;
import six.rockets.services.MissionService;
import six.rockets.services.MissionServiceImpl;

import java.util.List;

public class MissionServiceTest {

    private static MissionService missionService;

    @BeforeAll
    public static void setup() {
        missionService = new MissionServiceImpl();
    }

    @BeforeEach
    public void clearDb() {
        missionService.getAllMissions().clear();
    }

    @Test
    public void addSingleMissionTest() {
        Mission mission = new Mission("testMission");

        missionService.addMissions(mission);
        List<Mission> missions = missionService.getAllMissions();

        Assertions.assertEquals(1, missions.size());
        Assertions.assertEquals(mission.getId(), missions.get(0).getId());
        Assertions.assertEquals(MissionStatus.SCHEDULED, missions.get(0).getStatus());
        Assertions.assertEquals(0, missions.get(0).getRockets().size());
        Assertions.assertEquals(mission.getName(), missions.get(0).getName());
        Assertions.assertEquals(mission, missions.get(0));
    }

    @Test
    public void changeMissionStatusToInProgressTest() {
        Mission mission = new Mission("testMission");
        mission.getRockets().add(new Rocket("testRocket"));

        missionService.addMissions(mission);
        missionService.changeMissionStatus(mission.getId(), MissionStatus.IN_PROGRESS);
        List<Mission> missions = missionService.getAllMissions();

        Assertions.assertEquals(1, missions.size());
        Assertions.assertEquals(mission.getId(), missions.get(0).getId());
        Assertions.assertEquals(MissionStatus.IN_PROGRESS, missions.get(0).getStatus());
        Assertions.assertEquals(1, missions.get(0).getRockets().size());
        Assertions.assertEquals(mission.getName(), missions.get(0).getName());
        Assertions.assertEquals(mission, missions.get(0));
        Assertions.assertEquals(1, mission.getRockets().size());
    }

    @Test
    public void changeMissionStatusToPendingTest() {
        Mission mission = new Mission("testMission");
        mission.getRockets().add(new Rocket("testRocket1"));
        Rocket rocketInRepair = new Rocket("testRocket2");
        rocketInRepair.setStatus(RocketStatus.IN_REPAIR);
        mission.getRockets().add(rocketInRepair);

        missionService.addMissions(mission);
        missionService.changeMissionStatus(mission.getId(), MissionStatus.IN_PROGRESS);
        List<Mission> missions = missionService.getAllMissions();

        Assertions.assertEquals(1, missions.size());
        Assertions.assertEquals(mission.getId(), missions.get(0).getId());
        Assertions.assertEquals(MissionStatus.PENDING, missions.get(0).getStatus());
        Assertions.assertEquals(2, mission.getRockets().size());
        Assertions.assertEquals(2, missions.get(0).getRockets().size());
        Assertions.assertEquals(mission.getName(), missions.get(0).getName());
        Assertions.assertEquals(mission, missions.get(0));
    }

    @Test
    public void changeMissionStatusToEndedTest() {
        Mission mission = new Mission("testMission");
        mission.getRockets().add(new Rocket("testRocket"));

        missionService.addMissions(mission);
        missionService.changeMissionStatus(mission.getId(), MissionStatus.ENDED);
        List<Mission> missions = missionService.getAllMissions();

        Assertions.assertEquals(1, missions.size());
        Assertions.assertEquals(mission.getId(), missions.get(0).getId());
        Assertions.assertEquals(MissionStatus.ENDED, missions.get(0).getStatus());
        Assertions.assertEquals(0, missions.get(0).getRockets().size());
        Assertions.assertEquals(mission.getName(), missions.get(0).getName());
        Assertions.assertEquals(mission, missions.get(0));
        Assertions.assertEquals(0, mission.getRockets().size());
    }

}