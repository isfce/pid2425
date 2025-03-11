package org.isfce.pid.model;

import java.time.LocalTime;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Session {
	public enum EtatSession {
		OUVERTE, CLOTUREE, FERMEE
	};

	private final String nom;

	private EtatSession etat;

	private Boolean active;

	private LocalTime heureCloture;

	/**
	 * 
	 * @param nom
	 * @param heureCloture
	 */
	public Session(String nom, LocalTime heureCloture) {
		this.nom = nom;
		this.active = false;
		this.etat = EtatSession.FERMEE;
		this.heureCloture = heureCloture;
	}

	/**
	 * met la session active et la met dans un état fermée
	 */
	public void setActive() {
		active = true;
		etat = EtatSession.FERMEE;
	}

	/**
	 * Pour désactiver une session celle-ci doit être fermée A Adapter
	 */
	public void desactiveSession() {
		if (EtatSession.FERMEE.equals(etat)) {
			active = false;
		} else if (EtatSession.OUVERTE.equals(etat)) {
			// TODO
			// Que faire si des sandwichs ont déjà été commandés??
			active = false;
		}
	}

	/**
	 * Permet de savoir si on peut faire des commandes
	 * 
	 * @return
	 */
	public boolean estOuverte() {
		return active & etat == EtatSession.OUVERTE;
	}

	/**
	 * ouvre une session si elle est active et fermée et que l'heure n'est pas
	 * passée
	 */
	public void ouvrir() {
		if (active && EtatSession.FERMEE.equals(etat) && this.heureCloture.isAfter(LocalTime.now()))
			etat = EtatSession.OUVERTE;
	}

	/**
	 * cloture une session si elle était ouverte et active
	 */
	public void cloture() {
		if (active && EtatSession.OUVERTE.equals(etat))
			etat = EtatSession.CLOTUREE;
	}

	public void fermer() {
		if (active && EtatSession.CLOTUREE.equals(etat))
			etat = EtatSession.FERMEE;
	}

}
