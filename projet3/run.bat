@echo off
echo Exécution du projet Etat Civil...

REM Vérifier que les classes sont compilées
if not exist "target\classes\AppTest.class" (
    echo Erreur: Classes non compilées. Exécutez d'abord compile.bat
    pause
    exit /b 1
)

REM Exécuter l'application
echo Lancement de l'application...
java -cp "target/lib/*;target/classes" AppTest

echo.
echo Application terminée.
pause
