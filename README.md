Kareem Abbasi

# Hash Table implemenation using Java
A simple implementation of Hash Tables using java.

# FILE DESCRIPTION
1. SimpleHashSet.java - Contains the basic structure used for different types of hashSets.
2. OpenHashSet.java - Contains the implementation of the OpenHashSet.
3. ClosedHashSet.java - Implementation of the closedHashSet.
4. CollectionFacadeSet.java - Basic structure that different set types use.
5. SimpleSetPerformanceAnalyzer.java - Performance analyzer of the different types of data structures.
6. TreeSetFacade.java - uses the CollectionFacadeSet to implement the treeSet.
7. RESULTS - contains the results of the performance analyzer.
8. README


## TreeSetFacade.java
This file inherits from the CollectionFacadeSet.java file. It implements the TreeSet objects that are used
in the implementation of the OpenHashSet.
I chose to use the TreeSets instead of the LinkedList since they are faster in some functions.

## OpenHashSet

The OpenHashSet is an array of type TreeSetFacade. Each element in the array contains a TreeSet which contains
the values that has to be stored in it. By using the TreeSets I was able to get faster run times since the run time
for the [contains] in the TreeSet (O(logn) is less than the linkedList (O(n)), and the contains function is used
everytime in the add function to check if an object is already in the list. Therefore we achieve less run time.



## Delete in ClosedHashSet:

The delete function uses the contain method to check if the value that needs to be deleted is in the ClosedHashSet,
if it is not found there is no need to continue the methods, therefore the methods returns false and we finished.
If the `toDelete` value is in the ClosedHashSet then we go over each element in the HashSet and check if there is
a value (i.e not null) and if it equals the `toDelete` value. If we found the value we want to delete, we change
the value in the hashSet to ("") because we can't simply put null in its place.
We then check the `loadFactor` of the hashSet and see if it's less than the `lowerLoadFactor`. If yes we have to create a
new smaller hashTable using the method `resizeTable()`. After finishing we remove 1 from the `tableSize` and return true.


# PERFORMANCE ANALYSIS

Using the `checkWarmUp()` method I found that after around 7000 times of calculating running time the JVM came to a
still state and the changes were very small if there was any.

1. The closeHashSet did poorly in the add data1 because it is made of a normal array and adding any value requires
going over every element in the array to check if it is already added and that takes a lot of time.
As for the OpenHashSet it took more time since it is also made of a normal array but this time in each element there
is a TreeSet therefore we need to go over every TreeSet and check if it contains the value we want to add before adding.

2. We can see that the OpenHashSet performs very poorly in adding elements and in most of the containing checks
ClosedHashSet performs better than the OpenHashSet but still not comparable to the other DaSts.
The TreeSet has a good performance time in adding elements and is the best in finding elements that it contains.
The HashSet is the best in adding new elements and has not bad results in finding elements.
The linkedList has some ups and downs sometimes it's quicker than the closedHashSet and sometimes not.

3. The OpenHashSet is far more slower than the ClosedHashSet in adding elements and in finding elements. Since the
closedHashSet only checks for values directly in it's elements wheras the OpenHashSet check also in the TreeSet in
each of it's elements.

4. The Java's Built in HashSet is much more quicker than my implementation of the HashSet.

5. I was surprised from the performance of the Java's built in HashSet as I expected it to be close to my implementation
Also I expected the LinkedList to be quicker.

