import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListFacade extends CollectionFacadeSet {
    LinkedList<String> linkedList;

    public LinkedListFacade(LinkedList<String> linkedList) {
        super(linkedList);
        this.linkedList = linkedList;
    }

    public ListIterator getIterator(){
        return linkedList.listIterator(0);
    }
}
