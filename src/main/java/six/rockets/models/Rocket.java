package six.rockets.models;

import lombok.Getter;
import lombok.Setter;
import six.rockets.enums.RocketStatus;

@Getter
@Setter
public class Rocket extends EntityId {
    private RocketStatus status;
    private Mission mission;

    public Rocket() {
        this.status = RocketStatus.ON_GROUND;
    }
}
