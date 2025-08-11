package six.rockets.services;

import six.rockets.enums.RocketStatus;
import six.rockets.models.Rocket;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RocketService {
    void addRockets(Rocket... newRockets);
    void changeRocketStatus(UUID rocketId, RocketStatus status);
    List<Rocket> getAllRockets();
    Optional<Rocket> getRocketById(UUID rocketId);
}
