package DAO;

import Domain.Cliente;

import java.util.*;

public class ClienteMapDAO implements IClienteDAO {


    private final Map<Integer, Cliente> clientes = new HashMap<>();;

    public Map<Integer, Cliente> getClientes() {
        return clientes;
    }

    @Override
    public void inserirCliente(String[] dadosSeparados) {

        dadosSeparados[1].replaceAll("[^0-9]","");
        dadosSeparados[1].replaceAll(" ","");
        Integer cpf = Integer.parseInt(dadosSeparados[1]);

        Cliente construtorCliente = new Cliente(dadosSeparados[0],cpf,dadosSeparados[2],dadosSeparados[3],dadosSeparados[4]);

        clientes.put(cpf,construtorCliente);

    }

    @Override
    public String alterarCliente(Integer cpf, String editar,String novaInformacao) {

        if(editar.equalsIgnoreCase("nome")){
            clientes.get(cpf).setNome(novaInformacao);


        }else if(editar.equalsIgnoreCase("cpf")){
            novaInformacao.replaceAll("[^0-9]","");
            novaInformacao.replaceAll(" ","");
            Integer novoCpf = Integer.parseInt(novaInformacao);
            clientes.get(cpf).setCpf(novoCpf);


        }else if(editar.equalsIgnoreCase("telefone")){
            clientes.get(cpf).setTelefone(novaInformacao);


        }else if(editar.equalsIgnoreCase("email")){
            clientes.get(cpf).setEmail(novaInformacao);


        }else if(editar.equalsIgnoreCase("Endereco")||editar.equalsIgnoreCase("endereço")){
        clientes.get(cpf).setEndereco(novaInformacao);


        }else{
            return "Erro ao alterar cliente";
        }


        return "Dados alterados com sucesso!";
    }

    @Override
    public String excluirCliente(Integer cpf) {
        clientes.remove(cpf);
        return "Cliente excluido com sucesso!";
    }

    @Override
    public void listarCliente() {
        clientes.values().forEach(cliente -> System.out.println("Nome: "+cliente.getNome()+"\nCPF: "+cliente.getCpf()+"\nTelefone: "+cliente.getTelefone()+"\nEmail: "+cliente.getEmail()));
    }

    @Override
    public Cliente buscarCliente(Integer cpf) {

        return clientes.get(cpf);
    }
}
