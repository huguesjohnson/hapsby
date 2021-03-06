/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
OctalViewPane.java - pane for octal view
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.lang.Integer;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import ViewPane;

/** class OctalViewPane
* @author Hugues Johnson
*/
public class OctalViewPane extends ViewPane implements ListSelectionListener{
     
     /** constructor for OctalViewPane, creates an empty scrolling list
     */
     public OctalViewPane(){
          super();
     }
     
     /** addLine converts a byte array to a string and appends it to the end of the list
     * @param line the byte array to be appended
     */
     public void addLine(byte[] line){
          String octalLine=new String();
          String octalValue;
          for(int index=0;index<line.length;index++){
               octalValue=Integer.toOctalString((int)line[index]);
               if(octalValue.length()==1){
                    octalValue="00"+octalValue;
               } else if (octalValue.length()==2){
                    octalValue="0"+octalValue;
               } else if(octalValue.length()>3){
                    octalValue=octalValue.substring(octalValue.length()-3,octalValue.length());
               }
               octalLine+=" "+octalValue.toUpperCase();
          }
          this.addLine(octalLine);
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
