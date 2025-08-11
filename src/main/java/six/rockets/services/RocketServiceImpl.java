package six.rockets.services;

import six.rockets.enums.RocketStatus;
import six.rockets.models.Rocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RocketServiceImpl implements RocketService {

    List<Rocket> rockets = new ArrayList<>();

    @Override
    public void addRockets(Rocket... newRockets) {
        rockets.addAll(List.of(newRockets));
    }

    @Override
    public void changeRocketStatus(UUID rocketId, RocketStatus status) {
        Optional<Rocket> optionalRocket = getRocketById(rocketId);
        optionalRocket.ifPresent(rocket -> {
            rocket.setStatus(status);
        });
    }

    @Override
    public List<Rocket> getAllRockets() {
        return rockets;
    }

    @Override
    public Optional<Rocket> getRocketById(UUID rocketId) {
        return rockets.stream()
                .filter(rocket -> rocket.getId().equals(rocketId))
                .findAny();
    }

}