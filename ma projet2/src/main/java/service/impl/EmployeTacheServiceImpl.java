package service.impl;


import classes.EmployeTache;
import service.EmployeTacheService;


public class EmployeTacheServiceImpl extends AbstractHibernateDao<EmployeTache> implements EmployeTacheService {
    public EmployeTacheServiceImpl() { super(EmployeTache.class); }
}