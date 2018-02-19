/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
HexViewPane.java - pane for hexadecimal view
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.lang.Integer;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import ViewPane;

/** class HexViewPane
* @author Hugues Johnson
*/
public class HexViewPane extends ViewPane implements ListSelectionListener{

     /** constructor for HexViewPane, creates an empty scrolling list
     */
     public HexViewPane(){
          super();
     }

     /** addLine converts a byte array to a string and appends it to the end of the list
     * @param line the byte array to be appended
     */
     public void addLine(byte[] line){
          String hexLine=new String();
          String hexValue;
          for(int index=0;index<line.length;index++){
               hexValue=Integer.toHexString((int)line[index]);
               if(hexValue.length()==1){
                    hexValue="0"+hexValue;
               } else if(hexValue.length()>2){
                    hexValue=hexValue.substring(hexValue.length()-2,hexValue.length());
               }
               hexLine+=" "+hexValue.toUpperCase();
          }
          this.addLine(hexLine);
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
