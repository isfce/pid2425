package org.isfce.pid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity(name = "TArticle")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode
@Data
public class Article {
	@Id
	@Column(length = 4, updatable = false)
	@NotBlank
	private String code;

	@Column(length = 30, nullable = false, updatable = false)
	@NotBlank
	private String nom;
	@Column
	private boolean disponible;
}
