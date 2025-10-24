@echo off
echo Compilation et execution de la version simplifiee du projet...

REM Créer le dossier target/classes
mkdir target\classes 2>nul

REM Compiler les classes simples
echo Compilation des classes...
javac -d target\classes -cp "src\main\java" src\main\java\simple\*.java src\main\java\TestAppSimple.java

if %errorlevel% equ 0 (
    echo Compilation reussie !
    echo.
    echo Execution de TestAppSimple...
    echo ========================================
    java -cp "target\classes" TestAppSimple
    echo ========================================
    echo.
    echo Execution terminee !
) else (
    echo Erreur de compilation !
)

pause
