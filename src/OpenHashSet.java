import sun.reflect.generics.tree.Tree;

import java.util.Iterator;
import java.util.TreeSet;

public class OpenHashSet extends SimpleHashSet {

    private TreeSetFacade[] hashTable;

    private int tableSize;


    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        hashTable = new TreeSetFacade[capacity()];

        initHashTable(hashTable);

        tableSize = 0;
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this();

        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be ignored.
     * The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data) {
        this();

        for (int i = 0; i < data.length; i++) {
            add(data[i]);
        }
    }

    private void initHashTable(TreeSetFacade[] table) {
        for (int i = 0; i < table.length; i++) {
            table[i] = new TreeSetFacade(new TreeSet<>());
        }
    }


    /*
    receives the hash code for a value and clamps it to an index that is in the range of the table size.
     */
    @Override
    int clamp(int index) {
        return index & (capacity() - 1);
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

        if (getUpperLoadFactor() <=  ((float) tableSize / capacity())) {
            resizeTable(true);
        }

        int index = newValue.hashCode();
        int clampedIndex = clamp(index);

        if (hashTable[clampedIndex] == null) {
            hashTable[clampedIndex] = new TreeSetFacade(new TreeSet<>());
        }

        if (hashTable[clampedIndex].add(newValue)) {
            tableSize++;
            return true;
        }
        return false;
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

        TreeSetFacade[] newTable = new TreeSetFacade[capacity()];
        initHashTable(newTable);

        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                for (Iterator iter = hashTable[i].getIter(); iter.hasNext(); ) {
                    String currentString = iter.next().toString();
                    int currentIndex = currentString.hashCode();
                    int clampedIndex = clamp(currentIndex);

                    if (newTable[clampedIndex] == null) {
                        newTable[clampedIndex] = new TreeSetFacade(new TreeSet<>());
                    }

                    newTable[clampedIndex].add(currentString);
                }
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
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null && hashTable[i].contains(searchVal)) {
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
        if (getLowerLoadFactor() > ((float) tableSize / capacity())) {
            resizeTable(false);
        }
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null && hashTable[i].contains(toDelete)) {
                if (hashTable[i].delete(toDelete)) {
                    tableSize--;
                    return true;
                }
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
