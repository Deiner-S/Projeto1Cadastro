package DAO;

import Domain.Cliente;

public interface IClienteDAO {

    public String inserirCliente(String[] dadosSeparados);

    public String alterarCliente(Long cpf, String editar,String novaInformacao);

    public String excluirCliente(Long cpf);

    public String listarCliente();

    public String buscarCliente(Long cpf);


}
