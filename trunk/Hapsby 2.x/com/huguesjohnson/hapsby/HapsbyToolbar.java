/*
Hapsby - universal save game editor
HapsbyToolbar.java - toolbar for Hapsby UI
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