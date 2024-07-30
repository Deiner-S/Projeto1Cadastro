package DAO;

import Domain.Cliente;

public interface IClienteDAO {

    public String cadastrarCliente(String[] dadosSeparados);

    public String alterarCliente(String cpf, String editar,String novaInformacao);

    public String excluirCliente(String cpf);

    public String listarCliente();

    public String buscarCliente(String cpf);


}
