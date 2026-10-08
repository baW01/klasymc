package com.baw01.klasymc.rpg;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class CombatEntity {
    private final RpgClass rpgClass;
    private final ResourcePool resourcePool;
    private final CombatStats stats;
    private final EnumSet<CombatTag> tags = EnumSet.noneOf(CombatTag.class);
    private double health;

    public CombatEntity(RpgClass rpgClass, ResourcePool resourcePool, CombatStats stats, double health) {
        this.rpgClass = rpgClass;
        this.resourcePool = resourcePool;
        this.stats = stats;
        this.health = Math.max(0, health);
    }

    public RpgClass getRpgClass() {
        return rpgClass;
    }

    public ResourcePool getResourcePool() {
        return resourcePool;
    }

    public CombatStats getStats() {
        return stats;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = Math.max(0, health);
    }

    public void applyDamage(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        health = Math.max(0, health - amount);
    }

    public boolean hasTag(CombatTag tag) {
        return tags.contains(tag);
    }

    public void addTag(CombatTag tag) {
        tags.add(tag);
    }

    public void removeTag(CombatTag tag) {
        tags.remove(tag);
    }

    public Set<CombatTag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}
