@echo off
@echo Running Testcase 009: no left operand;no oprator;no right operand.
@echo ==============================================
@echo The input is:
type testcases\tc-010.infix
@echo ----------------------------------------------
cd bin

rem : Run the testcase with input direction
java Postfix < ..\testcases\tc-010.infix

rem : Compare the expected output
@echo ----------------------------------------------
@echo The output should be: 
type ..\testcases\tc-010.postfix

cd ..
@echo ==============================================
pause
@echo on