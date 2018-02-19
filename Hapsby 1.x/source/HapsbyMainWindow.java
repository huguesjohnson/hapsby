/*
Hapsby - universal save game editor
HapsbyMainWindow.java - main UI for Hapsby
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.io.File;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.lang.Integer;
import java.lang.String;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
/* imports for JFC 1.0.1
please note: com.sun.java.swing.JProgressBar is missing much of the functionality found in javax.swing.JProgressBar
import com.sun.java.swing.JFrame;
import com.sun.java.swing.JProgressBar;
import com.sun.java.swing.JSplitPane;
import com.sun.java.swing.JOptionPane;
import com.sun.java.swing.UIManager;
import com.sun.java.swing.LookAndFeel;
import com.sun.java.swing.SwingUtilities;
*/
import GameListPanel;
import FilePanel;
import IniFile;
import HapsbyMenu;
import HapsbyToolbar;
import HapsbyActionCommands;

/** Class Hapsby, user interface for Hapsby program
* @author Hugues Johnson
* @TODO (high priority) create a main window class and use Hapsby.class only to link main window to core routines
*/
public class HapsbyMainWindow extends JFrame{
     private GameListPanel leftPane;
     private FilePanel rightPane;
     private JProgressBar progress;
     private JSplitPane splitPane;
     private HapsbyMenu menu;
     private HapsbyToolbar toolbar;
     private String resourcePath;
     private String defaultResourcePath;
     private String settingsPath;
     private String currentThemeName;
     private String currentLookAndFeel;
          
     /** Default constructor for HapsbyMainWindow
     * Creates a JFrame to hold all UI components of the program.
     * Creates a JSplitPane to the two panels.
     * Overrides default ActionListeners to the one in this class.
     */
     public HapsbyMainWindow(String caption){
          /* invoke constructor for JFrame */
          super(caption);
          /* set the paths
          under some IDEs (such as Forte and Visual Cafe) the next line will not work because System.getProperty("user.dir") returns a path to the IDE and not to the working folder */
          this.settingsPath=new String(System.getProperty("user.dir")+File.separator+"settings");
          this.defaultResourcePath=new String(System.getProperty("user.dir")+File.separator+"themes"+File.separator+"default"+File.separator);
          this.setResourcePath(this.defaultResourcePath);
          this.addComponents();
          this.applySavedSettings();
     }
     
     /* addComponents() creates and add components to the window
     */
     private void addComponents(){
          /* create content pane */
          Container contentPane=this.getContentPane();
          /* initialize panels & override actionPerformed event */
          this.leftPane=new GameListPanel(){ public void actionPerformed(final ActionEvent e){ actionListener(e);}};
          this.rightPane=new FilePanel(this.resourcePath){ public void actionPerformed(final ActionEvent e){ actionListener(e);}};
          /* add menu */
          this.menu=new HapsbyMenu(this.resourcePath){ public void actionPerformed(final ActionEvent e){ actionListener(e);}};
          this.setJMenuBar(this.menu);
          /* add toolbar */
          this.toolbar=new HapsbyToolbar(this.resourcePath){ public void actionPerformed(final ActionEvent e){ actionListener(e);}};
          contentPane.add(this.toolbar,BorderLayout.NORTH);
          /* add status bar */
          this.progress=new JProgressBar();
          this.progress.setStringPainted(true);
          contentPane.add(this.progress,BorderLayout.SOUTH);
          /* create split pane to hold components */
          this.splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.leftPane,this.rightPane);
          this.splitPane.setOneTouchExpandable(true);
          /* size components */
          /** @TODO store size settings externally */
          this.splitPane.setDividerLocation(200);
          this.leftPane.setMinimumSize(new Dimension(200,200));
          this.rightPane.setMinimumSize(new Dimension(400,200));
          /* add split pane */
          contentPane.add(this.splitPane);
          /* add listener for close event */
          addWindowListener(new WindowAdapter(){ public void windowClosing(WindowEvent e){closeWindow();}});
     }
     
     /* setLookAndFeel changes the style of the window at run time
     * @param newLookAndFeel class name of new look & feel
     */
     public void setLookAndFeel(String newLookAndFeel){
          if(!newLookAndFeel.equals(currentLookAndFeel)){
               try{
                    UIManager.setLookAndFeel(newLookAndFeel);
                    SwingUtilities.updateComponentTreeUI(this);
                    this.currentLookAndFeel=new String(newLookAndFeel);
               } catch (Exception x){
                    x.printStackTrace();
               }
          }
     }
     
     /** refreshes the file panel (rightPane) based on a save game property
     * @param caption new caption for panel
     * @param gameDescription new game description
     * @param propertyDescription new property description
     * @param address new address
     * @param length new length (number of bytes)
     * @param maxValue new maximum value
     * @param minValue new minimum value
     * @param currentValue new current value
     */
     public void setCurrentProperty(String caption,String gameDescription,String propertyDescription,String address,String length,String maxValue,String minValue,String currentValue){
          if(caption!=null){this.rightPane.setCaption(caption);}
          if(gameDescription!=null){this.rightPane.setNameDesc(gameDescription);}
          if(propertyDescription!=null){this.rightPane.setPropertyDesc(propertyDescription);}
          if(address!=null){this.rightPane.setAddress(address);}
          if(length!=null){this.rightPane.setLength(length);}
          if(minValue!=null){this.rightPane.setMinValue(minValue);}
          if(maxValue!=null){this.rightPane.setMaxValue(maxValue);}
          if(currentValue!=null){this.rightPane.setCurrentValue(currentValue);}
     }

     /** getSelectedGame retrieves the name of the selected save game defintion
     * @return name of selected save game definition from game list (left pane)
     */
     public String getSelectedGame(){
          return(this.leftPane.getSelectedItem());
     }

     /** getSelectedProperty retrieves the name of the selected save game property
     * @return name of selected save game property (right pane)
     */
     public String getSelectedProperty(){
          return(this.rightPane.getSelectedProperty());
     }
     
     /** setGameList populates the save game definition panel
     * @param gameList array of games to go into the game list (left pane)
     */
     public void setGameList(String[] gameList){
          this.leftPane.createList(gameList);
     }

     /** setPropertyList populates the property list
     * @param setPropertyList array of properties to go into the property list (right pane)
     */
     public void setPropertyList(String[] propertyList){
          this.rightPane.setPropertyList(propertyList);
     }

     /** getCurrentPropertyValue retrieves the value of the current save game property
     * @return value of the current property
     */
     public String getCurrentPropertyValue(){
          return(this.rightPane.getCurrentValue());
     }

     /** getCurrentPropertyName retrieves the name of the selected property
     * @return name of selected property
     */
     public String getCurrentPropertyName(){
          return(this.rightPane.getSelectedProperty());
     }

     /** actionListener override this routine when creating a new HapsbyMainWindow
     * @param e ActionEvent received by UI
     */
     public void actionListener(final ActionEvent e){
          /* override this */
     }

     /** closeWindow override this routine when creating a new HapsbyMainWindow
     */
     public void closeWindow(){
          /* override this */
     }
     
     /** setResourcePath sets the path to the resource (image) directory
     * @param resourcePath a path to the resource (image) directory 
     */
     private void setResourcePath(String resourcePath){
          /* test if resource path is a valid directory */
          File temp=new File(resourcePath);
          if(temp.isDirectory()){
               this.resourcePath=resourcePath;
          }
     }
     
     /** changeTheme() changes the look of the window to the specified theme.
     * @param newTheme name of new theme
     */
     public void changeTheme(String newTheme){
          /* replace spaces with underscores */
          newTheme=newTheme.replace(' ','_');
          /* test if new theme is the same as the current theme */
          if(!(this.currentThemeName.equals(newTheme))){
               this.currentThemeName=newTheme;
               /* set the resource path
               under some IDEs (such as Forte and Visual Cafe) the next line will not work because System.getProperty("user.dir") returns a path to the IDE and not to the working folder */
               String resourcePath=new String(System.getProperty("user.dir")+File.separator+"themes"+File.separator+newTheme+File.separator);
               this.setResourcePath(resourcePath);
               this.removeComponents();
               this.addComponents();
          }
     }
     
     /** getResourcePath retrieves the path to the resource directory
     * @return path to the resource directory
     */
     public String getResourcePath(){
          return(this.resourcePath);
     }
     
     /* removes all components from the window - needed to change theme
     */
     private void removeComponents(){
          this.remove(this.toolbar);
          this.remove(this.menu);
          this.remove(this.rightPane);
          this.remove(this.leftPane);
          this.remove(this.progress);
          this.remove(this.splitPane);
     }
     
     /** setProgress
      * @param message
      * @param percent
      */
     public void setProgress(String message,int percent){
         if(this.progress!=null){
            this.progress.setString(message);
            this.progress.setValue(percent);
         }
     }
     
     /** centers the window in the middle of the screen
     */     
     public void centerWindow(){
          Dimension screenDimension=Toolkit.getDefaultToolkit().getScreenSize();
          Rectangle windowDimension=this.getBounds();
          this.setLocation((screenDimension.width-windowDimension.width)/2,(screenDimension.height-windowDimension.height)/2);
     }
     
     /** applySavedSettings, loads and applies saved settings
     */
     private void applySavedSettings(){
          IniFile iniFile=new IniFile(this.settingsPath+File.separator+"HapsbyMainWindow.ini");
          if(iniFile!=null){
               iniFile.read();
               String property=new String();
               final String defaultString=new String("__dEfAulT__");
               /* set current theme to default or changeTheme throws an exception */
               this.currentThemeName=new String("default");
               property=iniFile.getProperty("currentThemeName",defaultString);
               if(!property.equals(defaultString)){
                    this.changeTheme(property);
               }
               /* set current look & feel to default or setLookAndFeel throws an exception */
               this.currentLookAndFeel=UIManager.getCrossPlatformLookAndFeelClassName();
               property=iniFile.getProperty("currentLookAndFeel",defaultString);
               if(!property.equals(defaultString)){
                    this.setLookAndFeel(property);
               }
               property=iniFile.getProperty("x",defaultString);
               if(!property.equals(defaultString)){
                    try{
                         int xPos=Integer.valueOf(property).intValue();
                         if(xPos>=0){
                              /* x value is OK, get a y value */
                              property=iniFile.getProperty("y",defaultString);
                              if(!property.equals(defaultString)){
                                   int yPos=Integer.valueOf(property).intValue();
                                   if(yPos>=0){
                                        /* both values are OK, move the window */
                                        this.setLocation(xPos,yPos);
                                   }
                              }
                         }
                    } catch(Exception x){
                         x.printStackTrace();
                         /* continue execution */
                    }
               }                   
               iniFile.clear();
               iniFile=null;
          }
     }
     
    /** saveSettings, save program settings to ini file
     */
     public void saveSettings(){
          IniFile iniFile=new IniFile(this.settingsPath+File.separator+"HapsbyMainWindow.ini");
          if(iniFile!=null){
               iniFile.setProperty("currentThemeName",this.currentThemeName);
               iniFile.setProperty("currentLookAndFeel",this.currentLookAndFeel);
               iniFile.setProperty("x",this.getX());
               iniFile.setProperty("y",this.getY());
               iniFile.save("HapsbyMainWindow");
               iniFile=null;
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
