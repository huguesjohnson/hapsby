/*
Hapsby - universal save game editor
HapsbyActionCommands.java - action commands used by the Hapsby UI
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
