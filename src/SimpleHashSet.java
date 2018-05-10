public abstract class SimpleHashSet implements SimpleSet {
    protected final int INITIAL_CAPACITY = 16;
    protected final float DEFAULT_UPPER_LOAD_FACTOR = 0.75f;
    protected final float DEFAULT_LOWER_LOAD_FACTOR = 0.25f;

    protected int capacity;
    protected int capacityMinusOne = capacity - 1;
    protected float upperLoadFactor;
    protected float lowerLoadFactor;

    public SimpleHashSet() {
        this.capacity = INITIAL_CAPACITY;
        this.upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
        this.lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
    }

    protected float getUpperLoadFactor() {
        return upperLoadFactor;
    }

    protected float getLowerLoadFactor() {
        return lowerLoadFactor;
    }

    protected void increaseTableCapacity() {
        capacity *= 2;
    }

    protected void decreaseTableCapacity() {
        if (capacity > 1) {
            capacity /= 2;
        }
    }

    protected int capacity() {
        return capacity;
    }

    abstract int clamp(int index);


}
