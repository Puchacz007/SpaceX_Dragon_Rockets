package six.rockets.services;


import six.rockets.enums.RocketStatus;
import six.rockets.models.Rocket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RocketServiceImpl implements RocketService {

    List<Rocket> rockets = new ArrayList<>();

    @Override
    public UUID addRocket() {

        return null;
    }

    @Override
    public void changeRocketStatus(UUID rocketId, RocketStatus status) {

    }
}