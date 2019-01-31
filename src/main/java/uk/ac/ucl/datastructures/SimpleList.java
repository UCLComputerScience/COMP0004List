package uk.ac.ucl.datastructures;

/**
 * List data structure interface.
 * Copyright (c) 2019 Dept. of Computer Science, University College London
 *
 * @author Graham Roberts
 */

/**
 * This interface defines the operations supported on lists.
 * @param <T> The type of the value stored in the list.
 */
public interface SimpleList<T> extends InsertIterable<T>
{
  /**
   * Insert a new value at the head of the list.
   *
   * @param value reference of object to insert.
   */
  void insertAtHead(final T value);

  /**
   * Return the reference to the object at the head of the list.
   * The list is unchanged.
   *
   * @return reference to object at head of list or null if the
   * list is empty.
   */
  T getHeadOfList();

  /**
   * Return the tail of the list, which is a new list containing all the
   * elements except the first, in the same order.
   *
   * @return a new list that is a shallow copy of the current list minus
   * the first element, or an empty list if there is no tail.
   */
  SimpleList getTailOfList();

  /**
   * Test to see if the list is empty.
   *
   * @return true if the list is empty, false otherwise.
   */
  boolean isEmpty();
}