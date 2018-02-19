/*
Hapsby - universal save game editor
HapsbyActionCommands.java - action commands used by the Hapsby UI
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

/** Class HapsbyActionCommands stores all strings that can be used as action commands for swing components or as command line parameters.
* @author Hugues Johnson
*/
public abstract class HapsbyActionCommands{
     /** Open a save game */
     public static final String ACTION_OPEN ="-open";
     /** Save game property selected*/
     public static final String ACTION_SELECT ="-select";
     /** Save current value */
     public static final String ACTION_SAVE="-save";
     /** Exit program */
     public static final String ACTION_EXIT="-exit";
     /** Display 'About Program' information */
     public static final String ACTION_ABOUT="-about";
     /** Change look & feel to Java default style */
     public static final String ACTION_STYLE_JAVA="-style java";
     /** Change look & feel to operating system default style */
     public static final String ACTION_STYLE_SYSTEM="-style system";
     /** Restore a Hapsby backup */
     public static final String ACTION_RESTORE_BACKUP="-restore";
     /** Turn auto-backup on */
     public static final String ACTION_AUTOBACKUP_ON="-backup on";
     /** Turn auto-backup off */
     public static final String ACTION_AUTOBACKUP_OFF="-backup off";
     /** Prompt before creating backups */
     public static final String ACTION_AUTOBACKUP_PROMPT="-backup prompt";
     /** Change window theme */
     public static final String ACTION_THEME="-theme";
     /** Turn on sorting property list */
     public static final String ACTION_SORT_PROPERTIES_ON="-sort_properties on";
     /** Turn off sorting property list */
     public static final String ACTION_SORT_PROPERTIES_OFF="-sort_properties off";
     /** Turn on sorting game def list */
     public static final String ACTION_SORT_GAMES_ON="-sort_games on";
     /** Turn off sorting game def list */
     public static final String ACTION_SORT_GAMES_OFF="-sort_games off";
     /** show the file viewer */
     public static final String ACTION_SHOW_VIEWER="-fileview";
     /** show the number converter */
     public static final String ACTION_SHOW_NUMBERCONVERTER="-converter";     
}
