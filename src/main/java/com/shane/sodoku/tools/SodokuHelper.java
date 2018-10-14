package com.shane.sodoku.tools;

import com.shane.sodoku.DifficultyLevel;
import com.shane.sodoku.Sodoku;
import com.shane.sodoku.SodokuCell;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This class is used to simulate a Sodoku game / matrix helper.
 * On this class we have useful methods that can be used to fill / randomly
 *
 *  @author SHANE
 *  <BR>30-Sep-2018 : Created class (SHANE)
 */

public class SodokuHelper {
   
   private static final Log logger = LogFactory.getLog(SodokuHelper.class);
   private static final Integer[] possibleNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
   
   public static final Integer[][] PROBLEM_ARRAY = {
         {9, null, null, 1, null, null, null, null, 5},
         {null, null, 5, null, 9, null, 2, null, 1}, 
         {8, null, null, null, 4, null, null, null, null}, 
         {null, null, null, null, 8, null, null, null, null}, 
         {null, null, null, 7, null, null, null, null, null}, 
         {null, null, null, null, 2, 6, null, null, 9}, 
         {2, null, null, 3, null, null, null, null, 6}, 
         {null, null, null, 2, null, null, 9, null, null}, 
         {null, null, 1, 9, null, 4, 5, 7, null}, 
   };
   
   
   
   
   public static void randomlyFillSodokuUnsolvedPuzzle(final Sodoku sodoku){
      final SodokuCell[][] unsolvedMatrix = new SodokuCell[Sodoku.PUZZLE_MAX][Sodoku.PUZZLE_MAX];
      
      for(int row = 0; row < PROBLEM_ARRAY.length; row ++){
         for(int cell = 0; cell < PROBLEM_ARRAY[row].length; cell ++){
            unsolvedMatrix[row][cell] = new SodokuCell(PROBLEM_ARRAY[row][cell], true);
         }
      }
      
      /**final int validEntriesPerRow = 8;//appropriateValidEntriesPerRow(sodoku.getDifficultyLevel());
      
      for(int row = 0; row < Sodoku.PUZZLE_MAX; row ++){
         for(int cell = 0; cell <= validEntriesPerRow; cell ++){
            int randomCellPosition = cell;//newRandomColumn();
            SodokuCell randomCellValue = new SodokuCell(newRandomValue(), false);
            
            while(alreadyHasValueOnHorizontalAxis(row, randomCellPosition, randomCellValue.getValue(), unsolvedMatrix) || alreadyHasValueOnVerticalAxis(row, randomCellPosition, randomCellValue.getValue(), unsolvedMatrix) || alreadyHasValueInGroup(row, randomCellPosition, randomCellValue.getValue(), unsolvedMatrix)){
               randomCellPosition = newRandomColumn();
               randomCellValue.setValue(newRandomValue());
            }
            
            unsolvedMatrix[row][randomCellPosition] = randomCellValue;
         }
      }*/
      
      sodoku.setUnsolvedPuzzle(unsolvedMatrix);
   }
   
   
   public void fillUnsolvedPuzzle(final Sodoku sodoku){
      final SodokuCell[][] sodokuCell = new SodokuCell[Sodoku.PUZZLE_MAX][Sodoku.PUZZLE_MAX];
      final SodokuCell[][] unsolvedSodokuCell = sodoku.getUnsolvedPuzzle();
      
      for(int row = 0; row < unsolvedSodokuCell.length; row ++){
         for(int cell = 0; cell < unsolvedSodokuCell[row].length; cell ++){
            final SodokuCell currentEntry = unsolvedSodokuCell[row][cell];
         }
      }
   }
   
   /** Checks if we already have the value on a given vertical axis. */
   public static boolean alreadyHasValueOnVerticalAxis(final int currentRow, final int currentCell, final Integer currentValue, final SodokuCell[][] populatedMatrix){
      boolean hasValueOnAxis = false;
      
      for(int row = 0; row < populatedMatrix.length; row ++){
         final SodokuCell valueToCheck = populatedMatrix[row][currentCell];
         hasValueOnAxis = valueToCheck != null && valueToCheck.getValue() != null && valueToCheck.getValue().intValue() == currentValue.intValue() && row != currentRow;
         
         if(hasValueOnAxis){
            break;
         }
      }
      
      return hasValueOnAxis;
   }
   
   
   /** Checks if we alreay have a given value on a horizontal axis */
   public static boolean alreadyHasValueOnHorizontalAxis(final int currentRow, final int currentCell, final Integer currentValue, final SodokuCell[][] populatedMatrix){
      boolean hasValueOnAxis = false;
      
      for(int cell = 0; cell < populatedMatrix[currentRow].length; cell ++){
         final SodokuCell valueToCheck = populatedMatrix[currentRow][cell];
         hasValueOnAxis = valueToCheck != null && valueToCheck.getValue() != null && valueToCheck.getValue().intValue() == currentValue.intValue() && cell != currentCell;
         
         if(hasValueOnAxis){
            break;
         }
      }
      
      return hasValueOnAxis;
   }
   
   
   /** This method checks if we have a certain value already existing inside a 3 x 3 group.
    * @param currentRow, the current row we are iterating over.
    * @param currentCell, the current cell we are iterating over.
    * @param value, the value we are checking.
    * @param populatedMatrix, the puzzle populated thus far.
    * @return, true if we have the value already existing inside the 3 x 3 group based on the current cell and row. */
   public static boolean alreadyHasValueInGroup(final int currentRow, final int currentCell, final Integer value, final SodokuCell[][] populatedMatrix){
      final int horizontalGroupBounds = appropriateHorizontalGroupBounds(currentCell);
      final int verticalGroupBounds = appropriateVerticalGroupBounds(currentRow);
      boolean containsValueInGroup = false;
      
      for(int startingRow = verticalGroupBounds - 2; startingRow <= verticalGroupBounds; startingRow ++){
         for(int startingCell = horizontalGroupBounds - 2; startingCell <= horizontalGroupBounds; startingCell ++){
            final SodokuCell currentValue = populatedMatrix[startingRow][startingCell];
            containsValueInGroup = currentValue != null && currentValue.getValue() != null && currentValue.getValue().intValue() == value.intValue();
            
            if(containsValueInGroup){
               break;
            }
         }
         
         if(containsValueInGroup){
            break;
         }
      }
      
      return containsValueInGroup;
   }
   
   
   /** This method determines the vertical group bounds based on the current cell / row we are accessing. */
   static int appropriateVerticalGroupBounds(final int currentRow){
      int appropriatePosition;
      
      if(currentRow <= 2){
         appropriatePosition = 2;
      }else if(currentRow <= 5){
         appropriatePosition = 5;
      }else{
         appropriatePosition = 8;
      }
      
      return appropriatePosition;
   }
   
   /** This method determines the horizontal group bounds based on the current cell / row we are accessing. */
   static int appropriateHorizontalGroupBounds(final int currentCell){
      return appropriateVerticalGroupBounds(currentCell);
   }
   
   
   /** Check how many valid entries we may allow depending on the difficulty level */
   private static int appropriateValidEntriesPerRow(final DifficultyLevel difficultyLevel){
      int appropriateValidEntriesPerRow = Sodoku.PUZZLE_MAX;
      
      if(difficultyLevel == DifficultyLevel.EASY){
         appropriateValidEntriesPerRow -= 3;
      }else if(difficultyLevel == DifficultyLevel.MEDIUM){
         appropriateValidEntriesPerRow -= 5;
      }else{
         appropriateValidEntriesPerRow -= 6;
      }
      
      return appropriateValidEntriesPerRow;
   }
   
   
   /** Check the System.copyArray.... method as an alternative. */
   private static void copySodokuCellArray(final SodokuCell[][] origin, final SodokuCell[][] destination){
      if(origin != null){
         for(int row = 0; row < origin.length; row ++){
            if(origin[row].length >= 0) System.arraycopy(origin[row], 0, destination[row], 0, origin[row].length);
         }
      }
   }
}
