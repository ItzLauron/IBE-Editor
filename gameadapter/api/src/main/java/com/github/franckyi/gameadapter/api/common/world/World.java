package com.github.franckyi.gameadapter.api.common.world;

import com.github.franckyi.gameadapter.api.common.BlockPos;

public interface World {
    void setBlockInventoryItem(BlockPos blockPos, int slotId, Item item);

    WorldBlock getBlock(BlockPos blockPos);

    void setBlockData(BlockPos blockPos, Block block);

    WorldEntity getEntity(int entityId);

    void setEntityData(int entityId, Entity entity);
}