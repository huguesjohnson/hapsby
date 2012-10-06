echo on
rem ******************
rem * Hapsby         *
rem * win32 build    *
rem * last revised   *
rem * June 11, 2001  *
rem * -------------- *
rem * copyright 2001 *
rem * Hugues Johnson *
rem ******************
rem
rem ******************
rem * clean previous *
rem * build & files  *
rem ******************
del *.class
del ..\bin\Hapsby.jar
rem
rem ******************
rem * build class    *
rem * files          *
rem ******************
javac -classpath .\ -sourcepath .\ -O -deprecation NumberConversions.java
javac -classpath .\ -sourcepath .\ -O -deprecation NumberConverterPanel.java
javac -classpath .\ -sourcepath .\ -O -deprecation NumberConverter.java
javac -classpath .\ -sourcepath .\ -O -deprecation ViewPane.java
javac -classpath .\ -sourcepath .\ -O -deprecation BinaryViewPane.java
javac -classpath .\ -sourcepath .\ -O -deprecation DecimalViewPane.java
javac -classpath .\ -sourcepath .\ -O -deprecation HexViewPane.java
javac -classpath .\ -sourcepath .\ -O -deprecation OctalViewPane.java
javac -classpath .\ -sourcepath .\ -O -deprecation FileViewerActionCommands.java
javac -classpath .\ -sourcepath .\ -O -deprecation FileViewerMenu.java
javac -classpath .\ -sourcepath .\ -O -deprecation FileViewerWindow.java
javac -classpath .\ -sourcepath .\ -O -deprecation FileViewer.java
javac -classpath .\ -sourcepath .\ -O -deprecation IniFile.java
javac -classpath .\ -sourcepath .\ -O -deprecation ZipCompressor.java
javac -classpath .\ -sourcepath .\ -O -deprecation ZipDecompressor.java
javac -classpath .\ -sourcepath .\ -O -deprecation SplashScreen.java
rem javac -classpath .\ -sourcepath .\ -O -deprecation FortuneCookie.java
javac -classpath .\ -sourcepath .\ -O -deprecation HapsbyBackup.java
javac -classpath .\ -sourcepath .\ -O -deprecation HapsbyActionCommands.java
javac -classpath .\ -sourcepath .\ -O -deprecation HapsbyProgressCaptions.java
javac -classpath .\ -sourcepath .\ -O -deprecation HapsbyToolbar.java
javac -classpath .\ -sourcepath .\ -O -deprecation HapsbyMenu.java
javac -classpath .\ -sourcepath .\ -O -deprecation SaveGame.java
javac -classpath .\ -sourcepath .\ -O -deprecation SaveGameProperty.java
javac -classpath .\ -sourcepath .\ -O -deprecation SaveGameDefinition.java
javac -classpath .\ -sourcepath .\ -O -deprecation HapsbyBackupFilter.java
javac -classpath .\ -sourcepath .\ -O -deprecation DirectoryFilter.java
javac -classpath .\ -sourcepath .\ -O -deprecation SaveGameFilter.java
javac -classpath .\ -sourcepath .\ -O -deprecation SaveDefFilter.java
javac -classpath .\ -sourcepath .\ -O -deprecation HapsbyApp.java
javac -classpath .\ -sourcepath .\ -O -deprecation HapsbyCommandLine.java
javac -classpath .\ -sourcepath .\ -O -deprecation GameListPanel.java
javac -classpath .\ -sourcepath .\ -O -deprecation FilePanel.java
javac -classpath .\ -sourcepath .\ -O -deprecation HapsbyMainWindow.java
javac -classpath .\ -sourcepath .\ -O -deprecation Hapsby.java
rem
rem ******************
rem * create jar,    *
rem * this is very   *
rem * painful. One   *
rem * tiny mistake & *
rem * the jar file   *
rem * is fubar       *
rem ******************
jar cvfm Hapsby.jar hapsby_manifest.txt *.class
rem
rem ******************
rem * move jar file  *
rem * to \bin\ dir.  *
rem ******************
move Hapsby.jar ..\bin\
rem
rem ******************
rem * remove classes *
rem ******************
del *.class


