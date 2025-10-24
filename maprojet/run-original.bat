@echo off
echo Execution du TestApp original avec Maven...

REM Compiler le projet
.\apache-maven-3.9.6\bin\mvn.cmd compile

if %errorlevel% equ 0 (
    echo Compilation reussie !
    echo.
    echo Execution de TestApp...
    echo ========================================
    .\apache-maven-3.9.6\bin\mvn.cmd exec:java -Dexec.mainClass="TestApp"
    echo ========================================
    echo.
    echo Execution terminee !
) else (
    echo Erreur de compilation !
)

pause
