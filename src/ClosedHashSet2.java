public class ClosedHashSet2 extends SimpleHashSet {

    String[] hashTable;
    private int tableSize;



    public ClosedHashSet2(){
        hashTable = new String[capacity()];
        tableSize = 0;
        upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
        lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;
    }

    public ClosedHashSet2(float upperLoadFactor, float lowerLoadFactor){
        this();
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    public ClosedHashSet2(java.lang.String[] data) {
        this();
        for (int i=0; i<data.length; i++){
            add(data[i]);
        }
    }

    private int clamp(String value, int index){
        return (value.hashCode()+(index + index*index)/2)&(capacity()-1);
    }


    @Override
    public boolean add(String newValue) {
        if (contains(newValue)){
            return false;
        }

        if (getUpperLoadFactor() < (float)(tableSize/capacity)){
            biggerHashTable();
        }
        return false;
    }

    private void biggerHashTable(){
        increaseTableCapacity();
        String[] newTable = new String[capacity()];
        for(int i =0; i<hashTable.length; i++){

        }
    }

    @Override
    public boolean contains(String searchVal) {
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
