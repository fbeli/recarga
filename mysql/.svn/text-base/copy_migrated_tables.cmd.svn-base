REM Workbench Table Data copy script
REM 
REM Execute this to copy table data from a source RDBMS to MySQL.
REM Edit the options below to customize it. You will need to provide passwords, at least.
REM 
REM Source DB: Mysql@localhost:3306 (MySQL)
REM Target DB: Mysql@mysql.falemais.kinghost.net:3306


REM Source and target DB passwords
set arg_source_password=
set arg_target_password=
REM Uncomment the following options according to your needs

REM Whether target tables should be truncated before copy
REM set arg_truncate_target=--truncate-target
REM Enable debugging output
REM set arg_debug_output=--log-level=debug3


REM Creation of file with table definitions for copytable

set table_file=wb_tables_to_migrate.txt
TYPE NUL > wb_tables_to_migrate.txt


wbcopytables.exe --mysql-source=root@localhost:3306 --target=falemais@mysql.falemais.kinghost.net:3306  --source-password="%arg_source_password%" --target-password="%arg_target_password%" --table-file="%table_file%" %arg_truncate_target% %arg_debug_output%

REM Removes the file with the tabla definitions
DEL wb_tables_to_migrate.txt


