package service;
import classes.*;
import java.time.LocalDate;
import java.util.List;
public interface EmployeService extends dao.IDao<Employe> {
    List<Tache> tachesRealiseesParEmploye(Long employeId);
    List<Projet> projetsGeresParEmploye(Long employeId);
}