package six.rockets.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import six.rockets.enums.RocketStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Rocket extends EntityId {
    private String name;
    private RocketStatus status;
    @EqualsAndHashCode.Exclude
    private Mission mission;

    public Rocket(String name) {
        this.status = RocketStatus.ON_GROUND;
        this.name = name;
    }
}
