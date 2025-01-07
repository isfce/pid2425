package org.isfce.pid.service;

import java.util.List;

import org.isfce.pid.controller.exceptions.NotExistException;
import org.isfce.pid.dao.IUserDao;
import org.isfce.pid.model.User;
import org.isfce.pid.model.dto.UserDto;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {
	IUserDao daoUser;

	public UserService(IUserDao daoUser) {
		super();
		this.daoUser = daoUser;
	}

	public Double crediterUser(String username, Double montant) {
		var oUser = daoUser.findById(username);

		User user = oUser.orElseThrow(() -> new NotExistException(username));
		Double solde=user.crediter(montant);
		daoUser.save(user);
		return solde;

	}

	public List<UserDto> getAllUserDto() {
		return daoUser.getAllUserDto();
	}

}
