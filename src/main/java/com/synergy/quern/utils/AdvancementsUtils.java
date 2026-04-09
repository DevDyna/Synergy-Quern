package com.synergy.quern.utils;

import static com.synergy.quern.Main.ID;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;

public class AdvancementsUtils {
        public static Advancement.Builder getExistingParent(AdvancementHolder parent, ItemLike icon, String t,
            AdvancementType type, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(icon,
                Component.translatable(ID + ".advancement.branch." + t),
                Component.translatable(ID + ".advancement.branch." + t + ".desc"),
                null, type, showToast, announceToChat, hidden);
    }

    public static Advancement.Builder getExistingParent(String parent, ItemLike icon, String t,
            AdvancementType type, boolean showToast, boolean announceToChat, boolean hidden) {
        return getExistingParent(AdvancementSubProvider.createPlaceholder(parent), icon, t, type, showToast,
                announceToChat, hidden);
    }
}
