@echo off
echo Compilation du projet avec Java 21...

REM Créer le dossier target/classes
mkdir target\classes 2>nul

REM Compiler les classes Java
javac -d target\classes -cp "src\main\java" src\main\java\classes\*.java src\main\java\dao\*.java src\main\java\service\*.java src\main\java\util\*.java src\main\java\TestApp.java

if %errorlevel% equ 0 (
    echo Compilation réussie !
    echo.
    echo Exécution de TestApp...
    java -cp "target\classes" TestApp
) else (
    echo Erreur de compilation !
)

pause
