package DAO;

import Domain.Cliente;

import java.util.*;

public class ClienteSetDAO implements IClienteDAO {

    private Set<Cliente> clientes = new HashSet<>();

    public Set<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public void inserirCliente(String[] dadosSeparados) {
        dadosSeparados[1].replaceAll("[^0-9]","");
        dadosSeparados[1].replaceAll(" ","");
        Integer cpf = Integer.parseInt(dadosSeparados[1]);
        Cliente construtorCliente = new Cliente(dadosSeparados[0],cpf,dadosSeparados[2],dadosSeparados[3],dadosSeparados[4]);
        clientes.add(construtorCliente);
    }

    @Override
    public String alterarCliente(Integer cpf, String editar, String novaInformacao) {
        Optional<Cliente> optionalCliente = clientes.stream()
               .filter( p -> p.getCpf().equals(cpf)).findFirst();

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            clientes.remove(cliente);
            if(editar.equalsIgnoreCase("nome")){
                cliente.setNome(novaInformacao);


            }else if(editar.equalsIgnoreCase("cpf")){
                novaInformacao.replaceAll("[^0-9]","");
                novaInformacao.replaceAll(" ","");
                Integer novoCpf = Integer.parseInt(novaInformacao);
                cliente.setCpf(novoCpf);


            }else if(editar.equalsIgnoreCase("telefone")){
                cliente.setTelefone(novaInformacao);

            }else if(editar.equalsIgnoreCase("email")){
                cliente.setEmail(novaInformacao);

            }else if(editar.equalsIgnoreCase("Endereco") || editar.equalsIgnoreCase("Endereço" )){
                cliente.setEndereco(novaInformacao);

            }else{
                return "Erro ao alterar cliente";
            }
            clientes.add(cliente);
        } else {
            return "Pessoa não encontrada";
        }



        return "Dados alterados com sucesso";
    }

    @Override
    public String excluirCliente(Integer cpf) {
        Optional<Cliente> optionalCliente = clientes.stream()
                .filter( p -> p.getCpf().equals(cpf)).findFirst();
        clientes.remove(optionalCliente.get());
        return "Cliente excluido";
    }

    @Override
    public void listarCliente() {
        clientes.forEach(cliente -> System.out.println("Nome: "+cliente.getNome()+"\nCPF: "+cliente.getCpf()+"\nTelefone: "+cliente.getTelefone()+"\nEmail: "+cliente.getEmail()));
    }

    @Override
    public Cliente buscarCliente(Integer cpf) {
        Optional<Cliente> optionalCliente = clientes.stream()
                .filter( p -> p.getCpf().equals(cpf)).findFirst();
        return optionalCliente.get();
    }
}
