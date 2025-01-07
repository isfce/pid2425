package org.isfce.pid.dao;

import java.util.List;

import org.isfce.pid.model.User;
import org.isfce.pid.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserDao extends JpaRepository<User, String> {
	@Modifying
	@NativeQuery("update TUSER u set u.solde = u.solde + :montant where u.username= :user")
	void crediterUser(@Param("user") String username, @Param("montant") Double montant);

	@Query("select u.solde from TUSER u where u.username=?1")
	Double soldeByUsername(String username);

	@Query("select new org.isfce.pid.model.dto.UserDto(u.username,u.email,u.solde) from TUSER u")
	List<UserDto> getAllUserDto();
}
