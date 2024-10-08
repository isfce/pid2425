package org.isfce.pid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TGARNITURE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Garniture {
	@Id
	@Column(length = 4)
	private String code;
	
	@Column(length = 30,nullable = false )
	private String nom;
}
