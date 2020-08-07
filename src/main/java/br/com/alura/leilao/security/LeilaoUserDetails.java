package br.com.alura.leilao.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.alura.leilao.model.Usuario;

public class LeilaoUserDetails implements UserDetails {
	 
	private static final long serialVersionUID = 1L;
	private Usuario user;
     
    public LeilaoUserDetails(Usuario user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return Arrays.asList(authority);
    }
    
    public Usuario getUsuario() {
    	return this.user;
    }
 
    @Override
    public String getPassword() {
        return user.getSenha();
    }
 
    @Override
    public String getUsername() {
        return user.getNome();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
}
