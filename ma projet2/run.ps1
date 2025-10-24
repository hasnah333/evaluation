# Script d'exécution pour le projet de gestion de projets
param(
    [string]$Class = "test.MainTest"
)

Write-Host "=== Exécution du projet de gestion de projets ===" -ForegroundColor Green
Write-Host "Classe à exécuter: $Class" -ForegroundColor Yellow

# Ajouter Maven au PATH si nécessaire
if (-not (Get-Command mvn -ErrorAction SilentlyContinue)) {
    $env:PATH += ";$env:USERPROFILE\apache-maven-3.9.9\bin"
    Write-Host "Maven ajouté au PATH" -ForegroundColor Cyan
}

# Compiler le projet
Write-Host "`n1. Compilation du projet..." -ForegroundColor Cyan
mvn compile

# Copier les dépendances si nécessaire
if (-not (Test-Path "target/dependency")) {
    Write-Host "Copie des dépendances..." -ForegroundColor Cyan
    mvn dependency:copy-dependencies
}

if ($LASTEXITCODE -eq 0) {
    Write-Host "`n2. Exécution de la classe $Class..." -ForegroundColor Cyan
    
    # Construire le classpath
    $classpath = "target/classes"
    $dependencies = Get-ChildItem "target/dependency/*.jar" | ForEach-Object { $_.FullName }
    $classpath += ";" + ($dependencies -join ";")
    
    # Exécuter avec Java
    java -cp $classpath $Class
} else {
    Write-Host "Erreur lors de la compilation" -ForegroundColor Red
}
