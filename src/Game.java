import Characters.*;

import java.util.Scanner;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private Hero hero;
    private PotionTrader potionTrader;

    public final static String PRINT_THE_HERO = "0"; //"Герой";
    private final static String DARK_FOREST_WAY = "1"; //"В темный лес";
    private final static String TRADESMAN_WAY = "2"; //"К торговцу";
    private final static String FINISH_GAME_WAY = "3"; //"На выход";

    private final static String CONTINUE_COMBAT_WAY = "1"; //"Продолжить бой";
    public final static String RETURN_TO_CITY_WAY = "99"; //"Вернуться в город";


    public Game() {
    }

    public void startGame() {
        createHero();
        showCityNavigation();
        inputCityCommand(scanner.nextLine());
    }

    private void createHero() {
        // обернуть в цикл для проверки ввода
        System.out.println("Выберите имя для своего героя: ");
        String name = scanner.nextLine();
        hero = new Hero(name);
        System.out.println("Вы создали героя " + hero.getName());
    }

    private void showCityNavigation() {
        System.out.println("Куда вы хотите пойти?: ");
        System.out.println("'" + PRINT_THE_HERO + "' - показать героя" + '\n' +
                "'" + DARK_FOREST_WAY + "' - сразиться в монстром" + '\n' +
                "'" + TRADESMAN_WAY + "' - зайти к торговцу" + '\n' +
                "'" + FINISH_GAME_WAY + "' - закончить игру" + '\n');
    }

    private  void showForestNavigation() {
        System.out.println("Куда вы хотите пойти?: ");
        System.out.println("'" + PRINT_THE_HERO + "' - показать героя" + '\n' +
                "'" + CONTINUE_COMBAT_WAY + "' - продолжить бой" + '\n' +
                "'" + RETURN_TO_CITY_WAY + "' - венуться в город" + '\n');
    }

    private void inputCityCommand(String command) {
        switch (command) {
            case DARK_FOREST_WAY -> {
                goToForest();
                break;
            }
            case TRADESMAN_WAY -> {
                goToTrader();
                break;
            }
            case FINISH_GAME_WAY -> {
                finishGame();
                break;
            }
            case PRINT_THE_HERO -> {
                printHeroInfo();
                inputCityCommand(scanner.nextLine());
                break;
            }
            default -> {
                System.out.println("неизвестная команда");
                inputCityCommand(scanner.nextLine());
            }
        }
    }

    private void inputForestCommand(String command) {
        switch (command) {
            case CONTINUE_COMBAT_WAY -> {
                goToForest();
                break;
            }
            case RETURN_TO_CITY_WAY -> {
                showCityNavigation();
                inputCityCommand(scanner.nextLine());
                break;
            }
            case PRINT_THE_HERO -> {
                printHeroInfo();
                inputForestCommand(scanner.nextLine());
                break;
            }
            default -> {
                System.out.println("неизвестная команда");
                inputForestCommand(scanner.nextLine());
            }
        }
    }

    private void goToForest() {
        Combat combat = new Combat(hero, generateMonster(), new CombatCallback() {
            @Override
            public void heroWin() {
                showForestNavigation();
                inputForestCommand(scanner.nextLine());
            }

            @Override
            public void heroLoss() {
                finishGame();
            }
        });
        combat.startCombat();
    }

    private void goToTrader() {
        if (potionTrader==null) {
            potionTrader = new PotionTrader(hero);
        }
        potionTrader.lookAtProducts();
        showCityNavigation();
        inputCityCommand(scanner.nextLine());
    }

    private void finishGame() {
        System.out.println("Конец игры");
        System.exit(1);
    }

    private Monster generateMonster() {
        if (Math.random()>0.5) {
            return new Goblin();
        } else {
            return new Skeleton();
        }
    }

    private void printHeroInfo() {
        System.out.println(hero.toString());
    }
}
