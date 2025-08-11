package six.rockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import six.rockets.Exception.RocketException;
import six.rockets.enums.MissionStatus;
import six.rockets.enums.RocketStatus;
import six.rockets.models.Mission;
import six.rockets.models.Rocket;
import six.rockets.services.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MissionPlanningServiceTest {

    @Mock
    private RocketService rocketService;
    @Mock
    private MissionService missionService;
    @InjectMocks
    private MissionPlanningServiceImpl missionPlanningService;

    @Test
    public void assignSingleRocketToMissionTest() {
        Mission mission = new Mission("testMission");
        Rocket rocket = new Rocket("testRocket");

        missionPlanningService.assignRocketsToMission(mission, rocket);

        verify(missionService).changeMissionStatus(mission.getId(), MissionStatus.IN_PROGRESS);

        verify(rocketService).changeRocketStatus(rocket.getId(), RocketStatus.IN_SPACE);

        assertEquals(1, mission.getRockets().size());
        assertEquals(rocket, mission.getRockets().get(0));
    }

    @Test
    public void assignMultipleRocketToMissionTest() {
        Mission mission = new Mission("testMission");
        Rocket rocket1 = new Rocket("testRocket1");
        Rocket rocket2 = new Rocket("testRocket2");

        missionPlanningService.assignRocketsToMission(mission, rocket1, rocket2);

        assertEquals(2, mission.getRockets().size());
        assertEquals(rocket1, mission.getRockets().get(0));
        assertEquals(rocket2, mission.getRockets().get(1));

        verify(missionService, times(2)).changeMissionStatus(mission.getId(), MissionStatus.IN_PROGRESS);

        verify(rocketService).changeRocketStatus(rocket1.getId(), RocketStatus.IN_SPACE);
        verify(rocketService).changeRocketStatus(rocket2.getId(), RocketStatus.IN_SPACE);

    }

    @Test
    public void assignMultipleRocketWithOneWaitingForRepairToMissionTest() {
        Mission mission = new Mission("testMission");
        Rocket rocket1 = new Rocket("testRocket1");
        Rocket rocket2 = new Rocket("testRocket2");

        missionPlanningService.assignRocketsToMission(mission, rocket1, rocket2);

        assertEquals(2, mission.getRockets().size());
        assertEquals(rocket1, mission.getRockets().get(0));
        assertEquals(rocket2, mission.getRockets().get(1));

        verify(missionService, times(2)).changeMissionStatus(mission.getId(), MissionStatus.IN_PROGRESS);

        verify(rocketService).changeRocketStatus(rocket1.getId(), RocketStatus.IN_SPACE);
        verify(rocketService).changeRocketStatus(rocket2.getId(), RocketStatus.IN_SPACE);

    }

    @Test
    public void assignOneRocketToMultipleMissionFailureTest() {
        Mission mission1 = new Mission("testMission");
        Mission mission2 = new Mission("testMission2");
        Rocket rocket = new Rocket("testRocket");

        missionPlanningService.assignRocketsToMission(mission1, rocket);

        RocketException exception = assertThrows(RocketException.class, () -> {
            missionPlanningService.assignRocketsToMission(mission2, rocket);
        });

        assertEquals("Rocket with id " + rocket.getId() + "is already assigned to another mission with id "
                + mission1.getId(), exception.getMessage());

    }

    @Test
    public void getSummaryTest() {
        Mission mission1 = new Mission("testMission1");
        mission1.setStatus(MissionStatus.IN_PROGRESS);
        Rocket rocket1Mission1 = new Rocket("testRocket1Mission1");
        rocket1Mission1.setStatus(RocketStatus.IN_SPACE);
        Rocket rocket2Mission1 = new Rocket("testRocket2Mission1");
        rocket2Mission1.setStatus(RocketStatus.IN_SPACE);

        Mission mission2 = new Mission("testMission2");
        mission2.setStatus(MissionStatus.IN_PROGRESS);
        Rocket rocket1Mission2 = new Rocket("testRocket2Mission1");
        rocket1Mission2.setStatus(RocketStatus.IN_SPACE);
        Rocket rocket2Mission2 = new Rocket("testRocket2Mission2");
        rocket2Mission2.setStatus(RocketStatus.IN_SPACE);
        Rocket rocket3Mission2 = new Rocket("testRocket2Mission3");
        rocket3Mission2.setStatus(RocketStatus.IN_SPACE);

        Mission mission3 = new Mission("testMission3");
        Rocket rocket1Mission3 = new Rocket("testRocket1Mission3");

        Mission mission4 = new Mission("testMission4");
        mission4.setStatus(MissionStatus.PENDING);
        Rocket rocket1Mission4 = new Rocket("testRocket1Mission4");
        rocket1Mission4.setStatus(RocketStatus.IN_SPACE);
        Rocket rocket2Mission4 = new Rocket("testRocket2Mission4");
        rocket2Mission4.setStatus(RocketStatus.IN_REPAIR);

        Mission mission5 = new Mission("testMission5");

        Mission mission6 = new Mission("testMission6");
        mission6.setStatus(MissionStatus.ENDED);

        String testMissionSummary = """
                • testMission2 – In Progress – Dragons: 3
                o testRocket2Mission1 – In space
                o testRocket2Mission2 – In space
                o testRocket2Mission3 – In space
                • testMission1 – In Progress – Dragons: 2
                o testRocket1Mission1 – In space
                o testRocket2Mission1 – In space
                • testMission4 – Pending – Dragons: 2
                o testRocket1Mission4 – In space
                o testRocket2Mission4 – In repair
                • testMission3 – In Progress – Dragons: 1
                o testRocket1Mission3 – In space
                • testMission5 – Scheduled – Dragons: 0
                • testMission6 – Ended – Dragons: 0
                """;


        missionPlanningService.assignRocketsToMission(mission4, rocket1Mission4, rocket2Mission4);
        missionPlanningService.assignRocketsToMission(mission3, rocket1Mission3);
        missionPlanningService.assignRocketsToMission(mission1, rocket1Mission1, rocket2Mission1);
        missionPlanningService.assignRocketsToMission(mission2, rocket1Mission2, rocket2Mission2, rocket3Mission2);

        rocket1Mission3.setStatus(RocketStatus.IN_SPACE);
        mission3.setStatus(MissionStatus.IN_PROGRESS);

        when(missionService.getAllMissions()).thenReturn(List.of(mission1, mission2, mission3, mission4, mission5, mission6));
        String missionSummary = missionPlanningService.getMissionsSummary();

        assertEquals(testMissionSummary, missionSummary);
    }

}