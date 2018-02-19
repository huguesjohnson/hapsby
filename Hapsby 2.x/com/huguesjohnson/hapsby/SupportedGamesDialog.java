/*
Hapsby - universal save game editor
SupportedGamesDialog.java - Dialog to show the list of supported games
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
