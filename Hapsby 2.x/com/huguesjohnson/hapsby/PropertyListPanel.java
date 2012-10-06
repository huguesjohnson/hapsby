/*
Hapsby - universal save game editor
PropertyListPanel.java - UI for property list
Copyright  (C) 2000-2009 Hugues Johnson

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

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Panel to display a list of <code>SaveGameProperty</code> objects.
 * 
 * @author Hugues Johnson
 */
public abstract class PropertyListPanel extends JPanel implements ListSelectionListener,ActionListener{
	private static final long serialVersionUID=7219782524403238448L;
	private JList propertyList;
	private DefaultListModel propertyListModel;

	/**
	 * Creates a <code>JList</code> to hold game list.
	 */
	public PropertyListPanel(){
		/* create border and layout */
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Properties"));
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		/* initialize game list */
		this.propertyListModel=new DefaultListModel();
		this.propertyList=new JList(this.propertyListModel);
		this.propertyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.propertyList.addListSelectionListener(this);
		/* create scroll pane to hold list */
		JScrollPane listScrollPane=new JScrollPane(this.propertyList);
		this.add(listScrollPane);
	}

	/**
	 * Receives list selection events from the list. Meant to be overidden by parent window.
	 * 
	 * @param e list selection event from the list.
	 */
	public abstract void valueChanged(final ListSelectionEvent e);

	/**
	 * Returns the selected item in the property list.
	 * 
	 * @return The selected item in the property list.
	 */
	public SaveGameProperty getSelectedItem(){
		/* get index of selected item */
		int index=this.propertyList.getSelectedIndex();
		/* use index to get String value of selection from gameListModel */
		if(index>=0){
			return((SaveGameProperty)this.propertyListModel.elementAt(index));
		}
		return(null);
	}

	/**
	 * Sets the property list.
	 * 
	 * @param properties The new list to display.
	 */
	public void setPropertyList(ArrayList<SaveGameProperty> properties){
		/* clear existing list */
		this.propertyListModel.clear();
		/* add items to list */
		int size=properties.size();
		for(int index=0;index<size;index++){
			this.propertyListModel.addElement(properties.get(index));
		}
		this.propertyList.setSelectedIndex(0);
	}
}