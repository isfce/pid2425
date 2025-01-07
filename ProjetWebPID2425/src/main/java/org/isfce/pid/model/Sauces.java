package org.isfce.pid.model;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "TSauces")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Sauces extends Article {
	public Sauces(String code, String nom, boolean dispo) {
		super(code, nom, dispo);
	}

}
