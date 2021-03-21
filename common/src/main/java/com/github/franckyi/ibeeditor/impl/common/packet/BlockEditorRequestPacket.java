package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.network.Buffer;
import com.github.franckyi.minecraft.api.common.network.Packet;

public class BlockEditorRequestPacket implements Packet {
    private final BlockPos blockPos;
    private final boolean nbt;

    public BlockEditorRequestPacket(BlockPos blockPos, boolean nbt) {
        this.blockPos = blockPos;
        this.nbt = nbt;
    }

    public BlockEditorRequestPacket(Buffer buffer) {
        this(buffer.readPos(), buffer.readBoolean());
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writePos(blockPos);
        buffer.writeBoolean(nbt);
    }

    public BlockPos getPos() {
        return blockPos;
    }

    public boolean isNBT() {
        return nbt;
    }
}