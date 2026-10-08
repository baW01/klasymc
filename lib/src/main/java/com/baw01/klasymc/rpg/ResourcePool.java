package com.baw01.klasymc.rpg;

import java.util.EnumMap;
import java.util.Map;

public class ResourcePool {
    private static final int DEFAULT_COMBO_CAP = 5;
    private final Map<ResourceType, Integer> values = new EnumMap<>(ResourceType.class);
    private int comboCap = DEFAULT_COMBO_CAP;

    public ResourcePool(int mana, int rage, int energy) {
        values.put(ResourceType.MANA, Math.max(0, mana));
        values.put(ResourceType.RAGE, Math.max(0, rage));
        values.put(ResourceType.ENERGY, Math.max(0, energy));
        values.put(ResourceType.COMBO_POINTS, 0);
    }

    public int get(ResourceType type) {
        return values.getOrDefault(type, 0);
    }

    public boolean spend(ResourceType type, int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

        int current = get(type);
        if (current < cost) {
            return false;
        }

        values.put(type, current - cost);
        return true;
    }

    public void gain(ResourceType type, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        int next = get(type) + amount;
        if (type == ResourceType.COMBO_POINTS) {
            next = Math.min(comboCap, next);
        }
        values.put(type, next);
    }

    public int consumeComboPoints() {
        int points = get(ResourceType.COMBO_POINTS);
        values.put(ResourceType.COMBO_POINTS, 0);
        return points;
    }

    public int getComboCap() {
        return comboCap;
    }

    public void setComboCap(int comboCap) {
        if (comboCap < 0) {
            throw new IllegalArgumentException("Combo cap cannot be negative");
        }
        this.comboCap = comboCap;
        values.put(ResourceType.COMBO_POINTS, Math.min(get(ResourceType.COMBO_POINTS), comboCap));
    }
}
