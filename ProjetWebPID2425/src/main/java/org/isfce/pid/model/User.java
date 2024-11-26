package org.isfce.pid.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "TUSER")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class User {
	@EqualsAndHashCode.Include
	@Id
	@Column(length = 50, nullable = false)
	private String username;
	@Column(length = 50, nullable = false)
	@Email
	private String email;
	@Column(length = 30, nullable = false)
	private String nom;
	@Column(length = 30)
	private String prenom;
	@Column(scale = 5, precision = 2)
	private BigDecimal compte;
}
