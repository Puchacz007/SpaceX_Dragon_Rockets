package six.rockets.models;

import lombok.Getter;
import java.util.UUID;

@Getter
public abstract class EntityId {
    private final UUID id = UUID.randomUUID();
}
