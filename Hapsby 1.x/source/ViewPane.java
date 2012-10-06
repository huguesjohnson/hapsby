/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
ViewPane.java - base class for view panes
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

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/** class ViewPane
* @author Hugues Johnson
*/
public class ViewPane extends JPanel{
     private DefaultListModel listModel;
     private JList list;
     
     /** constructor for ViewPane
     */
     public ViewPane(){
          super();
          this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
          this.listModel=new DefaultListModel();
          this.list=new JList(this.listModel);
          JScrollPane scrollPane=new JScrollPane(this.list);
          this.add(scrollPane);
     }

     /** addLine
     * @param line
     */
     public void addLine(String line){
          this.listModel.addElement(line);
     }

     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
}