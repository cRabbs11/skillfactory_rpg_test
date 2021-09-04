public class Hero extends Character {

    private int healthPotionCount;
    private int healthPotionValue=75;
    private int maxHealth; //переменная для определения максимального значения здоровья при лечении

    public Hero(String name) {
        this(name, 10, 10);
    }

    public Hero(String name, int agility, int strength) {
        this(name, 100, 10, agility, 0, strength);
    }

    public Hero(String name, int hp, int gold, int agility, int xp, int strength) {
        super(name, hp, gold, agility, xp, strength);
        maxHealth = hp;
    }

    public boolean useHealthPotion() {
        if (getHp()<maxHealth) {
            if (healthPotionCount>0) {
                setHp((getHp()+healthPotionValue)<maxHealth ? (getHp() + healthPotionValue): maxHealth);
            }
            return true;
        } else {
            return false;
        }
    }
}
