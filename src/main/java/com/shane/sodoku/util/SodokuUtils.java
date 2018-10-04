package com.shane.sodoku.util;

import com.shane.sodoku.SodokuCell;


/**
 *
 * @author SHANE
 * <BR>30-Sep-2018 : Created class (SHANE)
 */

public class SodokuUtils {
   
   
   public static String matrixToString(final SodokuCell[][] matrix){
      final StringBuilder matrixBuilder = new StringBuilder();
      
      for(int outer = 0; outer < matrix.length; outer ++){
         for(int inner = 0; inner < matrix[outer].length; inner ++){
            final SodokuCell currentValue = matrix[outer][inner];
            matrixBuilder.append(currentValue == null ? "_" : currentValue.getValue()).append(" ");
         }
         
         matrixBuilder.append("\n");
      }
      
      return matrixBuilder.toString();
   }
}
