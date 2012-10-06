/*
NumberConverter - simple number format converter
NumberConverter.java - main program
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

import java.lang.Byte;
import java.lang.Long;
import java.lang.Math;
import java.lang.NumberFormatException;
import java.lang.String;
import java.util.StringTokenizer;

/** class NumberConversions, convert to and from decimal formats
* @author Hugues Johnson
*/
public abstract class NumberConversions{
     
     /** DecimalStringToAsciiString
     * @param decimalString
     * @return asciiString
     */
     public static String decimalStringToAsciiString(String decimalString){
          String asciiString=new String("");
          StringTokenizer tokenizer=new StringTokenizer(decimalString);
          while(tokenizer.hasMoreTokens()){
               try{     
                    byte b=Byte.valueOf(tokenizer.nextToken()).byteValue();
                    asciiString+=(char)b+" ";
               } catch(NumberFormatException nfe){
                    asciiString+=" ";
               }   
          }
          return(asciiString);
     }     

     /** decimalStringToHexString
     * @param decimalString
     * @return hexString
     */
     public static String decimalStringToHexString(String decimalString){
          String hexString=new String("");
          StringTokenizer tokenizer=new StringTokenizer(decimalString);
          while(tokenizer.hasMoreTokens()){
               try{
                    Long l=new Long(tokenizer.nextToken());
                    hexString+=Long.toHexString(l.longValue())+" ";
               } catch(NumberFormatException nfe){
                    hexString+=" ";
               }
          }
          return(hexString);
     }
     
     /** decimalStringToOctalString
     * @param decimalString
     * @return octalString
     */
     public static String decimalStringToOctalString(String decimalString){
          String octalString=new String("");
          StringTokenizer tokenizer=new StringTokenizer(decimalString);
          while(tokenizer.hasMoreTokens()){
               try{
                    Long l=new Long(tokenizer.nextToken());
                    octalString+=Long.toOctalString(l.longValue())+" ";
               } catch(NumberFormatException nfe){
                    octalString+=" ";
               }
          }
          return(octalString);
     }     
     
     /** decimalStringToBinaryString
     * @param decimalString
     * @return binaryString
     */
     public static String decimalStringToBinaryString(String decimalString){
          String binaryString=new String("");
          StringTokenizer tokenizer=new StringTokenizer(decimalString);
          while(tokenizer.hasMoreTokens()){
               try{
                    Long l=new Long(tokenizer.nextToken());
                    binaryString+=Long.toBinaryString(l.longValue())+" ";
               } catch(NumberFormatException nfe){
                    binaryString+=" ";
               }
          }
          return(binaryString);
     }
     
     /** asciiStringToDecimalString
     * @param asciiString
     * @return decimalString
     */
     public static String asciiStringToDecimalString(String asciiString){
          String decimalString=new String("");
          byte[] b=asciiString.getBytes();
          for(int index=0;index<b.length;index++){
               decimalString+=b[index]+" ";
          }
          return(decimalString);
     }
     
     /** hexStringToDecimalString
     * @param hexString
     * @return decimalString
     */
     public static String hexStringToDecimalString(String hexString){
          String decimalString=new String("");
          StringTokenizer tokenizer=new StringTokenizer(hexString);
          while(tokenizer.hasMoreTokens()){
               try{
                    /* this next line looks confusing, but here's what going on:
                    1) add "0x" to the current token
                    2) use the Long decode method to convert the string to a long decimal
                    3) convert the long value to a String
                    4) append a blank space to the end
                    */
                    decimalString+=Long.toString(Long.decode("0x"+tokenizer.nextToken()).longValue())+" ";
               } catch(NumberFormatException nfe){
                    decimalString+=" ";
               }
          }
          return(decimalString);
     }
     
     /** octalStringToDecimalString
     * @param octalString
     * @return decimalString
     */
     public static String octalStringToDecimalString(String octalString){
          String decimalString=new String("");
          StringTokenizer tokenizer=new StringTokenizer(octalString);
          while(tokenizer.hasMoreTokens()){
               char ch[]=tokenizer.nextToken().toCharArray();
               long decimalValue=0;
               for(int index=0;index<ch.length;index++){
                    long n;
                    try{
                         n=Long.valueOf(String.valueOf(ch[index])).longValue();
                         if(n>8L||n<0L){
                              throw(new NumberFormatException());
                         }
                    } catch(NumberFormatException nfe){
                         n=0L;
                    }
                    decimalValue+=n*(Math.pow(8.0D,(double)(ch.length-index-1)));
               }
               decimalString+=Long.toString(decimalValue)+" ";
          }
          return(decimalString);
     }
     
     /** hexStringToDecimalString
     * @param hexString
     * @return decimalString
     */
     public static String binaryStringToDecimalString(String binaryString){
          String decimalString=new String("");
          StringTokenizer tokenizer=new StringTokenizer(binaryString);
          while(tokenizer.hasMoreTokens()){
               char ch[]=tokenizer.nextToken().toCharArray();
               long decimalValue=0;
               for(int index=0;index<ch.length;index++){
                    long n;
                    try{
                         n=Long.valueOf(String.valueOf(ch[index])).longValue();
                         if(n>1L||n<0L){
                              throw(new NumberFormatException());
                         } else{
                              decimalValue+=n*(Math.pow(2.0D,(double)(ch.length-index-1)));
                         }
                    } catch(NumberFormatException nfe){
                         decimalString+=" ";
                    }
               }
               decimalString+=Long.toString(decimalValue)+" ";
          }
          return(decimalString);
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
}