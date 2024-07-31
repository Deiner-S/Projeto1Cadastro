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
        if(!validacaoUtil.isValidCpf(cpf)){
            return "Não foi possível buscar cadastro CPF invalido";
        }else if(!editar.matches("(?i)nome|cpf|telefone|email|endereco")){
            return "Opção de edição invalida.";

        }else{
            Optional<Cliente> optionalCliente = clientes.stream().filter(p -> p.getCpf().equals(validacaoUtil.converterCpf(cpf))).findFirst();
            if(optionalCliente.isPresent()){
                Cliente cliente = optionalCliente.get();
                clientes.remove(cliente);

                switch (editar.toLowerCase()){
                    case "nome":
                        cliente.setNome(novaInformacao);
                        break;
                    case "cpf":
                        if(!validacaoUtil.isValidCpf(novaInformacao)){
                            return "Novo CPF invalido";
                        }
                        cliente.setCpf(validacaoUtil.converterCpf(novaInformacao));
                        break;
                    case "telefone":
                        cliente.setTelefone(novaInformacao);
                        break;
                    case "email":
                        if(!validacaoUtil.isValidEmail(novaInformacao)){
                            return "Novo Email Invalido";
                        }
                        cliente.setEmail(novaInformacao);
                        break;
                    case "endereco":
                        cliente.setEndereco(novaInformacao);
                        break;

                }

            }else{
                return "Cliente não encontrado";
            }

            return "Cliente alterado com sucesso!";
        }

    }

    @Override
    public String excluirCliente(String cpf) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return "CPF invalido";
        }
        Optional<Cliente> optionalCliente = clientes.stream().filter(p -> p.getCpf().equals(validacaoUtil.converterCpf(cpf))).findFirst();
        if(optionalCliente.isPresent()){
            clientes.remove(optionalCliente.get());
        }else {
            return "Cliente não encontrado";
        }
        return "Cliente excluido com sucesso!";
    }

    @Override
    public String listarCliente() {
        return "Lista Clientes: \n\n" + clientes + "\n\n" + "quantidad " + clientes.size();
    }

    @Override
    public String buscarCliente(String cpf) {
        if(!validacaoUtil.isValidCpf(cpf)){
            return "CPF invalido";
        }
        Optional<Cliente> optionalCliente = clientes.stream().filter(p -> p.getCpf().equals(validacaoUtil.converterCpf(cpf))).findFirst();
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            return "Cliente encontrado: \n\nNome: "+ cliente.getNome()+"\nCPF: "+cliente.getCpf()+"\nTelefone: "+cliente.getTelefone()+"\nEmail: "+cliente.getEmail()+"\nEndereço: "+cliente.getEndereco();
        }else {
            return "Cliente não encontrado";
        }

    }
}