package org.fountainmc.api.event.entity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.event.AbstractCancellable;
import org.fountainmc.api.event.Cancellable;
import org.fountainmc.api.world.Location;

import static com.google.common.base.Preconditions.*;

@ParametersAreNonnullByDefault
public interface EntitySpawnEvent extends EntityEvent, Cancellable {

    @Nonnull
    Location getLocation();

    static EntitySpawnEvent create(Entity entity) {
        return create(entity, checkNotNull(entity, "Null entity").getLocation());
    }

    static EntitySpawnEvent create(Entity entity, Location location) {
        checkArgument(checkNotNull(entity, "Null entity").getWorld().equals(checkNotNull(location, "Null location").getWorld()), "Entity's world %s doesn't match location's world %s", entity.getWorld(), location.getWorld());
        class SimpleEntitySpawnEvent extends AbstractCancellable implements EntitySpawnEvent {

            @Override
            @Nonnull
            public Location getLocation() {
                return location;
            }

            @Override
            @Nonnull
            public Entity getEntity() {
                return entity;
            }
        }
        return new SimpleEntitySpawnEvent();
    }

}
