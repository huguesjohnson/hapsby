/*
Hapsby - universal save game editor
SelectGameDefinitionPage.java - Page to select a save game definition
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardController;
import org.netbeans.spi.wizard.WizardPage;
import org.netbeans.spi.wizard.WizardPanelNavResult;

/**
 * WizardPage to select a save game definition.
 * 
 * @author Hugues Johnson
 */
public class SelectGameDefinitionPage extends WizardPage{
	private static final long serialVersionUID=-8892817231830626374L;
	private SaveGameDefinitionTable table;
	
	/**
	 * Creates the page, adds components, and sets the wizard navigation mode.
	 * 
	 * @param saveGameDefinitions The list of save game definitions to display on this page.
	 */
	public SelectGameDefinitionPage(ArrayList<SaveGameDefinition> saveGameDefinitions){
		//add the table
		this.table=new SaveGameDefinitionTable(saveGameDefinitions);
		JScrollPane scrollpane=new JScrollPane(this.table);
		this.add(scrollpane);
		//wizard stuff
		this.setForwardNavigationMode(WizardController.MODE_CAN_CONTINUE);
	}
	
	/**
	 * "Select save state/save game definition"
	 * 
	 * @return "Select save state/save game definition"
	 */
	public static String getDescription(){ return("Select save state/save game definition"); }

	/** 
	 * Resizes table rows to display all data.
	 * 
	 * @see org.netbeans.spi.wizard.WizardPage#renderingPage()
	 */
	@Override 
	protected void renderingPage(){
		super.renderingPage();
		this.table.resizeStuff();
	    this.table.setRowSelectionInterval(0,0);
	}

	/**
	 * If a <code>SaveGameDefinition</code> has not been selected:
	 * <ol>
	 * <li>Displays a dialog indicating "Please select a save game definition."</li>
	 * <li>Returns <code>WizardPanelNavResult.REMAIN_ON_PAGE</code>.</li>
	 * </ol>
	 * <br>
	 * If a <code>SaveGameDefinition</code> has been selected:
	 * <ol>
	 * <li>Adds "saveGameDefinition" to the <code>WizardData</code>.</li>
	 * <li>Returns <code>WizardPanelNavResult.PROCEED</code>.</li>
	 * </ol>
	 * 
	 * @see org.netbeans.spi.wizard.WizardPage#allowNext(java.lang.String, java.util.Map, org.netbeans.spi.wizard.Wizard)
	 */
	@SuppressWarnings("unchecked") 
	@Override 
	public WizardPanelNavResult allowNext(String stepName,Map settings,Wizard wizard){
		int row=this.table.getSelectedRow();
		if(row<0){
			JOptionPane.showMessageDialog(this.getParent(),"Please select a save game definition.","Error",JOptionPane.ERROR_MESSAGE);
			return(WizardPanelNavResult.REMAIN_ON_PAGE);
		}
		SaveGameDefinition sgd=(SaveGameDefinition)this.table.getModel().getValueAt(row,0);
		settings.put("saveGameDefinition",sgd);
		return(WizardPanelNavResult.PROCEED);
	}
}
