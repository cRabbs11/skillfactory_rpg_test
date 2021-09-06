import Characters.Character;
import Characters.Hero;
import Characters.Monster;

public class Combat extends Thread {

    private Hero hero;
    private Monster enemy;
    private CombatCallback callback;

    public Combat(Hero hero, Monster enemy, CombatCallback callback) {
        this.hero = hero;
        this.enemy = enemy;
        this.callback = callback;
    }

    public void startCombat() {
        new Thread(runnable).start();
    }

    Runnable runnable = () -> {
        try {
            combat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    private void combat() throws InterruptedException {
        int xpReward = enemy.getHp();
        System.out.println("начинается бой между " + hero.getName() + " и " + enemy.getName() + ":");
        while (hero.isAlive() && enemy.isAlive()) {
            enemy.getDamage(hero.attack());
            sleep(500);
            if (enemy.isAlive()) {
                hero.getDamage(enemy.attack());
                sleep(500);
            }
        }

        if (hero.isAlive()) {
            System.out.println(hero.getName() + " побеждает " + enemy.getName() +
                    " и получает: " + enemy.getGold() + " золота, " + xpReward + " опыта.");
            hero.addGold(enemy.getGold());
            hero.addXP(xpReward);
            callback.heroWin();
        } else {
            System.out.println(hero.getName() + " погибает от рук " + enemy.getName());
            callback.heroLoss();
        }
    }
}
