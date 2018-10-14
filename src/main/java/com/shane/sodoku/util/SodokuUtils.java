package com.shane.sodoku.util;

/**
 *
 * @author SHANE
 * <BR>30-Sep-2018 : Created class (SHANE)
 */

public class SodokuUtils {
   
   
   public static String puzzleToString(final Integer[][] puzzle){
      final StringBuilder puzzleBuilder = new StringBuilder();
      
      for(int row = 0; row < puzzle.length; row ++){
         for(int cell = 0; cell < puzzle[row].length; cell ++){
            final Integer currentEntry = puzzle[row][cell];
            puzzleBuilder.append(currentEntry == null ? "_" : currentEntry).append(" ");
         }
         
         puzzleBuilder.append("\n");
      }
      
      return puzzleBuilder.toString();
   }
}
