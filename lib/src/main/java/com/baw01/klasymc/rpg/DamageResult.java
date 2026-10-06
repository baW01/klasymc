package com.baw01.klasymc.rpg;

public record DamageResult(
        double finalDamage,
        int additionalChainTargets,
        String triggeredReaction
) {
}
