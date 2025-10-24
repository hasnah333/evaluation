# Script PowerShell pour télécharger les dépendances Maven
$deps = @(
    @{
        groupId = "org.hibernate"
        artifactId = "hibernate-core"
        version = "6.3.1.Final"
        url = "https://repo1.maven.org/maven2/org/hibernate/hibernate-core/6.3.1.Final/hibernate-core-6.3.1.Final.jar"
    },
    @{
        groupId = "jakarta.persistence"
        artifactId = "jakarta.persistence-api"
        version = "3.1.0"
        url = "https://repo1.maven.org/maven2/jakarta/persistence/jakarta.persistence-api/3.1.0/jakarta.persistence-api-3.1.0.jar"
    },
    @{
        groupId = "com.mysql"
        artifactId = "mysql-connector-j"
        version = "8.0.33"
        url = "https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar"
    },
    @{
        groupId = "org.jboss.logging"
        artifactId = "jboss-logging"
        version = "3.5.0.Final"
        url = "https://repo1.maven.org/maven2/org/jboss/logging/jboss-logging/3.5.0.Final/jboss-logging-3.5.0.Final.jar"
    }
)

# Créer le dossier lib s'il n'existe pas
if (!(Test-Path "lib")) {
    New-Item -ItemType Directory -Name "lib"
}

# Télécharger chaque dépendance
foreach ($dep in $deps) {
    $fileName = "$($dep.artifactId)-$($dep.version).jar"
    $filePath = "lib\$fileName"
    
    if (!(Test-Path $filePath)) {
        Write-Host "Téléchargement de $fileName..."
        try {
            Invoke-WebRequest -Uri $dep.url -OutFile $filePath
            Write-Host "✓ $fileName téléchargé avec succès"
        }
        catch {
            Write-Host "✗ Erreur lors du téléchargement de $fileName : $($_.Exception.Message)"
        }
    } else {
        Write-Host "✓ $fileName déjà présent"
    }
}

Write-Host "Téléchargement terminé !"