package factory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modal.Contato;

public class ContatoDAO {

	public void save(Contato contato) throws SQLException {

		String sql = "INSERT INTO contatos(id, nome, idade, datacadastro) VALUES (?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToPostgres();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, contato.getId());
			pstm.setString(2, contato.getNome());
			pstm.setInt(3, contato.getIdade());
			pstm.setDate(4, new Date(contato.getDataCadastro().getTime()));

			pstm.execute();

			pstm.close();

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
			conn.close();
		}

	}

	public ArrayList<Contato> getList() {

		ArrayList<Contato> lista = new ArrayList<Contato>();
		Connection conn = null;
		try {
			conn = ConnectionFactory.createConnectionToPostgres();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from contatos");

			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int idade = rs.getInt("idade");
				Date data = rs.getDate("datacadastro");

				lista.add(new Contato(id, nome, idade, data));

			}

			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}
	
	
	public Contato getContato(int idContato) throws SQLException {
		
		final String SQL = "SELECT * FROM contatos WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;
		Contato contato = new Contato();
		try {
			conn = ConnectionFactory.createConnectionToPostgres();
			pstm = conn.prepareStatement(SQL);
			
			pstm.setInt(1, idContato);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setIdade(rs.getInt("idade"));
				contato.setDataCadastro(rs.getDate("datacadastro"));
			}
			
			pstm.close();
			rs.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();	
		}
		
		return contato;
	}
	
	
	
	public int delete(int idContato) throws SQLException {
		
		Connection conn = null;
		int retorno = 0;
		final String SQL = "DELETE FROM contatos WHERE id = ?";
		
		try {
			conn = ConnectionFactory.createConnectionToPostgres();
			PreparedStatement pStmt  = conn.prepareStatement(SQL);
			
			pStmt.setInt(1, idContato);
			
			retorno = pStmt.executeUpdate();
			pStmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		
		return retorno;
	}
	
	
	public int update(Contato contato) throws SQLException {
		
		Connection conn = null;
		int retorno = 0;
		final String SQL = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ?  WHERE id = ?";
		
		try {
			conn = ConnectionFactory.createConnectionToPostgres();
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, contato.getNome());
			pstmt.setInt(2, contato.getIdade());
			pstmt.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstmt.setInt(4, contato.getId());
			
			retorno = pstmt.executeUpdate();
			
			pstmt.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		return retorno;
	}
	
	
	

}
