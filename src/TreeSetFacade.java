import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetFacade extends CollectionFacadeSet {
    private TreeSet<String> treeSet;

    public TreeSetFacade(TreeSet<String> treeSet) {
        super(treeSet);

        this.treeSet = treeSet;
    }

    public Iterator getIter() {
        return treeSet.iterator();
    }
}
