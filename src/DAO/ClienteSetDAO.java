package DAO;
import Util.ValidacaoUtil;
import Domain.Cliente;
import java.util.*;

public class ClienteSetDAO implements IClienteDAO {
    private ValidacaoUtil validacaoUtil;
    private Set<Cliente> clientes = new HashSet<>();


    @Override
    public String cadastrarCliente(String dadosJuntos) {
        String[] dadosSeparados = dadosJuntos.split(",");
        if(!validacaoUtil.isValidCpf(dadosSeparados[1]) || !validacaoUtil.isValidEmail(dadosSeparados[3])){
            return "CPF Ou Email Invalido";
        }
        Optional<Cliente> optionalCliente = clientes.stream().filter( p -> p.getCpf().equals(validacaoUtil.converterCpf(dadosSeparados[1]))).findFirst();
        Cliente construtorCliente = new Cliente(dadosSeparados[0],validacaoUtil.converterCpf(dadosSeparados[1]),dadosSeparados[2],dadosSeparados[3],dadosSeparados[4]);

        if(optionalCliente.isPresent()){
            return "Cliente já está cadastrado";
        }else{
            clientes.add(construtorCliente);
            return "Cliente cadastrado com sucesso!";
        }


    }

    @Override
    public String alterarCliente(String cpf, String editar, String novaInformacao) {





        return "";
    }

    @Override
    public String excluirCliente(String cpf) {
        return "";
    }

    @Override
    public String listarCliente() {
        return "";
    }

    @Override
    public String buscarCliente(String cpf) {
        return "";
    }
}