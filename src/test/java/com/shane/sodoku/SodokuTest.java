package com.shane.sodoku;

import com.shane.sodoku.tools.SodokuHelper;

import org.junit.Test;


/** Test5 class which contains unit tests based on the {@link Sodoku} class.
 *
 * @author SHANE
 * <BR>30-Sep-2018 : Created class (SHANE)
 */

public class SodokuTest {
   
   
   @Test
   public void testSodokuRandomlyFilled(){
      final Sodoku sodoku = new Sodoku();
      sodoku.setUnsolvedPuzzle(SodokuHelper.UNSOLVED_PUZZLE);
      SodokuHelper.fillUnsolvedPuzzle(sodoku);
      
      System.out.println(sodoku.toString());
      
      //System.out.println(SodokuUtils.matrixToString(problemArray));
      //System.out.println("HasValueOnVerticalAxis >> " + SodokuHelper.hasValueOnVerticalAxis(8, 1, problemArray[8][1], problemArray));
   }
}
