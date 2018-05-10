import sun.reflect.generics.tree.Tree;

import java.util.Iterator;
import java.util.TreeSet;

public class OpenHashSet extends SimpleHashSet {

    TreeSetFacade[] hashTable;

    private int tableSize;


    public OpenHashSet() {
        hashTable = new TreeSetFacade[capacity()];
        tableSize = 0;
        this.upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
        this.lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
    }

    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        this();
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    public OpenHashSet(java.lang.String[] data){
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
        return index & (tableSize-1);
    }

    @Override
    public boolean add(String newValue) {
        if (contains(newValue)){
            return false;
        }

        if (getUpperLoadFactor() < (float)(tableSize/capacity())){
            biggerHashTable();
        }


    }

    private void biggerHashTable(){
        increaseTableCapacity();
        TreeSetFacade[] bigHashTable = new TreeSetFacade[capacity()];
        initHashTable(bigHashTable);
        for (int i = 0; i< hashTable.length; i++){
            if (hashTable[i] != null){
                for (Iterator iter = hashTable[i].getIter(); iter.hasNext();){
                    String currentString = iter.next().toString();
                    int currentIndex = currentString.hashCode();
                    int clampedIndex = clamp(currentIndex);

                    if(bigHashTable[clampedIndex] == null){
                        bigHashTable[clampedIndex] = new TreeSetFacade(new TreeSet<String>());
                    }
                    bigHashTable[clampedIndex].add(currentString);
                }
            }
        }
    }

    @Override
    public boolean contains(String searchVal) {
        for(int i =0; i<hashTable.length; i++){
            if (hashTable[i] != null && hashTable[i].contains(searchVal)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String toDelete) {
        return false;
    }

    @Override
    public int size() {
        return tableSize;
    }

}
