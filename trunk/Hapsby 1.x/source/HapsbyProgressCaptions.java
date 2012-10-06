/*
Hapsby - universal save game editor
HapsbyProgressCaptions.java - captions used in the progress bars
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

/** Class HapsbyProgressCaptions stores all strings that can be used as progress bar captions
* @author Hugues Johnson
*/
public abstract class HapsbyProgressCaptions{
     /** ready to accept input */
     public static final String PROGRESS_READY="Ready";
     /** terminating the program */
     public static final String PROGRESS_EXIT="Exiting program....";
     /** creating a list of save game definitions */
     public static final String PROGRESS_CREATE_DEF_LIST="Creating save definition list....";
     /** creating a backup file */
     public static final String PROGRESS_CREATE_BACKUP_FOR="Creating backup for: ";
     /** sorting save game defintion list */
     public static final String PROGRESS_SORT_DEF_LIST="Sorting save game definition list....";
     /** sorting property list */
     public static final String PROGRESS_SORT_PROPERTY_LIST="Sorting property list....";
}