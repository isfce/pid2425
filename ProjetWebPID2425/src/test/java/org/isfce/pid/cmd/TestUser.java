package org.isfce.pid.cmd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.isfce.pid.dao.IUserDao;
import org.isfce.pid.model.User;
import org.isfce.pid.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
class TestUser {
	@Autowired
	IUserDao dao;
	@Autowired
	UserService service;

	@Test
	@Transactional
	void testFindMontantByUsername() {
		var ou = dao.findById("vo");
		assertTrue(ou.isPresent());
		User vo = ou.get();
		Double m1 = vo.getSolde();
		Double mt = 10.9;
		dao.crediterUser("vo", mt);
		Double m2 = dao.soldeByUsername("vo");
		assertEquals(m1 + mt, m2);
	}

	@Test
	@Transactional
	void testServiceCrediter() {
		var ou = dao.findById("vo");
		assertTrue(ou.isPresent());
		User vo = ou.get();
		Double m1 = vo.getSolde();
		Double mt = 10.9;
		Double m2=service.crediterUser("vo", mt);
		assertEquals(m1 + mt, m2);
	}
	
	@Test
	@Transactional
	void testAllUserDto() {
		var liste= service.getAllUserDto();
		assertEquals(2,liste.size());
	}
}
