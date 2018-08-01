/**
 * 
 */
package br.com.juliogriebeler.model;

/**
 * @author juliofg
 *
 */
public enum UserType {
	SPON("Spon"),
	CUP("Cup"),
	LITER("Liter");
	
   private String unit;

   public String getUnit() {
	   return this.unit;
   }

   UserType(String unit) {
	   this.unit = unit;
   }
	
}
