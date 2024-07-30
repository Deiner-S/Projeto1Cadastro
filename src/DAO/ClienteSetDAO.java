package DAO;

import Domain.Cliente;

import javax.swing.*;
import java.util.*;

public class ClienteSetDAO implements IClienteDAO {

    private Set<Cliente> clientes = new HashSet<>();

    public Set<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public String cadastrarCliente(String[] dadosSeparados) {


        Cliente construtorCliente = new Cliente(dadosSeparados[0],converterCpf(dadosSeparados[1]),dadosSeparados[2],dadosSeparados[3],dadosSeparados[4]);
        clientes.add(construtorCliente);
        return "Cliente Cadastrado com sucesso!";
    }

    @Override
    public String alterarCliente(String cpf, String editar, String novaInformacao) {
        Optional<Cliente> optionalCliente = clientes.stream()
               .filter( p -> p.getCpf().equals(converterCpf(cpf))).findFirst();

        if (optionalCliente.isPresent()) {
            clientes.remove(optionalCliente.get());

            if(editar.equalsIgnoreCase("nome")){
                optionalCliente.get().setNome(novaInformacao);
            }else if(editar.equalsIgnoreCase("cpf")){
                optionalCliente.get().setCpf(converterCpf(editar));
            }else if(editar.equalsIgnoreCase("telefone")){
                optionalCliente.get().setTelefone(novaInformacao);
            }else if(editar.equalsIgnoreCase("email")){
                optionalCliente.get().setEmail(novaInformacao);
            }else if(editar.equalsIgnoreCase("Endereco") || editar.equalsIgnoreCase("Endereço" )){
                optionalCliente.get().setEndereco(novaInformacao);

            }else{
                return "";
            }
            clientes.add(optionalCliente.get());
        }else{
            return "";
        }
        return "";
    }

    @Override
    public boolean excluirCliente(String cpf) {
        Optional<Cliente> optionalCliente = clientes.stream()
                .filter( p -> p.getCpf().equals(converterCpf(cpf))).findFirst();

        clientes.remove(optionalCliente.get());
        return true;
    }

    @Override
    public int listarCliente() {
        JOptionPane.showMessageDialog(null, clientes.toString());
        return clientes.size();
    }

    @Override
    public boolean buscarCliente(String cpf) {
        Optional<Cliente> optionalCliente = clientes.stream()
                .filter( p -> p.getCpf().equals(converterCpf(cpf))).findFirst();

        if(optionalCliente.isPresent()){
            System.out.println(optionalCliente.get());
            return true;
        }else{
            return false;
        }


    }
    public Long converterCpf(String cpf){

        String stringCpf = cpf.replaceAll("\\D","");

        return Long.parseLong(stringCpf);
    }
}
