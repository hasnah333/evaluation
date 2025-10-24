package service;

import classes.*;
import java.time.LocalDate;
import java.util.List;


public interface ProjetService extends dao.IDao<Projet> {
    List<Tache> listerTachesPlanifiees(Long projetId);
    List<Tache> listerTachesRealisees(Long projetId); // avec dates réelles non nulles
}