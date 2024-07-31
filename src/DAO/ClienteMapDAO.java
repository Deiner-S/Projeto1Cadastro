package DAO;
import Util.ValidacaoUtil;
import Domain.Cliente;
import java.util.*;

public class ClienteMapDAO implements IClienteDAO {

    private ValidacaoUtil validacaoUtil;
    private final Map<Long, Cliente> clientes = new HashMap<>();

    @Override
    public String cadastrarCliente(String dadosJuntos) {
        String[] dadosSeparados = dadosJuntos.split(",");
        if(!validacaoUtil.isValidCpf(dadosSeparados[1]) || !validacaoUtil.isValidEmail(dadosSeparados[3])){
            return "CPF Ou Email Invalido";
        }
            Cliente construtorCliente = new Cliente(dadosSeparados[0],validacaoUtil.converterCpf(dadosSeparados[1]),dadosSeparados[2],dadosSeparados[3],dadosSeparados[4]);

            if(clientes.containsKey(validacaoUtil.converterCpf(dadosSeparados[1]))){

                return "Cliente já está cadastrado";
            }else{
                clientes.put(validacaoUtil.converterCpf(dadosSeparados[1]),construtorCliente);
                return "Cliente cadastrado com sucesso!";
            }



    }



    @Override
    public String alterarCliente(String cpf, String editar,String novaInformacao) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return "CPF invalido.";
        }else if (!editar.matches("(?i)nome|cpf|telefone|email|endereco")) {
            return "Opção de edição invalida.";

        }else{
            Long longCPF = validacaoUtil.converterCpf(cpf);

            switch (editar.toLowerCase()){
                case "nome":
                    clientes.get(longCPF).setNome(novaInformacao);
                    break;

                case "cpf":
                    if(!validacaoUtil.isValidCpf(novaInformacao)){
                        return "Novo CPF invalido!.";
                    }
                    clientes.get(longCPF).setCpf(validacaoUtil.converterCpf(novaInformacao));
                    break;

                case "telefone":
                    clientes.get(longCPF).setTelefone(novaInformacao);
                    break;

                case "email":
                    if (!validacaoUtil.isValidEmail(novaInformacao)) {
                        return "Novo Email invalido!.";
                    }
                    clientes.get(longCPF).setEmail(novaInformacao);
                    break;

                case "endereco":
                    clientes.get(longCPF).setEndereco(novaInformacao);
                    break;
            }

        }


        return "Cliente cadastrado!";
    }

    @Override
    public String excluirCliente(String cpf) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return "CPF invalido.";
        }
            clientes.remove(validacaoUtil.converterCpf(cpf));
            return "Cadastro excluido com sucesso!";



    }

    @Override
    public String listarCliente() {
        return "Lista Clientes: \n\n" + clientes.values() + "\n\n" + "quantidad" + clientes.size();
    }

    @Override
    public String buscarCliente(String cpf) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return "CPF invalido.";
        }
        Long longCPF = validacaoUtil.converterCpf(cpf);
        if(clientes.containsKey(longCPF)){
            return "Cliente encontrado!\n"+clientes.get(longCPF) ;

        }else {
            return "Cadastro não encontrado" ;
        }
    }


}
