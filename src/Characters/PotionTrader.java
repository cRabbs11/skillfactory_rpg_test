package Characters;

import java.util.Scanner;

public class PotionTrader extends Character implements Tradable {

    public final static String PRINT_THE_HERO = "0"; //"Герой";
    private final static String HEALTH_POTION = "1"; //"купить зелье здоровья";
    public final static String RETURN_TO_CITY_WAY = "99"; //"Вернуться в город";

    private final static int HEALTH_POTION_COST = 50; //"купить зелье здоровья";
    private Hero hero;

    public PotionTrader(Hero hero) {
        super("PotionTrader", 100, 1000, 5, 0, 5);
        this.hero = hero;
    }

    public void lookAtProducts() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Торговец продает зелья лечения.");
        System.out.println("'" + PRINT_THE_HERO + "' - показать героя" + '\n' +
                "'" + HEALTH_POTION + "' - зелье лечения, стоимость: " + HEALTH_POTION_COST + '\n' +
                        "'" + RETURN_TO_CITY_WAY + "' - вернуться в город" + '\n');

        boolean isTradeIsDone = false;
        while (!isTradeIsDone) {
            String way = scanner.nextLine();
            switch (way) {
                case HEALTH_POTION -> {
                    sell(HEALTH_POTION);
                    break;
                }
                case RETURN_TO_CITY_WAY -> {
                    isTradeIsDone = true;
                    break;
                }
                case PRINT_THE_HERO -> {
                    System.out.println(hero.toString());
                    break;
                }
                default -> {
                    System.out.println("неизвестная команда");
                }
            }
        }
    }

    public void sell(String productNumber) {
        switch (productNumber) {
            case HEALTH_POTION -> {
                if (hero.removeGold(HEALTH_POTION_COST)) {
                    hero.addHealthPotion();
                    System.out.println(hero.getName() + " купил зелья здоровья");
                } else {
                    System.out.println("Для покупки зелья здоровья недостаточно золотых");
                }
                break;
            }
        }
    }
}
