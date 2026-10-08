package com.baw01.klasymc.rpg;

public class ReactionEngine {
    private static final double SHATTER_MULTIPLIER = 3.5; // +250% damage
    private static final double WET_LIGHTNING_MULTIPLIER = 1.5;
    private static final int WET_LIGHTNING_CHAIN_BONUS = 2;
    private static final double TOXIC_EXPLOSION_BONUS_DAMAGE = 120.0;

    public ReactionResult resolve(CombatEntity target, DamageRequest request) {
        ReactionResult result = ReactionResult.none();

        if (target.hasTag(CombatTag.WET) && request.damageType() == DamageType.LIGHTNING) {
            result = result.merge(new ReactionResult(
                    WET_LIGHTNING_MULTIPLIER,
                    0,
                    WET_LIGHTNING_CHAIN_BONUS,
                    "WET_LIGHTNING"
            ));
        }

        if (target.hasTag(CombatTag.FROZEN) && request.heavyAttack()) {
            result = result.merge(new ReactionResult(
                    SHATTER_MULTIPLIER,
                    0,
                    0,
                    "SHATTER"
            ));
            target.removeTag(CombatTag.FROZEN);
            target.addTag(CombatTag.VULNERABLE);
        }

        if (target.hasTag(CombatTag.POISONED) && request.damageType() == DamageType.FIRE) {
            result = result.merge(new ReactionResult(
                    1.0,
                    TOXIC_EXPLOSION_BONUS_DAMAGE,
                    0,
                    "TOXIC_EXPLOSION"
            ));
        }

        return result;
    }
}
