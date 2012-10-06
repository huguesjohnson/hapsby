/*
Hapsby - universal save game editor
HapsbyActionCommands.java - action commands used by the Hapsby UI
Copyright  (C) 2000-2001 Hugues Johnson

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