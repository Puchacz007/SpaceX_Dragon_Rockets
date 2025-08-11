package six.rockets.services;

import six.rockets.enums.RocketStatus;
import six.rockets.models.Rocket;
import java.util.List;
import java.util.UUID;

public interface RocketService {
    UUID addRockets(Rocket... rockets);
    void changeRocketStatus(UUID rocketId, RocketStatus status);
    List<Rocket> getAllRockets();
    Rocket getRocketById(UUID rocketId);
}
