/*
Hapsby - universal save game editor
SelectGameDefinitionPage.java - Page to select a save game definition
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