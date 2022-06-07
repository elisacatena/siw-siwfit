package it.uniroma3.siw.siwfit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiwSiwfitApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SiwSiwfitApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

//		Trainer t = new Trainer();
//		t.setNome("simone");
//		t.setCognome("di ienno");
//		tr.save(t);
//		
//	 	Categoria c1 = new Categoria();
//		Categoria c2 = new Categoria();
//		
//		c1.setNome("cat1");
//		c2.setNome("cat2");
//		
//		Corso co1 = new Corso();
//		Corso co2 = new Corso();
//		
//		co1.setNome("corso1");
//		co2.setNome("corso2");
//		co1.setNumeroMaxPersone(0);
//		co2.setNumeroMaxPersone(2);
//		
//		co1.setTrainer(t);
//		co2.setTrainer(t);
//		t.getCorsi().add(co1);
//		t.getCorsi().add(co2);
//		
//		co1.setData("2022");
//		co2.setData("2023");
//		
//		Corso co3 = new Corso();
//		Corso co4 = new Corso();
//		
//		co3.setNome("corso3");
//		co4.setNome("corso4");
//		co3.setNumeroMaxPersone(3);
//		co4.setNumeroMaxPersone(4);
//		
//		co3.setData("2022");
//		co4.setData("2023");
//		
////		co1.setCategoria(c1);
////		co2.setCategoria(c2);
//		
//		List<Corso> corsi = new LinkedList<Corso>();
//		corsi.add(co1);
//		corsi.add(co2);
//		
//		List<Corso> corsi2 = new LinkedList<Corso>();
//		corsi2.add(co3);
//		corsi2.add(co4);
//		
//		c1.setCorsi(corsi);
//		c2.setCorsi(corsi2);
//		
//		User u1 = new User();
//		
//		ur.save(u1);
//		cor.save(co1);
//		cor.save(co2);
//		cor.save(co3);
//		cor.save(co4);
//		cr.save(c1);
//		cr.save(c2);
//		
//		
	}

}
