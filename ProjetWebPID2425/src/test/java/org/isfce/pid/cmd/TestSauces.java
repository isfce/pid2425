package org.isfce.pid.cmd;

import static org.junit.jupiter.api.Assertions.*;

import org.isfce.pid.dao.ISaucesDao;
import org.isfce.pid.model.Sauces;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class TestSauces {
	@Autowired
	ISaucesDao daoSauce;

	@Test
	void test1() {
		Sauces s1= new Sauces("SA1","test1",true);
		daoSauce.save(s1);
		var oS1= daoSauce.findById("SA1");
		assertTrue(oS1.isPresent());
		daoSauce.deleteById("SA1");
		assertFalse(daoSauce.existsById("SA1"));	
	}

}
