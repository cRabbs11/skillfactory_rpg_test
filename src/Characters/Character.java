package Characters;

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

    public void getDamage(int damage) {
        if (hp<=damage) {
            hp =0;
            System.out.println(getName() + " погибает");
        } else {
            hp -=damage;
            System.out.println("у " + getName() + " остается " + getHp() + " жизней");
        }
    }

    public int getGold() {
        return gold;
    }

    protected boolean addGold(int gold) {
        if (gold>0) {
            setGold(getGold()+gold);
            return true;
        }
        return false;
    }

    protected boolean removeGold(int gold) {
        if (gold>0 && getGold()>gold) {
            setGold(getGold()-gold);
            return true;
        }
        return false;
    }

    private void setGold(int gold) {
        this.gold = gold;
    }

    public int getAgility() {
        return agility;
    }

    protected void addAgility() {
        agility += 5;
    }

    public int getXp() {
        return xp;
    }

    protected void setXp(int xp) {
        this.xp = xp;
    }

    public int getStrength() {
        return strength;
    }

    protected void addStrength() {
        strength += 5;
    }

    public boolean isAlive() {
        return (hp>0);
    }
}
