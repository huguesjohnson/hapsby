/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
HexViewPane.java - pane for hexadecimal view
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