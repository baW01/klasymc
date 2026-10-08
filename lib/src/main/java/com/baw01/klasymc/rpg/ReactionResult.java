package com.baw01.klasymc.rpg;

public record ReactionResult(
        double damageMultiplier,
        double bonusDamage,
        int additionalChainTargets,
        String triggeredReaction
) {
    public static ReactionResult none() {
        return new ReactionResult(1.0, 0, 0, null);
    }

    public ReactionResult merge(ReactionResult other) {
        if (other == null) {
            return this;
        }
        String reactionName = triggeredReaction != null ? triggeredReaction : other.triggeredReaction;
        return new ReactionResult(
                damageMultiplier * other.damageMultiplier,
                bonusDamage + other.bonusDamage,
                additionalChainTargets + other.additionalChainTargets,
                reactionName
        );
    }
}
