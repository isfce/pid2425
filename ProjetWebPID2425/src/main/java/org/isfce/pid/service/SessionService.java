package org.isfce.pid.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.isfce.pid.model.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@EnableScheduling
public class SessionService {

	private List<Session> sessions = new ArrayList<>(3);

	public SessionService(@Value("${cafet.session.matin.stop-time}") String cronMatin,
			@Value("${cafet.session.apm.stop-time}") String cronApm,
			@Value("${cafet.session.soir.stop-time}") String cronSoir) {
		// Création des sessions dans l'ordre chronologique
		sessions.add(new Session("MATIN", extractHeureFromCron(cronMatin)));
		sessions.add(new Session("APM", extractHeureFromCron(cronApm)));
		sessions.add(new Session("SOIR", extractHeureFromCron(cronSoir)));

	}

	/**
	 * extraction de l'heure Il faut que les 2 paramètres "cron" contiennent que des
	 * valeurs ici
	 * 
	 * @param cron
	 * @return
	 */
	private LocalTime extractHeureFromCron(String cron) {
		// "0 30 9 * 0 1-6" ==> crée 9h30
		String[] tokens = cron.split(" ");
		return LocalTime.of(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1]));
	}

	/**
	 * Retourne les sessions actives
	 * 
	 * @return
	 */
	public List<Session> getActiveSession() {
		return sessions.stream().filter((s) -> s.estOuverte()).toList();
	}

	/**
	 * ouvre les sessions le matin en semaine
	 */
	@Scheduled(cron = "${cafet.session.activate.on-time}")
	private void activateSession() {
		sessions.forEach((s) -> s.ouvrir());
	}

	/**
	 * cloture la session du matin
	 */
	@Scheduled(cron = "${cafet.session.matin.stop-time}")
	private void closeMatin() {
		sessions.get(0).cloture();
		log.info("Session Matin clôturée: " + sessions);
	}

	/**
	 * cloture la session de l'après-midi
	 */
	@Scheduled(cron = "${cafet.session.apm.stop-time}")
	private void closeApm() {
		sessions.get(1).cloture();
		log.info("Session APM clôturée: " + sessions);
	}

	/**
	 * cloture la session du soir
	 */
	@Scheduled(cron = "${cafet.session.soir.stop-time}")
	private void closeSoir() {
		sessions.get(2).cloture();
		log.info("Session Soir clôturée: " + sessions);
	}

}
