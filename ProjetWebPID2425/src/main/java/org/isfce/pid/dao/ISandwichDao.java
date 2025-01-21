package org.isfce.pid.dao;

import java.util.List;

import org.isfce.pid.model.Sandwiches;
import org.springframework.data.jpa.repository.Query;

public interface ISandwichDao  extends IArticleDao<Sandwiches> {
	@Query("from TSANDWICHES s where s.disponible")
	List<Sandwiches> sandwichDiponibles();

}
