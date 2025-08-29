package com.prog.web.fatec.api_fatec.controller;
 
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prog.web.fatec.api_fatec.domain.cliente.ClienteRepository;
import com.prog.web.fatec.api_fatec.entities.Cliente;

import jakarta.annotation.PostConstruct;
 
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    private final List<Cliente> listaDeCliente = new ArrayList<>();

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteController() {
 
        
        listaDeCliente.add(new Cliente(1L, "Cayos", "Rua Lacre"));
    }

    @PostConstruct()
    public void dadosIniciais() {
        clienteRepository.save(new Cliente(null, "Jesus", "Rua Céu"));
        clienteRepository.save(new Cliente(null, "Socorro", "Rua Help"));
        clienteRepository.save(new Cliente(null, "Peido", "Rua Bufa"));
    }

    @GetMapping("/listarClientes")
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
    

    @GetMapping("/testeCliente1") //-> /api/clientes/testeCliente1
    public String testeCliente() {
        return "Teste Client";
    }

    @GetMapping("/testeCliente2/{nome}") //-> /api/clientes/testeCliente2/
    public String testeCliente2(@PathVariable String nome) {
        return nome;
    }

    @PostMapping("")
    public Cliente createCliente(@RequestBody Cliente cliente) {

        listaDeCliente.add(cliente);

        return cliente;
    }

    @DeleteMapping("/{id}")
    public String deletarCliente(@PathVariable Long id) {
        
        for (Cliente cliente: listaDeCliente) {
                if (cliente.getId() == id) {
                    listaDeCliente.remove(cliente);
                    return "OK";
                }
        }

        return "ID NÃO ENCONTRADO:"+id;
    }

    @PutMapping("/{id}")
    public String alterarCliente(@PathVariable Long id, @RequestBody Cliente entity) {
        
         for (Cliente cliente: listaDeCliente) {
                if (cliente.getId() == id) {
                    cliente.setId(id);
                    cliente.setNome(entity.getNome());
                    return "ENCONTROU";
                }
        }

        return "ID NÃO ENCONTRADO:"+id;    
    }
    
}
 

