/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruddesafio;

import dao.UsuarioDAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kevin
 */
public class CrudDesafio {

    public static void main(String[] args) {
    	
    	Scanner entrada = new Scanner(System.in);
    	Usuario usuario;
        
        // ARMAZENA O CAMINHO DO ARQUIVO EM UMA VARIAVEL
        String arquivo = "d:\\teste.txt.txt";
        int opcao = 0;
        
        // DADOS USUARIO
        int id;
        String nome;
        int idade;
        String sexo;
        boolean status;
        
        // LE O ARQUIVO ESPECIFICADO
        // FileReader lerArquivo = null;
        // GERENCIA A LEITURA DO ARQUIVO DE UMA FORMA MAIS OTIMIZADA
        // BufferedReader gerenciarLeitura = null;
        
        try(BufferedReader gerenciarLeitura = new BufferedReader(new FileReader(arquivo))) {
            // CRIA UMA VARIAVEL PARA ARMAZENAR A ACESSAR DE LINHA A LINHA OS DADOS DO ARQUIVO readLine()
            String linha = gerenciarLeitura.readLine(); // <- ESSA PRIMEIRA LINHA E REFERENTE AO CABECALHO
            linha = gerenciarLeitura.readLine(); // COMECA A ARMAZENAR REALMENTE OS DADOS
            
            // ENQUANTO NAO HOUVER LINHAS VAZIA, CONTINUE LENTO
            while(linha != null) {
                
                // NAVEGA NA STRING ARMAZENANDO OS DADOS NO VETOR LEVANDO COMO REFERENCIA AS VIRGULAS
                String[] vetor = linha.split(";");
                nome = vetor[0];
                idade = Integer.parseInt(vetor[1]);
                sexo = vetor[2];
                
                /* POST */
                usuario = new Usuario(); // LIMPA DOS DADOS DO USUARIO PARA INSERIR NOVOS DADOS
                usuario.setNome(nome);
                usuario.setIdade(idade);
                usuario.setSexo(sexo);
                
                /* RECEBE COMO RETORNO UM VALOR BOOLEANO DO METODO POST DE ACORDO COM O RESULTADO DO METODO */
                status = UsuarioDAO.inserirUsuario(usuario);

                if(status) {
                    System.out.println("Usuario cadastrado com sucesso!");

                }else {
                    System.out.println("Ocorreu um erro ao tentar cadastrar o usuario, tente novamente!");

                }
                
                System.out.println(linha);
                // ARMAZENA A PROXIMA LINHA NA VARIAVEL PARA IMPRIMIR NA TELA
                linha = gerenciarLeitura.readLine();
            
            }
            
            System.out.println("=====================================");
        	System.out.println("===== Seja Bem-vindo ao sistema =====");
        	System.out.println("=====================================");
            
            do {
            	
            	System.out.println("===========================");
            	System.out.println("===== Opcao 1: GET ========");
            	System.out.println("===== Opcao 2: POST =======");
            	System.out.println("===== Opcao 3: PUT ========");
            	System.out.println("===== Opcao 4: DELETE =====");
            	System.out.println("===== Opcao 0: SAIR =======");
            	System.out.println("===========================");
            	
            	System.out.print("==> ");
            	opcao = entrada.nextInt();
            	entrada.nextLine(); // LIMPA O CACHE DO TECLADO
            	
            	switch(opcao) {
            		case 1:
            			System.out.println("Metodo GET");
            			
            			// ACESSA O METODO GET QUE VAI ATE O BANCO DE DADOS PERCORRE ELE POR COMPLETO E TRAZ TODOS OS DADOS ARMAZENANDO-OS DENTRO DO Arraylist<> POR MEIO DO OBJETO Usuario  DESSA FORMA TRAZENDO OS DADOS MODELADOS DE ACORDO COM A CLASSE 
                        System.out.println("USUARIOS CADASTRADOS |=======================");
                        ArrayList<Usuario> listaUsuarios = UsuarioDAO.buscarUsuarios();
                        
                        // SE CRIA UMA FOR PARA PERCORRER O ArrayList<> E INFORMAR NA TELA TODOS OS DADOS CONTIDOS DENTRO DO Array 
                        for(Usuario u : listaUsuarios) {
                            System.out.println("ID: "+ u.getId());
                            System.out.println("Nome "+ u.getNome());
                            System.out.println("Idade: "+ u.getIdade());
                            System.out.println("Sexo: "+ u.getSexo());
                            System.out.println("");

                        }
                        
        			break;
        			
            		case 2:
            			System.out.println("Metodo POST");
            			
            			System.out.println("INSERINDO USUARIOS |=======================");
            			
            	        System.out.print("Digite o nome: ");
            	        nome = entrada.nextLine();
            	        
            	        System.out.print("Digite a idade: ");
            	        idade = entrada.nextInt();
            	        //LIMPA O CACHE DO SCANNER
            	        entrada.nextLine();
            	        
            	        System.out.print("Digite o sexo: ");
            	        sexo = entrada.nextLine();
            	        
            	        usuario = new Usuario(); // LIMPA DOS DADOS DO USUARIO PARA INSERIR NOVOS DADOS
            	        usuario.setNome(nome);
            	        usuario.setIdade(idade);
            	        usuario.setSexo(sexo);
            	        
            	        status = UsuarioDAO.inserirUsuario(usuario);
            	        
            	        if(status) {
            	            System.out.println("Usuario cadastrado com sucesso!");
            	        
            	        }else {
            	            System.out.println("Ocorreu um erro ao tentar cadastrar o usuario, tente novamente!");
            	            
            	        }
            	        
        			break;
        			
            		case 3:
            			System.out.println("Metodo PUT");
            			
            			System.out.println("ATUALIZAR USUARIO |=======================");
            	        
            	        System.out.print("Digite um ID para alterar um usuario: ");
            	        id = entrada.nextInt();
            	        entrada.nextLine();
            	        
            	        System.out.print("Nome: ");
            	        nome = entrada.nextLine();
            	        
            	        System.out.print("Idade: ");
            	        idade = entrada.nextInt();
            	        entrada.nextLine();
            	        
            	        System.out.print("Sexo: ");
            	        sexo = entrada.nextLine();
            	        
            	        usuario = new Usuario(); // LIMPA DOS DADOS DO USUARIO PARA INSERIR NOVOS DADOS
            	        usuario.setId(id);
            	        usuario.setNome(nome);
            	        usuario.setIdade(idade);
            	        usuario.setSexo(sexo);
            	        
            	        status = UsuarioDAO.atualizarUsuario(usuario);
            	        
            	        if(status) {
            	            System.out.println("Usuario atualizado com sucesso!");
            	        
            	        }else {
            	            System.out.println("Ocorreu um erro ao tentar atualizar o dado, tente novamente!");
            	            
            	        }
            	        
        			break;
        			
            		case 4:
            			System.out.println("Metodo DELETE");
            			
            			System.out.println("DELETAR USUARIO |=======================");
            	        
            	        System.out.print("Digite um ID para deletar um usuario: ");
            	        id = entrada.nextInt();
            	        
            	        usuario = new Usuario(); // LIMPA DOS DADOS DO USUARIO PARA INSERIR NOVOS DADOS
            	        usuario.setId(id);
            	        
            	        status = UsuarioDAO.deletarUsuario(usuario);
            	        
            	        if(status) {
            	            System.out.println("Usuario excluido com sucesso!");
            	        
            	        }else {
            	            System.out.println("Ocorreu um erro ao tentar excluir o usuario, tente novamente!");
            	            
            	        }
            	        
        			break;
        			
            		case 0:
            			System.out.println("SAIR");
        			break;
        			
            		default:
            			System.out.println("Opcao invalida, tente novamente!");
        			break;
            	
            	}
            	
            }while(opcao != 0);
            
            // ACESSA O METODO GET QUE VAI ATE O BANCO DE DADOS PERCORRE ELE POR COMPLETO E TRAZ TODOS OS DADOS ARMAZENANDO-OS DENTRO DO Arraylist<> POR MEIO DO OBJETO Usuario  DESSA FORMA TRAZENDO OS DADOS MODELADOS DE ACORDO COM A CLASSE
            /*System.out.println("USUARIOS CADASTRADOS |=======================");
            ArrayList<Usuario> usuarios = UsuarioDAO.buscarUsuarios();
            
            // SE CRIA UMA FOR PARA PERCORRER O ArrayList<> E INFORMAR NA TELA TODOS OS DADOS CONTIDOS DENTRO DO Array
            for(Usuario u : usuarios) {
                System.out.println("ID: "+ u.getId());
                System.out.println("Nome "+ u.getNome());
                System.out.println("Idade: "+ u.getIdade());
                System.out.println("Sexo: "+ u.getSexo());
                System.out.println("");

            }*/
        
        /* TRAZ UM ERRO DE LEITURA DE ARQUIVO */
        }catch(IOException erro) {
            /* PERCORRE O ERRO E TRAZ NA TELA SEU PORCURSO COMPLETO, INFORMANDO ONDE EXATAMENTE ESTA O ERRO */
            erro.printStackTrace();
            
            /* IMPRIME NA TELA UMA MENSAGEM DE ERRO */
            System.out.println(erro.getMessage());
        
        }
        
        /*
        // ARMAZENA O CAMINHO DO ARQUIVO EM UMA VARIAVEL
        String arquivo = "d:\\\\teste.txt.txt";
        
        // LE O ARQUIVO ESPECIFICADO
        FileReader lerArquivo = null;
        // GERENCIA A LEITURA DO ARQUIVO DE UMA FORMA MAIS OTIMIZADA
        BufferedReader gerenciarLeitura = null;
        
        try {
            // INSTANCIA A LEITURA DO ARQUIVO PASSANDO COMO PARAMETRO O CAMINHO DO ARQUIVO
            lerArquivo = new FileReader(arquivo);
            // INSTANCIA A LEITURA DO ARQUIVO PASSANDO COMO PARRAMENTRO A LEITURA DO ARQUIVO
            gerenciarLeitura = new BufferedReader(lerArquivo);
            
            // CRIA UMA CARIAVEL PARA ARMAZENAR A ACESSAR DE LINHA A LINHA OS DADOS DO ARQUIVO readLine()
            String linha = gerenciarLeitura.readLine();
            
            // ENQUANTO NAO HOUVER LINHAS VAZIA, CONTINUE LENTO
            while(linha != null) {
                System.out.println(linha);
                // ARMAZENA A PROXIMA LINHA NA VARIAVEL PARA IMPRIMIR NA TELA
                linha = gerenciarLeitura.readLine();
            
            }
        
        }catch(IOException erro) {
            erro.printStackTrace();
            
            System.out.println(erro.getMessage());
        
        }finally {
            // COMO PODE ACABAR GERANDO UMA EXECAO E NECESSARIO TRATALA COM TRY{}CATCH NOVAMENTE
            try {
                gerenciarLeitura.close();
                lerArquivo.close();
                
            }catch(IOException erro) {
                erro.printStackTrace();
                
            }
        
        }*/
        
        /*
        // LENDO UM ARQUIVO .CSV
        // \\ = significa a barra invertida
        File arquivo = new File("d:\\teste.txt.txt");
        Scanner entrada = null;
        
        try {
            // LE UMA STRING DE UM ARQUIVO EM ESPECIFICO
            entrada = new Scanner(arquivo);
            
            // ENQUANTO HOUVER LINHAS NO ARQUIVO, CONTINUE IMPRIMINDO NA TELA (hasNextLine()) 
            while(entrada.hasNextLine()) {
                // ARMAZENA O VALOR CAPITURADO DO ARQUIVO E ARMAZENA EM UMA VARIAVEL
                String linha = entrada.nextLine();
                
                System.out.println(linha);
                
            }
        
        }catch(IOException erro) {
            erro.printStackTrace();
            
            System.out.println(erro.getMessage());
            
        }finally {
            // FINALIZA O SCANNER
            if(entrada != null) {
                entrada.close();
                
            }
        
        }*/
        
        /* GET
        System.out.println("USUARIOS CADASTRADOS |=======================");
        ArrayList<Usuario> usuarios = UsuarioDAO.buscarUsuarios();
        
        for(Usuario usuario : usuarios) {
            System.out.println("ID: "+ usuario.getId());
            System.out.println("Nome "+ usuario.getNome());
            System.out.println("Idade: "+ usuario.getIdade());
            System.out.println("Sexo: "+ usuario.getSexo());
            System.out.println("");
            
        }
        */
        
        /* POST
        System.out.println("INSERINDO USUARIOS |=======================");
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Digite o nome: ");
        String nome = entrada.nextLine();
        
        System.out.print("Digite a idade: ");
        int idade = entrada.nextInt();
        //LIMPA O CACHE DO SCANNER
        entrada.nextLine();
        
        System.out.print("Digite o sexo: ");
        String sexo = entrada.nextLine();
        
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setIdade(idade);
        usuario.setSexo(sexo);
        
        boolean status = UsuarioDAO.inserirUsuario(usuario);
        
        if(status) {
            System.out.println("Usuario cadastrado com sucesso!");
        
        }else {
            System.out.println("Ocorreu um erro ao tentar cadastrar o usuario, tente novamente!");
            
        } 
        */
        
        /* PUT
        System.out.println("ATUALIZAR USUARIO |=======================");
        Scanner entrada = new Scanner(System.in);
        
        ArrayList<Usuario> usuarios = UsuarioDAO.buscarUsuarios();
        
        for(Usuario usuario: usuarios) {
            System.out.println("ID: ["+ usuario.getId() +"]");
            System.out.println("Nome "+ usuario.getNome());
            System.out.println("Idade: "+ usuario.getIdade());
            System.out.println("Sexo: "+ usuario.getSexo());
            System.out.println("");
        
        }
        
        System.out.print("Digite um ID para alterar um usuario: ");
        int id = entrada.nextInt();
        entrada.nextLine();
        
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        
        System.out.print("Idade: ");
        int idade = entrada.nextInt();
        entrada.nextLine();
        
        System.out.print("Sexo: ");
        String sexo = entrada.nextLine();
        
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setIdade(idade);
        usuario.setSexo(sexo);
        
        boolean status = UsuarioDAO.atualizarUsuario(usuario);
        
        if(status) {
            System.out.println("Usuario atualizado com sucesso!");
        
        }else {
            System.out.println("Ocorreu um erro ao tentar atualizar o dado, tente novamente!");
            
        }
        */
        
        /* DELETE
        System.out.println("DELETAR USUARIO |=======================");
        Scanner entrada = new Scanner(System.in);
        
        ArrayList<Usuario> usuarios = UsuarioDAO.buscarUsuarios();
        
        for(Usuario usuario : usuarios) {
            System.out.println("ID: ["+ usuario.getId() +"]");
            System.out.println("Nome "+ usuario.getNome());
            System.out.println("");
        
        }
        
        System.out.print("Digite um ID para deletar um usuario: ");
        int id = entrada.nextInt();
        
        Usuario usuario = new Usuario();
        usuario.setId(id);
        
        boolean status = UsuarioDAO.deletarUsuario(usuario);
        
        if(status) {
            System.out.println("Usuario excluido com sucesso!");
        
        }else {
            System.out.println("Ocorreu um erro ao tentar excluir o usuario, tente novamente!");
            
        }
        */
        
    }
    
}
