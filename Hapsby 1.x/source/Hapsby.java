/*
Hapsby - universal save game editor
Hapsby.java - entry point for program
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
import java.awt.Cursor;
import javax.swing.UIManager;
/* imports for JFC 1.0.1 
import com.sun.java.swing.UIManager;
*/
import HapsbyApp;
import HapsbyActionCommands;
import HapsbyMainWindow;

/** Class Hapsby, user interface for Hapsby program
* @author Hugues Johnson
*/
final class Hapsby{
     private final static String ERROR_UNABLE_TO_LOAD="Unable to load program. \nPlease check that the latest version of the Java Runtime Environment is installed.";
     private final HapsbyApp hapsbyApp;
     private HapsbyMainWindow mainWindow;
     
     /** Default constructor for Hapsby
     * Intializes main window and program core.
     */
     public Hapsby(String args[]){
          createWindow();
          /* make sure window has been created successfully */
          if(this.mainWindow==null){
               System.err.println(ERROR_UNABLE_TO_LOAD);
               System.exit(0);
          } 
          SplashScreen splash=new SplashScreen(this.mainWindow,this.mainWindow.getResourcePath()+"splash.gif");
          this.hapsbyApp=new HapsbyApp(this.mainWindow){
              public void updateStatus(String message){
                  updateProgress(message);
              }
          };
          initWindow();
          /* process command line */
          if((this.hapsbyApp!=null)&&(args.length>0)){
               HapsbyCommandLine commandLine=new HapsbyCommandLine(args,this);
          }
          splash.dispose();
     }

     /* unloadWindow receives the windowClosing event from the main window
     */
     private final void unloadWindow(){
          this.mainWindow.saveSettings();
          this.mainWindow.setVisible(false);
          this.mainWindow=null;
          this.hapsbyApp.endProgram();
     }

     /* createWindow creates a new instance of HapsbyMainWindow and overrides actionListener() and closeWindow() 
     */
     private final void createWindow(){
          mainWindow=new HapsbyMainWindow(HapsbyApp.PROGRAM_NAME){
               public void actionListener(final ActionEvent e){
                    actionHandler(e);
               }
               public void closeWindow(){
                    unloadWindow();
               }
          };
     }

     /* initWindow intializes mainWindow and shows it
     */
     private final void initWindow(){
          this.mainWindow.pack();
          this.mainWindow.setVisible(true);
          this.mainWindow.setGameList(this.hapsbyApp.getDefList());
     }
     
    /* updateProgress, receives status messages from hapsbyApp
     * @param message, message sent from hapsbyApp
     */
    private void updateProgress(String message){
        if(this.mainWindow!=null){
            this.mainWindow.setProgress(message,0);
        }
    }     
     
     /* actionHandler receives all actions and parses for source and actionCommand.
     * A message is then created and sent to the message handler.
     * @param e ActionEvent sent to the UI
     * @TODO split command parser into another routine to allow command line support - just use this to intercept messages
     */
     private final void actionHandler(ActionEvent e){
          this.executeCommand(e.getActionCommand().toLowerCase(),null);
     }
     
     /* executeCommand sends a command to the program core and receives the response back.
     * @param command the HapsbyActionCommand being sent to the program core
     * @param parameter the string parameter if needed (only used by HapsbyCommandLine right now)
     */
     public final void executeCommand(String command,String parameter){
          this.mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          int response;
          /* convert the String command to an int HapsbyCommand for faster processing */
          try{
               /* convert to lower case just to be safe */
               command=command.toLowerCase();
                /* switch won't work with Strings so this might be confusing */
               if(command.equals(HapsbyActionCommands.ACTION_OPEN)){
                    if(this.mainWindow.getSelectedGame()!=null){
                         boolean success=this.hapsbyApp.openSaveGame(this.mainWindow.getSelectedGame());
                         if(success==true){
                              this.mainWindow.setPropertyList(this.hapsbyApp.getPropertyList());
                         }
                    }
               } else if(command.equals(HapsbyActionCommands.ACTION_SELECT)){
                    this.refreshFilePanel();
               } else if(command.equals(HapsbyActionCommands.ACTION_SAVE)){
                    this.hapsbyApp.save(this.mainWindow.getSelectedProperty(),this.mainWindow.getCurrentPropertyValue());
               } else if(command.equals(HapsbyActionCommands.ACTION_EXIT)){
                    this.unloadWindow();
               } else if(command.equals(HapsbyActionCommands.ACTION_ABOUT)){
                    this.hapsbyApp.showAboutDialog();
               } else if(command.equals(HapsbyActionCommands.ACTION_STYLE_JAVA)){
                    this.mainWindow.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
               } else if(command.equals(HapsbyActionCommands.ACTION_STYLE_SYSTEM)){
                    this.mainWindow.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               } else if(command.equals(HapsbyActionCommands.ACTION_RESTORE_BACKUP)){
                    this.hapsbyApp.restoreBackup(null);
               } else if(command.equals(HapsbyActionCommands.ACTION_AUTOBACKUP_ON)){
                    this.hapsbyApp.setBackupFlag(this.hapsbyApp.FLAG_AUTOBACKUP_ON);
               } else if(command.equals(HapsbyActionCommands.ACTION_AUTOBACKUP_OFF)){
                    this.hapsbyApp.setBackupFlag(this.hapsbyApp.FLAG_AUTOBACKUP_OFF);
               } else if(command.equals(HapsbyActionCommands.ACTION_AUTOBACKUP_PROMPT)){
                    this.hapsbyApp.setBackupFlag(this.hapsbyApp.FLAG_AUTOBACKUP_PROMPT);
               } else if(command.startsWith(HapsbyActionCommands.ACTION_THEME)){
                    String themeName=command.substring(command.lastIndexOf(' ')+1);
                    this.mainWindow.changeTheme(themeName);
                    this.mainWindow.setGameList(this.hapsbyApp.getDefList());
               } else if(command.equals(HapsbyActionCommands.ACTION_SORT_PROPERTIES_ON)){
                    this.hapsbyApp.setPropertySortFlag(true);
               } else if(command.equals(HapsbyActionCommands.ACTION_SORT_PROPERTIES_OFF)){
                    this.hapsbyApp.setPropertySortFlag(false);
               } else if(command.equals(HapsbyActionCommands.ACTION_SORT_GAMES_ON)){
                    this.hapsbyApp.setDefListSortFlag(true);
               } else if(command.equals(HapsbyActionCommands.ACTION_SORT_GAMES_OFF)){
                    this.hapsbyApp.setDefListSortFlag(true);
               } else if(command.equals(HapsbyActionCommands.ACTION_SHOW_VIEWER)){
                    this.hapsbyApp.showFileViewer();
               } else if(command.equals(HapsbyActionCommands.ACTION_SHOW_NUMBERCONVERTER)){
                    this.hapsbyApp.showNumberConverter();
               } else{
                    System.err.println("unrecognized command: "+command);
               }
          } catch(Exception x){
               x.printStackTrace();
          } finally{     
               this.mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
          }
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          if(this.hapsbyApp!=null){
               tostring+='\n'+this.hapsbyApp.toString();
          }
          if(this.mainWindow!=null){
               tostring+='\n'+this.mainWindow.toString();
          }
          return(tostring);
     }
     
     /* refreshes the file panel based on a save game property
     */
     private final void refreshFilePanel(){
          if(this.mainWindow.getCurrentPropertyName()!=null){
               SaveGameProperty p=this.hapsbyApp.getProperty(this.mainWindow.getCurrentPropertyName());
               if(p!=null){
                    if(p.getName()!=null){
                         String caption=this.hapsbyApp.getGameTitle();
                         String gameDescription=this.hapsbyApp.getGameDesc();
                         String propertyDescription=p.getDescription();
                         String address=Integer.toString(p.getAddress());
                         String length=Integer.toString(p.getLength());
                         String minValue=Integer.toString(p.getMinValue());
                         String maxValue=Integer.toString(p.getMaxValue());
                         String currentValue=this.hapsbyApp.getCurrentValue(p.getAddress(),p.getLength(),p.getDataType());
                         this.mainWindow.setCurrentProperty(caption,gameDescription,propertyDescription,address,length,maxValue,minValue,currentValue);
                    }
               }
          }
     }
     
     /** entry point for Hapsby program
     */
     public static void main(String args[]){
          try{
               Hapsby appInstance=new Hapsby(args);
          } catch(Exception x){
               System.err.println(ERROR_UNABLE_TO_LOAD);
               x.printStackTrace();
          }
     }
}