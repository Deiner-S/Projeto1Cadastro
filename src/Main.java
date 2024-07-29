import DAO.ClienteMapDAO;

import java.util.Scanner;
import java.util.Set;

/**
 * @author Deiner.Souza
 */
public static void main(String[] args) {
    ClienteMapDAO clienteMapDAO = new ClienteMapDAO();

    Boolean porta =  true;
    System.out.println("iniciando sistema de cadastro!");
    while (porta) {
        Scanner sc = new Scanner(System.in);

            System.out.println("Qual operação gostaria de efetuar ?\n1 - Cadastrar\n2 - EditarCadastro\n3 - ExcluirCadastro\n4 - ExibirTodos\n5 - BuscarCadastro\n\n6 - Sair\n\n Digite o numero da opção ou o nome:");
            String opcao = sc.nextLine();

            if (opcao.matches("(?i)Cadastrar|1")) {
                System.out.println("Digite os dados do cliente separados por virgula nome,cpf,telefone,email,endereco: ");
                String dadosJuntos = sc.nextLine();
                String[] dadosSeparados = dadosJuntos.split(",");
                System.out.println(clienteMapDAO.inserirCliente(dadosSeparados));


            } else if (opcao.matches("(?i)EditarCadastro|2")) {
                System.out.println("Ïnforme o cpf");
                String cpf = sc.nextLine();
                String stringCpf = cpf.replaceAll("[^0-9]+","");

                Long numeroCpf = Long.parseLong(stringCpf);
                System.out.println("Ïnforme o dado que gostaria de editar: nome, cpf, telefone, email, endereco");
                String editar = sc.nextLine();

                if (!editar.matches("(?i)nome|cpf|telefone|email|endereco")) {
                    System.out.println("Opção de edição invalida.");

                } else {
                    System.out.println("Informe o novo " + editar);
                    String novoDado = sc.nextLine();
                    System.out.println(clienteMapDAO.alterarCliente(numeroCpf,editar,novoDado));
                }




            } else if (opcao.matches("(?i)ExcluirCadastro|3")) {
                System.out.println("Ïnforme o cpf");
                String cpf = sc.nextLine();
                cpf.replaceAll("[^0-9]","");
                cpf.replaceAll(" ","");
                Long numeroCpf = Long.parseLong(cpf);
                System.out.println(clienteMapDAO.excluirCliente(numeroCpf));


            } else if (opcao.matches("(?i)ExibirTodos|4")) {
                System.out.println(clienteMapDAO.listarCliente());


            } else if (opcao.matches("(?i)BuscarCadastro|5")) {
                System.out.println("Ïnforme o cpf");
                String cpf = sc.nextLine();
                cpf.replaceAll("[^0-9]","");
                cpf.replaceAll(" ","");
                Long numeroCpf = Long.parseLong(cpf);

                clienteMapDAO.buscarCliente(numeroCpf);
                





            } else if(opcao.matches("(?i)Sair|6")) {
                porta = false;
                System.out.println("Finalizando sistema de cadastro!");

            }else{
                System.out.println("Opção invalida tente novamente");
                continue;
            }



    }


}

