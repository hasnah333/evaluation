package test;

import classes.*;
import service.*;
import service.impl.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;

public class MainTest {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("=== Démarrage du test de gestion de projets ===\n");
        
        // 1. Insertion des données de test
        System.out.println("1. Insertion des données de test...");
        insertTestData();
        
        // 2. Affichage des résultats
        System.out.println("\n2. Affichage des résultats...\n");
        displayResults();
        
        System.out.println("\n=== Test terminé avec succès ===");
    }
    
    private static void insertTestData() {
        EmployeService empSrv = new EmployeServiceImpl();
        ProjetService projSrv = new ProjetServiceImpl();
        TacheService tacheSrv = new TacheServiceImpl();

        Employe ali = empSrv.save(new Employe("ALI", "Karim"));
        Employe sara = empSrv.save(new Employe("SARA", "Mansour"));

        Projet p = projSrv.save(new Projet("Gestion de stock", LocalDate.of(2013,1,14), ali));

        Tache t1 = tacheSrv.save(new Tache("Analyse", LocalDate.of(2013,2,1), LocalDate.of(2013,2,10), 1500, p));
        Tache t2 = tacheSrv.save(new Tache("Conception", LocalDate.of(2013,3,1), LocalDate.of(2013,3,10), 1200, p));
        Tache t3 = tacheSrv.save(new Tache("Développement", LocalDate.of(2013,4,1), LocalDate.of(2013,4,20), 5000, p));

        // Renseigner les dates réelles
        t1.setDateDebutReelle(LocalDate.of(2013,2,10)); 
        t1.setDateFinReelle(LocalDate.of(2013,2,20)); 
        tacheSrv.update(t1);
        
        t2.setDateDebutReelle(LocalDate.of(2013,3,10)); 
        t2.setDateFinReelle(LocalDate.of(2013,3,15)); 
        tacheSrv.update(t2);
        
        t3.setDateDebutReelle(LocalDate.of(2013,4,10)); 
        t3.setDateFinReelle(LocalDate.of(2013,4,25)); 
        tacheSrv.update(t3);

        System.out.println("Données insérées avec succès.");
    }
    
    private static void displayResults() {
        ProjetService projSrv = new ProjetServiceImpl();
        TacheService tacheSrv = new TacheServiceImpl();
        EmployeService empSrv = new EmployeServiceImpl();

        // Affichage du projet ID=1
        Projet p = projSrv.findById(1L);
        if (p != null) {
            System.out.printf("Projet : %d Nom : %s Date début : %s\n",
                    p.getId(), p.getNom(), p.getDateDebutPrevue());

            System.out.println("Liste des tâches:");
            System.out.println("Num Nom Date Début Réelle Date Fin Réelle");
            List<Tache> tachesReal = projSrv.listerTachesRealisees(p.getId());
            for (Tache t: tachesReal) {
                System.out.printf("%2d %-14s %-16s %s\n",
                        t.getId(), t.getNom(),
                        t.getDateDebutReelle()!=null? DF.format(t.getDateDebutReelle()):"-",
                        t.getDateFinReelle()!=null? DF.format(t.getDateFinReelle()):"-");
            }

            // Tâches planifiées pour le projet
            System.out.println("\nTâches planifiées:");
            for (Tache t: projSrv.listerTachesPlanifiees(p.getId())) {
                System.out.printf("- %s (plan: %s → %s) prix=%.2f DH\n",
                        t.getNom(), DF.format(t.getDateDebutPlan()), DF.format(t.getDateFinPlan()), t.getPrix());
            }
        }

        // Tâches d'un employé
        Employe ali = empSrv.findById(1L);
        if (ali != null) {
            System.out.println("\nTâches réalisées par l'employé: " + ali.getNom());
            for (Tache t: empSrv.tachesRealiseesParEmploye(ali.getId())) {
                System.out.println("- " + t.getNom());
            }

            // Projets gérés par un employé
            System.out.println("\nProjets gérés par " + ali.getNom());
            for (Projet pr: empSrv.projetsGeresParEmploye(ali.getId())) {
                System.out.println("- "+pr.getNom());
            }
        }

        // Requête nommée: prix > 1000 DH
        System.out.println("\nTâches avec prix > 1000 DH");
        for (Tache t: tacheSrv.tachesPrixSup(1000)) {
            System.out.printf("- %-14s (%.2f DH)\n", t.getNom(), t.getPrix());
        }
    }
}
