/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
AsciiViewPane.java - pane for ascii view
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

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import ViewPane;

/** class AsciiViewPane
* @author Hugues Johnson
*/
public class AsciiViewPane extends ViewPane implements ListSelectionListener{

     /** constructor for AsciiViewPane, creates an empty scrolling list
     */
     public AsciiViewPane(){
          super();
     }
     
     /** addLine converts a byte array to a string and appends it to the end of the list
     * @param line the byte array to be appended
     */
     public void addLine(byte[] line){
          String asciiLine=new String();
          for(int index=0;index<line.length;index++){
               asciiLine+=" "+(char)line[index];
          }
          this.addLine(asciiLine);
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