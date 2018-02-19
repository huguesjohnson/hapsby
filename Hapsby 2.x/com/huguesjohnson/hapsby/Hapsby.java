/*
Hapsby - universal save game editor
Hapsby.java - entry point for program
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardPage;

import com.huguesjohnson.GzipCompressor;
import com.huguesjohnson.IniFile;
import com.huguesjohnson.hapsby.SaveGameProperty.DataType;
import com.huguesjohnson.ui.SplashScreen;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Main class for the Hapsby application.
 * 
 * @author Hugues Johnson
 */
public final class Hapsby{
	private HapsbyMainWindow mainWindow;
	private ArrayList<SaveGameDefinition> gameDefinitions;
	private SaveGameDefinition activeDefinition;
	private SaveGame activeSaveGame;
	private IniFile iniFile;
	private PrintStream errorStream;
	private SelectGameDefinitionPage page1;
	private OpenSaveGamePage page2;
	private SaveGameProperty activeProperty;

	/**
	 * Initializes the main window and application.
	 * The following actions are taken at startup:
	 * <ol>
	 * <li>Splash screen displayed.</li>
	 * <li>Error stream redirected to ${user.dir}\Hapsby.err.log.</li>
	 * <li>Settings are loaded from Hapsby.ini.</li>
	 * <li>Save game definitions are loaded from ${user.dir}\hsd.</li>
	 * <li>Open save game wizard pages are pre-initialized.</li>
	 * <li>Main window is initialized and displayed.</li>
	 * <li>Splash screen closed.</li>
	 * </ol>
	 */
	public Hapsby(){
		SplashScreen splash=null;
		boolean errorStreamSet=false;
		try{
			this.createWindow();
			splash=new SplashScreen(this.mainWindow,"/com/huguesjohnson/hapsby/images/splash.gif");
			this.setErrorStream();
			errorStreamSet=true;
			this.loadSavedSettings();
			this.loadDefinitions();
			this.page1=new SelectGameDefinitionPage(this.gameDefinitions);
			this.page2=new OpenSaveGamePage();
			this.initWindow();
		} catch(Exception x){
			if(errorStreamSet){ 
				x.printStackTrace(); 
				JOptionPane.showMessageDialog(null,"Something bad happened during startup.\n\nPlease see Hapsby.err.log for details.\n\nError message is:\n"+stackTraceToString(x),"Bad times",JOptionPane.INFORMATION_MESSAGE);
			} else{
				JOptionPane.showMessageDialog(null,"Something bad happened during startup.\n\nError message is:\n"+stackTraceToString(x),"Bad times",JOptionPane.INFORMATION_MESSAGE);
			}
			System.exit(0);
		} finally{
			if(splash!=null){
				splash.dispose();
			}
		}
	}

	private void setErrorStream(){
		try{
			this.errorStream=new PrintStream(new FileOutputStream(System.getProperty("user.dir")+File.separator+"hapsby.err.log",true));
			System.setErr(this.errorStream);
		} catch(Exception x){
			x.printStackTrace();
		}
	}
	
	private final void loadDefinitions() throws Exception{
		String defFilePath=new String(System.getProperty("user.dir")+File.separator+"hsd");
		File f=new File(defFilePath);
		String defList[]={""};
		defList=new String[f.list().length];
		defList=f.list(new SaveDefFilter());
		int length=defList.length;
		this.gameDefinitions=new ArrayList<SaveGameDefinition>();
		XStream xstream=new XStream(new DomDriver());
		for(int index=0;index<length;index++){
			this.gameDefinitions.add((SaveGameDefinition)xstream.fromXML(new FileReader(defFilePath+File.separator+defList[index])));
		}
	}
	
	/**
	 * Executed when the windowClosing event is received from the main window.
	 * The following actions are taken:
	 * <ol>
	 * <li>Most recent save game property change saved.</li>
	 * <li>Currently open save game closed.</li>
	 * <li>Settings are saved to ${user.dir}\Hapsby.ini.</li>
	 * <li>Error log closed, deleted if empty.</li>
	 * <il>Main window closed.</li>
	 * <li><code>System.exit(0)</code> called.</li>
	 * </ol>
	 */
	public final void unloadWindow(){
		try{
			this.updateProgress("Saving settings and exiting..");
			this.saveCurrentProperty();
			// close the open save game
			if(this.activeSaveGame!=null){
				this.activeSaveGame.close();
			}
			this.saveSettings();
			// close the error log
			this.errorStream.flush();
			this.errorStream.close();
			File errorLog=new File(System.getProperty("user.dir")+File.separator+"hapsby.err.log");
			if(errorLog.length()<1){
				errorLog.delete();
			}
		} catch(Exception x){
			JOptionPane.showMessageDialog(null,"Something bad happened during close.\n\nPlease see Hapsby.err.log for details.\n\nError message is:\n"+stackTraceToString(x),"Bad times",JOptionPane.INFORMATION_MESSAGE);
		} finally{
			this.mainWindow.setVisible(false);
			System.exit(0);
		}
	}

	private void loadSavedSettings(){
		this.iniFile=new IniFile(System.getProperty("user.dir")+File.separator+"Hapsby.cfg");
		try{
			this.iniFile.read();
		} catch(IOException x){ /* no need to log this */ }
		String property=new String();
		String defaultString=new String("__dEfAulT__");
		//last directory opened
		property=this.iniFile.getProperty("lastOpenPath",defaultString);
		if(property.equals(defaultString)){
			System.setProperty("FileBrowsePanel.lastPath",System.getProperty("user.dir"));
		} else{
			System.setProperty("FileBrowsePanel.lastPath",new String(property));
		}
		//window style
		property=this.iniFile.getProperty("LookAndFeelName",defaultString);
		if(!(property.equals(defaultString))){
			this.executeCommand(HapsbyActionCommands.ACTION_SET_THEME+property);
		}
	}
	
	private static String stackTraceToString(Throwable throwable){
		StringBuffer tostring=new StringBuffer(throwable.toString());
		tostring.append("\n");
		//add each element of the stack trace
		for(StackTraceElement element:throwable.getStackTrace()){
			tostring.append("  ");
			tostring.append(element.toString());
			tostring.append("\n");
		}
		return(tostring.toString());
	}

	private void saveSettings() throws IOException{
		this.iniFile.setProperty("lastOpenPath",System.getProperty("FileBrowsePanel.lastPath"));
		this.iniFile.setProperty("LookAndFeelName",this.mainWindow.getCurrentLookAndFeelName());
		this.iniFile.save("Hapsby 2.0");
	}	
	
	/*
	 * createWindow creates a new instance of HapsbyMainWindow and overrides actionListener() and closeWindow()
	 */
	private final void createWindow(){
		this.mainWindow=new HapsbyMainWindow("Hapsby 2.0"){
			private static final long serialVersionUID=-7280147156045153299L;
			@Override 
			public void actionListener(final ActionEvent e){
				actionHandler(e);
			}
			@Override 
			public void closeWindow(){
				unloadWindow();
			}
			@Override 
			public void listSelectionListener(ListSelectionEvent e){
				listSelectionHandler(e);
			}
		};
	}

	/*
	 * initWindow intializes mainWindow and shows it
	 */
	private final void initWindow(){
		this.mainWindow.pack();
		this.mainWindow.centerWindow();
		this.mainWindow.setProgress("Please open a save game",0);
		this.mainWindow.setVisible(true);
	}

	/**
	 * Sends status messages to the main window.
	 * 
	 * @param message The message to display in the statusbar of the main window.
	 */
	public void updateProgress(String message){
		if(this.mainWindow!=null){
			this.mainWindow.setProgress(message,0);
		}
	}

	/**
	 * Receives actions from the main window and forwards them to <code>executeCommand(String command)</code>.
	 * 
	 * @param e <code>ActionEvent</code> sent from the main window.
	 */
	public final void actionHandler(ActionEvent e){
		this.executeCommand(e.getActionCommand());
	}
	

	/**
	 * listSelectionHandler is invoked when a new property is selected in the main window.
	 * The last edited property is saved and the main window is updated with the selected property.
	 * 
	 * @param e <code>ListSelectionEvent</li> received from the main window.
	 */
	public final void listSelectionHandler(ListSelectionEvent e){
		if(!e.getValueIsAdjusting()){
			// save the current value
			this.saveCurrentProperty();
			this.activeProperty=this.mainWindow.getSelectedProperty();
			if(this.activeProperty!=null){
				if(this.activeProperty.getDataType()==DataType.TYPE_INTEGER){
					this.mainWindow.setCurrentProperty(this.activeProperty,this.activeSaveGame.getIntValue(this.activeProperty.getAddress(),this.activeProperty.getLength(),this.activeProperty.getByteOrder()));
				} else if(this.activeProperty.getDataType()==DataType.TYPE_STRING){
					this.mainWindow.setCurrentProperty(this.activeProperty,this.activeSaveGame.getStringValue(this.activeProperty.getAddress(),this.activeProperty.getLength(),this.activeProperty.getByteOrder()));
				}
			}
		}
	}

	/**
	 * Processes action commands received from the UI.
	 * 
	 * @param command One of the commands defined in <code>HapsbyActionCommands</code>.
	 * @see HapsbyActionCommands
	 */
	@SuppressWarnings("unchecked") 
	public final void executeCommand(String command){
		this.mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try{
			if(command.equals(HapsbyActionCommands.ACTION_OPEN)){
				Wizard wizard=WizardPage.createWizard(
						new WizardPage[]{ 
	                		this.page1,this.page2
	                	}
				);
				Map returnValue=(Map)WizardDisplayer.showWizard(wizard);
				if(returnValue!=null){
					this.saveCurrentProperty();
					this.activeProperty=null;
					this.activeDefinition=(SaveGameDefinition)returnValue.get("saveGameDefinition");
					String activeSaveGamePath=(String)returnValue.get("saveGamePath");
					if(this.activeSaveGame!=null){
						this.activeSaveGame.close();
					}
					this.activeSaveGame=new SaveGame(activeSaveGamePath);
					this.mainWindow.setGameDescription(this.activeDefinition.getGameDescription());
					this.mainWindow.setPropertyList(this.activeDefinition.getProperties());
					// create backup
					if(returnValue.containsKey("backupFilePath")){
						String backupFilePath=(String)returnValue.get("backupFilePath");
						if((backupFilePath!=null)&&(backupFilePath.length()>0)){
							new GzipCompressor(activeSaveGamePath,(String)returnValue.get("backupFilePath"));
						}
					}
					this.mainWindow.setProgress("Opened "+activeSaveGamePath,0);
				}
			} else if(command.equals(HapsbyActionCommands.ACTION_EXIT)){
				this.unloadWindow();
			} else if(command.equals(HapsbyActionCommands.ACTION_ABOUT)){
				String title="About Hapsby";
				String prompt="Hapsby\nVersion 2.0\n\nCopyright (C) 2000-2009 Hugues Johnson\nhttp://HuguesJohnson.com/";
				JOptionPane.showMessageDialog(this.mainWindow,prompt,title,JOptionPane.INFORMATION_MESSAGE);
			} else if(command.equals(HapsbyActionCommands.ACTION_SHOW_GAME_LIST)){
				new SupportedGamesDialog(this.mainWindow,this.gameDefinitions);
			} else if(command.startsWith(HapsbyActionCommands.ACTION_SET_THEME)){
				String lfName=command.substring(HapsbyActionCommands.ACTION_SET_THEME.length());
				this.mainWindow.setLookAndFeel(lfName);
			} else{
				System.err.println("unrecognized command: "+command);
			}
		} catch(Exception x){
			x.printStackTrace();
		} finally{
			this.mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	private final void saveCurrentProperty(){
		if(this.activeProperty!=null){
			if(this.activeProperty.getDataType()==DataType.TYPE_INTEGER){
				int newValue=Integer.valueOf(this.mainWindow.getCurrentPropertyValue()).intValue();
				int currentValue=this.activeSaveGame.getIntValue(this.activeProperty.getAddress(),this.activeProperty.getLength(),this.activeProperty.getByteOrder());
				if(newValue!=currentValue){
					this.activeSaveGame.setIntValue(this.activeProperty.getAddress(),this.activeProperty.getLength(),newValue,this.activeProperty.getByteOrder());
					this.mainWindow.setProgress("Saved "+this.activeProperty.getName(),0);
				}
			} else if(this.activeProperty.getDataType()==DataType.TYPE_STRING){
				String newValue=this.mainWindow.getCurrentPropertyValue();
				String currentValue=this.activeSaveGame.getStringValue(this.activeProperty.getAddress(),this.activeProperty.getLength(),this.activeProperty.getByteOrder());
				if(!newValue.equals(currentValue)){
					this.activeSaveGame.setStringValue(this.activeProperty.getAddress(),this.activeProperty.getLength(),newValue,this.activeProperty.getByteOrder());
					this.mainWindow.setProgress("Saved "+this.activeProperty.getName(),0);
				}
			}
		}
	}
	
	/**
	 * Entry point for Hapsby application.
	 * 
	 * @param args Command line arguments ignored.
	 */
	public static void main(String args[]){
		new Hapsby();
	}
}
