package DAO;

import Domain.Cliente;

import javax.swing.*;
import java.util.*;

public class ClienteMapDAO implements IClienteDAO {


    private final Map<Long, Cliente> clientes = new HashMap<>();

    @Override
    public String cadastrarCliente(String[] dadosSeparados) {
        if(isValidCpf(dadosSeparados[1]) && isValidEmail(dadosSeparados[3])){
            return "CPF Ou Email Invalido";
        }
            Cliente construtorCliente = new Cliente(dadosSeparados[0],converterCpf(dadosSeparados[1]),dadosSeparados[2],dadosSeparados[3],dadosSeparados[4]);

            if(clientes.containsKey(converterCpf(dadosSeparados[1]))){

                return "Cliente já está cadastrado";
            }else{
                clientes.put(converterCpf(dadosSeparados[1]),construtorCliente);
                return "Cliente cadastrado com sucesso!";
            }



    }



    @Override
    public String alterarCliente(String cpf, String editar,String novaInformacao) {
        if(!isValidCpf(cpf)){
            return "CPF invalido.";
        }else if (!editar.matches("(?i)nome|cpf|telefone|email|endereco")) {
            return "Opção de edição invalida.";

        }else{

            if(editar.equalsIgnoreCase("nome")){
                clientes.get(converterCpf(cpf)).setNome(novaInformacao);
            }else if(editar.equalsIgnoreCase("cpf")){
                if(!isValidCpf(novaInformacao)){
                    return "Novo CPF invalido!.";
                }
                clientes.get(converterCpf(cpf)).setCpf(converterCpf(novaInformacao));
            }else if(editar.equalsIgnoreCase("telefone")){
                clientes.get(converterCpf(cpf)).setTelefone(novaInformacao);
            }else if(editar.equalsIgnoreCase("email")){
                if (!isValidEmail(novaInformacao)) {
                    return "Novo Email invalido!.";
                }
                clientes.get(converterCpf(cpf)).setEmail(novaInformacao);
            }else if(editar.equalsIgnoreCase("Endereco")||editar.equalsIgnoreCase("endereço")){
            clientes.get(converterCpf(cpf)).setEndereco(novaInformacao);
            }

        }


        return "Cliente cadastrado!";
    }

    @Override
    public String excluirCliente(String cpf) {
        if(!isValidCpf(cpf)){
            return "CPF invalido.";
        }
            clientes.remove(converterCpf(cpf));
            return "Cadastro excluido com sucesso!";



    }

    @Override
    public String listarCliente() {
        return "Lista Clientes: \n\n" + clientes.values() + "\n\n" "quantidad" + clientes.size();
    }

    @Override
    public String buscarCliente(String cpf) {
        if(!isValidCpf(cpf)){
            return "CPF invalido.";
        }

        if(clientes.containsKey(converterCpf(cpf))){
            return "Cliente encontrado!\n"+clientes.get(converterCpf(cpf)) ;

        }else {
            return "Cadastro não encontrado" ;
        }
    }

    public Long converterCpf(String cpf){

        String stringCpf = cpf.replaceAll("\\D","");

        return Long.parseLong(stringCpf);
    }

    public boolean isValidCpf(String cpf){
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public boolean isValidEmail(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
}
