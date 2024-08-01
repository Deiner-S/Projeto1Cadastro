import DAO.ClienteMapDAO;
import DAO.ClienteSetDAO;
import javax.swing.JOptionPane;



/**
 * @author Deiner.Souza
 */
public static void main(String[] args) {
    Boolean porta =  true;
    System.out.println("iniciando sistema de cadastro!");

    IClienteDAO clienteDAO = new ClienteMapDAO();

    






    while (porta) {

            String opcao = JOptionPane.showInputDialog(null,"Qual operação gostaria de efetuar ?\n1 - Cadastrar\n2 - EditarCadastro\n3 - ExcluirCadastro\n4 - ExibirTodos\n5 - BuscarCadastro\n\n6 - Sair\n\n Digite o numero da opção ou o nome:", "CRUD", JOptionPane.INFORMATION_MESSAGE);

            if (opcao.matches("(?i)Cadastrar|1")) {
                cadastrar(clienteDAO);
               

            } else if (opcao.matches("(?i)EditarCadastro|2")) {
                editarCadastro(clienteDAO);
                // editarCadastro(clienteSetDAO);


            } else if (opcao.matches("(?i)ExcluirCadastro|3")) {
                excluirCadastro(clienteDAO);
                

            } else if (opcao.matches("(?i)ExibirTodos|4")) {
                listarClientes(clienteDAO);
                


            } else if (opcao.matches("(?i)BuscarCadastro|5")) {
                buscarCadastro(clienteDAO);
                


            } else if(opcao.matches("(?i)Sair|6")) {
                porta = false;
                JOptionPane.showMessageDialog(null, "Finalizando sistema de cadastro!\n","Systema de Cadastro", JOptionPane.INFORMATION_MESSAGE);


            }else{
                JOptionPane.showMessageDialog(null, "Opção invalida tente novamente.\n", " Systema de Cadastro", JOptionPane.INFORMATION_MESSAGE);

            }

    }


}

private static void listarClientes(IClientDAO clienteDAO) {
    JOptionPane.showMessageDialog(null,clienteDAO.listarCliente(),"Exibir todos os cadastros", JOptionPane.INFORMATION_MESSAGE);
}

/**
 *
 */
private static void buscarCadastro(IClienteDAO clienteDAO) {
    String cpf = JOptionPane.showInputDialog(null,"Informe o cpf", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,clienteDAO.buscarCliente(cpf),"Buscar cadastro",JOptionPane.INFORMATION_MESSAGE );

}
/**
 *
 */
private static void excluirCadastro(IClienteDAO clienteDAO) {
    String cpf = JOptionPane.showInputDialog(null,"Informe o cpf", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,clienteDAO.excluirCliente(cpf),"Excluir cadastro",JOptionPane.INFORMATION_MESSAGE );

}
/**
 *
 */
private static void editarCadastro(IClienteDAO clienteDAO) {

    String cpf = JOptionPane.showInputDialog(null, "Informe o CPF do cadastro que será editado","Editar cadastro", JOptionPane.INFORMATION_MESSAGE);
    String editar = JOptionPane.showInputDialog(null, "Informe o dado que gostaria de editar: nome, cpf, telefone, email, endereco\n\n OBS: somente é possivel alterar 1 por vez","Editar cadastro", JOptionPane.INFORMATION_MESSAGE);
    String novoDado = JOptionPane.showInputDialog(null, "Digite a nova informação de cadastro","Editar cadastro", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,clienteDAO.alterarCliente(cpf, editar, novoDado),"Editar cadastro",JOptionPane.INFORMATION_MESSAGE );

}
/**
 *
 */
private static void cadastrar(IClienteDAO clienteDAO) {
    String dadosJuntos = JOptionPane.showInputDialog(null,"Digite os dados do cliente separados por virgula nome,cpf,telefone,email,endereco: ", "Green dinner", JOptionPane.INFORMATION_MESSAGE);

    JOptionPane.showMessageDialog(null,clienteDAO.cadastrarCliente(dadosJuntos),"Cadastrar clientes",JOptionPane.INFORMATION_MESSAGE );

}

