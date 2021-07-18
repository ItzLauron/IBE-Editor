package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.network.Buffer;
import com.github.franckyi.minecraft.api.common.network.Packet;
import com.github.franckyi.minecraft.api.common.world.Entity;

public class EntityUpdatePacket implements Packet {
    private final int entityId;
    private final Entity entity;

    public EntityUpdatePacket(int entityId, Entity entity) {
        this.entityId = entityId;
        this.entity = entity;
    }

    public EntityUpdatePacket(Buffer buffer) {
        this(buffer.readInt(), Minecraft.getCommon().createEntity(buffer.readTag()));
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writeInt(entityId);
        buffer.writeTag(entity.getData());
    }

    public int getEntityId() {
        return entityId;
    }

    public Entity getEntity() {
        return entity;
    }
}
