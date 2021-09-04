public class Skeleton extends Character{
    public Skeleton(String name) {
        this(name, 10, 10);
    }

    public Skeleton(String name, int agility, int strength) {
        super(name, 50, 50, agility, 0, strength);
    }
}
