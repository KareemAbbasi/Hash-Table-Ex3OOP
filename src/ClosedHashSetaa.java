//public class ClosedHashSetaa extends SimpleHashSet{
//
//    String[] hashTable;
//    int tableSize;
//    float loadFactor = tableSize/capacity;
//
//    public ClosedHashSetaa(){
//        hashTable = new String[INITIAL_CAPACITY];
//        tableSize = 0;
//        upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
//        lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
//
//    }
//
//    @Override
//    int clamp(int index) {
//        return 0;
//    }
//
//    public ClosedHashSetaa(float upperLoadFactor, float lowerLoadFactor){
//        this();
//        this.upperLoadFactor = upperLoadFactor;
//        this.lowerLoadFactor = lowerLoadFactor;
//    }
//
//    public ClosedHashSetaa(java.lang.String[] data){
//        this();
//        for (int i=0; i< data.length; i++) {
//            add(data[i]);
//        }
//    }
//    public int clamp(String value, int i) {
//        return (value.hashCode()+(i + i*i)/2)&(capacity - 1);
//    }
//    private int findIndex(String value){
//        for (int i =0; i<hashTable.length; i++){
//            int index = clamp(value, i);
//            if(hashTable[index] == null || index > capacity()) {
//                return -1;
//            } else if (hashTable[index].equals(value)){
//                return index;
//            }
//        }
//        return -1;
//    }
//
//    private int emptyIndex(String value, String[] table){
//        int index = clamp(value, 0);
//        for (int i=0; i< table.length; i++){
//            index = clamp(value ,i);
//            if (table[index] == null || table[index].equals("")){
//                return index;
//            }
//        }
//        return index;
//    }
//
//    @Override
//    public boolean add(String newValue) {
//        if (contains(newValue)){
//            return false;
//        }
//
//        if (upperLoadFactor < loadFactor){
//            biggerTable();
//        }
//        int newValueIndex = emptyIndex(newValue, hashTable);
//        hashTable[newValueIndex] = newValue;
//        tableSize++;
//
//        return true;
//    }
//
//    @Override
//    public boolean contains(String searchVal) {
//        int searchValIndex = findIndex(searchVal);
//        return (searchValIndex != -1);
//    }
//
//    @Override
//    public boolean delete(String toDelete) {
//        int toDeleteIndex = findIndex(toDelete);
//        if (toDeleteIndex == -1){
//            return false;
//        }
//        hashTable[toDeleteIndex] = "";
//        tableSize --;
//
//        if (getLowerLoadFactor() > loadFactor){
//            smallerTable();
//        }
//        return true;
//    }
//
//    @Override
//    public int size() {
//        return tableSize;
//    }
//
//    public void biggerTable(){
//        increaseTableCapacity();
//        String[] biggerHashTable = new String[capacity()];
//        for (int i=0; i<hashTable.length; i++){
//            if (!(hashTable[i] == null || hashTable[i].equals(""))){
//                biggerHashTable[emptyIndex(hashTable[i], biggerHashTable)] = hashTable[i];
//            }
//        }
//        hashTable = biggerHashTable;
//    }
//
//    public void smallerTable(){
//        decreaseTableCapacity();
//        String[] smallerHashTable = new String[capacity()];
//        for (int i =0; i< hashTable.length; i++){
//            if (hashTable[i] != null && !hashTable[i].equals("")){
//                smallerHashTable[emptyIndex(hashTable[i], smallerHashTable)] = hashTable[i];
//            }
//        }
//        hashTable = smallerHashTable;
//    }
//
//
//}
