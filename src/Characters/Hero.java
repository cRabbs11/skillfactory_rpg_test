package Characters;

import Things.HealPotion;
import Things.Thing;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Character implements Fighterable, Healable {

    private int level=1;
    private List<Thing> potionList = new ArrayList<>();
    private int maxHealth; //переменная для определения максимального значения здоровья при лечении

    public Hero(String name) {
        this(name, 20, 20);
    }

    public Hero(String name, int agility, int strength) {
        this(name, 100, 10, agility, 0, strength);
    }

    public Hero(String name, int hp, int gold, int agility, int xp, int strength) {
        super(name, hp, gold, agility, xp, strength);
        maxHealth = hp;
    }

    public boolean addGold(int gold) {
        return super.addGold(gold);
    }

    public boolean removeGold(int gold) {
        return super.removeGold(gold);
    }

    public void addXP(int xp) {
        setXp(getXp()+xp);
        if (getXp()>(level*100)) {
            addLevel();
        }
    }

    public void addHealthPotion() {
        potionList.add(new HealPotion());
    }

    @Override
    public void getDamage(int damage) {
        if (getHp()<=damage) {
            setHp(0);
            System.out.println(getName() + " погибает");
        } else {
            setHp(getHp()-damage);
            System.out.println("у " + getName() + " остается " + getHp() + " жизней");
            if (isHealthIsLow()) {
                heal();
            }
        }
    }

    public int getLevel() {
        return level;
    }

    private boolean isHealthIsLow() {
        return (getHp()<maxHealth/3);
    }

    @Override
    public String toString() {
        return  "Имя: " + getName() + '\n' +
                "жизней: " + getHp() + '\n' +
                "золото: " + getGold() + '\n' +
                "ловкость: " + getAgility() + '\n' +
                "сила: " + getStrength() + '\n' +
                "опыт: " + getXp() + '\n' +
                "уровень: " + getLevel() + '\n' +
                "зелий здоровья: " + getHealPotionCount();
    }

    private int getHealPotionCount() {
        return (int) potionList.stream().filter((Thing t) -> t.getType()==Thing.TYPE_HEAL_POTION).count();
    }

    private void addMaxHealth() {
        maxHealth +=10;
    }

    private void addLevel() {
        level++;
        addAgility();
        addStrength();
        addMaxHealth();
        setHp(maxHealth);
        System.out.println(getName() + " получает новый уровень: " + getLevel());
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

    private HealPotion findHealPotion() {
        HealPotion thing = null;
        for(Thing potion: potionList) {
            if (potion.getType()==Thing.TYPE_HEAL_POTION) {
                thing = (HealPotion) potion;
            }
            break;
        }
        return thing;
    }

    @Override
    public void heal() {
        HealPotion potion = findHealPotion();
        if (potion!=null) {
            setHp((getHp()+ HealPotion.value)<maxHealth ? (getHp() + HealPotion.value): maxHealth);
            System.out.println(getName() + " выпивает зелье здоровья, жизней: " + getHp());
            potionList.remove(potion);
        }
    }
}
