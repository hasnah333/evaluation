# Script pour installer MySQL
Write-Host "Téléchargement de MySQL Installer..."

# Télécharger MySQL Installer
$mysqlUrl = "https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-8.0.35.0.msi"
$downloadPath = "$env:TEMP\mysql-installer.msi"

try {
    Write-Host "Téléchargement en cours..."
    Invoke-WebRequest -Uri $mysqlUrl -OutFile $downloadPath
    
    Write-Host "MySQL Installer téléchargé avec succès!"
    Write-Host "Veuillez exécuter manuellement: $downloadPath"
    Write-Host "Ou utilisez une base de données H2 en mémoire pour les tests."
    
} catch {
    Write-Host "Erreur lors du téléchargement: $($_.Exception.Message)"
    Write-Host "Alternative: Utilisez une base de données H2 en mémoire"
}
