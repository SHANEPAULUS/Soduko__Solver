package com.shane.sodoku;

import com.shane.sodoku.util.SodokuUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This class represents a full Sodoku board.
 * Whether incomplete / completed.
 *
 * @author SHANE
 *
 * <BR>30-Sep-2018 : Created class (SHANE)
*/

public class Sodoku {
   
   public static final int PUZZLE_MAX = 9;
   
   private static final Log logger = LogFactory.getLog(Sodoku.class);
   
   private Integer [][] unsolvedPuzzle;
   private Integer [][] solvedPuzzle;
   private DifficultyLevel difficultyLevel;
   
   
   public Sodoku(){
      this.unsolvedPuzzle = new Integer[PUZZLE_MAX][PUZZLE_MAX];
      this.solvedPuzzle = new Integer[PUZZLE_MAX][PUZZLE_MAX];
   }
   
   
   /**
    * Getter for property unsolvedPuzzle.
    * @return Value of property unsolvedPuzzle.
    */
   public final Integer[][] getUnsolvedPuzzle(){
      return this.unsolvedPuzzle;
   }
   
   /**
    * Setter for property unsolvedPuzzle.
    * @param newUnsolvedPuzzle, the new value for property unsolvedPuzzle.
    */
   public final void setUnsolvedPuzzle(final Integer[][] newUnsolvedPuzzle){
      this.unsolvedPuzzle = newUnsolvedPuzzle;
   }
   
   /**
    * Getter for property solvedPuzzle.
    * @return Value of property solvedPuzzle.
    */
   public final Integer[][] getSolvedPuzzle(){
      return this.solvedPuzzle;
   }
   
   /**
    * Setter for property solvedPuzzle.
    * @param newSolvedPuzzle, the new value for property solvedPuzzle.
    */
   public final void setSolvedPuzzle(final Integer[][] newSolvedPuzzle){
      this.solvedPuzzle = newSolvedPuzzle;
   }
   
   /**
    * Getter for property difficultyLevel.
    * @return Value of property difficultyLevel.
    */
   public final DifficultyLevel getDifficultyLevel(){
      return this.difficultyLevel;
   }
   
   /**
    * Setter for property difficultyLevel.
    * @param newDifficultyLevel, the new value for property difficultyLevel.
    */
   public final void setDifficultyLevel(final DifficultyLevel newDifficultyLevel){
      this.difficultyLevel = newDifficultyLevel;
   }
   
   
   
   @Override
   public String toString(){
      final StringBuilder toStringBuilder = new StringBuilder("SODOKU\n");
      
      
      if(this.unsolvedPuzzle != null){
         toStringBuilder.append("\nUnsolved puzzle\n");
         toStringBuilder.append(SodokuUtils.puzzleToString(this.unsolvedPuzzle));
      }
      
      if(this.solvedPuzzle != null){
         toStringBuilder.append("\nSolved puzzle\n");
         toStringBuilder.append(SodokuUtils.puzzleToString(this.solvedPuzzle));
      }
      
      return toStringBuilder.toString();
   }
}
