package org.isfce.pid.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"lignes"})
@EqualsAndHashCode(exclude = {"lignes"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;

	private LocalDate date;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cmd",fetch = FetchType.EAGER )
	private List<LigneCmd> lignes = new ArrayList<>();

	public Commande(LocalDate date) {
		super();
		this.date = date;
	}
	
}
