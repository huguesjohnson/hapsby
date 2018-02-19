/*
Hapsby - universal save game editor
HapsbyProgressCaptions.java - captions used in the progress bars
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
