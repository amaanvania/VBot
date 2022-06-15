package api.script.access;

import api.BotContext;

public class Skills
{
    public enum Skill
    {
        ATTACK,
        STRENGTH,
        DEFENCE,
        RANGE,
        PRAYER,
        MAGIC,
        RUNECRAFTING,
        HITPOINTS,
        AGILITY,
        HERBLORE,
        THIEVING,
        CRAFTING,
        FLETCHING,
        SLAYER,
        MINING,
        SMITHING,
        FISHING,
        COOKING,
        FIREMAKING,
        WOODCUTTING,
        FARMING
    }

    public static int getCurrentLevel(Skill skill)
    {
        return BotContext.client.getCurrentStats()[skill.ordinal()];
    }

    public static int getPrayerLevel() {
        return BotContext.client.getCurrentStats()[5];
    }

    public static int getStrengthLevel() {
        return BotContext.client.getCurrentStats()[2];
    }

    public static int getAttackLevel() {
        return BotContext.client.getCurrentStats()[2];
    }

    public static int getRangedLevel() {
        return BotContext.client.getCurrentStats()[4];
    }

    public static int getMagicLevel() {
        return BotContext.client.getCurrentStats()[6];
    }

    public static int getHealthLevel() {
        return BotContext.client.getCurrentStats()[9];
    }

    public int getMaxLevel(Skill skill)
    {
        return BotContext.client.getCurrentStats()[skill.ordinal()];
    }

    public int getCurrentExperience(Skill skill)
    {
        return BotContext.client.getCurrentStats()[skill.ordinal()];
    }

    /**
     * Returns true if the skill is boosted (can be both negative and positive)
     * @param skill
     * @return
     */
    public boolean isSkillBoosted(Skill skill)
    {
        return getCurrentLevel(skill) != getMaxLevel(skill);
    }

    public boolean isSkillPositivelyBoosted(Skill skill)
    {
        return getCurrentLevel(skill) > getMaxLevel(skill);
    }

    public boolean isSkillNegativelyBoosted(Skill skill)
    {
        return getCurrentLevel(skill) < getMaxLevel(skill);
    }
}
