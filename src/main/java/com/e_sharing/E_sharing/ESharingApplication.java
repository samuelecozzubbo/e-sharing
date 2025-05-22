package com.e_sharing.E_sharing;

import com.e_sharing.E_sharing.DTO.UserAccountDTO;
import com.e_sharing.E_sharing.DTO.LeadDTO;
import com.e_sharing.E_sharing.Enum.LeadState;
import com.e_sharing.E_sharing.Service.LeadService;
import com.e_sharing.E_sharing.Service.UserAccountService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ESharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ESharingApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserAccountService userAccountService, LeadService leadService) {
		return args -> {
			System.out.println("✅ Avvio test nel main...");

			// Creo un DTO utente
			UserAccountDTO utente = new UserAccountDTO();
			utente.setEmail("mario.rossi@example.com");
			utente.setNome("Mario");
			utente.setCognome("Rossi");
			utente.setUsername("mariorossi");
			utente.setPassword("password123");

			// Salvo l’utente
			try {
				userAccountService.saveUserAccount(utente);
				System.out.println("✔️ Utente salvato con successo.");
			} catch (Exception e) {
				System.out.println("⚠️ Errore durante il salvataggio: " + e.getMessage());
			}

			// Recupero l’utente appena salvato
			userAccountService.findUserByEmail("mario.rossi@example.com")
					.ifPresentOrElse(
							u -> System.out.println("👤 Utente trovato: " + u.getNome() + " " + u.getCognome()),
							() -> System.out.println("❌ Utente non trovato.")
					);

			// Recupero tutti gli utenti
			var utenti = userAccountService.getAllUserAccountsAuto();
			System.out.println("📋 Lista di tutti gli utenti:");
			utenti.forEach(u -> System.out.println("👉 " + u.getEmail()));

			// Salvo un lead associato all'utente
			LeadDTO lead = new LeadDTO();
			lead.setDataAcquisto(LocalDate.now());
			lead.setStatus(LeadState.RENTED); // Assicurati che LeadState.NUOVO sia un valore valido
			lead.setSconto(10);
			lead.setUserEmail("mario.rossi@example.com");

			try {
				String result = leadService.saveLead(lead);
				System.out.println("✔️ " + result);
			} catch (Exception e) {
				System.out.println("⚠️ Errore durante il salvataggio del lead: " + e.getMessage());
			}

			// Recupero tutti i lead
			var leads = leadService.getAllLeadsGeneric();
			System.out.println("📋 Lista di tutti i lead:");
			leads.forEach(l -> System.out.println
					("👉 Lead ID: " + l.getId() + ", Email utente: " + l.getUserEmail() + ", Data Acquisto: " + l.getDataAcquisto() + ", Stato: " + l.getStatus() + ", Sconto: " + l.getSconto()));

			//Cancello un lead
			leadService.deleteLead(252L);
			//Mostro tutti i lead
			leads = leadService.getAllLeadsGeneric();
			System.out.println("📋 Lista di tutti i lead:");
			leads.forEach(l -> System.out.println
					("👉 Lead ID: " + l.getId() + ", Email utente: " + l.getUserEmail() + ", Data Acquisto: " + l.getDataAcquisto() + ", Stato: " + l.getStatus() + ", Sconto: " + l.getSconto()));

			leadService.deleteAllLeads();
			//Mostro utenti
			utenti = userAccountService.getAllUserAccountsAuto();
			utenti.forEach(u -> System.out.println("👉" + u.toString()));
		};
	}


}
