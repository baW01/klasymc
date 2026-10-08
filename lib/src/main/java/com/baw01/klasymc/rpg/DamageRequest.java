package com.baw01.klasymc.rpg;

public record DamageRequest(
        double baseDamage,
        DamageType damageType,
        boolean heavyAttack,
        double intelligenceScaling
) {
    public DamageRequest {
        if (baseDamage < 0) {
            throw new IllegalArgumentException("Base damage cannot be negative");
        }
        if (intelligenceScaling < 0) {
            throw new IllegalArgumentException("Intelligence scaling cannot be negative");
        }
    }
}
