package br.com.higor.dashboard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.higor.dashboard.dao.UsuarioDAO;
import br.com.higor.dashboard.model.Usuario;

@CrossOrigin("*")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ArrayList<Usuario> recuperaTodoMundo() {
		ArrayList<Usuario> lista;
		
		lista = (ArrayList<Usuario>)dao.findAll();
		return lista;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> logarUsuario(@RequestBody Usuario dadosLogin) {
		Usuario resultado = dao.findByRacfOrEmail(dadosLogin.getRacf(), dadosLogin.getEmail());
		
		if(resultado != null) {
			if(resultado.getSenha().equals(dadosLogin.getSenha())) {
				return ResponseEntity.ok(resultado);
			} else {
				return ResponseEntity.status(401).build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
