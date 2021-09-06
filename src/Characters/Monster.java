package Characters;

import Characters.Character;

abstract public class Monster extends Character implements Fighterable {

    public Monster(String name, int hp, int gold, int agility, int xp, int strength) {
        super(name, hp, gold, agility, xp, strength);
    }

    @Override
    public int attack() {
        if (isAttackMiss()) {
            if (isAttackCritical()) {
                System.out.println(getName() + " наносит критический удар: " + getStrength()*2 + " урона");
                return getStrength()*2;
            } else {
                System.out.println(getName() + " удар: " + getStrength()*2 + " урона");
                return getStrength();
            }
        } else {
            System.out.println(getName() + " промахивается");
            return 0;
        }
    }

    private boolean isAttackMiss() {
        int attackChance = getAgility()*3;
        int missChance = (int) Math.round(Math.random()*100);
        return attackChance>missChance;
    }

    private boolean isAttackCritical() {
        int criticalChance = (int) Math.round(Math.random()*100);
        criticalChance -=10;
        return criticalChance>50;
    }
}
