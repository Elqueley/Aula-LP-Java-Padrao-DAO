package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Processo;
import entidades.Advogado;

public class daoProcesso {

	
	public boolean inserir(Processo processo) throws SQLException {
				
		Connection conexao = ConnectionFactory.getConexao();
		
		String sql = "insert into processo (NOME_PROCESSO, ESTADO_PROCESSO, IDADVOGADO) values(? , ? , ?);";
		PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, processo.getNome());
		ps.setString(2, processo.getEstado());
		ps.setInt(3, processo.getAdvogado().getId());
		
		int linhasAfetadas = ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			processo.setId(id);
		}
		
		return (linhasAfetadas == 1 ? true : false);
	}

	public boolean atualizar(Processo processo) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "update processo set descricao = ?, estado = ? where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, processo.getNome());
		ps.setString(2, processo.getEstado());
		ps.setInt(3, processo.getId());
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean excluir(int id) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "delete from processo where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public Processo buscar(int idBuscado) throws SQLException {
		
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from processo where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Processo processo = null;
		
		if( result.next() ) {
			int id = result.getInt("id");
			String nome = result.getString("NOME");
			String estado = result.getString("ESTADO");
			int idadvogado = result.getInt("IDADVOGADO");
			
			DaoAdvogado daoAdvogado = new DaoAdvogado();
			Advogado u = daoAdvogado.buscarPorId(idadvogado);
			
			processo = new Processo(id, nome, estado, u);
		}
		
		return processo;
	}

	public List<Processo> buscarTodas() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from processo";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		List<Processo> processo = new ArrayList<Processo>();
		
		DaoAdvogado daoAdvogado = new DaoAdvogado();

		while( result.next() ) {
			int id = result.getInt("id");
			String nome = result.getString("nome");
			String estado = result.getString("estado");
			int idAdvogado = result.getInt("idAdvogado");
			
			Advogado a = daoAdvogado.buscarPorId(idAdvogado);
			
			Processo p = new Processo (id, nome, estado, a);
	
			processo.add(p);
		}
		
		return processo;
	}

	public List<Processo> pesquisarPorEstado(String texto) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from processo where descricao like ? ";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "%"+texto+"%");
		
		ResultSet result = ps.executeQuery();
		
		List<Processo> processo = new ArrayList<Processo>();
		
		DaoAdvogado daoAdvogado = new DaoAdvogado();
		
		while( result.next() ) {
			int id = result.getInt("id");
			String nome = result.getString("nome");
			String estado = result.getString("estado");
			int idAdvogado = result.getInt("idAdvogado");
			
			Advogado a = daoAdvogado.buscarPorId(idAdvogado);
			Processo p = new Processo(id, nome, estado, a);
	
			processo.add(p);
		}
		
		return processo;
	}
	
	public List<Processo> processoPorAdvogado(String nome) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from processo left join advogado on processo.idAdvogado = advogado.id where advogado.nome = ?;";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, nome);
		
		ResultSet result = ps.executeQuery();
		
		List<Processo> processo = new ArrayList<Processo>();
		
		
		if( result.next() ) {			
			int idAdvogado = result.getInt("idAdvogado");
			String cpf = result.getString("cpf");
			String datan = result.getString("data_nascimento");
			String endereco = result.getString("endereco");
			String oab = result.getString("oab");
			
			Advogado advogado = new Advogado(idAdvogado, nome, cpf,datan,endereco,oab);
			
			do {
				int id = result.getInt("IDPROCESSO");
				String nomep = result.getString("nome");
				String estado = result.getString("estado");
				
				Processo p = new Processo(id,nomep,estado,advogado);
		
				processo.add(p);
			}while(result.next());
		}
		
		return processo;
	}
}
