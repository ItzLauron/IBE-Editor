package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.minecraft.api.common.network.Buffer;
import com.github.franckyi.minecraft.api.common.network.Packet;

public class EntityEditorRequestPacket implements Packet {
    private final int entityId;
    private final boolean nbt;

    public EntityEditorRequestPacket(int entityId, boolean nbt) {
        this.entityId = entityId;
        this.nbt = nbt;
    }

    public EntityEditorRequestPacket(Buffer buffer) {
        this(buffer.readInt(), buffer.readBoolean());
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writeInt(entityId);
        buffer.writeBoolean(nbt);
    }

    public int getEntityId() {
        return entityId;
    }

    public boolean isNBT() {
        return nbt;
    }
}