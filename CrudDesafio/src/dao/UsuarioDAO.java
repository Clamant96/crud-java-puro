/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.cj.jdbc.Driver;
import cruddesafio.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class UsuarioDAO {
    
    /* CONSTANTES DO SISTEMA */
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    
    /* CRUD */
    /* GET */
    public static ArrayList<Usuario> buscarUsuarios() {
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        /* INSTANCIA UM NOVO DRIVER PARA SE COMUNICAR COM O MYSQL */
        try {
            Driver driver = new Driver();
            /* REGISTRA ESSA INSTANCIA PARA QUE O JAVA SAIBA QUE E EXATAMENTE ESSE DRIVE QUE ESTAMOS QUERENDO UTILIZAR */
            DriverManager.registerDriver(driver);
            
            /* TIPO DE PROTOCOLO E CONEXAO COM O BANCO DE DADOS */
            /* jdbc = Java DataBase Connection */
            /* mysql = Tipo do banco */
            /* localhost = enderecamento do banco */
            /* :3306 = porta de conexao com o banco */
            /* tambem como paremtro e necessario passar o usuario e senha */
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            
            /* CONSTROI A QUARY */
            PreparedStatement query = conexao.prepareStatement("SELECT * FROM desafio_flashsafe.usuario");
            
            /* EXECUTA A QUERY ENVIANDO-A PARA O BANCO DE DADOS */
            /* E ARMAZENA ESSA RESPOSTA/RETORNO EM UMA VARIAVEL */
            ResultSet retorno = query.executeQuery();
            
            /* ACESSA O CONTEUDO ARMAZENADO NA VARAIVEL QUE RECEBEU O RETORNO DO BANCO DE DADOS */
            /* SE CRIA UMA CONDICAO COM BASE NO RETORNO DA CLASSE ResultSet (true ou false) */
            /* DESSA FORMA, CASO HAJA UMA PROXIMA LINHA, RETORNAMOS, CASO CONTRARIO SAI DO LOOP */
            while(retorno.next()) {
                long id = retorno.getLong("id");
                String nome = retorno.getString("nome");
                int idade = retorno.getInt("idade");
                String sexo = retorno.getString("sexo");
                
                /* INSTANCIA UM NOVO USUARIO, E ARMAZE OS DADOS VINDOS DO BANCO DETRO DO USUARIO */
                Usuario novoUsuario = new Usuario();
                
                /* POPULA O USUARIO COM OS DADOS */
                novoUsuario.setId(id);
                novoUsuario.setNome(nome);
                novoUsuario.setIdade(idade);
                novoUsuario.setSexo(sexo);
                
                /* ADICIONA ESSE NOVO USUARIO DENTRO DO ARRAY DE USUARIOS */
                usuarios.add(novoUsuario);
            
            }
            
            /* FINALIZA A CONEXAO E QUERY COM O BANCO DE DADOS */
            query.close();
            conexao.close();
            
        }catch(SQLException erro) {
            /* INFORMA NA TELA TODO O TRAJETO DO ERRO */
            /* PERCORRE O ERRO POR COMPLETO */
            erro.printStackTrace();
            
        }
        
        /* RETORNA UM ARRAY LISTA COM TODOS OS DADOS DO BANCO DE DADOS, MODELADOS DE ACORDO COM A CLASSE USUARIO */
        return usuarios;
    }
    
    /* POST */
    public static boolean inserirUsuario(Usuario usuario) {
        boolean status = false;
        boolean validacao = true;
        
        /* CARREGA A LISTA DE USUARIO DO BANCO DE DADOS */
        ArrayList<Usuario> listaUsuarios = buscarUsuarios();
    	
        /* PERCORRE O ARRAY E VERIFICA SE O USUARIO EXISTE NA BASE DE DADOS */
    	for(int i = 0; i < listaUsuarios.size(); i++) {        		
    		if(listaUsuarios.get(i).getNome().contains(usuario.getNome())) {
    			System.out.println(listaUsuarios.get(i).getNome() +" ja existe na base de dados!");
    			validacao = false;
    			
    		}
    		
    		
    	}
        
    	/* CASO O USUARIO NAO EXISTA NA BASE DE DADOS, E INSERIDO O DADO NA BASE DE DADOS */
        if(validacao) {
        	try {
        		System.out.println("Acessou o cadastro");
        		
                /* INSTANCIA UM NOVO DRIVER PARA SE COMUNICAR COM O MYSQL */
                Driver driver = new Driver();
                
                /* REGISTRA ESSA INSTANCIA PARA QUE O JAVA SAIBA QUE E EXATAMENTE ESSE DRIVE QUE ESTAMOS QUERENDO UTILIZAR */
                DriverManager.registerDriver(driver);
                
                /* REALIZA CONEXAO COM O BANCO DE DADOS */
                Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                
                /* CONSTROE UMA QUERY PARA REALIZAR A INSERSAO DE DADOS NA BASE DE DADOS */
                /* COMO AINDA NAO TEMOS OS DADOS, INFORMAMOS NO LUGAR DO VALUES ?, DE ACORDO COM A QTD DE ITENS CONTIDOS NA TABELA EM QUE ESTAMOS INSERINDO OS DADOS */
                PreparedStatement query = conexao.prepareStatement("INSERT INTO desafio_flashsafe.usuario(nome, idade, sexo) VALUES(?, ?, ?)");
                
                /* INSERE DADOS NAS POSICOES ESPECIFICADAS DA QUARY */
                query.setString(1, usuario.getNome());
                query.setInt(2, usuario.getIdade());
                query.setString(3, usuario.getSexo());
                
                /* INSERE OS DADOS NA BASE DE DADOS, E COMO RETORNO NOS TRAZ A QTD DE LINHAS AFETADAS COM A ALTERACAO DO BANCO */
                /* ARMAZENA A QTD DE LINHAS AFETADAS DENTRO DE UMA VARAIVEL */
                int linhasAfetadas =  query.executeUpdate();
                
                /* VERIRICA SE O DADO FOI INSERIDO COM SUCESSO, LEVANDO COMO PARAMETRO O RETORNO DO METODO executeUpdate() QUE RETONA UM INTEIRO COM AS LINHAS AFETADAS */
                /* CASO A QTD DE LINHAS SEJA MAIOR QUE ZERO, SABEMOS HOUVE UMA ALTERACAO NO BANCO */
                if(linhasAfetadas > 0) {
                    status = true;
                    
                }
                
                /* FINALIZA A CONEXAO E QUERY COM O BANCO DE DADOS */
                query.close();
                conexao.close();
                
            }catch(SQLException erro) {
                /* INFORMA NA TELA TODO O TRAJETO DO ERRO */
                /* PERCORRE O ERRO POR COMPLETO */
                erro.printStackTrace();
                
            }
        	
        }
        
        return status;
        
    }
    
    /*PUT*/
    public static boolean atualizarUsuario(Usuario usuario) {
        boolean status = false;
        
        try {
            /* INSTANCIA UM NOVO DRIVER PARA SE COMUNICAR COM O MYSQL */
            Driver driver = new Driver();
            
            /* REGISTRA ESSA INSTANCIA PARA QUE O JAVA SAIBA QUE E EXATAMENTE ESSE DRIVE QUE ESTAMOS QUERENDO UTILIZAR */
            DriverManager.registerDriver(driver);
            
            /* REALIZA CONEXAO COM O BANCO DE DADOS */
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            
            /* CONSTROE UMA QUERY PARA REALIZAR A ATUALIZACAO DE DADOS NA BASE DE DADOS */
            /* COMO AINDA NAO TEMOS OS DADOS, INFORMAMOS NO LUGAR DO SET ? */
            PreparedStatement query = conexao.prepareStatement("UPDATE desafio_flashsafe.usuario SET nome = ?, idade = ?, sexo = ? WHERE id = ?");
            
            /* INSERE DADOS NAS POSICOES ESPECIFICADAS DA QUARY */
            query.setString(1, usuario.getNome());
            query.setInt(2, usuario.getIdade());
            query.setString(3, usuario.getSexo());
            query.setLong(4, usuario.getId());
            
            /* INSERE OS DADOS NA BASE DE DADOS, E COMO RETORNO NOS TRAZ A QTD DE LINHAS AFETADAS COM A ALTERACAO DO BANCO */
            /* ARMAZENA A QTD DE LINHAS AFETADAS DENTRO DE UMA VARAIVEL */
            int linhasAfetadas = query.executeUpdate();
            
            /* VERIRICA SE O DADO FOI INSERIDO COM SUCESSO, LEVANDO COMO PARAMETRO O RETORNO DO METODO executeUpdate() QUE RETONA UM INTEIRO COM AS LINHAS AFETADAS */
            /* CASO A QTD DE LINHAS SEJA MAIOR QUE ZERO, SABEMOS HOUVE UMA ALTERACAO NO BANCO */
            if(linhasAfetadas > 0) {
                //System.out.println("Os dados foram atualizados com sucesso!");
                status = true;
                
            }
            
            /* FINALIZA A CONEXAO E QUERY COM O BANCO DE DADOS */
            query.close();
            conexao.close();
            
        }catch(SQLException erro) {
            /* INFORMA NA TELA TODO O TRAJETO DO ERRO */
            /* PERCORRE O ERRO POR COMPLETO */
            erro.printStackTrace();
            
        }
        
        return status;
        
    }
    
    /* DELETE */
    public static boolean deletarUsuario(Usuario usuario) {
        boolean status = false;
        
        try {
            /* INSTANCIA UM NOVO DRIVER PARA SE COMUNICAR COM O MYSQL */
            Driver driver = new Driver();
            
            /* REGISTRA ESSA INSTANCIA PARA QUE O JAVA SAIBA QUE E EXATAMENTE ESSE DRIVE QUE ESTAMOS QUERENDO UTILIZAR */
            DriverManager.registerDriver(driver);
            
            /* REALIZA CONEXAO COM O BANCO DE DADOS */
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            
            /* CONSTROE UMA QUERY PARA REALIZAR A DELECAO DE UM DADOS DA BASE DE DADOS */
            /* COMO AINDA NAO TEMOS OS DADOS, INFORMAMOS NO LUGAR DO WHERE ? */
            PreparedStatement query = conexao.prepareStatement("DELETE FROM desafio_flashsafe.usuario WHERE id = ?");
            
            /* INSERE DADOS NAS POSICOES ESPECIFICADAS DA QUARY */
            query.setLong(1, usuario.getId());
            
            /* INSERE OS DADOS NA BASE DE DADOS, E COMO RETORNO NOS TRAZ A QTD DE LINHAS AFETADAS COM A ALTERACAO DO BANCO */
            /* ARMAZENA A QTD DE LINHAS AFETADAS DENTRO DE UMA VARAIVEL */
            int linhasAfetadas = query.executeUpdate();
            
            /* VERIRICA SE O DADO FOI INSERIDO COM SUCESSO, LEVANDO COMO PARAMETRO O RETORNO DO METODO executeUpdate() QUE RETONA UM INTEIRO COM AS LINHAS AFETADAS */
            /* CASO A QTD DE LINHAS SEJA MAIOR QUE ZERO, SABEMOS HOUVE UMA ALTERACAO NO BANCO */
            if(linhasAfetadas > 0) {
                status = true;
            
            }
            
            /* FINALIZA A CONEXAO E QUERY COM O BANCO DE DADOS */
            query.close();
            conexao.close();
            
        }catch(SQLException erro){
            /* INFORMA NA TELA TODO O TRAJETO DO ERRO */
            /* PERCORRE O ERRO POR COMPLETO */
            erro.printStackTrace();
            
        }
        
        return status;
    
    }
    
}
