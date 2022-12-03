package dev.ifrs.Model;

import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;


public class User {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private List<Cafe> cafes;

    public User() {
        this.cafes = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return this.login;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getSenha(){
        return this.senha;
    }

    public List<Cafe> getCafes() {
        return cafes;
    }

    public void setCafes(List<Cafe> cafes) {
        this.cafes = cafes;
    }

    public void addCafe(Cafe cafe) {
        this.cafes.add(cafe);
    }
}
