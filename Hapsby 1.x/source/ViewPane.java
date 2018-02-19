/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
ViewPane.java - base class for view panes
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
