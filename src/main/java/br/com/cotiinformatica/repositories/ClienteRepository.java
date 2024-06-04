package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ClienteRepository {

	/*
	 * Método para incluir um cliente no banco de dados
	 */
	public void create(Cliente cliente) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//executando um comando sql (query) para inserir um cliente na tabela do banco
		PreparedStatement statement = connection.prepareStatement
				("insert into cliente(id, nome, email, telefone) values(?,?,?,?)");
		
		statement.setObject(1, cliente.getId());
		statement.setString(2, cliente.getNome());
		statement.setString(3, cliente.getEmail());
		statement.setString(4, cliente.getTelefone());
		statement.execute();
		
		//fechando a conexão
		connection.close();
	}
	
	/*
	 * Método para editar um cliente no banco de dados
	 */
	public void update(Cliente cliente) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//executando um comando sql (query) para atualizar um cliente na tabela do banco
		PreparedStatement statement = connection.prepareStatement
				("update cliente set nome=?, email=?, telefone=? where id=?");
		
		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getTelefone());
		statement.setObject(4, cliente.getId());
		statement.execute();
		
		//fechando a conexão
		connection.close();
	}
	
	/*
	 * Método para excluir um cliente no banco de dados
	 */
	public void delete(UUID id) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//executando o comando sql (query) para excluir um cliente na tabela do banco
		PreparedStatement statement = connection.prepareStatement
				("delete from cliente where id=?");
		
		statement.setObject(1, id);
		statement.execute();
		
		//fechando a conexão
		connection.close();
	}
	
	/*
	 * Método para consultar todos os clientes no banco de dados
	 */
	public List<Cliente> findAll() throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//executando o comando sql (query) para consultar todos os clientes na tabela do banco
		PreparedStatement statement = connection.prepareStatement
				("select * from cliente order by nome");
		
		//executando a consulta e capturar os dados retornados
		ResultSet resultSet = statement.executeQuery();
		
		//declarando uma lista de clientes, inicialmente sem elementos.
		List<Cliente> lista = new ArrayList<Cliente>();
		
		//ler cada registro obtido do banco de dados
		while(resultSet.next()) {
			
			Cliente cliente = new Cliente();
			
			cliente.setId(UUID.fromString(resultSet.getString("id")));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setTelefone(resultSet.getString("telefone"));
			
			lista.add(cliente); //adicionar o cliente dentro da lista
		}
		
		//fechando a conexão
		connection.close();
		
		//retornando a lista
		return lista;
	}
	
	/*
	 * Método para consultar 1 cliente através do ID
	 */
	public Cliente findById(UUID id) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//executando o comando sql (query) para consultar 1 cliente na tabela do banco
		PreparedStatement statement = connection.prepareStatement
				("select * from cliente where id=?");
		
		statement.setObject(1, id);
		ResultSet resultSet = statement.executeQuery();
		
		Cliente cliente = null;
		
		if(resultSet.next()) {
			
			cliente = new Cliente(); //instanciando o cliente
			
			cliente.setId(UUID.fromString(resultSet.getString("id")));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setTelefone(resultSet.getString("telefone"));
		}
		
		//fechando a conexão
		connection.close();
		
		//retornando o cliente ou vazio (null)
		return cliente; 
	}
	
}




