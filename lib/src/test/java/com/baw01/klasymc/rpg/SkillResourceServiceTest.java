package com.baw01.klasymc.rpg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SkillResourceServiceTest {
    private final SkillResourceService skillResourceService = new SkillResourceService();

    @Test
    void shouldConsumeManaWhenEnoughResource() {
        CombatEntity mage = new CombatEntity(
                RpgClass.MAGE,
                new ResourcePool(120, 0, 0),
                CombatStats.empty(),
                100
        );

        boolean consumed = skillResourceService.consumeOnCast(mage, ResourceType.MANA, 35);

        assertTrue(consumed);
        assertEquals(85, mage.getResourcePool().get(ResourceType.MANA));
    }

    @Test
    void shouldRejectCastWhenNotEnoughResource() {
        CombatEntity rogue = new CombatEntity(
                RpgClass.ROGUE,
                new ResourcePool(0, 0, 10),
                CombatStats.empty(),
                100
        );

        boolean canCast = skillResourceService.canCast(rogue, ResourceType.ENERGY, 20);
        boolean consumed = skillResourceService.consumeOnCast(rogue, ResourceType.ENERGY, 20);

        assertFalse(canCast);
        assertFalse(consumed);
        assertEquals(10, rogue.getResourcePool().get(ResourceType.ENERGY));
    }

    @Test
    void shouldCapAndConsumeComboPoints() {
        CombatEntity rogue = new CombatEntity(
                RpgClass.ROGUE,
                new ResourcePool(0, 0, 100),
                CombatStats.empty(),
                100
        );

        skillResourceService.awardComboPoints(rogue, 2);
        skillResourceService.awardComboPoints(rogue, 10);
        int spent = skillResourceService.consumeComboPoints(rogue);

        assertEquals(5, spent);
        assertEquals(0, rogue.getResourcePool().get(ResourceType.COMBO_POINTS));
    }
}
