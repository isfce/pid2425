package org.isfce.pid.dao;

import java.util.List;

import org.isfce.pid.model.Garniture;
import org.isfce.pid.model.RGarniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IGarnitureJpaDao extends JpaRepository<Garniture, String>{

	List<Garniture> findByDisponible(boolean dispo);
	
	@Query(value = "SELECT * FROM TGARNITURE   where DISPONIBLE= :dispo  order by nom",nativeQuery = true)
	List<Garniture> garnitureDiponibles(@Param(value = "dispo") boolean dispo);
	
	@Query("from TGARNITURE g where g.disponible=:dispo")
	List<Garniture> garnitureDiponibles2(@Param(value = "dispo") boolean dispo);
	
	@Query("select new org.isfce.pid.model.RGarniture(g.code,g.nom,g.disponible) from TGARNITURE g")
	List<RGarniture> listeGarniture();
}
