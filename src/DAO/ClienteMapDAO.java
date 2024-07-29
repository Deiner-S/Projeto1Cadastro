package DAO;

import Domain.Cliente;

import java.lang.annotation.Retention;
import java.util.*;

public class ClienteMapDAO implements IClienteDAO {


    private final Map<Long, Cliente> clientes = new HashMap<>();;

    public Map<Long, Cliente> getClientes() {
        return clientes;
    }

    @Override
    public String inserirCliente(String[] dadosSeparados) {
        String stringCpf = dadosSeparados[1].replaceAll("[^0-9]+","");
        Long longCpf = Long.parseLong(stringCpf);
        Cliente construtorCliente = new Cliente(dadosSeparados[0],longCpf,dadosSeparados[2],dadosSeparados[3],dadosSeparados[4]);

        clientes.put(longCpf,construtorCliente);
        return "Cadastro realizado com sucesso!\n";
    }

    @Override
    public String alterarCliente(Long cpf, String editar,String novaInformacao) {

        if(editar.equalsIgnoreCase("nome")){
            clientes.get(cpf).setNome(novaInformacao);


        }else if(editar.equalsIgnoreCase("cpf")){
            String stringCpf = novaInformacao.replaceAll("[^0-9]+","");

            Long novoCpf = Long.parseLong(stringCpf);
            clientes.get(cpf).setCpf(novoCpf);


        }else if(editar.equalsIgnoreCase("telefone")){
            clientes.get(cpf).setTelefone(novaInformacao);


        }else if(editar.equalsIgnoreCase("email")){
            clientes.get(cpf).setEmail(novaInformacao);


        }else if(editar.equalsIgnoreCase("Endereco")||editar.equalsIgnoreCase("endereço")){
        clientes.get(cpf).setEndereco(novaInformacao);


        }else{
            return "Erro ao alterar cliente\n";
        }


        return "Dados alterados com sucesso!\n";
    }

    @Override
    public String excluirCliente(Long cpf) {
        clientes.remove(cpf);
        return "Cliente excluido com sucesso!\n";
    }

    @Override
    public String listarCliente() {
        clientes.values().forEach(cliente -> System.out.println(
                "=================================================\n" +
                "Nome: "+cliente.getNome()+"\nCPF: "+cliente.getCpf()+"\nTelefone: "
                +cliente.getTelefone()+"\nEmail: "+cliente.getEmail()
                ));

        return "Foram encontrados " + clientes.size() + " com sucesso!\n\n";
    }

    @Override
    public String buscarCliente(Long cpf) {
        if(clientes.containsKey(cpf)){
        System.out.println(clientes.get(cpf));
            return "Cadastro encontrado!\n" ;

        }else {
            return "Cadastro não encontrado.\n" ;
        }

    }
}
