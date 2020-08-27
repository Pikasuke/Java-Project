import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import com.moi.gestion.models.Client;
import com.moi.gestion.models.Compte;
import com.moi.gestion.models.Operation;

public class Main {

	public static void main(String[] args) {
		
		Client client = new Client(1, "Ali", "Baba");
		System.out.println(client);
		Compte compte1 = new Compte(1, "007", 0, client);
		client.setCompte(compte1);
		System.out.println(compte1);
		LocalDate today = LocalDate.now();
		LocalDate firstDay_2014 = LocalDate.of(2014, Month.JANUARY, 1);
		LocalDate firstDay_2015 = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate firstDay_2016 = LocalDate.of(2016, Month.JANUARY, 1);
		Operation ope1 = new Operation(firstDay_2014, "VERS", 4000, compte1);
		compte1.addOperation(ope1);
		
		Operation ope2 = new Operation(firstDay_2015, "VERS", 5000, compte1);
		compte1.addOperation(ope2);
		
		compte1.addOperation(new Operation(firstDay_2016, "RETR", 200, compte1));
		
		compte1.addOperation(new Operation(today, "RETR", 3000, compte1));
		
		for (Operation o : compte1.getOperations()) {
			System.out.println("Date \t Type \t Montant \t ");
			System.out.println(o.getDate() + "\t " + o.getType() + "\t " + o.getMontant() );
		}
		
		System.out.println(compte1);
		
		System.out.println("Terminé");
		
	}
}
