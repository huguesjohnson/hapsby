/*
NumberConverter - simple number format converter
NumberConverter.java - main program
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
