/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
BinaryViewPane.java - pane for binary view
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