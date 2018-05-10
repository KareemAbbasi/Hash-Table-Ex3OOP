import sun.reflect.generics.tree.Tree;

import java.util.Iterator;
import java.util.TreeSet;

public class OpenHashSet extends SimpleHashSet {

    private TreeSetFacade[] hashTable;

    private int tableSize;


    public OpenHashSet() {
        hashTable = new TreeSetFacade[capacity()];
        initHashTable(hashTable);
        tableSize = 0;
    }

    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        this();
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    public OpenHashSet(java.lang.String[] data){
        this();
        for (int i=0; i< data.length; i++){
            add(data[i]);
        }
    }

    private void initHashTable(TreeSetFacade[] table) {
        for (int i=0; i< table.length; i++) {
            table[i] = new TreeSetFacade(new TreeSet<String>());
        }
    }


    @Override
    int clamp(int index) {
        return index & (capacity()-1);
    }

    @Override
    public boolean add(String newValue) {
        if (contains(newValue)){
            return false;
        }

        if (getUpperLoadFactor() < (float)(tableSize/capacity())){
            resizeTable(true);
        }

        int index = newValue.hashCode();
        int clampedIndex = clamp(index);

        if(hashTable[clampedIndex] == null){
            hashTable[clampedIndex] = new TreeSetFacade(new TreeSet<String>());
        }

        if (hashTable[clampedIndex].add(newValue)){
            tableSize++;
            return true;
        }
        return false;
    }

    private void resizeTable(boolean increase) {
        if (increase){
            increaseTableCapacity();
        } else {
            decreaseTableCapacity();
        }

        TreeSetFacade[] newTable = new TreeSetFacade[capacity()];
        initHashTable(newTable);

        for (int i=0; i<hashTable.length; i++){
            if (hashTable[i] != null){
                for (Iterator iter = hashTable[i].getIter(); iter.hasNext();){
                    String currentString = iter.next().toString();
                    int currentIndex = currentString.hashCode();
                    int clampedIndex = clamp(currentIndex);

                    if (newTable[clampedIndex] == null){
                        newTable[clampedIndex] = new TreeSetFacade(new TreeSet<String>());
                    }

                    newTable[clampedIndex].add(currentString);
                }
            }
        }

        hashTable = newTable;
    }

    @Override
    public boolean contains(String searchVal) {
        for (int i=0; i< hashTable.length; i++) {
            if (hashTable[i] != null && hashTable[i].contains(searchVal)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String toDelete) {
        if (getLowerLoadFactor() > (float)(tableSize/capacity())){
            resizeTable(false);
        }
        for (int i = 0; i< hashTable.length; i++) {
            if (hashTable[i] != null && hashTable[i].contains(toDelete)){
                if (hashTable[i].delete(toDelete)){
                    tableSize --;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return tableSize;
    }

}
