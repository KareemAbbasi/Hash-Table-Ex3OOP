public class OpenHashSet extends SimpleHashSet {

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
