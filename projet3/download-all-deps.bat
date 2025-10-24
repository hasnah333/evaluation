@echo off
echo Téléchargement de toutes les dépendances Hibernate...

REM Créer le dossier lib s'il n'existe pas
if not exist "target\lib" mkdir target\lib

echo Téléchargement des dépendances principales...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/javax/persistence/javax.persistence-api/2.2/javax.persistence-api-2.2.jar' -OutFile 'target\lib\javax.persistence-api-2.2.jar' } catch { Write-Host 'Erreur javax.persistence-api' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/hibernate/hibernate-core/5.6.15.Final/hibernate-core-5.6.15.Final.jar' -OutFile 'target\lib\hibernate-core-5.6.15.Final.jar' } catch { Write-Host 'Erreur hibernate-core' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar' -OutFile 'target\lib\mysql-connector-j-8.4.0.jar' } catch { Write-Host 'Erreur mysql-connector' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/jboss/logging/jboss-logging/3.4.3.Final/jboss-logging-3.4.3.Final.jar' -OutFile 'target\lib\jboss-logging-3.4.3.Final.jar' } catch { Write-Host 'Erreur jboss-logging' }"

echo Téléchargement des dépendances supplémentaires...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/javax/transaction/jta/1.1/jta-1.1.jar' -OutFile 'target\lib\jta-1.1.jar' } catch { Write-Host 'Erreur jta' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy/1.12.23/byte-buddy-1.12.23.jar' -OutFile 'target\lib\byte-buddy-1.12.23.jar' } catch { Write-Host 'Erreur byte-buddy' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/hibernate/common/hibernate-commons-annotations/5.1.2.Final/hibernate-commons-annotations-5.1.2.Final.jar' -OutFile 'target\lib\hibernate-commons-annotations-5.1.2.Final.jar' } catch { Write-Host 'Erreur hibernate-commons-annotations' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/fasterxml/classmate/1.5.1/classmate-1.5.1.jar' -OutFile 'target\lib\classmate-1.5.1.jar' } catch { Write-Host 'Erreur classmate' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/javassist/javassist/3.29.2-GA/javassist-3.29.2-GA.jar' -OutFile 'target\lib\javassist-3.29.2-GA.jar' } catch { Write-Host 'Erreur javassist' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/antlr/antlr/2.7.7/antlr-2.7.7.jar' -OutFile 'target\lib\antlr-2.7.7.jar' } catch { Write-Host 'Erreur antlr' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/jboss/spec/javax/transaction/jboss-transaction-api_1.2_spec/1.1.1.Final/jboss-transaction-api_1.2_spec-1.1.1.Final.jar' -OutFile 'target\lib\jboss-transaction-api_1.2_spec-1.1.1.Final.jar' } catch { Write-Host 'Erreur jboss-transaction-api' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/jboss/jandex/2.4.2.Final/jandex-2.4.2.Final.jar' -OutFile 'target\lib\jandex-2.4.2.Final.jar' } catch { Write-Host 'Erreur jandex' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/fasterxml/classmate/1.5.1/classmate-1.5.1.jar' -OutFile 'target\lib\classmate-1.5.1.jar' } catch { Write-Host 'Erreur classmate' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar' -OutFile 'target\lib\dom4j-1.6.1.jar' } catch { Write-Host 'Erreur dom4j' }"

powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/hibernate/common/hibernate-commons-annotations/5.1.2.Final/hibernate-commons-annotations-5.1.2.Final.jar' -OutFile 'target\lib\hibernate-commons-annotations-5.1.2.Final.jar' } catch { Write-Host 'Erreur hibernate-commons-annotations' }"

echo Téléchargement terminé.
pause
