package DAO;

import Domain.Cliente;

public interface IClienteDAO {

    public void inserirCliente(String[] dadosSeparados);

    public String alterarCliente(Integer cpf, String editar,String novaInformacao);

    public String excluirCliente(Integer cpf);

    public void listarCliente();

    public Cliente buscarCliente(Integer cpf);


}
