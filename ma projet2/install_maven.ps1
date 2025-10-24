# Script pour télécharger et installer Maven
$mavenVersion = "3.9.9"
$mavenUrl = "https://archive.apache.org/dist/maven/maven-3/$mavenVersion/binaries/apache-maven-$mavenVersion-bin.zip"
$downloadPath = "$env:TEMP\maven.zip"
$installPath = "$env:USERPROFILE\maven"

Write-Host "Téléchargement de Maven $mavenVersion..."
Invoke-WebRequest -Uri $mavenUrl -OutFile $downloadPath

Write-Host "Extraction de Maven..."
Expand-Archive -Path $downloadPath -DestinationPath "$env:USERPROFILE" -Force

Write-Host "Ajout de Maven au PATH..."
$mavenBinPath = "$installPath\bin"
$currentPath = [Environment]::GetEnvironmentVariable("PATH", "User")
if ($currentPath -notlike "*$mavenBinPath*") {
    [Environment]::SetEnvironmentVariable("PATH", "$currentPath;$mavenBinPath", "User")
    $env:PATH += ";$mavenBinPath"
}

Write-Host "Maven installé avec succès!"
Write-Host "Redémarrez votre terminal ou exécutez: refreshenv"
