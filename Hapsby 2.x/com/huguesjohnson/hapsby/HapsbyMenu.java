/*
Hapsby - universal save game editor
HapsbyMenu.java - Menu system for Hapsby
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
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Menu for the Hapsby main window, extends <code>JMenuBar</code>.
 * 
 * @author Hugues Johnson
 */
public abstract class HapsbyMenu extends JMenuBar implements ActionListener{
	private static final long serialVersionUID=2648698703276178622L;
	private String resourcePath;

	/**
	 * Creates the menu, loads icons, and sets action commands.
	 * 
	 * @param resourcePath Resource path for icons, icon images are constructed with <code>menuItem.setIcon(new ImageIcon(this.getClass().getResource(resourcePath+"icon.gif")))</code>.
	 */
	public HapsbyMenu(String resourcePath){
		this.setResourcePath(resourcePath);
		this.createMenu();
	}

	/*
	 * creates the menu
	 */
	private void createMenu(){
		//create the main menu
		JMenu menu;
		menu=new JMenu("Hapsby");
		menu.setMnemonic(KeyEvent.VK_H);
		this.add(menu);
		//open save game
		JMenuItem menuItem;
		menuItem=new JMenuItem("Open Save Game..");
		menuItem.setIcon(new ImageIcon(this.getClass().getResource(this.resourcePath+"open.gif")));
		menuItem.setMnemonic(KeyEvent.VK_O);
		menuItem.setActionCommand(HapsbyActionCommands.ACTION_OPEN);
		menuItem.addActionListener(this);
		menu.add(menuItem);
		//look & feel
	    LookAndFeelInfo lf[]=UIManager.getInstalledLookAndFeels();
		int length=lf.length;
		if(length>1){
			JMenu lfMenu=new JMenu("Window Style");
		    for(int index=0;index<length;index++){
				menuItem=new JMenuItem(lf[index].getName());
				menuItem.setActionCommand(HapsbyActionCommands.ACTION_SET_THEME+lf[index].getClassName());
				menuItem.addActionListener(this);
				lfMenu.add(menuItem);
		    }
			menu.addSeparator();
			menu.add(lfMenu);
		}
		//supported games
		menu.addSeparator();
		menuItem=new JMenuItem("Supported Games..");
		menuItem.setIcon(new ImageIcon(this.getClass().getResource(this.resourcePath+"list.gif")));
		menuItem.setMnemonic(KeyEvent.VK_G);
		menuItem.setActionCommand(HapsbyActionCommands.ACTION_SHOW_GAME_LIST);
		menuItem.addActionListener(this);
		menu.add(menuItem);
		//about menu
		menu.addSeparator();
		menuItem=new JMenuItem("About..");
		menuItem.setIcon(new ImageIcon(this.getClass().getResource(this.resourcePath+"about.gif")));
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.setActionCommand(HapsbyActionCommands.ACTION_ABOUT);
		menuItem.addActionListener(this);
		menu.add(menuItem);
		//exit
		menu.addSeparator();
		menuItem=new JMenuItem("Exit");
		menuItem.setIcon(new ImageIcon(this.getClass().getResource(this.resourcePath+"exit.gif")));
		menuItem.setMnemonic(KeyEvent.VK_X);
		menuItem.setActionCommand(HapsbyActionCommands.ACTION_EXIT);
		menuItem.addActionListener(this);
		menu.add(menuItem);
	}

	/*
	 * setResourcePath sets the path to the resource (image) directory
	 * 
	 * @param resourcePath resource path for icons - icons images are constructed with <code>menuItem.setIcon(new ImageIcon(this.getClass().getResource(resourcePath+"icon.gif")))</code>
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
	public abstract void actionPerformed(final ActionEvent e);
}