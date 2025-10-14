package com.tp.metier;

import com.tp.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("metier")
public class MetierImpl implements IMetier {
    @Autowired
    private IDao dao;                 // pas de @Qualifier ici
    @Override
    public double calcul() { return dao.getValue() * 2; }

    @PostConstruct
    private void init() {
        System.out.println("[TRACE] DAO injecté = " + dao.getClass().getSimpleName());
    }
}
