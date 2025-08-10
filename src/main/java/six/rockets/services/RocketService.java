package six.rockets.services;

import six.rockets.enums.RocketStatus;

import java.util.UUID;

public interface RocketService {
    UUID addRocket();
    void changeRocketStatus(UUID rocketId, RocketStatus status);
}
