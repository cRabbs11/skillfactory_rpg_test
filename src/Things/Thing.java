package Things;

abstract public class Thing {
    public final static int TYPE_HEAL_POTION = 1;

    private int type;
    protected int costValue;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
