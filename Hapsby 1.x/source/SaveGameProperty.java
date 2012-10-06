/*
Hapsby - universal save game editor
SaveGameProperty.java - Save game property
Copyright  (C) 2000-2001 Hugues Johnson

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
the GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

/**
 * Class SaveGameProperty, class to describe a property (field) of a save game
 * @author Hugues Johnson
*/
public class SaveGameProperty{
     /* begin constants for data types */
     /* @TODO support more data types - change setDataType if new are added */
     /* integer stored low byte first */
     public static final int DATA_TYPE_INTEGER_LOW=1;
     /* integer stored high byte first */
     public static final int DATA_TYPE_INTEGER_HIGH=2; 
     /* end constants for data types */
     
     /* begin private shared members */
     /* name of property */
     private String name;
     /* description of property */
     private String description;
     /* data type of property - must be one of the above defined types*/
     private int dataType;
     /* address of property, in decimal format */
     private int address;
     /* length (number of bytes) of the property */
     private int length;
     /* maximum value of the property */
     private int maxValue;
     /* minimum value of the property */
     private int minValue;
     /* end private shared members */
     
     /** empty constructor
     * @TODO write some default values
     */
     public SaveGameProperty(){
     }
     
     /** Default constructor - creates a new property from the given parameters.
     * @param name name of new property
     * @param description description of new property
     * @param dataType data type of new property
     * @param address address of new property, in decimal
     * @param length length (number of bytes) of new property
     * @param maxValue maximum value of new property, in decimal
     * @param minValue minimum value of new property, in decimal
     */
     public SaveGameProperty(String name,String description,int dataType,int address,int length,int maxValue,int minValue){
          /* initialize these to zero to prevent errors when calling the set methods later on */
          this.maxValue=0; 
          this.minValue=0;
          /* use methods because they check if the parameters are valid */
          try{
               this.setName(name);
               this.setDescription(description);
               this.setDataType(dataType);
               this.setAddress(address);
               this.setLength(length);
               this.setMaxValue(maxValue);
               this.setMinValue(minValue);
          } catch(Exception x){
               x.printStackTrace();
          }
     }
     
     /* begin methods for setting values */
     /** setName - sets the name of the property
     * @param name new name
     */
     public final void setName(String name){
          this.name=name;
     }

     /** setDescription - sets the description of the property
     * @param description new description
     */
     public final void setDescription(String description){
          this.description=description;
     }
     
     /** setDataType - sets the data type & checks if it's valid
     * @param dataType new data type
     * @throws Exception if the data type is invalid 
     */
     public final void setDataType(int dataType) throws Exception{
          try{
               /* change this next line if new data types are added */
               if(dataType>=this.DATA_TYPE_INTEGER_LOW && dataType<=this.DATA_TYPE_INTEGER_HIGH){
                    this.dataType=dataType;
               } else{
                    this.dataType=this.DATA_TYPE_INTEGER_LOW;
                    throw(new Exception("Bad data type"));
               }
          } catch(Exception x){
               x.printStackTrace();
          }
     }
     
     /** setAddress - sets the address & checks if it's valid
     * @param address new address in decimal form, must be >= 0
     * @throws Exception if address is <0
     */
     public final void setAddress(int address) throws Exception{
          try{
               if(address>=0){
                    this.address=address;
               } else{
                    this.address=0;
                    throw(new Exception("Address must be >= 0"));
               }
          } catch(Exception x){
               x.printStackTrace();
          }
     }
     
     /** setLength sets the maximum value of the property, in decimal format. Length must be > 0.
     * @param length new length
     * @throws Exception if length <= 0
     */
     public final void setLength(int length) throws Exception{
          try{
               if(length>0){
                    this.length=length;
               } else{
                    this.length=1;
                    throw(new Exception("Length must be > 0"));
               }
          } catch(Exception x){
               x.printStackTrace();
          }
     }
     
     /** setMaxValue sets the maximum value of the property, in decimal format.
     * Maximum value must be >= minimum value.
     * @param maxValue new maximum value
     */
     public final void setMaxValue(int maxValue){
          this.maxValue=maxValue;
     }
     
     /** setMinValue sets the minimum value of the property, in decimal format.
     * Minimum value must be <= maximum value.
     * @param minValue new minimum value
     */
     public final void setMinValue(int minValue){
          this.minValue=minValue;
     }
     /* end methods for setting values */
     
     /* begin methods for retreiving values */
     /** getName retrieves the name of the property.
     * @return name
     */
     public final String getName(){
          return(this.name);
     }

     /** getDescription retrieves the description of the property.
     * @return description
     */
     public final String getDescription(){
          return(this.description);
     }
     
     /** getDataType retrieves the data type of the property.
     * @return dataType
     */
     public final int getDataType(){
          return(this.dataType);
     }
     
     /** getAddress retrieves the address of the property in decimal.
     * @return address
     */
     public final int getAddress(){
          return(this.address);
     }
     
     /** getLength retrieves the length of the property, number of bytes.
     * @return length
     */
     public final int getLength(){
          return(this.length);
     }
     
     /** getMaxValue retrieves the maximum value of the property, in decimal.
     * @return maxValue
     */
     public final int getMaxValue(){
          return(this.maxValue);
     }
     
     /** getMinValue retrieves the minimum value of the property, in decimal.
     * @return minValue
     */
     public final int getMinValue(){
          return(this.minValue);
     }
     /* end methods for retrieving values */
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
}