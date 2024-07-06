package com.heliton.repository;

// Java //
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

// SQL e Listas //
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// Modelos //
import com.heliton.model.Beneficiario;
import com.heliton.model.Documento;

@Repository
public class BeneficiarioRepositorioMySQL {
	
	// Atributos (arquivo propriedade) //
    @Value("${spring.datasource.driverClassName}")
	private String DRIVER; 
	@Value("${spring.datasource.url}")
	private String URL; 
	@Value("${spring.datasource.password}")
	private String PASSWORD; 
	@Value("${spring.datasource.username}")
	private String USERNAME; 
	
	Connection connection =  null;
	
	// Conexao MYSQL //	
	public void setConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	// Lista Beneficiario //
    public List<Beneficiario> listarBeneficiario() throws SQLException, ClassNotFoundException {
    	
		List<Beneficiario> beneficiario = new ArrayList<Beneficiario>();
		setConnection();
		
		String sql = "select id, nome, telefone, datanascimento, datainclusao, dataatualizacao from Beneficiario ";
		
		PreparedStatement preparedStmt;
		preparedStmt = connection.prepareStatement(sql);
		ResultSet result = preparedStmt.executeQuery();
		while(result.next()) {
			Beneficiario lst = new Beneficiario();
			lst.setId(result.getInt("Id"));				
			lst.setNome(result.getString("Nome"));
			lst.setTelefone(result.getString("Telefone"));
			lst.setDataNascimento(result.getDate("DataNascimento"));
			lst.setDataInlusao(result.getDate("DataInclusao"));
			lst.setDataAtualizacao(result.getDate("DataAtualizacao"));
			beneficiario.add(lst);
		}
		preparedStmt.close();
		
		return beneficiario;
    }	
	
	// Lista Beneficiario Documento //    
    public List<Documento> listarBeneficiarioDocumento(int id) throws SQLException, ClassNotFoundException {
    	
		setConnection();

    	List<Documento> documento = new ArrayList<Documento>();

		String sql = "select IdDocumento, Id, TipoDocumento, Descricao, datainclusao, dataatualizacao from Documento ";			
		sql += " where id = " + id;
		
		PreparedStatement preparedStmt;
		preparedStmt = connection.prepareStatement(sql);
		ResultSet result = preparedStmt.executeQuery();
		while(result.next()) {
			Documento lst = new Documento();
			lst.setIdDocumento(result.getInt("IdDocumento"));				
			lst.setId(result.getInt("Id"));				
			lst.setTipoDocumento(result.getString("TipoDocumento"));
			lst.setDescricao(result.getString("Descricao"));
			lst.setDataInlusao(result.getDate("DataInclusao"));
			lst.setDataAtualizacao(result.getDate("DataAtualizacao"));
			documento.add(lst);
		}
		preparedStmt.close();			
		return documento;
    }
    
	// Adicionar Beneficiario e Documentos //
	public void adicionarBeneficiario(Beneficiario beneficiario) throws SQLException, ClassNotFoundException {
		
    	setConnection();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dtnascimento = sdf.format(beneficiario.getDataNascimento());
		String dtinclusao = sdf.format(beneficiario.getDataInclusao());		
		
		String sql = "insert into Beneficiario (nome, telefone, datanascimento, datainclusao, dataatualizacao) ";			
		sql += " values(";			
		sql += "'" + beneficiario.getNome() + "',";
		sql += "'" + beneficiario.getTelefone() + "',";
		sql += "'" + dtnascimento + "',";
		sql += "'" + dtinclusao + "',";
		sql += "'" + dtinclusao + "'";
		sql += ")";
		
		PreparedStatement preparedStmt = connection.prepareStatement(sql);
		preparedStmt.execute();
		preparedStmt.close();			
		
		int id = 0;
		sql = "select max(id) as Id from Beneficiario";
		preparedStmt = connection.prepareStatement(sql);
		ResultSet result = preparedStmt.executeQuery();
		while(result.next()) {
			id = result.getInt("Id");
		}	
		preparedStmt.close();
		
		for(int i = 0; i < beneficiario.getDocumento().size(); i++)
		{
			dtinclusao = sdf.format(beneficiario.getDocumento().get(i).getDataInclusao());			
			
			sql = "insert into Documento (id, TipoDocumento, Descricao, datainclusao, dataatualizacao) ";
			sql += " values(";			
			sql += id + ",";				
			sql += "'" + beneficiario.getDocumento().get(i).getTipoDocumento() + "',";
			sql += "'" + beneficiario.getDocumento().get(i).getDescricao() + "',";
			sql += "'" + dtinclusao + "',";
			sql += "'" + dtinclusao + "'";
			sql += ")";
			
			preparedStmt = connection.prepareStatement(sql);
			preparedStmt.execute();
			preparedStmt.close();
		}
    }

    // Alterar Beneficiario //    
	public void alterarBeneficiario(Beneficiario beneficiario) throws SQLException, ClassNotFoundException {

    	setConnection();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dtnascimento = sdf.format(beneficiario.getDataNascimento());
		String atualizacao = sdf.format(beneficiario.getDataAtualizacao());		
		
		String sql = "update Beneficiario set ";
		sql += " nome = '" + beneficiario.getNome() + "',";
		sql += " telefone = '" + beneficiario.getTelefone() + "',";
		sql += " datanascimento = '" + dtnascimento + "',";
		sql += " dataatualizacao = '" + atualizacao + "'";
		sql += " where id = " + beneficiario.getId();
		
		PreparedStatement preparedStmt = connection.prepareStatement(sql);
		preparedStmt.execute();
		preparedStmt.close();
    }
    
	// Deletar Beneficiario //    
    public void deletarBeneficiario(int id) throws SQLException, ClassNotFoundException {
		setConnection();

		String sql = "delete from Documento where id = " + id;			
		
		PreparedStatement preparedStmt = connection.prepareStatement(sql);
		preparedStmt.execute();
		preparedStmt.close();
		
		sql = "delete from Beneficiario where id = " + id;			
		
		preparedStmt = connection.prepareStatement(sql);
		preparedStmt.execute();
		preparedStmt.close();
    }
}