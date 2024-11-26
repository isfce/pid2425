package org.isfce.pid.cmd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.isfce.pid.dao.ICommandeDao;
import org.isfce.pid.dao.ILigneCmdDao;
import org.isfce.pid.model.Commande;
import org.isfce.pid.model.LigneCmd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("dev")
public class TestCommande {
	@Autowired
	ICommandeDao daoCmd;
	@Autowired
	ILigneCmdDao daoLigne;

	@Test
	public void test1() {
		Commande cmd1 = new Commande(LocalDate.now());
		cmd1.getLignes().add(new LigneCmd(cmd1, "Steak", 1));
		cmd1.getLignes().add(new LigneCmd(cmd1, "Eau", 5));
		cmd1 = daoCmd.save(cmd1);
		assertNotNull(cmd1.getNum());
		var liste = cmd1.getLignes();
		assertEquals(2, liste.size());
		assertNotNull(liste.get(0).getNum());
		assertNotNull(liste.get(1).getNum());
	}

	@Test
	public void test2() {
		var oCmd = daoCmd.findById(4);
		assertTrue(oCmd.isPresent());
		Commande cmd = oCmd.get();
		var liste=cmd.getLignes();
		assertEquals(2, liste.size());
		assertNotNull(liste.get(0).getNum());
		assertNotNull(liste.get(1).getNum());
	}

}
