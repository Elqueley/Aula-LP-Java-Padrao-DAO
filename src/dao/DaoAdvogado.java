package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Advogado;
import entidades.Processo;

public class DaoAdvogado {
	
	public boolean inserir(Advogado advogado) throws SQLException {
		
		Connection conexao = ConnectionFactory.getConexao();
		
		String sql = "insert into advogado (id, oab, nome,cpf,data_nascimento,endereco) values(?,?,?,?,?,?);";
		PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, advogado.getOab());
		ps.setString(2, advogado.getNome());
		ps.setString(3, advogado.getCpf());
		ps.setString(4, advogado.getDatan());
		ps.setString(5, advogado.getEndereco());

		int linhasAfetadas = ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			advogado.setId(id);
		}
		
		return (linhasAfetadas == 1 ? true : false);
	}
	
	public boolean atualizarDados(Advogado advogado) throws SQLException {
		return true;
	}
	
	public boolean atualizarSenha(Advogado advogado) throws SQLException {
		return true;
	}

	public boolean excluir(int id) throws SQLException {
		return true;
	}
	
	public Advogado buscarPorId(int idBuscado) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from advogado where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Advogado advogado = null;
		
		if( result.next() ) {
			
			int id = result.getInt("id");
			String oab = result.getString("oab");
			String nome = result.getString("nome");
			String cpf = result.getString("cpf");
			String datan = result.getString("data_nascimento");
			String endereco = result.getString("endereco");
			
			advogado = new Advogado(id, oab, nome,cpf,datan,endereco);
		}
		
		return advogado;
	}
	
	public Advogado buscarPorNome(String nome) throws SQLException {
		return null;
	}
	
	public List<Advogado> buscarTodos() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from advogado";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		List<Advogado> advogado = new ArrayList<Advogado>();
		
		while( result.next() ) {
			int id = result.getInt("id");
			String oab = result.getString("oab");
			String nome = result.getString("nome");
			String cpf = result.getString("cpf");
			String datan = result.getString("data_nascimento");
			String endereco = result.getString("endereco");
			
			Advogado advogado1 = new Advogado(id, oab, nome,cpf,datan,endereco);
	
			advogado.add(advogado1);
		}
		
		return advogado;
	}
}
