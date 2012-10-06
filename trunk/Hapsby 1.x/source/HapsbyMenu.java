/*
Hapsby - universal save game editor
HapsbyMenu.java - Menu system for Hapsby
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
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
/* imports for JFC 1.0.1
import com.sun.java.swing.ImageIcon;
import com.sun.java.swing.JMenuBar;
import com.sun.java.swing.JMenu;
import com.sun.java.swing.JMenuItem;
*/
import DirectoryFilter;
import HapsbyActionCommands;

/** Class HapsbyMenu creates a JMenuBar that provides access to valid Hapsby commands.
* @author Hugues Johnson
*/
public class HapsbyMenu extends JMenuBar implements ActionListener{
     private String resourcePath;

     /** Default constructor creates the toolbar, draws images, and binds actionCommands.
     */
     public HapsbyMenu(){
          /* set the resource path
          under some IDEs (such as Forte and Visual Cafe) the next line will not work because System.getProperty("user.dir") returns a path to the IDE and not to the working folder */
          String defaultResourcePath=new String(System.getProperty("user.dir")+File.separator+"themes"+File.separator+"Default"+File.separator);
          this.setResourcePath(defaultResourcePath);          
          this.createMenu();
     }
     
     
     /** Constructor creates the toolbar, draws images, binds actionCommands, and sets the resource path.
     * @param resourcePath path to folder containing images for menu
     */
     public HapsbyMenu(String resourcePath){
          this.setResourcePath(resourcePath);
          this.createMenu();
     }
     
     /* creates the menu
     */
     private void createMenu(){
          this.addFileMenu();
          this.addSettingsMenu();
          this.addToolsMenu();
          this.addHelpMenu();
     }
     
     private void addFileMenu(){
          JMenu menu;
          JMenuItem menuItem;
          
          menu=new JMenu("File");
          menu.setMnemonic(KeyEvent.VK_F);
          this.add(menu);
          menuItem=new JMenuItem("Open Save Game..",new ImageIcon(resourcePath+"open.gif"));
          menuItem.setMnemonic(KeyEvent.VK_O);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_OPEN);
          menuItem.addActionListener(this);
          menu.add(menuItem);
          menuItem=new JMenuItem("Save Property",new ImageIcon(resourcePath+"save.gif"));
          menuItem.setMnemonic(KeyEvent.VK_S);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_SAVE);
          menuItem.addActionListener(this);
          menu.add(menuItem);
          menu.addSeparator();
          menuItem=new JMenuItem("Exit", new ImageIcon(resourcePath+"exit.gif"));
          menuItem.setMnemonic(KeyEvent.VK_X);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_EXIT);
          menuItem.addActionListener(this);
          menu.add(menuItem);
     }
     
     private void addSettingsMenu(){
          JMenu menu;
          JMenu subMenu;
          JMenuItem menuItem;
          
          menu=new JMenu("Settings");
          menu.setMnemonic(KeyEvent.VK_S);
          this.add(menu);
          subMenu=new JMenu("Window Style");
          subMenu.setMnemonic(KeyEvent.VK_W);
          menu.add(subMenu);
          menuItem=new JMenuItem("Java",new ImageIcon(resourcePath+"style_java.gif"));
          menuItem.setMnemonic(KeyEvent.VK_J);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_STYLE_JAVA);
          menuItem.addActionListener(this);
          subMenu.add(menuItem);
          menuItem=new JMenuItem("System",new ImageIcon(resourcePath+"style_system.gif"));
          menuItem.setMnemonic(KeyEvent.VK_S);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_STYLE_SYSTEM);
          menuItem.addActionListener(this);
          subMenu.add(menuItem);
          /* add themes under settings menu */
          this.loadThemes(menu);
          menu.addSeparator();
          /* begin autobackup subMenu */
          subMenu=new JMenu("Auto-Backup");
          subMenu.setMnemonic(KeyEvent.VK_B);
          menu.add(subMenu);
          menuItem=new JMenuItem("On",new ImageIcon(resourcePath+"on.gif"));
          menuItem.setMnemonic(KeyEvent.VK_O);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_AUTOBACKUP_ON);
          menuItem.addActionListener(this);
          subMenu.add(menuItem);
          menuItem=new JMenuItem("Off",new ImageIcon(resourcePath+"off.gif"));
          menuItem.setMnemonic(KeyEvent.VK_F);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_AUTOBACKUP_OFF);
          menuItem.addActionListener(this);
          subMenu.add(menuItem);
          menuItem=new JMenuItem("Prompt",new ImageIcon(resourcePath+"prompt.gif"));
          menuItem.setMnemonic(KeyEvent.VK_P);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_AUTOBACKUP_PROMPT);
          menuItem.addActionListener(this);
          subMenu.add(menuItem);
          /* end autobackup subMenu */
          /* begin sort property list menu */
          subMenu=new JMenu("Sort Properties");
          subMenu.setMnemonic(KeyEvent.VK_P);
          menu.add(subMenu);
          menuItem=new JMenuItem("On",new ImageIcon(resourcePath+"on.gif"));
          menuItem.setMnemonic(KeyEvent.VK_O);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_SORT_PROPERTIES_ON);
          menuItem.addActionListener(this);
          subMenu.add(menuItem);
          menuItem=new JMenuItem("Off",new ImageIcon(resourcePath+"off.gif"));
          menuItem.setMnemonic(KeyEvent.VK_F);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_SORT_PROPERTIES_OFF);
          menuItem.addActionListener(this);
          subMenu.add(menuItem);
          /* end sort property list menu */
          /* begin sort game def list menu */
          subMenu=new JMenu("Sort Game List");
          subMenu.setMnemonic(KeyEvent.VK_G);
          menu.add(subMenu);
          menuItem=new JMenuItem("On",new ImageIcon(resourcePath+"on.gif"));
          menuItem.setMnemonic(KeyEvent.VK_O);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_SORT_GAMES_ON);
          menuItem.addActionListener(this);
          subMenu.add(menuItem);
          menuItem=new JMenuItem("Off",new ImageIcon(resourcePath+"off.gif"));
          menuItem.setMnemonic(KeyEvent.VK_F);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_SORT_GAMES_OFF);
          menuItem.addActionListener(this);
          subMenu.add(menuItem);          
          /* end sort game def list menu */
     }
     
     private void addToolsMenu(){
          JMenu menu;
          JMenuItem menuItem;
          menu=new JMenu("Tools");
          menu.setMnemonic(KeyEvent.VK_T);
          this.add(menu);
          menuItem=new JMenuItem("Restore Backup..",new ImageIcon(resourcePath+"restore_backup.gif"));
          menuItem.setMnemonic(KeyEvent.VK_R);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_RESTORE_BACKUP);
          menuItem.addActionListener(this);
          menu.add(menuItem);
          menuItem=new JMenuItem("Hex\\Oct\\Bin Viewer..");
          menuItem.setMnemonic(KeyEvent.VK_V);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_SHOW_VIEWER);
          menuItem.addActionListener(this);
          menu.add(menuItem);
          menuItem=new JMenuItem("Number Converter..");
          menuItem.setMnemonic(KeyEvent.VK_N);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_SHOW_NUMBERCONVERTER);
          menuItem.addActionListener(this);
          menu.add(menuItem);
     }
     
     private void addHelpMenu(){
          JMenu menu;
          JMenuItem menuItem;
          menu=new JMenu("Help");
          menu.setMnemonic(KeyEvent.VK_H);
          this.add(menu);
          menuItem=new JMenuItem("About..",new ImageIcon(resourcePath+"about.gif"));
          menuItem.setMnemonic(KeyEvent.VK_A);
          menuItem.setActionCommand(HapsbyActionCommands.ACTION_ABOUT);
          menuItem.addActionListener(this);
          menu.add(menuItem);
     }
     
     /* loadThemes opens up the working directory, loads available themes, and adds them under parent menu
     * @param parentMenu the menu to add the themes to
     */
     private void loadThemes(JMenu parentMenu){
          String imageName;
          JMenuItem menuItem;
          JMenu themeMenu=new JMenu("Theme");
          themeMenu.setMnemonic(KeyEvent.VK_T);
          parentMenu.add(themeMenu);
          String themeList[]={""};
          try{
               /* under some IDEs (such as Forte and Visual Cafe) the next line will not work because System.getProperty("user.dir") returns a path to the IDE and not to the working folder */
               String themePath=new String(System.getProperty("user.dir")+File.separator+"themes"+File.separator);
               File f=new File(themePath);
               themeList=new String[f.list().length];
               themeList=f.list(new DirectoryFilter());
               for(int q=0;q<themeList.length;q++){
                    imageName=themePath+themeList[q]+File.separator+"theme.gif";
                    /* replace underscores with blanks when adding to menu */
                    menuItem=new JMenuItem(themeList[q].replace('_',' '),new ImageIcon(imageName));
                    menuItem.setActionCommand(HapsbyActionCommands.ACTION_THEME+" "+themeList[q]);
                    menuItem.addActionListener(this);
                    themeMenu.add(menuItem);
               }
          } catch(Exception x){
               x.printStackTrace();
          }
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
     
     /** actionPerformed empty method meant to be overidden by parent window.
     * @param e ActionEvent received
     */
     public void actionPerformed(final java.awt.event.ActionEvent e){
     }
}