public abstract class SimpleHashSet implements SimpleSet {
    protected final int INITIAL_CAPACITY = 16;
    protected final float DEFAULT_UPPER_LOAD_FACTOR = 0.75f;
    protected final float DEFAULT_LOWER_LOAD_FACTOR = 0.25f;

    protected int capacityMinusOne = INITIAL_CAPACITY-1;
    protected int capacity;
    protected float upperLoadFactor;
    protected float lowerLoadFactor;

    protected float getUpperLoadFactor() {
        return upperLoadFactor;
    }

    protected float getLowerLoadFactor() {
        return lowerLoadFactor;
    }



}
