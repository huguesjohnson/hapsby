/*
Hapsby - universal save game editor
HapsbyToolbar.java - toolbar for Hapsby UI
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

import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
/* imports for JFC 1.0.1
import com.sun.java.swing.JToolBar;
import com.sun.java.swing.JButton;
import com.sun.java.swing.ImageIcon;
*/
import HapsbyActionCommands;

/** Class HapsbyToolbar creates a toolbar with access to valid Hapsby commands.
* @author Hugues Johnson
*/
public class HapsbyToolbar extends JToolBar implements ActionListener{
     private String resourcePath;
     
     /** Constructor creates the toolbar, adds images, and binds actionCommands.
     */
     public HapsbyToolbar(){
          /* set the resource path
          under some IDEs (such as Forte and Visual Cafe) the next line will not work because System.getProperty("user.dir") returns a path to the IDE and not to the working folder */
          String defaultResourcePath=new String(System.getProperty("user.dir")+File.separator+"themes"+File.separator+"Default"+File.separator);
          this.setResourcePath(defaultResourcePath);
          this.addButtons();
     }

     /**  creates the toolbar, adds images, and binds actionCommands.
     */
     public HapsbyToolbar(String resourcePath){
          this.setResourcePath(resourcePath);
          this.addButtons();
     }
     
     /* adds all buttons and their images.
     */
     private void addButtons(){
          JButton button;
          button=new JButton(new ImageIcon(this.resourcePath+"open.gif"));
          button.setActionCommand(HapsbyActionCommands.ACTION_OPEN);
          button.setToolTipText("Open Save Game");
          button.addActionListener(this);
          this.add(button);
          
          button=new JButton(new ImageIcon(this.resourcePath+"save.gif"));
          button.setActionCommand(HapsbyActionCommands.ACTION_SAVE);
          button.setToolTipText("Save Property");
          button.addActionListener(this);
          this.add(button);

          button=new JButton(new ImageIcon(this.resourcePath+"restore_backup.gif"));
          button.setActionCommand(HapsbyActionCommands.ACTION_RESTORE_BACKUP);
          button.setToolTipText("Restore Backup");
          button.addActionListener(this);
          this.add(button);          
     }
     
     /** setResourcePath sets the path to the resource (image) directory
     * @param resourcePath a path to the resource (image) directory 
     */
     public void setResourcePath(String resourcePath){
          /* test if resource path is a valid directory */
          File temp=new File(resourcePath);
          if(temp.isDirectory()){
               this.resourcePath=resourcePath;
          }
     }     

     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
     
     /** actionPerformed meant to be overidden by parent window.
     * @param e ActionEvent received
     */
     public void actionPerformed(ActionEvent e){
     }
}