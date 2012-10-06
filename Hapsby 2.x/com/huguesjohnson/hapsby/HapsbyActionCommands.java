/*
Hapsby - universal save game editor
HapsbyActionCommands.java - action commands used by the Hapsby UI
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

/**
 * Stores all strings that can be used as action commands for swing components.
 * 
 * @author Hugues Johnson
 */
public abstract class HapsbyActionCommands{
	/** Open a save game. */
	public static final String ACTION_OPEN="open";
	/** Save current value. */
	public static final String ACTION_SET_THEME="setTheme:";
	/** Exit program. */
	public static final String ACTION_EXIT="exit";
	/** Display 'About Hapsby' dialog. */
	public static final String ACTION_ABOUT="about";
	/** Display dialog listing all supported games. */
	public static final String ACTION_SHOW_GAME_LIST="showGameList";
}