public abstract class Character {
    private final String name;
    private int hp;
    private int gold;
    private int agility;
    private int xp;
    private int strength;

    public Character(String name, int hp, int gold, int agility, int xp, int strength) {
        this.name = name;
        this.hp = hp;
        this.gold = gold;
        this.agility = agility;
        this.xp = xp;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    protected void setHp(int hp) {
        this.hp = hp;
    }

    public int getGold() {
        return gold;
    }

    public int getAgility() {
        return agility;
    }

    public int getXp() {
        return xp;
    }

    public int getStrength() {
        return strength;
    }

    public int toAttack() {
        if (isAttackMiss()) {
            if (isAttackCritical()) {
                return strength*2;
            } else {
                return strength;
            }
        } else {
            return 0;
        }
    }

    private boolean isAttackMiss() {
        int attackChance = agility*10;
        int missChance = (int) Math.round(Math.random()*100);
        missChance -=10;
        return attackChance>missChance;
    }

    private boolean isAttackCritical() {
        int criticalChance = (int) Math.round(Math.random()*100);
        criticalChance -=10;
        return criticalChance>50;
    }
}
