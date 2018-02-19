/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
DecimalViewPane.java - pane for decimal view
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.lang.Integer;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import ViewPane;

/** class DecimalViewPane
* @author Hugues Johnson
*/
public class DecimalViewPane extends ViewPane implements ListSelectionListener{

     /** constructor for DecimalViewPane, creates an empty scrolling list
     */
     public DecimalViewPane(){
          super();
     }

     /** addLine converts a byte array to a string and appends it to the end of the list
     * byte values range from 0-255 -> the string is padded to fit 3 spaces before being added to the list
     * @param line the byte array to be appended
     */
     public void addLine(byte[] line){
          String decimalLine=new String();
          String decimalValue;
          for(int index=0;index<line.length;index++){
               /* convert the byte to int then and with 0xff because byte values > 127 become negative when converted to int */
               decimalValue=Integer.toString((int)line[index]&0xff);
               /* pad the string if needed */
               if(decimalValue.length()==1){
                    decimalValue="00"+decimalValue;
               } else if (decimalValue.length()==2){
                    decimalValue="0"+decimalValue;
               } else if(decimalValue.length()>3){
                    decimalValue=decimalValue.substring(decimalValue.length()-3,decimalValue.length());
               }
               decimalLine+=" "+decimalValue;
          }
          /* append the line using the super class addLine method */
          this.addLine(decimalLine);
     }

     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }
     
     /** valueChanged receives ListSelectionEvents, should be overridden by derived classes 
     *@param e the event received
     */
     public void valueChanged(ListSelectionEvent e){
     }
}
