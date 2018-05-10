public class CollectionFacadeSet implements SimpleSet {
    protected java.util.Collection<java.lang.String> collection;

    CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {
        this.collection = collection;
    }

    @Override
    public boolean add(String newValue) {
        if (!collection.contains(newValue)) {
            return collection.add(newValue);
        }
        return false;

    }

    @Override
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        return collection.remove(toDelete);
    }

    @Override
    public int size() {
        return collection.size();
    }

}
