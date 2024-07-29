package DAO;

import Domain.Cliente;

import java.util.*;

public class ClienteSetDAO implements IClienteDAO {

    private Set<Cliente> clientes = new HashSet<>();

    public Set<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public String inserirCliente(String[] dadosSeparados) {
        String stringCpf = dadosSeparados[1].replaceAll("[^0-9]+","");
        Long longCpf = Long.parseLong(stringCpf);
        Cliente construtorCliente = new Cliente(dadosSeparados[0],longCpf,dadosSeparados[2],dadosSeparados[3],dadosSeparados[4]);
        clientes.add(construtorCliente);
        return "Cadastro realizado com sucesso!";
    }

    @Override
    public String alterarCliente(Long cpf, String editar, String novaInformacao) {
        Optional<Cliente> optionalCliente = clientes.stream()
               .filter( p -> p.getCpf().equals(cpf)).findFirst();

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            clientes.remove(cliente);
            if(editar.equalsIgnoreCase("nome")){
                cliente.setNome(novaInformacao);


            }else if(editar.equalsIgnoreCase("cpf")){
                String stringCpf = novaInformacao.replaceAll("[^0-9]+","");

                Long novoCpf = Long.parseLong(stringCpf);
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
    public String excluirCliente(Long cpf) {
        Optional<Cliente> optionalCliente = clientes.stream()
                .filter( p -> p.getCpf().equals(cpf)).findFirst();
        clientes.remove(optionalCliente.get());
        return "Cliente excluido";
    }

    @Override
    public String listarCliente() {
        clientes.forEach(cliente -> System.out.println("Nome: "+cliente.getNome()+"\nCPF: "+cliente.getCpf()+"\nTelefone: "+cliente.getTelefone()+"\nEmail: "+cliente.getEmail()));
        return "Foram encontrados "+clientes.size()+" registros!";
    }

    @Override
    public String buscarCliente(Long cpf) {
        Optional<Cliente> optionalCliente = clientes.stream()
                .filter( p -> p.getCpf().equals(cpf)).findFirst();

        if(optionalCliente.isPresent()){
            System.out.println(optionalCliente.get());
            return "Cadastro encontrado!" ;
        }else{
            return "Cadastro não encontrado.";
        }


    }
}
