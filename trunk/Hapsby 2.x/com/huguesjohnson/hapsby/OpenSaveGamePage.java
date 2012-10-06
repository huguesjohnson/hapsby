/*
Hapsby - universal save game editor
OpenSaveGamePage.java - Page to open a save game
Copyright  (C) 2008 Hugues Johnson

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

package com.huguesjohnson.hapsby;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPage;
import org.netbeans.spi.wizard.WizardPanelNavResult;

import com.huguesjohnson.ui.FileBrowsePanel;

/**
 * WizardPage to open a save game.
 * 
 * @author Hugues Johnson
 */
public class OpenSaveGamePage extends WizardPage{
	private static final long serialVersionUID=7887840498982353541L;
	private FileBrowsePanel browsePanel;
	private JCheckBox createBackupCheckbox;
	private JTextField backupFilePathTextField;
	
	/**
	 * Creates the page, adds components, and sets the wizard navigation mode.
	 */
	public OpenSaveGamePage(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		//add the browse panel
		this.browsePanel=new FileBrowsePanel("Browse for save game",new SaveGameFilter("","*")){
			private static final long serialVersionUID=3050706209024093245L;
			public void onBrowseComplete(){ onFileBrowsePanelComplete(); }
		};
		this.add(this.browsePanel);
		//add the create backup panel
		JPanel createBackupPanel=new JPanel();
		createBackupPanel.setLayout(new BoxLayout(createBackupPanel,BoxLayout.Y_AXIS));
		createBackupPanel.setBorder(new TitledBorder("Backup save state"));
		JPanel panel1=new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.createBackupCheckbox=new JCheckBox("Create backup");
		this.createBackupCheckbox.setEnabled(false);
		this.createBackupCheckbox.addItemListener(
				new ItemListener(){
					@Override 
					public void itemStateChanged(ItemEvent e){
						onCheckboxClicked();
					}
				});
		panel1.add(this.createBackupCheckbox);
		createBackupPanel.add(panel1);
		JPanel panel2=new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel labelBackup=new JLabel("Backup File: ");
		labelBackup.setHorizontalAlignment(SwingConstants.RIGHT);
		panel2.add(labelBackup);
		this.backupFilePathTextField=new JTextField();
		this.backupFilePathTextField.setEditable(false);
		this.backupFilePathTextField.setToolTipText("Path to the file");
		this.backupFilePathTextField.setMinimumSize(new Dimension(300,21));
		this.backupFilePathTextField.setPreferredSize(new Dimension(300,21));
		this.backupFilePathTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE,21));
		panel2.add(this.backupFilePathTextField);
		createBackupPanel.add(panel2);
		this.add(createBackupPanel);
		//fill out the bottom
		Dimension maxSize=new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
		this.add(new Box.Filler(maxSize,maxSize,maxSize));
		//wizard stuff
		this.setForwardNavigationMode(WizardController.MODE_CAN_FINISH);
	}

	/** 
	 * Looks for the <code>SaveGameDefinition</code> stored in the <code>WizardData</code> under the key "saveGameDefinition".
	 * Sets the filter for the browse panel based on the retreived <code>SaveGameDefinition</code>.
	 * 
	 * @see org.netbeans.spi.wizard.WizardPage#renderingPage()
	 */
	@Override 
	protected void renderingPage(){
		super.renderingPage();
		SaveGameDefinition sgd=(SaveGameDefinition)this.getWizardData("saveGameDefinition");
		SaveGameFilter filter=new SaveGameFilter(sgd.getSaveFilePattern(),sgd.getGameTitle());
		this.browsePanel.setFilters(filter);
		this.browsePanel.setTitle("Browse for "+sgd.getGameTitle()+" save game");
		this.browsePanel.clear();
		this.backupFilePathTextField.setText("");
	}
	
	/**
	 * "Open save state/save game"
	 * 
	 * @return "Open save state/save game"
	 */
	public static String getDescription(){ return("Open save state/save game"); }

	/**
	 * Update fields after browsing for a file is complete.
	 */
	public void onFileBrowsePanelComplete(){
		if(this.browsePanel.getFilePath().length()<1){
			this.createBackupCheckbox.setEnabled(false);
		} else{
			if(this.createBackupCheckbox.isEnabled()){
				if(this.createBackupCheckbox.isSelected()){
					String backupFilePath=this.browsePanel.getFilePath()+".gzip";
					File f=new File(backupFilePath);
					int i=1;
					while(f.exists()){
						backupFilePath=this.browsePanel.getFilePath()+"["+i+"].gzip";
						f=new File(backupFilePath);
						i++;
					}
					this.backupFilePathTextField.setText(backupFilePath);
				}
			} else{
				this.createBackupCheckbox.setEnabled(true);
			}
		}
	}
	
	/**
	 * Update "Backup File Path" field when the checkbox is clicked.
	 */
	public void onCheckboxClicked(){
		if(this.createBackupCheckbox.isSelected()){
			String backupFilePath=this.browsePanel.getFilePath()+".gzip";
			File f=new File(backupFilePath);
			int i=1;
			while(f.exists()){
				backupFilePath=this.browsePanel.getFilePath()+"["+i+"].gzip";
				f=new File(backupFilePath);
				i++;
			}
			this.backupFilePathTextField.setText(backupFilePath);
		} else{
			this.backupFilePathTextField.setText("");
		}
	}
	
	/**
	 * Clears the browse panel and returns <code>WizardPanelNavResult.PROCEED</code>.
	 *  
	 * @see org.netbeans.spi.wizard.WizardPage#allowBack(java.lang.String, java.util.Map, org.netbeans.spi.wizard.Wizard)
	 */
	@SuppressWarnings("unchecked")
	@Override 
	public WizardPanelNavResult allowBack(String stepName,Map settings,Wizard wizard){
		this.browsePanel.clear();
		return(WizardPanelNavResult.PROCEED);
	}

	/**
	 * If a file has not been selected:
	 * <ol>
	 * <li>Displays a dialog indicating "Please select a save game."</li>
	 * <li>Returns <code>WizardPanelNavResult.REMAIN_ON_PAGE</code>.</li>
	 * </ol>
	 * <br>
	 * If a file has been selected:
	 * <ol>
	 * <li>Adds "saveGamePath" to the <code>WizardData</code>.</li>
	 * <li>Adds "backupFilePath" to the <code>WizardData</code>.</li>
	 * <li>Returns <code>WizardPanelNavResult.PROCEED</code>.</li>
	 * </ol>
	 * 
	 * @see org.netbeans.spi.wizard.WizardPage#allowFinish(java.lang.String, java.util.Map, org.netbeans.spi.wizard.Wizard)
	 */
	@SuppressWarnings("unchecked")
	@Override 
	public WizardPanelNavResult allowFinish(String stepName,Map settings,Wizard wizard){
		String filePath=this.browsePanel.getFilePath();
		if(filePath.length()<1){
			JOptionPane.showMessageDialog(this.getParent(),"Please select a save game.","Error",JOptionPane.ERROR_MESSAGE);
			return(WizardPanelNavResult.REMAIN_ON_PAGE);
		}
		settings.put("saveGamePath",filePath);
		settings.put("backupFilePath",this.backupFilePathTextField.getText());
		return(WizardPanelNavResult.PROCEED);	
	}
}