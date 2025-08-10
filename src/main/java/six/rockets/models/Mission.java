package six.rockets.models;

import lombok.Getter;
import lombok.Setter;
import six.rockets.enums.MissionStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Mission extends EntityId {
    private String name;
    private MissionStatus status;
    private List<Rocket> rockets = new ArrayList<>();

    public Mission(String name) {
        this.name = name;
        status = MissionStatus.SCHEDULED;
    }
}
