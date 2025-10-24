package test;
import classes.*;
import service.*;
import service.impl.*;


import java.time.LocalDate;


public class SeedData {
    public static void main(String[] args) {
        EmployeService empSrv = new EmployeServiceImpl();
        ProjetService projSrv = new ProjetServiceImpl();
        TacheService tacheSrv = new TacheServiceImpl();


        Employe ali = empSrv.save(new Employe("ALI", "Karim"));
        Employe sara = empSrv.save(new Employe("SARA", "Mansour"));


        Projet p = projSrv.save(new Projet("Gestion de stock", LocalDate.of(2013,1,14), ali));


        Tache t1 = tacheSrv.save(new Tache("Analyse", LocalDate.of(2013,2,1), LocalDate.of(2013,2,10), 1500, p));
        Tache t2 = tacheSrv.save(new Tache("Conception", LocalDate.of(2013,3,1), LocalDate.of(2013,3,10), 1200, p));
        Tache t3 = tacheSrv.save(new Tache("Développement", LocalDate.of(2013,4,1), LocalDate.of(2013,4,20), 5000, p));


// Renseigner les dates réelles (exemple issue Énoncé)
        t1.setDateDebutReelle(LocalDate.of(2013,2,10)); t1.setDateFinReelle(LocalDate.of(2013,2,20)); tacheSrv.update(t1);
        t2.setDateDebutReelle(LocalDate.of(2013,3,10)); t2.setDateFinReelle(LocalDate.of(2013,3,15)); tacheSrv.update(t2);
        t3.setDateDebutReelle(LocalDate.of(2013,4,10)); t3.setDateFinReelle(LocalDate.of(2013,4,25)); tacheSrv.update(t3);


        System.out.println("Données insérées.");
    }
}