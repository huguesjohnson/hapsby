/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
DecimalViewPane.java - pane for decimal view
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