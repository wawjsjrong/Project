@echo off
@echo Running Testcase 007: Illegal oprator.
@echo ==============================================
@echo The input is:
type testcases\tc-007.infix
@echo ----------------------------------------------
cd bin

rem : Run the testcase with input direction
java Postfix < ..\testcases\tc-007.infix

rem : Compare the expected output
@echo ----------------------------------------------
@echo The output should be: 
type ..\testcases\tc-007.postfix

cd ..
@echo ==============================================
pause
@echo on