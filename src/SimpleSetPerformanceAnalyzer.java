import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {

    private final String DATA1_PATH = "data1.txt";
    private final String DATA2_PATH = "data2.txt";

    private ClosedHashSet closedHashSet;
    private OpenHashSet openHashSet;
    private CollectionFacadeSet treeSet;
    private CollectionFacadeSet linkedList;
    private CollectionFacadeSet hashSet;

    private void initializeSets() {
        closedHashSet = new ClosedHashSet();
        openHashSet = new OpenHashSet();
        treeSet = new CollectionFacadeSet(new TreeSet<String>());
        linkedList = new CollectionFacadeSet(new LinkedList<String>());
        hashSet = new CollectionFacadeSet(new HashSet<String>());
    }

    private void addDataToOneSet(SimpleSet set, String[] data){
        long timeBefore = System.nanoTime();
        for (int i=0; i< data.length; i++){
            set.add(data[i]);
        }

        long timeAfter = System.nanoTime() - timeBefore;
        System.out.printf("Took %d\n to add to %s\n", timeAfter, set.toString());
    }

    private void addDataToSets(boolean firstData){
        String[] data;
        if (firstData){
            data = Ex3Utils.file2array(DATA1_PATH);
        } else {
            data = Ex3Utils.file2array(DATA2_PATH);
        }

        addDataToOneSet(closedHashSet,data);
        addDataToOneSet(openHashSet, data);
        addDataToOneSet(treeSet, data);
        addDataToOneSet(linkedList, data);
        addDataToOneSet(hashSet, data);
    }

    private void containsBigNumber(){

    }



}
