package com.github.franckyi.gameadapter.api.common;

import java.util.List;

public interface Registries {
    List<Enchantment> getEnchantments();

    List<Attribute> getAttributes();

    List<String> getAttributeSuggestions();
}
