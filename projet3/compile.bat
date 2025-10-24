@echo off
echo Compilation du projet Etat Civil...

REM Créer le dossier target/classes s'il n'existe pas
if not exist "target\classes" mkdir target\classes

REM Télécharger les dépendances Maven (si Maven est disponible)
echo Tentative de téléchargement des dépendances...
mvn dependency:copy-dependencies -DoutputDirectory=target/lib 2>nul
if errorlevel 1 (
    echo Maven non disponible, compilation manuelle...
    goto :manual_compile
)

REM Compiler avec Maven
echo Compilation avec Maven...
mvn compile
if errorlevel 1 (
    echo Erreur de compilation Maven, tentative manuelle...
    goto :manual_compile
)
goto :end

:manual_compile
echo Compilation manuelle...
javac -cp "target/lib/*" -d target/classes src/main/java/beans/*.java src/main/java/dao/*.java src/main/java/service/*.java src/main/java/util/*.java src/main/java/com/example/*.java src/main/java/AppTest.java
if errorlevel 1 (
    echo Erreur: Impossible de compiler sans les dépendances Maven
    echo Veuillez installer Maven ou télécharger manuellement les JARs suivants:
    echo - javax.persistence-api-2.2.jar
    echo - hibernate-core-5.6.15.Final.jar
    echo - mysql-connector-java-8.4.0.jar
    echo - jboss-logging-3.4.3.Final.jar
    goto :end
)

:end
echo Compilation terminée.
pause
