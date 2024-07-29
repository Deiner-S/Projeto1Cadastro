import DAO.ClienteMapDAO;

import java.util.Scanner;
import java.util.Set;


public static void main(String[] args) {
    ClienteMapDAO clienteMapDAO = new ClienteMapDAO();

    String porta = "sim";
    System.out.println("iniciando sistema de cadastro!");
    while (porta.equalsIgnoreCase("sim")) {
        try(Scanner sc = new Scanner(System.in)){

            System.out.println("Qual operação gostaria de efetuar ?\n1 - Cadastrar\n2 - EditarCadastro\n3 - ExcluirCadastro\n4 - ExibirTodos\n5 - BuscarCadastro\n\n Digite o numero da opção ou o nome:");
            String opcao = sc.nextLine();

            if (opcao.matches("(?i)Cadastrar|1")) {
                System.out.println("Digite os dados do cliente separados por virgula nome,cpf,telefone,email,endereco: ");
                String dadosJuntos = sc.nextLine();
                String[] dadosSeparados = dadosJuntos.split(",");
                clienteMapDAO.inserirCliente(dadosSeparados);


            } else if (opcao.matches("(?i)EditarCadastro|2")) {
                System.out.println("Ïnforme o cpf");
                String cpf = sc.nextLine();
                cpf.replaceAll("[^0-9]","");
                cpf.replaceAll(" ","");
                Integer numeroCpf = Integer.parseInt(cpf);
                System.out.println("Ïnforme o dado que gostaria de editar: nome, cpf, telefone, email, endereco");
                String editar = sc.nextLine();
                if (!editar.matches("(?i)nome|cpf|telefone|email|endereco")) {
                    System.out.println("Opção de edição invalida.");

                } else {
                    System.out.println("Informe o novo " + editar);
                    String novoDado = sc.nextLine();
                    clienteMapDAO.alterarCliente(numeroCpf,editar,novoDado);
                }




            } else if (opcao.matches("(?i)ExcluirCadastro|3")) {
                System.out.println("Ïnforme o cpf");
                String cpf = sc.nextLine();
                cpf.replaceAll("[^0-9]","");
                cpf.replaceAll(" ","");
                Integer numeroCpf = Integer.parseInt(cpf);
                clienteMapDAO.excluirCliente(numeroCpf);

            } else if (opcao.matches("(?i)ExibirTodos|4")) {
                clienteMapDAO.listarCliente();

            } else if (opcao.matches("(?i)BuscarCadastro|5")) {
                System.out.println("Ïnforme o cpf");
                String cpf = sc.nextLine();
                cpf.replaceAll("[^0-9]","");
                cpf.replaceAll(" ","");
                Integer numeroCpf = Integer.parseInt(cpf);
                clienteMapDAO.buscarCliente(numeroCpf);
            } else {
                System.out.println("Opção invalida tente novamente");
                continue;
            }
        }





        porta = sc.nextLine();
    }
}
