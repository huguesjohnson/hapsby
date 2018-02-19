/*
Hapsby - universal save game editor
HapsbyMenu.java - Menu system for Hapsby
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
