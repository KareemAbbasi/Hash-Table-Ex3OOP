public class ClosedHashSet extends SimpleHashSet{

    String[] hashTable;

    public ClosedHashSet(){
        hashTable = new String[INITIAL_CAPACITY];
        upperLoadFactor = DEFAULT_UPPER_LOAD_FACTOR;
        lowerLoadFactor = DEFAULT_LOWER_LOAD_FACTOR;

    }

    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        this();
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    public ClosedHashSet(java.lang.String[] data){
        this();
        for (int i=0; i< data.length; i++) {
            add(data[i]);
        }
    }

    @Override
    public boolean add(String newValue) {
        return false;
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
        return 0;
    }


}
