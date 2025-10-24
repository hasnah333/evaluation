@echo off
echo Téléchargement des dépendances Maven...

REM Créer le dossier lib s'il n'existe pas
if not exist "target\lib" mkdir target\lib

echo Téléchargement de javax.persistence-api-2.2.jar...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/javax/persistence/javax.persistence-api/2.2/javax.persistence-api-2.2.jar' -OutFile 'target\lib\javax.persistence-api-2.2.jar' } catch { Write-Host 'Erreur téléchargement javax.persistence-api' }"

echo Téléchargement de hibernate-core-5.6.15.Final.jar...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/hibernate/hibernate-core/5.6.15.Final/hibernate-core-5.6.15.Final.jar' -OutFile 'target\lib\hibernate-core-5.6.15.Final.jar' } catch { Write-Host 'Erreur téléchargement hibernate-core' }"

echo Téléchargement de mysql-connector-java-8.4.0.jar...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.4.0/mysql-connector-java-8.4.0.jar' -OutFile 'target\lib\mysql-connector-java-8.4.0.jar' } catch { Write-Host 'Erreur téléchargement mysql-connector' }"

echo Téléchargement de jboss-logging-3.4.3.Final.jar...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/jboss/logging/jboss-logging/3.4.3.Final/jboss-logging-3.4.3.Final.jar' -OutFile 'target\lib\jboss-logging-3.4.3.Final.jar' } catch { Write-Host 'Erreur téléchargement jboss-logging' }"

echo Téléchargement terminé.
pause
