/*
Hapsby - universal save game editor
SaveGameDefinition.java - Save game defintion
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

import java.io.FileReader;
import java.io.LineNumberReader;
import java.lang.Integer;
import SaveGameProperty;

/** Class SaveGameDefinition, class to define the properties and fields of a save game.
 * @author Hugues Johnson
 */
public class SaveGameDefinition{
     /* begin shared members */
     /* array to hold all properties */
     private SaveGameProperty[] propertyList; 
     /* total number of properties */
     private int propertyCount;
     /* title of the game */
     private String gameTitle; 
     /* description of save game */
     private String gameDescription; 
     /* file name or pattern of save game */
     private String saveFileName; 
     /* end shared members */
     
     /** Constructor for SaveGameDefinition
      * @param fileName Name of save definition to open
      */
     public SaveGameDefinition(String fileName){
          this.readDefinition(fileName);
     }
     
     /* readDefinition reads a save game defintion and sets variables and property list.
     * @param fileName the save game definition to open and read
     */
     private void readDefinition(String fileName){
          try{
               LineNumberReader reader=new LineNumberReader(new FileReader(fileName));
               Integer lineValue;
               /* read game title */
               this.gameTitle=reader.readLine();
               /* read game description */
               this.gameDescription=reader.readLine();
               /* read save file name */
               this.saveFileName=reader.readLine();
               /* read property count and convert to int */
               lineValue=Integer.valueOf(reader.readLine());
               this.propertyCount=lineValue.intValue();
               propertyList=new SaveGameProperty[this.propertyCount];
               /* create property list */
               for(int q=0;q<this.propertyCount;q++){
                    /* instantiate item */
                    this.propertyList[q]=new SaveGameProperty();
                    /* read property name */
                    this.propertyList[q].setName(reader.readLine());
                    /* read property description */
                    this.propertyList[q].setDescription(reader.readLine());
                    /* read data type and convert to int */
                    lineValue=Integer.valueOf(reader.readLine());
                    this.propertyList[q].setDataType(lineValue.intValue());
                    /* read address and convert to int */
                    lineValue=Integer.valueOf(reader.readLine());
                    this.propertyList[q].setAddress(lineValue.intValue());
                    /* read length and convert to int */
                    lineValue=Integer.valueOf(reader.readLine());
                    this.propertyList[q].setLength(lineValue.intValue());
                    /* read minimum value and convert to int */
                    lineValue=Integer.valueOf(reader.readLine());
                    this.propertyList[q].setMinValue(lineValue.intValue());
                    /* read maximum value and convert to int */
                    lineValue=Integer.valueOf(reader.readLine());
                    this.propertyList[q].setMaxValue(lineValue.intValue());
               }
          } catch(Exception x){
               System.err.println("Invalid save game definition: "+fileName);
               x.printStackTrace();
          }
     }
     
     /*  begin methods for retrieving values */
     /** getGameTitle retrieves the game title
      * @return game title
      */
     public final String getGameTitle(){
          return(this.gameTitle);
     }
     
     /** getGameDescription retrieves the description of the game.
      * @return game description
      */
     public final String getGameDescription(){
          return(this.gameDescription);
     }
     
     /** getSaveFileName retrieves the name, or pattern of a save game.
      * @return file name (ie. save.dat) or pattern (ie. *.dat)
      */
     public final String getSaveFileName(){
          return(this.saveFileName);
     }
     
     /* Passing arrays of SaveGameProperties can be memory consuming and painful. 
     * The next two methods allow a means to pass a list of property titles & an individual property. 
     */
     /** getPropertyList returns a String array of all game properties for this definition.
      * @return array of all property names.
      */
     public String[] getPropertyList(){
          String list[]=new String[this.propertyCount];
          for(int q=0;q<this.propertyCount;q++){
               list[q]=this.propertyList[q].getName();
          }
          return(list);
     }
     
     /** getSaveGameProperty is used to access a specific save game property.
      * @param index element to retrieve
      * @return save game property at index, or a new property if the index is invalid.
      */
     public SaveGameProperty getSaveGameProperty(int index){
          if(index>=0 && index<=this.propertyCount){
               return(propertyList[index]);
          } else{
               return(new SaveGameProperty());
          }
     }

     /** getSaveGameProperty is used to access a specific save game property.
      * @param name name of the element to retrieve
      * @return save game property at index, or a new property if the index is invalid.
     */
     public SaveGameProperty getSaveGameProperty(String name){
          int index=0;
          boolean found=false;
          name=name.toLowerCase();
          while(index<this.propertyList.length&&found==false){
               if(this.propertyList[index].getName().toLowerCase().equals(name)){
                    found=true;
               } else{
                    index++;
               }
          }
          if(found==true){
               return(this.propertyList[index]);
          } else{
               return(new SaveGameProperty());
          }
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
}