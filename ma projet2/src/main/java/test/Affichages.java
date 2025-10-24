package test;
import classes.*;
import service.*;
import service.impl.*;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class Affichages {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static void main(String[] args) {
        ProjetService projSrv = new ProjetServiceImpl();
        TacheService tacheSrv = new TacheServiceImpl();
        EmployeService empSrv = new EmployeServiceImpl();


// Suppose qu’on affiche le projet ID=1
        Projet p = projSrv.findById(1L);
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


// Tâches d’un employé
        Employe ali = empSrv.findById(1L);
        System.out.println("\nTâches réalisées par l’employé: " + ali.getNom());
        for (Tache t: empSrv.tachesRealiseesParEmploye(ali.getId())) {
            System.out.println("- " + t.getNom());
        }


// Projets gérés par un employé
        System.out.println("\nProjets gérés par " + ali.getNom());
        for (Projet pr: empSrv.projetsGeresParEmploye(ali.getId())) {
            System.out.println("- "+pr.getNom());
        }


// Requête nommée: prix > 1000 DH
        System.out.println("\nTâches avec prix > 1000 DH");
        for (Tache t: tacheSrv.tachesPrixSup(1000)) {
            System.out.printf("- %-14s (%.2f DH)\n", t.getNom(), t.getPrix());
        }
    }
}