/*
Hapsby - universal save game editor
HapsbyCommandLine.java - routines for parsing and executing command line
Copyright (c) 2000-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import HapsbyActionCommands;
import Hapsby;

/** class HapsbyCommandLine parses a command line and sends commands back to the main program.
* @author Hugues Johnson
*/
final class HapsbyCommandLine{
     /* help character */
     private final static String HELP_CHARACTER="-?";
     /* help string */
     private final static String HELP_STRING="-help";
     /* error message for bad argument count */
     private final static String BAD_ARGUMENT_COUNT="Invalid command line: wrong number of arguments";
     /* error message for bad argument */
     private final static String BAD_ARGUMENT="Invalid argument: ";
     
     /* command line help is stored as an array simply to make the source more readable */
     private final static String[] COMMAND_LINE_HELP={ 
          "Hapsby Copyright (C) 2000-2001 Hugues Johnson\n",
          "Command Line Help\n",
          "Settings:\n",
          "\t-style java\t\tset window style to Java\n",
          "\t-style system\t\tset window style to OS default\n",
          "\t-backup on\t\tturn autobackup on\n",
          "\t-backup off\t\tturn autobackup off\n",
          "\t-backup prompt\t\tprompt before creating backups\n",
          "Commands:\n",
          "\t-open <.hsd file>\t\topen save game for editing\n",
          "\t-restore <.hbk file>\t\trestore backup\n"
     };
     
     /** constructor reads the command line, checks if it's valid, and sends messages back to the main class.
     * @param args String array of command line arguments
     * @param parent main program
     */
     public HapsbyCommandLine(String args[],Hapsby parent){
          int index;
          /* all command line arguments must come in pairs, except help */
          if(args.length%2==0){
               this.parseArguments(args,parent);
          } else if ((args[0].toLowerCase().equals(this.HELP_CHARACTER))||(args[0].toLowerCase().equals(this.HELP_STRING))){
               this.displayCommandLineHelp();
          } else{
               System.out.println(this.BAD_ARGUMENT_COUNT);
          }
     }
     
     /* parseArguments() reads the command line arguments and figures out what to do.
     * @param args String array of command line arguments
     * @param parent main program
     */
     private final void parseArguments(String args[],Hapsby parent){
          /**@TODO find a better way to do this */
          for(int index=0;index<args.length;index++){
               args[index]=args[index].toLowerCase();
               args[index+1]=args[index+1].toLowerCase();
               if((args[index]+" "+args[index+1]).equals(HapsbyActionCommands.ACTION_STYLE_JAVA)){
                    parent.executeCommand(HapsbyActionCommands.ACTION_STYLE_JAVA,null);
               } else if((args[index]+" "+args[index+1]).equals(HapsbyActionCommands.ACTION_STYLE_SYSTEM)){
                    parent.executeCommand(HapsbyActionCommands.ACTION_STYLE_SYSTEM,null);
               } else if((args[index]+" "+args[index+1]).equals(HapsbyActionCommands.ACTION_AUTOBACKUP_ON)){
                    parent.executeCommand(HapsbyActionCommands.ACTION_AUTOBACKUP_ON,null);
               } else if((args[index]+" "+args[index+1]).equals(HapsbyActionCommands.ACTION_AUTOBACKUP_OFF)){
                    parent.executeCommand(HapsbyActionCommands.ACTION_AUTOBACKUP_OFF,null);
               } else if((args[index]+" "+args[index+1]).equals(HapsbyActionCommands.ACTION_AUTOBACKUP_PROMPT)){
                    parent.executeCommand(HapsbyActionCommands.ACTION_AUTOBACKUP_PROMPT,null);
               } else if(args[index].equals(HapsbyActionCommands.ACTION_OPEN)){
                    parent.executeCommand(HapsbyActionCommands.ACTION_OPEN,args[index+1]);
               } else if(args[index].equals(HapsbyActionCommands.ACTION_RESTORE_BACKUP)){
                    parent.executeCommand(HapsbyActionCommands.ACTION_RESTORE_BACKUP,args[index+1]);
               } else{ /* invalid argument */
                    System.out.println(this.BAD_ARGUMENT+args[index]);
               }
          }
     }
     
     /* displays the command line help
     */
     private final void displayCommandLineHelp(){
          for(int q=0;q<this.COMMAND_LINE_HELP.length;q++){
               System.out.println(this.COMMAND_LINE_HELP[q]);
          }
     }
     
     /** toString returns a String representation of this object
      * @return String representation of this object
      */
     public String toString(){
          String tostring=new String(super.toString());
          return(tostring);
     }     
}     
