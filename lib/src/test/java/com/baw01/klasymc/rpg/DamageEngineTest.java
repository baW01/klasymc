package com.baw01.klasymc.rpg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DamageEngineTest {
    private final DamageEngine damageEngine = new DamageEngine(new ReactionEngine());

    @Test
    void shouldApplyWetLightningReaction() {
        CombatEntity mage = new CombatEntity(
                RpgClass.MAGE,
                new ResourcePool(100, 0, 0),
                new CombatStats(0, 0, 10, 0, 0),
                100
        );
        CombatEntity target = new CombatEntity(
                RpgClass.WARRIOR,
                new ResourcePool(0, 0, 0),
                CombatStats.empty(),
                100
        );
        target.addTag(CombatTag.WET);

        DamageResult result = damageEngine.calculate(mage, target, new DamageRequest(80, DamageType.LIGHTNING, false, 0));

        assertEquals(120.0, result.finalDamage(), 0.0001);
        assertEquals(2, result.additionalChainTargets());
        assertEquals("WET_LIGHTNING", result.triggeredReaction());
    }

    @Test
    void shouldApplyShatterOnFrozenHeavyAttack() {
        CombatEntity warrior = new CombatEntity(
                RpgClass.WARRIOR,
                new ResourcePool(0, 50, 0),
                CombatStats.empty(),
                100
        );
        CombatEntity target = new CombatEntity(
                RpgClass.MAGE,
                new ResourcePool(100, 0, 0),
                CombatStats.empty(),
                100
        );
        target.addTag(CombatTag.FROZEN);

        DamageResult result = damageEngine.calculate(warrior, target, new DamageRequest(100, DamageType.PHYSICAL, true, 0));

        assertEquals(350.0, result.finalDamage(), 0.0001);
        assertEquals("SHATTER", result.triggeredReaction());
        assertTrue(target.hasTag(CombatTag.VULNERABLE));
    }

    @Test
    void shouldApplyToxicExplosionBonusDamage() {
        CombatEntity mage = new CombatEntity(
                RpgClass.MAGE,
                new ResourcePool(100, 0, 0),
                CombatStats.empty(),
                100
        );
        CombatEntity target = new CombatEntity(
                RpgClass.ROGUE,
                new ResourcePool(0, 0, 100),
                CombatStats.empty(),
                100
        );
        target.addTag(CombatTag.POISONED);

        DamageResult result = damageEngine.calculate(mage, target, new DamageRequest(80, DamageType.FIRE, false, 0));

        assertEquals(200.0, result.finalDamage(), 0.0001);
        assertEquals("TOXIC_EXPLOSION", result.triggeredReaction());
    }
}
