package uk.ac.ucl.datastructures;

/**
 *  Interface for iterator that allows insertion.
 *  Copyright (c) 2019
 *  Dept. of Computer Science, University College London
 *  @author Graham Roberts
 */

import java.util.Iterator;

/**
 * This interface extends Iterator<T>, which is defined in the standard
 * Java class libraries.
 * @param <T> The type of the value stored in the list being iterated over.
 */
public interface InsertIterator<T> extends Iterator<T>
{
  /**
   * Insert a new value following the current value.
   *
   * @param value value to insert in list.
   */
  void insert(final T value);
}
