import java.util.LinkedList;
import java.util.ListIterator;

public class OpenHashSetaa extends SimpleHashSet {

    private LinkedListFacade[] hashTable;
    private int tableSize;

    public OpenHashSetaa(){
        hashTable = new LinkedListFacade[capacity()];
        tableSize = 0;
        initHashTable(hashTable);
    }

    public OpenHashSetaa(float upperLoadFactor, float lowerLoadFactor){
        this();
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    public OpenHashSetaa(java.lang.String[] data){
        this();
        for (int i=0; i< data.length; i++){
            add(data[i]);
        }
    }

    private void initHashTable(LinkedListFacade[] hashTable){
        for(int i=0; i<hashTable.length; i++){
            hashTable[i] = new LinkedListFacade(new LinkedList<String>());
        }
    }

    protected int clamp(String value){
        return value.hashCode()&(capacity()-1);
    }

    @Override
    public boolean add(String newValue) {
        int index = clamp(newValue);

        if (hashTable[index] == null) {
            hashTable[index] = new LinkedListFacade(new LinkedList<String>());
        }

        boolean didAdd = false;

        didAdd = hashTable[index].add(newValue);

        if (didAdd){
            tableSize++;
            if (getUpperLoadFactor() < (float)tableSize / (float)capacity()){
                biggerHashTable();
            }
        }

        return didAdd;
    }

    @Override
    public boolean contains(String searchVal) {
        int index = clamp(searchVal);
        if (hashTable[index] != null){
            return hashTable[index].contains(searchVal);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String toDelete) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    private void biggerHashTable(){
        increaseTableCapacity();
        LinkedListFacade[] bigHashTable = new LinkedListFacade[capacity()];
        initHashTable(bigHashTable);

        for(int i=0; i<hashTable.length; i++){
            if (hashTable[i] != null) {
                for (ListIterator iter = hashTable[i].getIterator(); iter.hasNext();){
                    String value = iter.next().toString();
                    int index = clamp(value);
                    if (bigHashTable[index] == null){
                        bigHashTable[index] = new LinkedListFacade(new LinkedList<String>());
                    }
                    bigHashTable[index].add(value);
                }
            }
        }
        hashTable = bigHashTable;
    }

    private void smallerHashTable(){
        decreaseTableCapacity();
        LinkedListFacade[] smallHashTable = new LinkedListFacade[capacity()];
        initHashTable(smallHashTable);

        for (int i=0; i<hashTable.length; i++){
            if (hashTable[i] != null){
                for(ListIterator iter = hashTable[i].getIterator(); iter.hasNext();){
                    String value = iter.next().toString();
                    int index = clamp(value);
                    if (smallHashTable[index] ==null){
                        smallHashTable[index] = new LinkedListFacade(new LinkedList<String>());
                    }

                    smallHashTable[index].add(value);
                }
            }
        }
        hashTable = smallHashTable;
    }

    @Override
    int clamp(int index) {
        return 0;
    }
}
