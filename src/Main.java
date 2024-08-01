import DAO.ClienteMapDAO;
import DAO.ClienteSetDAO;
import DAO.IClienteDAO;
import javax.swing.JOptionPane;
import java.util.Optional;


/**
 * @author Deiner.Souza
 */
public static void main(String[] args) {
    boolean porta =  true;
    System.out.println("iniciando sistema de cadastro!");

    IClienteDAO iClienteDAO =  new ClienteMapDAO();   // new ClienteSetDAO();

    while (porta) {

            String opcao = JOptionPane.showInputDialog(null,"Qual operação gostaria de efetuar ?\n1 - Cadastrar\n2 - EditarCadastro\n3 - ExcluirCadastro\n4 - ExibirTodos\n5 - BuscarCadastro\n\n6 - Sair\n\n Digite o numero da opção ou o nome:", "CRUD", JOptionPane.INFORMATION_MESSAGE);

            if (opcao.matches("(?i)Cadastrar|1")) {
                cadastrar(iClienteDAO);


            } else if (opcao.matches("(?i)EditarCadastro|2")) {
                editarCadastro(iClienteDAO);



            } else if (opcao.matches("(?i)ExcluirCadastro|3")) {
                excluirCadastro(iClienteDAO);


            } else if (opcao.matches("(?i)ExibirTodos|4")) {
                listarClientes(iClienteDAO);



            } else if (opcao.matches("(?i)BuscarCadastro|5")) {
                buscarCadastro(iClienteDAO);



            } else if(opcao.matches("(?i)Sair|6")) {
                porta = false;
                JOptionPane.showMessageDialog(null, "Finalizando sistema de cadastro!\n","Systema de Cadastro", JOptionPane.INFORMATION_MESSAGE);


            }else{
                JOptionPane.showMessageDialog(null, "Opção invalida tente novamente.\n", " Systema de Cadastro", JOptionPane.INFORMATION_MESSAGE);

            }

    }


}

private static void listarClientes(IClienteDAO iClienteDAO) {
    JOptionPane.showMessageDialog(null,iClienteDAO.listarCliente(),"Exibir todos os cadastros", JOptionPane.INFORMATION_MESSAGE);
}

/**
 *
 */
private static void buscarCadastro(IClienteDAO iClienteDAO) {
    String cpf = JOptionPane.showInputDialog(null,"Informe o cpf","Buscar Cadastro", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,iClienteDAO.buscarCliente(cpf),"Buscar cadastro",JOptionPane.INFORMATION_MESSAGE );

}
/**
 *
 */
private static void excluirCadastro(IClienteDAO iClienteDAO) {
    String cpf = JOptionPane.showInputDialog(null,"Informe o cpf","Excluir Cadastro",JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,iClienteDAO.excluirCliente(cpf),"Excluir cadastro",JOptionPane.INFORMATION_MESSAGE );

}
/**
 *
 */
private static void editarCadastro(IClienteDAO iClienteDAO) {

    String cpf = JOptionPane.showInputDialog(null, "Informe o CPF do cadastro que será editado","Editar cadastro", JOptionPane.INFORMATION_MESSAGE);
    String editar = JOptionPane.showInputDialog(null, "Informe o dado que gostaria de editar: nome, cpf, telefone, email, endereco\n\n OBS: somente é possivel alterar 1 por vez","Editar cadastro", JOptionPane.INFORMATION_MESSAGE);
    String novoDado = JOptionPane.showInputDialog(null, "Digite a nova informação de cadastro","Editar cadastro", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,iClienteDAO.alterarCliente(cpf, editar, novoDado),"Editar cadastro",JOptionPane.INFORMATION_MESSAGE );

}
/**
 *
 */
private static void cadastrar(IClienteDAO iClienteDAO) {
    String dadosJuntos = JOptionPane.showInputDialog(null,"Digite os dados do cliente separados por virgula nome,cpf,telefone,email,endereco: ", "Green dinner", JOptionPane.INFORMATION_MESSAGE);

    JOptionPane.showMessageDialog(null,iClienteDAO.cadastrarCliente(dadosJuntos),"Cadastrar clientes",JOptionPane.INFORMATION_MESSAGE );

}

