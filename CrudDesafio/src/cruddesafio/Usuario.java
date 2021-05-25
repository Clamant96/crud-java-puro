/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruddesafio;

import java.io.Serializable;

/**
 *
 * @author kevin
 */
                                // UTILIZADO PARA REALIZAR A GRAVACAO DOS DADOS OU TRAFEGO EM REDE, EVITA DAR ERRO
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private long id;
    
    private String nome;
    
    private int idade;
    
    private String sexo;
    
    public Usuario() {
    }
    
    public Usuario(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        
    }
    
    public void setNome(String nome) {
        this.nome = nome;
        
    }
    
    public long getId() {
        
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
        
    }
    
    public String getNome() {
        
        return this.nome;
    }
    
    public void setIdade(int idade) {
        this.idade = idade;
        
    }
    
    public int getIdade() {
        
        return this.idade;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
        
    }
    
    public String getSexo() {
        
        return this.sexo;
    }
}
