package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import factory.ContatoDAO;
import modal.Contato;

public class Main {
	
public static void main(String[] args) throws SQLException, ParseException {
		
		
			
			
			//Contato	contato = new Contato(6, "Vital danado", 5, new SimpleDateFormat("dd/MM/yyyy").parse("18/11/2018"));
			
			//new ContatoDAO().save(contato);
	
			
			/*
			 * ArrayList<Contato> lista = new ArrayList<Contato>();
			 * 
			 * lista = new ContatoDAO().getList();
			 * 
			 * for (Contato contato : lista) { System.out.println("Id: " + contato.getId() +
			 * " Nome: " + contato.getNome() + " Idade: " + contato.getIdade() +
			 * " Datacadastro: " + contato.getDataCadastro()); }
			 */
			 
		
			//System.out.println( new ContatoDAO().delete(6));
			 
			
			Contato contato = new ContatoDAO().getContato(3);
			
			contato.setNome("Eloisa Morena");
			
			System.out.println( new ContatoDAO().update(contato) );
			
	
	}

}
