package six.rockets.services;

import six.rockets.enums.RocketStatus;
import six.rockets.models.Rocket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RocketServiceImpl implements RocketService {

    List<Rocket> rockets = new ArrayList<>();

    @Override
    public UUID addRockets(Rocket... rockets) {
        return null;
    }

    @Override
    public void changeRocketStatus(UUID rocketId, RocketStatus status) {

    }

    @Override
    public List<Rocket> getAllRockets() {
        return List.of();
    }

    @Override
    public Rocket getRocketById(UUID rocketId) {
        return null;
    }

}