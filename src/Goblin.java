public class Goblin extends Character{
    public Goblin(String name) {
        this(name, 5, 5);
    }

    public Goblin(String name, int agility, int strength) {
        super(name, 30, 30, agility, 0, strength);
    }
}
