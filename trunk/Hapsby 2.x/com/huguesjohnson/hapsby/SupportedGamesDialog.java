/*
Hapsby - universal save game editor
SupportedGamesDialog.java - Dialog to show the list of supported games
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
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Dialog to show the list of supported games.
 * 
 * @author Hugues Johnson
 */
public class SupportedGamesDialog extends JDialog{
	private static final long serialVersionUID=768729315818041356L;

	/**
	 * Adds components to the dialog, populates the table, and displays the dialog.
	 * 
	 * @param owner The parent window.
	 * @param saveGameDefinitions The <code>ArrayList</code> of save game definitions.
	 */
	public SupportedGamesDialog(Frame owner,ArrayList<SaveGameDefinition> saveGameDefinitions){
		super(owner,"Supported Games");
		this.setModal(true);
		this.setResizable(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		//setup the scroll pane
		SaveGameDefinitionTable table=new SaveGameDefinitionTable(saveGameDefinitions);
		JScrollPane scrollpane=new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(400,300));
		this.getContentPane().add(scrollpane);
		//add a close button
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton button=new JButton("Close");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ close(); }
		});
		buttonPanel.add(button);
		this.getContentPane().add(buttonPanel);
		//pack and resize table
		this.pack();
		table.resizeStuff();
		//center to parent
		Rectangle parentDimension=owner.getBounds();
		Rectangle windowDimension=this.getBounds();
		int x;
		int y;
		if(parentDimension.width>windowDimension.width){
			x=((parentDimension.width-windowDimension.width)/2)+parentDimension.x;
		} else{
			x=parentDimension.x;
		}
		if(parentDimension.height>windowDimension.height){
			y=((parentDimension.height-windowDimension.height)/2)+parentDimension.y;
		} else{
			y=parentDimension.y;
		}
		this.setLocation(x,y);
		this.setVisible(true);
	}
	
	/**
	 * Close the dialog (same as <code>setVisible(false)</code>).
	 */
	public void close(){
		this.setVisible(false);
	}
}