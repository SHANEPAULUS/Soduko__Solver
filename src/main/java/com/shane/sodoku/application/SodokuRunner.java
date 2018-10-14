package com.shane.sodoku.application;

import java.util.Scanner;

import com.shane.sodoku.Sodoku;
import com.shane.sodoku.tools.SodokuHelper;
import com.shane.sodoku.util.SodokuUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/** This is the main class which drives (starts) the application.
  * The problem for this exercise is having to deal with incomplete Sodoku puzzles.
  *
  * The application will generate incomplete puzzles, and solve them.
  *
  * @author SHANE
  * <BR>30-Sep-2018 : Created class (SHANE)
  */

public class SodokuRunner {
   
   private static final Log logger = LogFactory.getLog(SodokuRunner.class);
   
   public static void main(String [] args){
      final Sodoku sodoku = new Sodoku();
      int selection = menuSelection();
      
      sodoku.setUnsolvedPuzzle(SodokuHelper.UNSOLVED_PUZZLE);
      
      while(selection > 0 && selection < 3){
         try{
            switch(selection){
               case 1:
                  printPuzzles(sodoku, true);
                  break;
               case 2:
                  SodokuHelper.fillUnsolvedPuzzle(sodoku);
                  printPuzzles(sodoku, false);
                  break;
               case 3:
                  System.exit(0);
               default:
                  break;
            }
         }catch(final Exception exception){
            logger.error("An unexpected exception occurred", exception);
         }
         
         selection = menuSelection();
      }
   }
   
   
   private static int menuSelection(){
      System.out.println("************************************************");
      System.out.println("\t\t\t\tPlease make a selection\n");
      System.out.println("(1) Print unsolved puzzle");
      System.out.println("(2) Print solved + unsolved puzzle");
      System.out.println("(3) Exit");
      
      
      final Scanner scanner = new Scanner(System.in);
      return scanner.nextInt();
   }
   
   
   private static void printPuzzles(final Sodoku sodoku, final boolean unsolvedOnly){
      if(unsolvedOnly){
         System.out.println(SodokuUtils.puzzleToString(sodoku.getUnsolvedPuzzle()));
      }else{
         System.out.println(sodoku.toString());
      }
   }
}
