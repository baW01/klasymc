package com.baw01.klasymc.rpg;

public class SkillResourceService {
    public boolean canCast(CombatEntity caster, ResourceType type, int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }
        return caster.getResourcePool().get(type) >= cost;
    }

    public boolean consumeOnCast(CombatEntity caster, ResourceType type, int cost) {
        return caster.getResourcePool().spend(type, cost);
    }

    public void awardComboPoints(CombatEntity caster, int amount) {
        caster.getResourcePool().gain(ResourceType.COMBO_POINTS, amount);
    }

    public int consumeComboPoints(CombatEntity caster) {
        return caster.getResourcePool().consumeComboPoints();
    }
}
