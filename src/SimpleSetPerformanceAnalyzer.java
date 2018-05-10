import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {

    private final String DATA1_PATH = "/cs/usr/kareem/Desktop/ex3OOP/ex3OOP/src/data1.txt";
    private final String DATA2_PATH = "/cs/usr/kareem/Desktop/ex3OOP/ex3OOP/src/data2.txt";

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

    private void startTest(){
        initializeSets();

        addDataToSets(true);

        checkContainsAllSets("hi");

        checkContainsAllSets("-13170890158");

        initializeSets();

        addDataToSets(false);

        checkContainsAllSets("23");

        checkContainsAllSets("hi");

    }

    private void addDataToOneSet(SimpleSet set, String[] data) {
        long timeBefore = System.nanoTime();
        for (int i = 0; i < data.length; i++) {
            set.add(data[i]);
        }

        long timeAfter = System.nanoTime() - timeBefore;
        long timeMilli = timeAfter/1000000;
        System.out.printf("Took %d ms\n to add to %s\n", timeMilli, set.toString());
    }

    private void addDataToSets(boolean firstData) {
        String[] data;
        if (firstData) {
            data = Ex3Utils.file2array(DATA1_PATH);
        } else {
            data = Ex3Utils.file2array(DATA2_PATH);
        }

        addDataToOneSet(closedHashSet, data);
        addDataToOneSet(openHashSet, data);
        addDataToOneSet(treeSet, data);
        addDataToOneSet(linkedList, data);
        addDataToOneSet(hashSet, data);
    }

    private void checkContainsSingleSet(String value, SimpleSet set){
        long timeBefore = System.nanoTime();
        set.contains(value);
        long timeAfter = System.nanoTime() - timeBefore;

        System.out.printf("It took %d\n to find %s\n in %s\n", timeAfter, value, set.toString());
    }

    private void checkContainsAllSets(String value){
        checkContainsSingleSet(value, closedHashSet);
        checkContainsSingleSet(value, openHashSet);
        checkContainsSingleSet(value, treeSet);
        checkContainsSingleSet(value, linkedList);
        checkContainsSingleSet(value, hashSet);
    }



    public static void main(String[] args) {
        SimpleSetPerformanceAnalyzer tester = new SimpleSetPerformanceAnalyzer();
        tester.startTest();
    }


}
