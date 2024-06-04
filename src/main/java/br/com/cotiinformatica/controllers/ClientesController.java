package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ConsultarClienteDto;
import br.com.cotiinformatica.dtos.CriarClienteDto;
import br.com.cotiinformatica.dtos.EditarClienteDto;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.repositories.ClienteRepository;

@RestController // define a classe como um controlador de API REST
@RequestMapping(value = "/api/clientes") // define o ENDPOINT da classe
public class ClientesController {

	/*
	 * Método na API para gerar um serviço de cadastro de cliente (POST)
	 */
	@PostMapping("criar")
	public String criar(@RequestBody CriarClienteDto dto) {

		try {

			Cliente cliente = new Cliente();

			cliente.setId(UUID.randomUUID());
			cliente.setNome(dto.getNomeCliente());
			cliente.setEmail(dto.getEmailCliente());
			cliente.setTelefone(dto.getTelefoneCliente());

			ClienteRepository clienteRepository = new ClienteRepository();
			clienteRepository.create(cliente);

			return "Cliente cadastrado com sucesso.";
		} catch (Exception e) {
			return "Erro: " + e.getMessage();
		}
	}

	/*
	 * Método na API para gerar um serviço de edição de cliente (PUT)
	 */
	@PutMapping("editar")
	public String editar(@RequestBody EditarClienteDto dto) {

		try {

			Cliente cliente = new Cliente();

			cliente.setId(dto.getIdCliente());
			cliente.setNome(dto.getNomeCliente());
			cliente.setEmail(dto.getEmailCliente());
			cliente.setTelefone(dto.getTelefoneCliente());

			ClienteRepository clienteRepository = new ClienteRepository();
			clienteRepository.update(cliente);

			return "Cliente atualizado com sucesso.";
		} catch (Exception e) {
			return "Erro: " + e.getMessage();
		}
	}

	/*
	 * Método na API para gerar um serviço de exclusão de cliente (DELETE)
	 */
	@DeleteMapping("excluir/{idCliente}")
	public String delete(@PathVariable("idCliente") UUID idCliente) {

		try {

			// excluindo o cliente no banco de dados
			ClienteRepository clienteRepository = new ClienteRepository();
			clienteRepository.delete(idCliente);

			return "Cliente excluído com sucesso";
		} catch (Exception e) {
			return "Erro: " + e.getMessage();
		}
	}

	/*
	 * Método na API para consultar todos os clientes
	 */
	@GetMapping("consultar")
	public List<ConsultarClienteDto> getAll() {

		try {

			// consultar todos os clientes obtidos do banco de dados
			ClienteRepository clienteRepository = new ClienteRepository();
			List<Cliente> lista = clienteRepository.findAll();

			// criando uma lista de objetos da classe DTO
			List<ConsultarClienteDto> clientes = new ArrayList<ConsultarClienteDto>();

			// percorrendo a lista de clientes obtida do banco de dados
			for (Cliente cliente : lista) {
				ConsultarClienteDto dto = new ConsultarClienteDto();
				dto.setIdCliente(cliente.getId());
				dto.setNomeCliente(cliente.getNome());
				dto.setEmailCliente(cliente.getEmail());
				dto.setTelefoneCliente(cliente.getTelefone());

				clientes.add(dto);
			}

			return clientes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
