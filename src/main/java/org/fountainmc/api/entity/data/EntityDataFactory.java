package org.fountainmc.api.entity.data;

import java.util.List;
import java.util.UUID;
import javax.annotation.concurrent.ThreadSafe;

import org.fountainmc.api.GameMode;
import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.Server;
import org.fountainmc.api.entity.EntityType;

@ThreadSafe
@NonnullByDefault
public interface EntityDataFactory {

    Server getServer();

    EntityData createEntityData(
            EntityType<?> type,
            float pitch,
            float yaw,
            List<? extends EntityData> passengers,
            int ticksOnFire,
            boolean immuneToFire
    );

    LivingEntityData createLivingEntityData(
            EntityData entityData,
            double health,
            double maxHealth
    );

    PlayerData createPlayerData(
            LivingEntityData entityData,
            String name,
            UUID uniqueId,
            GameMode gameMode,
            boolean canFly,
            float percentageToNextExperienceLevel,
            int experienceLeve
    );


}
