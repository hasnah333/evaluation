@echo off
echo Exécution du projet Etat Civil avec H2 (base de données en mémoire)...

REM Vérifier que les classes sont compilées
if not exist "target\classes\AppTestH2.class" (
    echo Erreur: Classes H2 non compilées. Exécutez d'abord compile.bat
    pause
    exit /b 1
)

REM Exécuter l'application avec H2
echo Lancement de l'application avec H2...
java -cp "target/lib/*;target/classes" AppTestH2

echo.
echo Application terminée.
pause
