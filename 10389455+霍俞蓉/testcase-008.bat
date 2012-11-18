@echo off
@echo Running Testcase 008: no left operand.
@echo ==============================================
@echo The input is:
type testcases\tc-008.infix
@echo ----------------------------------------------
cd bin

rem : Run the testcase with input direction
java Postfix < ..\testcases\tc-008.infix

rem : Compare the expected output
@echo ----------------------------------------------
@echo The output should be: 
type ..\testcases\tc-008.postfix

cd ..
@echo ==============================================
pause
@echo on