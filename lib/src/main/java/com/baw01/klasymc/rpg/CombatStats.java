package com.baw01.klasymc.rpg;

public record CombatStats(
        int strength,
        int dexterity,
        int intelligence,
        int vitality,
        int spirit
) {
    public static CombatStats empty() {
        return new CombatStats(0, 0, 0, 0, 0);
    }
}
