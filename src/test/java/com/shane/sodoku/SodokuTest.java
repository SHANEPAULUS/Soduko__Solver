package com.shane.sodoku;

import com.shane.sodoku.tools.SodokuHelper;

import org.junit.Ignore;
import org.junit.Test;


/** Test5 class which contains unit tests based on the {@link Sodoku} class.
 *
 * @author SHANE
 * <BR>30-Sep-2018 : Created class (SHANE)
 */

@Ignore
public class SodokuTest {
   
   private final Integer[][] problemArray = {
         {null, 7, 9, 6, null, null, null, 3, null},
         {2, 6, null, 9, 5, null, null, 3, null},
         {3, 6, null, null, 1, 2, 5, 1},
         {6, null, 4, 9, null, null, null, 7, 3},
         {6, 5, 1, null, null, null, null, 7, 8},
         {null, null, 6, 2, null, 9, 7, 5, 8},
         {null, 1, 2, null, null, null, 6, null, 9},
         {null, 6, 1, null, null, 4, null, 7, 5},
         {2, 8, 6, null, null, null, 4, null}};
   
   
   
   @Test
   public void testSodokuRandomlyFilled(){
      final Sodoku sodoku = new Sodoku();
      SodokuHelper.randomlyFillSodokuUnsolvedPuzzle(sodoku);
      //SodokuHelper.fillUnsolvedPuzzle(sodoku);
      
      System.out.println(sodoku.toString());
      
      //System.out.println(SodokuUtils.matrixToString(problemArray));
      //System.out.println("HasValueOnVerticalAxis >> " + SodokuHelper.hasValueOnVerticalAxis(8, 1, problemArray[8][1], problemArray));
   }
}
