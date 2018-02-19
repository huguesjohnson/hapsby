/*
Hapsby - universal save game editor
SaveGame.java - Save game file
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.Math;

/** Class SaveGame, points to a save game file and allows reading & writting values to it.
 * @author Hugues Johnson
 * @TODO consider rewriting this so it extends java.io.RandomAccessFile
 */
public class SaveGame{
     /* pointer to file */
     private RandomAccessFile saveGame; 
     /* (2^8) - java.lang only defines a max signed byte */
     private int MAX_UNSIGNED_BYTE=256; 
     /* read-write mode of the file */
     private final static String READ_WRITE_MODE="rw";
     /* length of a single byte */
     private final static int BYTE_LENGTH=8;
     /* data type for integers stored low byte first*/
     public final static int LOW_BYTE_FIRST=1;
     /* data type for integers stored high byte first */
     public final static int HIGH_BYTE_FIRST=2;
          
     /** Blank constructor for class SaveGame.
      */
     public SaveGame(){
     }
     
     /** Constructor for class SaveGame, accepts a file to reference.
      * @param file save game file.
      */
     public SaveGame(File file){
          openSaveGame(file);
     }
     
     /* openSaveGame opens a save game for reading and writing.
     * @param file the save game to open
     */
     private void openSaveGame(File file){
          try{
               this.saveGame=new RandomAccessFile(file,this.READ_WRITE_MODE);
          } catch(Exception x){
               x.printStackTrace();
          }
     }

     /** getIntValue retrieves an integer value from a save game.
      * @param offset decimal value pointing to the first byte of the value to retrieve
      * @param length length (number of bytes) of the value
      * @param byteOrder the byte order as defined in this class
      * @return value stored at offset X (offset*length) in decimal format
     */
     public int getIntValue(int offset,int length,int byteOrder){
          int value=0;
          try{
               if(this.saveGame!=null){
                    for(int q=0;q<length;q++){
                         saveGame.seek(offset+q);
                         if(byteOrder==this.LOW_BYTE_FIRST){
                              value+=((int)(saveGame.read()))*(Math.pow(MAX_UNSIGNED_BYTE,q));
                         } else if(byteOrder==this.HIGH_BYTE_FIRST){
                              value+=((int)(saveGame.read()))*(Math.pow(MAX_UNSIGNED_BYTE,(length-q-1)));
                         }
                    }
               }
          } catch(Exception x){
               x.printStackTrace();
          }
          return(value);
     }
          
     /** setIntValue stores a integer value in a save game.
      * @param offset starting address (in decimal) of value to save
      * @param length size (number of bytes) being stored
      * @param value decimal number to be stored at offset X (offset*length)
      * @param byteOrder the byte order as defined in this class
      */
     public void setIntValue(int offset,int length,int value,int byteOrder){
          int q;
          int w;
          int currentByte=0;
          try{
               if(this.saveGame!=null){
                    for(q=0;q<length;q++){
                         currentByte=((value>>(q*this.BYTE_LENGTH))%(this.MAX_UNSIGNED_BYTE));
                         if(byteOrder==this.LOW_BYTE_FIRST){
                              this.saveGame.seek(offset+q);
                         } else if(byteOrder==this.HIGH_BYTE_FIRST){
                              this.saveGame.seek(offset+(length-q-1));
                         } else{
                              throw new Exception("invalid byte order");
                         }
                         this.saveGame.write(currentByte);
                    }
               }
          } catch(Exception x){
               x.printStackTrace();
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
