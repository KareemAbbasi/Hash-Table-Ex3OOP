public abstract class SimpleHashSet implements SimpleSet {
    protected final int INITIAL_CAPACITY = 16;
    protected final float DEFAULT_UPPER_LOAD_FACTOR = 0.75f;
    protected final float DEFAULT_LOWER_LOAD_FACTOR = 0.25f;

    protected int capacity;
    protected int capacityMinusOne = capacity() - 1;
    protected float upperLoadFactor;
    protected float lowerLoadFactor;


    /**
     * initializes a new SimpleHashSet with the initial capacity, upper and lower laod factors.
     */
    protected SimpleHashSet() {
        this.capacity = INITIAL_CAPACITY;
        this.upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
        this.lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
    }

    /**
     * returns the upperLoadFactor.
     *
     * @return the upperLoadFactor.
     */
    protected float getUpperLoadFactor() {
        return upperLoadFactor;
    }

    /**
     * returns the lowerLoadFactor.
     *
     * @return the lowerLoadFactor.
     */
    protected float getLowerLoadFactor() {
        return lowerLoadFactor;
    }


    /**
     * increases the tableCapacity by 2.
     */
    protected void increaseTableCapacity() {
        capacity *= 2;
    }

    /**
     * Decreases the tableCapacity by 2.
     */
    protected void decreaseTableCapacity() {
        if (capacity > 1) {
            capacity /= 2;
        }
    }

    /**
     * capacity in class SimpleHashSet.
     *
     * @return - The current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    /*
    receives the hash code for a value and clamps it to an index that is in the range of the table size.
     */
    //protected abstract int clamp(int index);
    protected int clamp(int index) {
        int clampedIndex = index;
        if (this instanceof OpenHashSet) {
            return index & (capacity()-1);
        } else {
            for (int i = 0; i < capacity(); i++) {
                clampedIndex = index + ((i + i * i) / 2) & (capacity()-1);
                System.out.println(((ClosedHashSet)this).hashTable.length);
                if ((((ClosedHashSet) this).hashTable[clampedIndex] == null) ||
                        ("").equals(((ClosedHashSet) this).hashTable[clampedIndex])) {
                    break;
                }
            }
        }
        return clampedIndex;


    }
}
