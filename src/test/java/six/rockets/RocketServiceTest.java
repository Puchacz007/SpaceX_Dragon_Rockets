package six.rockets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import six.rockets.enums.RocketStatus;
import six.rockets.models.Rocket;
import six.rockets.services.RocketService;
import six.rockets.services.RocketServiceImpl;

import java.util.List;

public class RocketServiceTest {

    private static RocketService rocketService;

    @BeforeAll
    public static void setup() {
        rocketService = new RocketServiceImpl();
    }

    @BeforeEach
    public void clearDb() {
        rocketService.getAllRockets().clear();
    }

    @Test
    public void addSingleRocketTest() {
        Rocket rocket = new Rocket("testRocket");

        rocketService.addRockets(rocket);
        List<Rocket> rockets = rocketService.getAllRockets();

        Assertions.assertEquals(1, rockets.size());
        Assertions.assertEquals(rocket.getId(), rockets.get(0).getId());
        Assertions.assertEquals(RocketStatus.ON_GROUND, rockets.get(0).getStatus());
        Assertions.assertNull(rockets.get(0).getMission());
        Assertions.assertEquals(rocket, rockets.get(0));
    }

    @Test
    public void addSingleRocketChangeStatusTest() {
        Rocket rocket = new Rocket("testRocket");

        rocketService.addRockets(rocket);
        rocketService.changeRocketStatus(rocket.getId(), RocketStatus.IN_SPACE);
        List<Rocket> rockets = rocketService.getAllRockets();


        Assertions.assertEquals(1, rockets.size());
        Assertions.assertEquals(RocketStatus.IN_SPACE, rockets.get(0).getStatus());
        Assertions.assertNull(rockets.get(0).getMission());
        Assertions.assertEquals(rocket.getId(), rockets.get(0).getId());
        Assertions.assertEquals(rocket, rockets.get(0));

    }


}