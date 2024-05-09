rem x1 = Shell(sPath + sFile, vbNormalFocus)

set EQUIPMENT=%1
set SLOT=%2
set DESCRIPTION=%3

:: Change Current Directory to the location of this batch file
:: http://ss64.com/nt/cd.html
CD /d "%~dp0"

:: Open the command prompt, and type command (CMD /k for development)
:: http://ss64.com/nt/cmd.html

:: https://ss64.com/nt/syntax-args.html

:: ---------------------------------
:: All parameters:
:: CMD /c %JAVA_HOME%bin\java -cp Inventory_01_port-1.0-SNAPSHOT.jar;C:\UTILS_WO\00_dependency205\* com.mxb.inventory.Inventory_01_port_port %*

:: ---------------------------------
:: Development :
:: CMD /k %JAVA_HOME%bin\java -cp Inventory_01_port-1.0-SNAPSHOT.jar;C:\UTILS_WO\00_dependency205\* com.mxb.inventory.Inventory_01_port %EQUIPMENT% %SLOT% %DESCRIPTION%

CMD /c %JAVA_HOME%bin\java -cp Inventory_01_port-1.0-SNAPSHOT.jar;C:\UTILS_WO\00_dependency205\* com.mxb.inventory.Inventory_01_port %EQUIPMENT% %SLOT% %DESCRIPTION%
