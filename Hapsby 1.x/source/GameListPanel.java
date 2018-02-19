/*
Hapsby - universal save game editor
GameListPanel.java - UI for game list
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/* imports for JFC 1.0.1
import com.sun.java.swing.JPanel;
import com.sun.java.swing.JList;
import com.sun.java.swing.JButton;
import com.sun.java.swing.DefaultListModel;
import com.sun.java.swing.JScrollPane;
import com.sun.java.swing.ListSelectionModel;
import com.sun.java.swing.BorderFactory;
import com.sun.java.swing.BoxLayout;
import com.sun.java.swing.event.ListSelectionEvent;
import com.sun.java.swing.event.ListSelectionListener;
*/

/** Class GameListPanel, game list interface for Hapsby program
* @author Hugues Johnson
*/
public class GameListPanel extends JPanel implements ListSelectionListener,ActionListener{
     /* begin captions for controls */
     private final static String PANEL_CAPTION="Supported Saves";
     private final static String SAVEBUTTON_LABEL="Open Save Game";
     /* end captions for controls */
     
     /* begin shared objects */
     private JList gameList;
     private DefaultListModel gameListModel;
     /* end shared objects */
     
     /** Default constructor for GameList Panel class.
     * Creates a scrolling list to hold game list, although it could be used for about anything.
     */
     public GameListPanel(){
          /* create border and layout */
          this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),PANEL_CAPTION));
          this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
          /* initialize game list */
          this.gameListModel=new DefaultListModel();
          this.gameList=new JList(gameListModel);
          this.gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.gameList.addListSelectionListener(this);
          /* create scroll pane to hold list */
          JScrollPane listScrollPane=new JScrollPane(gameList);
          this.add(listScrollPane);
     }
     
     /** actionPerformed - empty method. Meant to be overidden by parent window.
     * @param e ActionEvent received in panel 
     */
     public void actionPerformed(final ActionEvent e){
     }
     
     /** valueChanged receives messages from JList. Meant to be overiddeb by parent window.
     * @param e ListSelection message sent to JList
     */
     public void valueChanged(final ListSelectionEvent e){
     }
     
     /** getSelctedItem allows access to the selected item in the save game list.
     * @return text of selected item
     */
     public String getSelectedItem(){
          /* get index of selected item */
          int index=this.gameList.getSelectedIndex();
          /* use index to get String value of selection from gameListModel */
          if(index>=0){
               return((String)this.gameListModel.elementAt(index));
          } else{
               return(null);
          }
     }
          
     /** createList clears gameList and creates a new one based on newList.
     * @param newList newList to be added to gameList.
     */
     public void createList(String[] newList){
          /* clear existing list */
          this.gameListModel.clear();
          /* add items to list */
          for(int q=0;q<newList.length;q++){
               this.gameListModel.addElement(newList[q]);
          }
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }
}
