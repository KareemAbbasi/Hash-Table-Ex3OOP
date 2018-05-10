public class ClosedHashSet extends SimpleHashSet {
    private String[] hashTable;
    private int tableSize;


    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        hashTable = new String[capacity()];
        tableSize = 0;
        upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
        lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this();

        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).

     * @param data Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data) {
        this();

        for (int i = 0; i < data.length; i++) {
            add(data[i]);
        }
    }

    /*
    receives the hash code for a value and clamps it to an index that is in the range of the table size.
     */
    @Override
    int clamp(int index) {
        int clampedIndex = index;
        for (int i = 0; i < capacity(); i++) {
            clampedIndex = (index + (i + i * i) / 2) & (tableSize - 1);
            if (clampedIndex < capacity()-1) {
                if (hashTable[clampedIndex] == null || "".equals(hashTable[clampedIndex])) {

                    break;
                }
            }
        }
        return clampedIndex;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if (contains(newValue)) {
            return false;
        }

        if (getUpperLoadFactor() < ((float) (tableSize) /capacity())) {
            resizeTable(true);
        }

        int newValueIndex = findEmptyIndex();
        hashTable[newValueIndex] = newValue;
        tableSize++;

        return true;
    }

    /*
    Finds the first empty index in the hashTable and returns it.
     */
    private int findEmptyIndex() {
        int index = 0;
        for (int i = 0; i < capacity(); i++) {
            if (hashTable[i] == null || "".equals(hashTable[i])) {
                index = i;
            }
        }
        return index;
    }


    /*
   receives a boolean value that show whether we want to increase or decrease the hashTable size. The method
   creates a new bigger/smaller hashTable that is twice as big or half the size of the original hashSet. And then
   puts all the original values in the new hashTable
   @param - boolean value that show if to increase or decrease
     */
    private void resizeTable(boolean increase) {
        if (increase) {
            increaseTableCapacity();
        } else {
            decreaseTableCapacity();
        }

        String[] newTable = new String[capacity()];
        for (int i = 0; i < hashTable.length; i++) {
            if (!(hashTable[i] == null || hashTable[i].equals(""))) {
                int clampedIndex = clamp(hashTable[i].hashCode());

                newTable[clampedIndex] = hashTable[i];
            }
        }
        hashTable = newTable;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        for (int i = 0; i < capacity(); i++) {
            if (hashTable[i] != null && hashTable[i].equals(searchVal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        if (!contains(toDelete)) {
            return false;
        }

        for (int i = 0; i < capacity(); i++) {
            if (hashTable[i] != null && hashTable[i].equals(toDelete)) {
                hashTable[i] = "";

                if (getLowerLoadFactor() > ((float) tableSize / (float) capacity())) {
                    resizeTable(false);
                }

                tableSize--;
                return true;
            }
        }
        return false;

    }

    /**
     * The number of elements currently in the set
     * @return
     */
    @Override
    public int size() {
        return tableSize;
    }

    /**
     * capacity in class SimpleHashSet
     * @return - The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return capacity;
    }
}
