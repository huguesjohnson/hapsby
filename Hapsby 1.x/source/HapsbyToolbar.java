/*
Hapsby - universal save game editor
HapsbyToolbar.java - toolbar for Hapsby UI
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
