package com.shane.sodoku.tools;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/** This test class contains unit tests on the {@link SodokuHelper} class.
 * @author SHANE
 * <BR>30-Sep-2018 : Created class (SHANE)
 */

public class SodokuHelperTest {


   @Test
   public void testVerticalBoundsOnGroup1(){
      final int currentRow = 0;
      final int appropriateVerticalBounds = SodokuHelper.appropriateVerticalGroupBounds(currentRow);
      
      assertEquals("The vertical bounds for a row on index 0 should be 2", 2, appropriateVerticalBounds);
   }
   
   
   @Test
   public void testVerticalBoundsOnGroup2(){
      final int currentRow = 3;
      final int appropriateVerticalBounds = SodokuHelper.appropriateVerticalGroupBounds(currentRow);
      
      assertEquals("The vertical bounds for a row on index 0 should be 5", 5, appropriateVerticalBounds);
   }
   
   
   @Test
   public void testVerticalBoundsOnGroup3(){
      final int currentRow = 7;
      final int appropriateVerticalBounds = SodokuHelper.appropriateVerticalGroupBounds(currentRow);
      
      assertEquals("The vertical bounds for a row on index 7 should be 8", 8, appropriateVerticalBounds);
   }
   
   
   @Test
   public void testHorizontalBoundsOnGroup1(){
      final int currentCell = 1;
      final int appropriateHorizontalBounds = SodokuHelper.appropriateHorizontalGroupBounds(currentCell);
   
      assertEquals("The horizontal bounds for cell index 1 should be 2", 2, appropriateHorizontalBounds);
   }
   
   
   @Test
   public void testHorizontalBoundsOnGroup2(){
      final int currentCell = 4;
      final int appropriateHorizontalBounds = SodokuHelper.appropriateHorizontalGroupBounds(currentCell);
      
      assertEquals("The horizontal bounds for cell index 1 should be 5", 5, appropriateHorizontalBounds);
   }
   
   @Test
   public void testHorizontalBoundsOnGroup3(){
      final int currentCell = 6;
      final int appropriateHorizontalBounds = SodokuHelper.appropriateHorizontalGroupBounds(currentCell);
      
      assertEquals("The horizontal bounds for cell index 6 should be 8", 8, appropriateHorizontalBounds);
   }
}
