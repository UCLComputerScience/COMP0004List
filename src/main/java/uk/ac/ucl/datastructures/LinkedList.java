package uk.ac.ucl.datastructures;

/**
 * An example generic Linked List class to demonstrate the basic
 * principles of implementing a linked list. This should not be taken as a
 * production quality class - use the standard java.util.LinkedList class.
 * Copyright (c) 2019
 * Dept. of Computer Science, University College London
 *
 * @author Graham Roberts
 */

import java.util.Iterator;


public class LinkedList<T> implements SimpleList<T>
{
  /**
   * A list is a chain of ListElement objects. The variable head references
   * the first object in the chain or is null if the list is empty.
   */
  private ListElement<T> head;

  /**
   *  Private helper class used to implement the list chain. Instances of
   *  this class are never made available to client code of the list.
   *  Note that this a generic class that declares a type variable named E
   *  rather than E, to avoid confusion with the T declared by LinkedList.
   *  Also, this is a static class, so cannot access the enclosing LinkedList
   *  class scope to access instance variables or methods.
   */
  private static class ListElement<E>
  {
    /**
     *  Reference to next node in a chain.
     */
    private ListElement<E> next;

    /**
     *  Reference to data object.
     */
    private E value;

    /**
     *  Constructor for ListElement.
     *
     *  @param  next  reference to next node in chain or null.
     *  @param  value reference to data object.
     */
    public ListElement(final ListElement<E> next, final E value)
    {
      this.next = next;
      this.value = value;
    }

    /**
     *  Recursive helper method to copy the chain of ListElements starting with
     *  and including the element the method is called for. Objects referenced
     *  by ListElements are not copied.
     *
     *  @return reference to copied chain of elements.
     */
    public ListElement<E> copy()
    {
      return new ListElement<E>(next == null ? null : next.copy(), value);
    }

    /**
     * Getter for next.
     *
     * @return reference to next list element.
     */
    public ListElement<E> next()
    {
      return next;
    }

    /**
     * Getter for value.
     *
     * @return reference to element value.
     */
    public E value()
    {
      return value;
    }
  }

  /**
   *  Constructor for LinkedList. By default a list is empty, marked by head
   *  being null.
   */
  public LinkedList()
  {
    head = null;
  }

  /**
   *  Private helper constructor for LinkedList to quickly construct
   *  a new list given a chain of elements.
   *
   *  @param e reference to the element chain forming the list to be held by
   *  the new list object. The chain is not copied.
   */
  private LinkedList(final ListElement<T> e)
  {
    head = e;
  }

  /**
   *  Insert a new value at the head of the list.
   *
   *  @param  val  data object reference to insert.
   */
  public void insertAtHead(final T val)
  {
    head = new ListElement<T>(head, val);
  }

  /**
   *  Return the value (object reference) at the head of the list.
   *  The list is unchanged.
   *  @return reference to object at head of list or null if the
   *  list is empty.
   */
  public T getHeadOfList()
  {
    return (head == null) ? null : head.value();
  }

  /**
   *  Return the tail of the list (i.e., the list that is the copy of the
   *  current list minus the head element). The list elements are copied
   *  but not the objects held in the list (shallow copy).
   *
   *  @return a new list that is a copy of the current list minus the head
   *  element, or an empty list if there is no tail.
   */
  public SimpleList<T> getTailOfList()
  {
    if ((head == null) || (head.next() == null))
    {
      return new LinkedList<T>();
    }
    return new LinkedList<T>(head.next().copy());
  }

  /**
   *  Test to see if the list is empty.
   *
   *  @return true if the list is empty, false otherwise.
   */
  public boolean isEmpty()
  {
    return head == null;
  }

  /**
   *  Iterator class to allow each list element to be
   *  visited in sequence. The iterator class is nested in the list class
   *  and is non-static meaning it has access to the state of the
   *  list object being iterated.
   *  This class implements the standard generic Iterator interface but is
   *  not a generic class itself. The type variable T declared by the enclosing
   *  list class can be used in this class as it is a member class not a static
   *  class.
   */
  private class LinkedListIterator implements Iterator<T>
  {
    /**
     * Instance variable used to store the current position of the iteration.
     * This class uses the technique of creating a dummy list element added to
     * the head of the list chain. This makes it straightforward to implement
     * the next method that needs to look at the next element.
     */
    protected ListElement<T> dummy = new ListElement<T>(head, null);
    protected ListElement<T> current = dummy;

    /**
     *  Determine if there is another element in the sequence, i.e.,
     *  another value in the list.
     *  @return true if another element in the sequence is available, false
     * otherwise.
     */
    public boolean hasNext()
    {
      return current.next() != null;
    }

    /**
     *  Return the object reference of the next value in the list. The position
     *  is moved forward before the value is returned.
     *
     *  @return the next object reference in the sequence or null if at the end
     *  of the sequence.
     */
    public T next()
    {
      if (hasNext())
      {
        current = current.next();
        return current.value();
      }
      return null;
    }

    /**
     * The remove operation is not supported by this iterator. This illustrates
     * that a method required by an implemented interface can be written to not
     * support the operation but should throw an exception if called.
     * UnsupportedOperationException is a subclass of RuntimeException and is
     * not required to be caught at runtime, so the remove method does not
     * have a throws declaration. Calling methods do not have to use a try/catch
     * block pair.
     *
     * @throws UnsupportedOperationException if method is called.
     */
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }

  /**
   *  Return a new Iterator object for the current list.
   *
   *  @return new iterator reference.
   */
  public Iterator<T> iterator()
  {
    return new LinkedListIterator();
  }

  /**
   * This iterator class provides an iterator that can insert a value
   * in a list following the current item. This class also illustrates several
   * Java features:
   * - it is both a subclass and implements an interface.
   * - it is a subclass of another member class in the enclosing list class.
   * - InsertIterator extends the Iterator interface to declare the additional
   *   insert method.
   */
  private class LinkedListInsertIterator extends LinkedListIterator
    implements InsertIterator<T>
  {
    /**
     * Insert a new value following the current value.
     * @param value  value to insert in list.
     */
    public void insert(final T value)
    {
      if ((head == null) || (current == dummy))
      {
        insertAtHead(value);
        current = new ListElement<T>(head, null);
        return;
      }
      current.next = new ListElement<T>(current.next(), value);
    }
  }

  /**
   *  Return a new InsertIterator object for the current list, allowing
   *  items to be inserted into the list.
   *
   *  @return new insert iterator reference.
   */
  public InsertIterator<T> insertIterator()
  {
    return new LinkedListInsertIterator();
  }
}
