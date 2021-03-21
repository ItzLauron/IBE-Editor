package com.github.franckyi.minecraft.api.common.tag;

public interface IntTag extends Tag {
    @Override
    default byte getType() {
        return Tag.INT_ID;
    }

    int getValue();

    @Override
    String toString();
}