/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
BinaryViewPane.java - pane for binary view
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.lang.Integer;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import ViewPane;

/** class BinaryViewPane
* @author Hugues Johnson
*/
public class BinaryViewPane extends ViewPane implements ListSelectionListener{
     
     /** constructor for BinaryViewPane, creates an empty scrolling list
     */
     public BinaryViewPane(){
          super();
     }
     
     /** addLine converts a byte array to a string and appends it to the end of the list
     * byte values range from 0-255 (00000000000000000000000000000000-11111111111111111111111111111111) -> the string is padded to fit 32 spaces
     * @param line the byte array to be appended
     */
     public void addLine(byte[] line){
          String binaryLine=new String();
          String binaryValue;
          for(int index=0;index<line.length;index++){
               binaryValue=Integer.toBinaryString((int)line[index]);
               int length=binaryValue.length();
               if(length<32){
                    for(int i=0;i<(32-length);i++){
                         binaryValue="0"+binaryValue;
                    }
               }
               binaryLine+=" "+binaryValue;
          }
          this.addLine(binaryLine);
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
     public void valueChanged(final ListSelectionEvent e){
     }
}
