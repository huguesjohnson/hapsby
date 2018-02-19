/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
FileViewerMenu.java - menu for file viewer window
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import FileViewerActionCommands;

public class FileViewerMenu extends JMenuBar implements ActionListener{
     public FileViewerMenu(){
          this.addFileMenu();
     }
     
     private final void addFileMenu(){
          JMenu menu;
          JMenuItem menuItem;
          
          menu=new JMenu("File");
          menu.setMnemonic(KeyEvent.VK_F);
          this.add(menu);
          menuItem=new JMenuItem("Open File..");
          menuItem.setMnemonic(KeyEvent.VK_O);
          menuItem.setActionCommand(FileViewerActionCommands.CMD_OPEN);
          menuItem.addActionListener(this);
          menu.add(menuItem);
          menu.addSeparator();
          menuItem=new JMenuItem("Exit");
          menuItem.setMnemonic(KeyEvent.VK_X);
          menuItem.setActionCommand(FileViewerActionCommands.CMD_EXIT);
          menuItem.addActionListener(this);
          menu.add(menuItem);
     }
     
     private final void addHelpMenu(){
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
     
     /** actionPerformed empty method meant to be overidden by parent window.
     * @param e ActionEvent received
     */
     public void actionPerformed(final ActionEvent e){
     }
}
