/*
Hapsby - universal save game editor
FileViewer - simple multi-format file viewer
FileViewerMenu.java - menu for file viewer window
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