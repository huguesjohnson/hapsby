/*
Hapsby - universal save game editor
IniFile.java - Routines to read/write .ini files
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.Exception;
import java.lang.String;
import java.lang.System;
import java.util.Properties;

/** class IniFile
 * @author Hugues Johnson
 */
public class IniFile{
     private String fileName;
     private Properties properties;
     
     /** constructor
      * @param fileName, name of ini file to read/write
      */
     public IniFile(String fileName){
          this.fileName=fileName;
          this.properties=new Properties();
     }
     
     /** read, reads the file specified in the constuctor
      * all data previously saved in memory is lost after read
      */
     public void read(){
          /* erase current contents of properties, if the file is not found the contents of properties should be empty*/
          this.properties=new Properties();
          try{
               FileInputStream fIn=new FileInputStream(this.fileName);
               this.properties.load(fIn);
               fIn.close();
          } catch(Exception x){
               x.printStackTrace();
          }
     }
     
     /** save, saves settings to file specfied in the constuctor - overwrites old file
      * @param header, header for ini file
      */
     public void save(String header){
          try{
               FileOutputStream fOut=new FileOutputStream(this.fileName);
               this.properties.store(fOut,header);
               fOut.close();
          } catch(Exception x){
               x.printStackTrace();
          }
     }
     
     /** clear, clears out property list
      */
     public void clear(){
          if(this.properties!=null){
               this.properties.clear();
               this.properties=null;
          }
     }
     
     /** setProperty, sets a property in memory - use save() to write all properties to file
      * @name, name (key) of the setting
      * @value, value of the setting
      */
     public void setProperty(String name,String value){
          this.properties.setProperty(name.trim(),value.trim());
     }

     /** setProperty, sets a property in memory - use save() to write all properties to file
      * @name, name (key) of the setting
      * @value, value of the setting
      */
     public void setProperty(String name,int value){
          this.setProperty(name,String.valueOf(value));
     }

     /** setProperty, sets a property in memory - use save() to write all properties to file
      * @name, name (key) of the setting
      * @value, value of the setting
      */
     public void setProperty(String name,boolean value){
          this.setProperty(name,String.valueOf(value));
     }
     
     /** setProperty, sets a property in memory - use save() to write all properties to file
      * @name, name (key) of the setting
      * @value, value of the setting
      */
     public void setProperty(String name,long value){
          this.setProperty(name,String.valueOf(value));
     }     

     /** setProperty, sets a property in memory - use save() to write all properties to file
      * @name, name (key) of the setting
      * @value, value of the setting
      */
     public void setProperty(String name,float value){
          this.setProperty(name,String.valueOf(value));
     }     
     
     /** setProperty, sets a property in memory - use save() to write all properties to file
      * @name, name (key) of the setting
      * @value, value of the setting
      */
     public void setProperty(String name,double value){
          this.setProperty(name,String.valueOf(value));
     }     

      /** setProperty, sets a property in memory - use save() to write all properties to file
      * @name, name (key) of the setting
      * @value, value of the setting
      */
     public void setProperty(String name,char value){
          this.setProperty(name,String.valueOf(value));
     }
     
     /** setProperty, sets a property in memory - use save() to write all properties to file
      * @name, name (key) of the setting
      * @value, value of the setting
      */
     public void setProperty(String name,byte value){
          this.setProperty(name,String.valueOf(value));
     }     
     
     /** getProperty, retrieves a property from memory, use read() to load all properties from file
      * @name, name (key) of the property to retrieve
      * @defaultValue, value to return if property is not found
      * @return value of the property
      */
     public String getProperty(String name,String defaultValue){
          return(this.properties.getProperty(name,defaultValue).trim());
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
}