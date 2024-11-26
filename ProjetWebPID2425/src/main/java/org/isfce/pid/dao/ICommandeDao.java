package org.isfce.pid.dao;

import org.isfce.pid.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommandeDao extends JpaRepository<Commande, Integer> {

}
