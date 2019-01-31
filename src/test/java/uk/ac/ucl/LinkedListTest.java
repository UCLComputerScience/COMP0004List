/*
 * Basic JUnit 4 test class for LinkedList class, including its iterators.
 * Copyright (c) 2019
 * Dept. of Computer Science, University College London
 * @author Graham Roberts
 */

package uk.ac.ucl;

import uk.ac.ucl.datastructures.LinkedList;
import uk.ac.ucl.datastructures.SimpleList;
import uk.ac.ucl.datastructures.InsertIterator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Iterator;

public class LinkedListTest
{
  private SimpleList<Integer> empty ;
  private SimpleList<Integer> one ;
  private SimpleList<Integer> several ;

  @Before
  public void setUp()
  {
    empty = new LinkedList<Integer>() ;
    one = new LinkedList<Integer>() ;
    one.insertAtHead(0) ;
    several = new LinkedList<Integer>() ;
    several.insertAtHead(2) ;
    several.insertAtHead(1) ;
    several.insertAtHead(0) ;
  }

  @Test
  public void testHeadOfEmptyList()
  {
    assertNull(empty.getHeadOfList()) ;
  }

  @Test
  public void testHeadOfListOfSizeOne()
  {
    assertEquals(Integer.valueOf(0), one.getHeadOfList()) ;
  }

  @Test
  public void testContentsOfList()
  {
    assertEquals(Integer.valueOf(0),several.getHeadOfList()) ;
    assertEquals(Integer.valueOf(1),several.getTailOfList().getHeadOfList()) ;
    assertEquals(Integer.valueOf(2),several.getTailOfList().getTailOfList().getHeadOfList()) ;
  }

  @Test
  public void testEmptyListIsEmpty()
  {
    assertTrue(empty.isEmpty());
  }

  @Test
  public void testListOfSizeOneBecomesEmpty()
  {
    assertFalse(one.isEmpty());
    assertTrue(one.getTailOfList().isEmpty()) ;
  }

  @Test
  public void testListBecomesEmpty()
  {
    assertFalse(several.isEmpty());
    assertFalse(several.getTailOfList().isEmpty()) ;
    assertFalse(several.getTailOfList().getTailOfList().isEmpty()) ;
    assertTrue(several.getTailOfList().getTailOfList().getTailOfList().isEmpty()) ;
  }

  @Test
  public void testIteratorOnEmpty()
  {
    for (Iterator<Integer> iterator = empty.iterator() ; iterator.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
  }

  @Test
  public void testIteratorOnOne()
  {
    int counter = 0 ;
    for (Iterator<Integer> iterator = one.iterator() ; iterator.hasNext(); )
    {
      assertEquals(Integer.valueOf(counter++),iterator.next()) ;
    }
    assertEquals(1,counter);
  }

  @Test
  public void testIteratorOnSeveral()
  {
    int counter = 0 ;
    for (Iterator<Integer> iterator = several.iterator() ; iterator.hasNext(); )
    {
      assertEquals(Integer.valueOf(counter++),iterator.next()) ;
    }
    assertEquals(3,counter);
  }

  @Test
  public void testIteratorViaEnhancedForLoop()
  {
    int counter = 0 ;
    for (int i : empty)
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (int i : one)
    {
      assertEquals(counter++,i) ;
    }
    assertEquals(1,counter);
    counter = 0 ;
    for (int i : several)
    {
      assertEquals(counter++,i) ;
    }
    assertEquals(3,counter);
  }

  @Test (expected=UnsupportedOperationException.class)
  public void testIteratorRemove()
  {
    Iterator<Integer> iterator = one.iterator();
    iterator.remove();
  }
  public void checkContents(SimpleList<Integer> list, int[] expected)
  {
    Iterator iterator = list.iterator();
    for (int n : expected)
    {
      assertEquals(n,iterator.next());
    }
  }

  @Test
  public void testInsertOnEmpty()
  {
    InsertIterator<Integer> iterator = empty.insertIterator();
    assertFalse(iterator.hasNext());
    iterator.insert(2);
    assertTrue(iterator.hasNext());
    assertEquals(Integer.valueOf(2),iterator.next());
    checkContents(empty, new int[]{2});
  }

  @Test
  public void testInsertOnOneAtFront()
  {
    InsertIterator<Integer> iterator = one.insertIterator();
    assertTrue(iterator.hasNext());
    iterator.insert(2);
    assertEquals(Integer.valueOf(2),iterator.next());
    assertEquals(Integer.valueOf(0),iterator.next());
    assertFalse(iterator.hasNext());
    checkContents(one, new int[]{2,0});
  }

  @Test
  public void testInsertOnOneAtEnd()
  {
    InsertIterator<Integer> iterator = one.insertIterator();
    assertTrue(iterator.hasNext());
    iterator.next();
    assertFalse(iterator.hasNext());
    iterator.insert(2);
    assertTrue(iterator.hasNext());
    assertEquals(Integer.valueOf(2),iterator.next());
    assertFalse(iterator.hasNext());
    checkContents(one, new int[]{0,2});
  }

  @Test
  public void testInsertOnSeveralInMiddle()
  {
    InsertIterator<Integer> iterator = several.insertIterator();
    iterator.next();
    assertTrue(iterator.hasNext());
    iterator.insert(5);
    assertTrue(iterator.hasNext());
    assertEquals(Integer.valueOf(5),iterator.next());
    assertTrue(iterator.hasNext());
    checkContents(several,new int[]{0,5,1,2});
  }

  @Test
  public void testNextNullAtEnd()
  {
    Iterator<Integer> iterator = one.insertIterator();
    iterator.next();
    assertNull(iterator.next());
  }
}