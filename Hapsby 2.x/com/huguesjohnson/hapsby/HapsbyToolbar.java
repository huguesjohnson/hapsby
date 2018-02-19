/*
Hapsby - universal save game editor
HapsbyToolbar.java - toolbar for Hapsby UI
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.huguesjohnson.hapsby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Toolbar for the Hapsby main window, extends <code>JToolBar</code>.
 * 
 * @author Hugues Johnson
 */
public abstract class HapsbyToolbar extends JToolBar implements ActionListener{
	private static final long serialVersionUID=6671809423692358354L;
	private String resourcePath;

	/**
	 * Creates the toolbar, loads icons, and sets action commands.
	 * 
	 * @param resourcePath Resource path for icons, icon images are constructed with <code>menuItem.setIcon(new ImageIcon(this.getClass().getResource(resourcePath+"icon.gif")))</code>.
	 */
	public HapsbyToolbar(String resourcePath){
		this.setResourcePath(resourcePath);
		this.addButtons();
	}

	/*
	 * adds all buttons and their images.
	 */
	private void addButtons(){
		JButton button;
		button=new JButton("Open Save Game..");
		button.setIcon(new ImageIcon(this.getClass().getResource(this.resourcePath+"open.gif")));
		button.setActionCommand(HapsbyActionCommands.ACTION_OPEN);
		button.setToolTipText("Open Save Game");
		button.addActionListener(this);
		this.add(button);
	}

	/*
	 * setResourcePath sets the path to the resource (image) directory
	 * 
	 * @param resourcePath resource path for icons - icons images are constructed with <code>button.setIcon(new ImageIcon(this.getClass().getResource(resourcePath+"icon.gif")))</code>
	 */
	private void setResourcePath(String resourcePath){
		if(!resourcePath.endsWith("/")){ 
			this.resourcePath=(resourcePath+"/");
		} else{
			this.resourcePath=resourcePath;
		}
	}

	/**
	 * Method invoked when an action is performed. Override this routine when creating a new <code>HapsbyMainWindow</code>.
	 * 
	 * @param e <code>ActionEvent</code> received by UI
	 */
	public abstract void actionPerformed(ActionEvent e);
}
