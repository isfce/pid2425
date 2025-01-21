package org.isfce.pid.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "TSANDWICHES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)

public class Sandwiches extends Article {
	
	public Sandwiches(String code, String nom, boolean disponible, BigDecimal prix) {
		super(code, nom, disponible);
		this.prix = prix;
	}

	@Getter
	@Setter
	@Column(precision = 4, scale = 2)
	private BigDecimal prix;
}
