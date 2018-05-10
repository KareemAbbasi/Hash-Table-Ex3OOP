public class ClosedHashSet extends SimpleHashSet {
    private String[] hashTable;
    private int tableSize;


    public ClosedHashSet() {
        hashTable = new String[capacity()];
        tableSize = 0;
        upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
        lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
    }

    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this();

        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    public ClosedHashSet(java.lang.String[] data) {
        this();

        for (int i = 0; i < data.length; i++) {
            add(data[i]);
        }
    }

    @Override
    int clamp(int index) {
        int clampedIndex = index;
        for (int i = 0; i < capacity(); i++) {
            clampedIndex = (index + (i + i * i) / 2) & (tableSize - 1);
            if (hashTable[clampedIndex] == null || "".equals(hashTable[clampedIndex])) {
                break;
            }
        }
        return clampedIndex;
    }

    @Override
    public boolean add(String newValue) {
        if (contains(newValue)) {
            return false;
        }

        if (getUpperLoadFactor() < ((float) tableSize / capacity())) {
            resizeTable(true);
        }

        int newValueIndex = findEmptyIndex();
        hashTable[newValueIndex] = newValue;
        tableSize++;

        return true;
    }

    private int findEmptyIndex() {
        int index = 0;
        for (int i = 0; i < capacity(); i++) {
            if (hashTable[i] == null || "".equals(hashTable[i])) {
                index = i;
            }
        }
        return index;
    }

//    private void biggerHashTable(){
//        increaseTableCapacity();
//        String[] newTable = new String[capacity()];
//        for(int i =0; i<capacityMinusOne; i++){
//            if(hashTable[i] != null && !hashTable[i].equals("")){
//                int valueIndex = hashTable[i].hashCode();
//                int clampedIndex = clamp(valueIndex);
//                newTable[clampedIndex] = hashTable[i];
//            }
//        }
//        hashTable = newTable;
//    }
//
//    private void smallerHashTable() {
//        decreaseTableCapacity();
//        String[] newTable = new String[capacity()];
//        for (int i = 0; i < hashTable.length; i++) {
//            if (!(hashTable[i] == null || hashTable[i].equals(""))) {
//                int valueIndex = hashTable[i].hashCode();
//                int clampedIndex = clamp(valueIndex);
//                newTable[clampedIndex] = hashTable[i];
//            }
//            hashTable = newTable;
//        }
//    }

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

    @Override
    public boolean contains(String searchVal) {
        for (int i = 0; i < capacity(); i++) {
            if (hashTable[i] != null && hashTable[i].equals(searchVal)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String toDelete) {
        if (!contains(toDelete)) {
            return false;
        }

        for (int i = 0; i < capacity(); i++) {
            if (hashTable[i] != null && hashTable[i].equals(toDelete)) {
                hashTable[i] = "";

                if (getLowerLoadFactor() > ((float) tableSize / capacity())) {
                    resizeTable(false);
                }

                tableSize--;
                return true;
            }
        }
        return false;

    }

    @Override
    public int size() {
        return tableSize;
    }
}
