package com.baw01.klasymc.rpg;

public class DamageEngine {
    private final ReactionEngine reactionEngine;

    public DamageEngine(ReactionEngine reactionEngine) {
        this.reactionEngine = reactionEngine;
    }

    public DamageResult calculate(CombatEntity attacker, CombatEntity target, DamageRequest request) {
        double statDamage = request.baseDamage() + (attacker.getStats().intelligence() * request.intelligenceScaling());
        ReactionResult reactionResult = reactionEngine.resolve(target, request);
        double finalDamage = (statDamage * reactionResult.damageMultiplier()) + reactionResult.bonusDamage();
        return new DamageResult(
                finalDamage,
                reactionResult.additionalChainTargets(),
                reactionResult.triggeredReaction()
        );
    }
}
