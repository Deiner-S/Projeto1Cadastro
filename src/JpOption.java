import DAO.ClienteMapDAO;
import javax.swing.JOptionPane;



/**
 * @author Deiner.Souza
 */
public static void main(String[] args) {
    Boolean porta =  true;
    System.out.println("iniciando sistema de cadastro!");
    ClienteMapDAO clienteMapDAO = new ClienteMapDAO();


    while (porta) {

            String opcao = JOptionPane.showInputDialog(null,"Qual operação gostaria de efetuar ?\n1 - Cadastrar\n2 - EditarCadastro\n3 - ExcluirCadastro\n4 - ExibirTodos\n5 - BuscarCadastro\n\n6 - Sair\n\n Digite o numero da opção ou o nome:", "Green dinner", JOptionPane.INFORMATION_MESSAGE);

            if (opcao.matches("(?i)Cadastrar|1")) {
                cadastrar(clienteMapDAO);

            } else if (opcao.matches("(?i)EditarCadastro|2")) {
                editarCadastro(clienteMapDAO);

            } else if (opcao.matches("(?i)ExcluirCadastro|3")) {
                excluirCadastro(clienteMapDAO);

            } else if (opcao.matches("(?i)ExibirTodos|4")) {
                System.out.println(clienteMapDAO.listarCliente());

            } else if (opcao.matches("(?i)BuscarCadastro|5")) {
                buscarCadastro(clienteMapDAO);

            } else if(opcao.matches("(?i)Sair|6")) {
                porta = false;
                JOptionPane.showMessageDialog(null, "Finalizando sistema de cadastro!\n");


            }else{
                JOptionPane.showMessageDialog(null, "Opção invalida tente novamente. \n");

            }

    }


}

/**
 *
 */
private static void buscarCadastro(ClienteMapDAO clienteMapDAO) {
    String cpf = JOptionPane.showInputDialog(null,"Informe o cpf", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,clienteMapDAO.buscarCliente(cpf),"Buscar cadastro",JOptionPane.INFORMATION_MESSAGE );
   
}
/**
 *
 */
private static void excluirCadastro(ClienteMapDAO clienteMapDAO) {
    String cpf = JOptionPane.showInputDialog(null,"Informe o cpf", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,clienteMapDAO.excluirCliente(cpf),"Excluir cadastro",JOptionPane.INFORMATION_MESSAGE );

}
/**
 *
 */
private static void editarCadastro(ClienteMapDAO clienteMapDAO) {

    String cpf = JOptionPane.showInputDialog(null, "Informe o cpf","Editar cadastro", JOptionPane.INFORMATION_MESSAGE);
    String editar = JOptionPane.showInputDialog(null, "Informe o dado que gostaria de editar: nome, cpf, telefone, email, endereco","Editar cadastro", JOptionPane.INFORMATION_MESSAGE);
    String novoDado = JOptionPane.showInputDialog(null, "Informe o novo " + editar,"Editar cadastro", JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,clienteMapDAO.alterarCliente(cpf, editar, novoDado),"Editar cadastro",JOptionPane.INFORMATION_MESSAGE );

}
/**
 *
 */
private static void cadastrar(ClienteMapDAO clienteMapDAO) {
    String dadosJuntos = JOptionPane.showInputDialog(null,"Digite os dados do cliente separados por virgula nome,cpf,telefone,email,endereco: ", "Green dinner", JOptionPane.INFORMATION_MESSAGE);
    String[] dadosSeparados = dadosJuntos.split(",");
    JOptionPane.showMessageDialog(null,clienteMapDAO.cadastrarCliente(dadosSeparados),"Cadastrar clientes",JOptionPane.INFORMATION_MESSAGE );



}

