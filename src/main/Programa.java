package main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.daoProcesso;
import dao.DaoAdvogado;
import entidades.Processo;
import entidades.Advogado;

public class Programa {
	private static daoProcesso daoProcesso = new daoProcesso();
	private static DaoAdvogado daoAdvogado = new DaoAdvogado();
	
		public static void main(String[] args) throws SQLException {
			
			Scanner scanner = new Scanner(System.in);
			int opcao;
			
			do {
				System.out.println("Digite:");
				System.out.println("1 - Cadastrar processo");
				System.out.println("2 - Atualizar processo");
				System.out.println("3 - Buscar processo");
				System.out.println("4 - Excluir processo");
				System.out.println("5 - Listar processo");
				System.out.println("6 - Pesquisar processo");
				
				System.out.println("7 - Cadastrar Advogado");
				System.out.println("8 - Listar Advogado");
				
				System.out.println("9 - Listar tarefas por Advogado");
				
				System.out.println("0 - Sair");
				
				opcao = Integer.parseInt( scanner.nextLine() );
				
				switch(opcao) {
					case 1:
						cadastrarProcesso();
						break;
					case 2:
						atualizarProcesso();
						break;
					case 3:
						buscarProcesso();
						break;
					case 4:
						excluirProcesso();
						break;
					case 5:
						listarProcesso();
						break;
					case 6:
						pesquisarProcesso();
						break;
					case 7:
						cadastrarAdvogado();
						break;
					case 8:
						listarAdvogado();
						break;
					case 9:
						listarProcessoPorAdvogado();
						break;
					case 0:
						System.out.println("Até mais.");
						break;
					default:
						System.out.println("Opção inválida!");
				}
				
			}while(opcao != 0);
		}
		
		public static void cadastrarProcesso() throws SQLException {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Nome do processo: ");
			String nome = scanner.nextLine();
			
			System.out.println("Estado do processo: ");
			String estado = scanner.nextLine();
			
			System.out.println("Informe o ID do Advogado: ");
			int idAdvogado = Integer.parseInt(scanner.nextLine());

			Advogado a = daoAdvogado.buscarPorId(idAdvogado);
			
			if(a != null) {
				Processo p = new Processo(nome, estado, a);
		
				System.out.println( daoProcesso.inserir( p ) ? "Cadastrou!" : "Falha do cadastro...");
		
				System.out.println("Processo cadastrado sob o ID " + a.getId());
			}else {
				System.out.println("Não existe Advvogado com o ID informado!");
			}
		}
		
		public static void atualizarProcesso() throws SQLException{
			System.out.println("##### Atualizando Processo #####");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Informe o ID: ");
			int id = Integer.parseInt(scanner.nextLine());

			Processo processo = daoProcesso.buscar(id);
			
			if(processo != null) {
				
				System.out.println("Estado atual: " + processo.getEstado());
				System.out.println("Informe o novo estado ou pressione enter:");
				
				String estado = scanner.nextLine();
				
				if(estado.length() > 0) {
					
					processo.setEstado_processo(estado);
				}
				
				System.out.println("Nome atual da processo: " + processo.getEstado());
				System.out.println("Informe o novo nome Estado ou pressione enter:");
				
				String nnome = scanner.nextLine();
				
				if(nnome.length() > 0) {
					
					processo.setNome(nnome);
				}
				
				if( daoProcesso.atualizar(processo) ) {
					System.out.println("Processo atualizada!");
				}else {
					System.out.println("Houve um erro ao atualizar.");
				}
				
			}else {
				System.out.println("Erro ao localizar Processo. O Processo "+ id +" existe?");
			}
		}
		
		public static void buscarProcesso() throws SQLException {
			System.out.println("\n##### Buscando Processo por ID ######");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Informe o ID: ");
			int id = Integer.parseInt(scanner.nextLine());

			Processo p = daoProcesso.buscar(id);
			
			if(p != null) {
				System.out.println("ID: " + p.getId());
				System.out.println("Nome: " + p.getNome());
				System.out.println("Estado: " + p.getEstado());
				System.out.println("Advogado: " + p.getAdvogado().getNome() + "\n");
			}else {
				System.out.println("Processo não existe...");
			}
		}

		public static void excluirProcesso() throws SQLException{
			System.out.println("\n##### Excluindo Processo por ID ######");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Informe o ID: ");
			int id = Integer.parseInt(scanner.nextLine());

			boolean r = daoProcesso.excluir(id);
			
			if( r ) {
				System.out.println("Processo excluída!");
			}else {
				System.out.println("Houve um erro ao excluir. O processo "+ id +" existe?");
			}
		}
		
		public static void listarProcesso() throws SQLException {
			
			System.out.println("\n##### Listar Processo #####\n");
			
			List<Processo> tasks = daoProcesso.buscarTodas();

			Scanner scanner = new Scanner(System.in);
			
			for(Processo t : tasks) {
				System.out.println("ID: " + t.getId());
				System.out.println("Nome: " + t.getNome());
				System.out.println("Estado: " + t.getEstado());
				System.out.println("Advogado: " + t.getAdvogado().getNome() +"\n");

				
				scanner.nextLine();
			}
		}

		public static void pesquisarProcesso() throws SQLException {
			System.out.println("\n##### Buscando Processo por nome ######");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Informe o descrição: ");
			String pesquisa = scanner.nextLine();

			List<Processo> tasks = daoProcesso.pesquisarPorEstado(pesquisa);
			
			for(Processo t : tasks) {
				System.out.println("ID: " + t.getId());
				System.out.println("Nome: " + t.getNome());
				System.out.println("Estado: " + t.getEstado());
				System.out.println("Advogado: " + t.getAdvogado().getNome() +"\n");
				
				scanner.nextLine();
			}
		}

		public static void cadastrarAdvogado() throws SQLException{
			Scanner scanner = new Scanner(System.in);

			System.out.println("Digite seu nome: ");
			String nome = scanner.nextLine();
			
			System.out.println("Digite seu cpf: ");
			String cpf = scanner.nextLine();

			System.out.println("Digite sua data de nascimento: ");
			String datan = scanner.nextLine();
			
			System.out.println("Digite seu endereço: ");
			String endereco = scanner.nextLine();
			
			System.out.println("Digite o numero da OAB: ");
			String oab = scanner.nextLine();
			
			Advogado advogado = new Advogado (nome, cpf,datan,endereco,oab);

			System.out.println( daoAdvogado.inserir( advogado ) ? "Cadastrou!" : "Falha do cadastro...");

			System.out.println("Advogado cadastrado sob o ID " + advogado.getId());
		}
		
		public static void listarAdvogado() throws SQLException {
			
			System.out.println("\n##### Listar Advogado #####\n");
			
			List<Advogado> advogado = daoAdvogado.buscarTodos();
			
			for(Advogado u : advogado) {
				System.out.println("Nome: " + u.getNome());
				System.out.println("CPF: " + u.getCpf());
				System.out.println("Data de Nascimento: " + u.getDatan());
				System.out.println("Endereço: " + u.getEndereco());
				System.out.println("OAB: " + u.getOab());
			}
		}
		
		public static void listarProcessoPorAdvogado() throws SQLException{
			System.out.println("\n##### Listar Processo por Advogado #####\n");
			
			Scanner scanner = new Scanner(System.in);

			System.out.println("Digite o nome do Advogado: ");
			String nome = scanner.nextLine();
			
			List<Processo> tasks = daoProcesso.processoPorAdvogado(nome);
			
			for(Processo u : tasks) {

				System.out.println("ID: " + u.getId());
				System.out.println("Nome: " + u.getNome());
				System.out.println("Estado: " + u.getEstado());
				System.out.println("Advogado: " + u.getAdvogado().getNome());
				
				scanner.nextLine();
			}

	}

}
