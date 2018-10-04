package com.shane.sodoku.tools;

import java.util.Random;

import com.shane.sodoku.DifficultyLevel;
import com.shane.sodoku.Sodoku;
import com.shane.sodoku.SodokuCell;
import com.shane.sodoku.util.SodokuUtils;

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
   
   
   public static void randomlyFillSodokuUnsolvedPuzzle(final Sodoku sodoku){
      final SodokuCell[][] unsolvedMatrix = new SodokuCell[Sodoku.PUZZLE_MAX][Sodoku.PUZZLE_MAX];
      final int validEntriesPerRow = appropriateValidEntriesPerRow(sodoku.getDifficultyLevel());
      
      for(int row = 0; row < Sodoku.PUZZLE_MAX; row ++){
         for(int cell = 0; cell <= validEntriesPerRow; cell ++){
            int randomCellPosition = newRandomColumn();
            SodokuCell randomCellValue = new SodokuCell(newRandomValue(), false);
            
            while(alreadyHasValueOnHorizontalAxis(row, randomCellPosition, randomCellValue.getValue(), unsolvedMatrix) || alreadyHasValueOnVerticalAxis(row, randomCellPosition, randomCellValue.getValue(), unsolvedMatrix) || alreadyHasValueInGroup(row, randomCellPosition, randomCellValue.getValue(), unsolvedMatrix)){
               randomCellPosition = newRandomColumn();
               randomCellValue.setValue(newRandomValue());
            }
            
            unsolvedMatrix[row][randomCellPosition] = randomCellValue;
         }
      }
      
      sodoku.setUnsolvedPuzzle(unsolvedMatrix);
   }
   
   public static void fillUnsolvedPuzzle(final Sodoku sodoku){
      final SodokuCell[][] unsolvedPuzzle = new SodokuCell[sodoku.getUnsolvedPuzzle().length][sodoku.getUnsolvedPuzzle()[0].length];
      copySodokuCellArray(sodoku.getUnsolvedPuzzle(), unsolvedPuzzle);
      
      System.out.println("Original puzzle >> \n" + SodokuUtils.matrixToString(unsolvedPuzzle));
   
      for(int row = 0; row < unsolvedPuzzle.length; row ++){
         for(int cell = 0; cell < unsolvedPuzzle[row].length; cell ++){
            final SodokuCell currentValue = unsolvedPuzzle[row][cell];
            
            if(currentValue == null){   // We obviously need to fill the null values...
               final SodokuCell newRandomValue = new SodokuCell(null, true);   // using the random strategy wastes memory and time.
               boolean foundMissingValue = false;
               
               for(int currentNumber : possibleNumbers){
                  newRandomValue.setValue(currentNumber);
                  
                  if(fitsIntoCell(row, cell, newRandomValue, unsolvedPuzzle)){
                     foundMissingValue = true;
                     break;
                  }
               }
               
               if(foundMissingValue){
                  unsolvedPuzzle[row][cell] = newRandomValue;
               }else{
                  swapWithWeakCells(unsolvedPuzzle, row, cell, newRandomValue);
               }
            }
         }
      }
      
      sodoku.setSolvedPuzzle(unsolvedPuzzle);
   }
   
   
   /** This method checks the current position and see if we can swap it out with a weak cell. */
   private static void swapWithWeakCells(final SodokuCell[][] unsolvedPuzzle, final int currentRow, final int currentCell, final SodokuCell randomCell){
      boolean hasFoundValue = false;
      
      for(final Integer proposedValue : possibleNumbers){
         randomCell.setValue(proposedValue);
         
         for(int cell = 0; cell < unsolvedPuzzle[currentRow].length; cell ++){
            final SodokuCell sodokuCell = unsolvedPuzzle[currentRow][cell];
            
            if(sodokuCell != null && sodokuCell.getIsWeakCell() && sodokuCell.getValue().intValue() != proposedValue.intValue()){
               System.out.println(SodokuUtils.matrixToString(unsolvedPuzzle));
               unsolvedPuzzle[currentRow][cell] = null;   // temporary set this to null or else the check will always fail!
               System.out.println("1. " + sodokuCell.getValue() + " on " + currentCell + " >> " + fitsIntoCell(currentRow, currentCell, sodokuCell, unsolvedPuzzle));
               System.out.println("2. " + randomCell.getValue() + " on " + cell + " >> " + fitsIntoCell(currentRow, cell, randomCell, unsolvedPuzzle) + "\n");
               
               hasFoundValue = fitsIntoCell(currentRow, currentCell, sodokuCell, unsolvedPuzzle) && fitsIntoCell(currentRow, cell, randomCell, unsolvedPuzzle);
               
               if(hasFoundValue){
                  System.out.println("Swapped out cell " + cell + " with value " + sodokuCell.getValue() + " with cell " + currentCell + " with value >> " + randomCell.getValue());
                  unsolvedPuzzle[currentRow][currentCell] = sodokuCell;
                  unsolvedPuzzle[currentRow][cell] = randomCell;
                  break;
               }else{
                  unsolvedPuzzle[currentRow][cell] = sodokuCell;
               }
            }
         }
         
         if(hasFoundValue){
            break;
         }
      }
      
//      if(!hasFoundValue){
//         throw new IllegalStateException("Could not fill the unsolved Sodoku puzzle as all possible combinations has been used. Check methods on the SodokuHelper class for for problems.");
//      }
   }
   
   
   private static boolean fitsIntoCell(final int row, final int cell, final SodokuCell sodokuCell, final SodokuCell[][] sodokuCells){
      return !alreadyHasValueOnHorizontalAxis(row, cell, sodokuCell.getValue(), sodokuCells) && !alreadyHasValueOnVerticalAxis(row, cell, sodokuCell.getValue(), sodokuCells) && !alreadyHasValueInGroup(row, cell, sodokuCell.getValue(), sodokuCells);
   }
   
   
   
   /** Randomly generate a cell position we will fill for the unsolved puzzle. */
   private static int newRandomColumn(){
      return new Random().nextInt(Sodoku.PUZZLE_MAX);
   }
   
   
   /** Randomly generate a value we can use to fill the unsolved puzzle / generate a new value for the solved puzzle. */
   private static Integer newRandomValue(){
      return new Random().nextInt(Sodoku.PUZZLE_MAX) + 1;
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
