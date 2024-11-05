package org.isfce.pid.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TSANDWICHES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sandwiches {
	@Id
	@Column(length = 4,updatable = false)
	private String code;
	
	@Column(length = 30,nullable = false,updatable = false )
	private String nom;
	
	private boolean disponible;
	
	@Column(precision = 4,scale = 2)
	private BigDecimal prix;

}
