import uk.ac.ucl.datastructures.LinkedList;
import uk.ac.ucl.datastructures.SimpleList;

public class Main
{
  public SimpleList<Integer> createIntList(int ... n)
  {
    SimpleList<Integer> intList = new LinkedList<>();
    for (int x : n)
    {
      intList.insertAtHead(x);
    }
    return intList;
  }

  public SimpleList<String> createStringList(String ... strings)
  {
    SimpleList<String> stringList = new LinkedList<>();
    for (String s : strings)
    {
      stringList.insertAtHead(s);
    }
    return stringList;
  }

  public <T> void printList(SimpleList<T> list)
  {
    boolean first = true;
    System.out.print("{");
    for (T item : list)
    {
      if (first)
      {
        System.out.print(item);
        first = false;
      }
      else
      {
        System.out.print(", " + item);
      }
    }
    System.out.println("}");
  }

  public void go()
  {
    SimpleList<Integer> list1 = createIntList(1,2,3,4,5,6,7,8,9);
    printList(list1);
    SimpleList<String> list2 = createStringList("a", "b", "c", "d", "e", "f");
    printList(list2);
    System.out.print("Tail of list: ");
    printList(list1.getTailOfList());
  }

  public static void main(String[] args)
  {
    new Main().go();
  }
}
