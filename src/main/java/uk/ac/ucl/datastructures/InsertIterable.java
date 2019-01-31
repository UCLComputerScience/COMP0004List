package uk.ac.ucl.datastructures;

/**
 * An extension of the Iterable interface that adds a method to return an
 * iterator that allows values to be inserted into the underlying container
 * during iteration.
 *
 * Copyright (c) 2019 Dept. of Computer Science, University College
 * London
 *
 * @author Graham Roberts
 */

/**
 * This interface extends Iterable<T>, which is defined in the standard
 * Java class libraries.
 * @param <T> The type of the value stored in the list being iterated over.
 */
public interface InsertIterable<T> extends Iterable<T>
{
  /**
   * Calling this method gets an iterator that can be used to insert a value
   * at the current position during an iteration.
   *
   * @return an insert iterator object.
   */
  InsertIterator<T> insertIterator();
}