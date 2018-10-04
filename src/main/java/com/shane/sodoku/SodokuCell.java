package com.shane.sodoku;

/** This class represents and individual cell on a Sodoku puzzle.
 *
 * @author SHANE
 * <BR>03-Oct-2018 : Created class (SHANE)
 */

public class SodokuCell {
   
   private Integer value;
   private boolean isWeakCell;   // This indicates whether this cell can be swapped out with a new cell or not..
   
   
   public SodokuCell(final Integer newValue, final boolean newIsWeakCell){
      this.value = newValue;
      this.isWeakCell = newIsWeakCell;
   }
   
   
   /**
    * Getter for property value.
    * @return Value of property value.
    */
   public final Integer getValue(){
      return this.value;
   }
   
   /**
    * Setter for property value.
    * @param newValue, the new value for property value.
    */
   public final void setValue(final Integer newValue){
      this.value = newValue;
   }
   
   /**
    * Getter for property isWeakCell.
    * @return Value of property isWeakCell.
    */
   public final boolean getIsWeakCell(){
      return this.isWeakCell;
   }
   
   /**
    * Setter for property isWeakCell.
    * @param newIsWeakCell, the new value for property isWeakCell.
    */
   public final void setIsWeakCell(final boolean newIsWeakCell){
      this.isWeakCell = newIsWeakCell;
   }
   
   
   @Override
   public boolean equals(final Object obj){
      boolean equals = obj instanceof SodokuCell;
      
      if(equals){
         final SodokuCell sodokuCell = (SodokuCell)obj;
         equals = sodokuCell == this || sodokuCell.getValue().intValue() == this.value.intValue();
      }
      
      return equals;
   }
   
   
   @Override
   public String toString(){
      return "SodokuCell >> " + (this.isWeakCell ? "weak" : "strong") + ", value " + this.value;
   }
}
