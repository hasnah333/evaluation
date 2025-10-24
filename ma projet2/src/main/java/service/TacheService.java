package service;
import classes.*;
import java.time.LocalDate;
import java.util.List;

public interface TacheService extends dao.IDao<Tache> {
    List<Tache> tachesPrixSup(double minPrix); // via requête nommée
    List<Tache> tachesRealiseesEntre(LocalDate debut, LocalDate fin);
}