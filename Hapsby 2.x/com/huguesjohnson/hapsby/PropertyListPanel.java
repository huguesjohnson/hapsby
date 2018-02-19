/*
Hapsby - universal save game editor
PropertyListPanel.java - UI for property list
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
