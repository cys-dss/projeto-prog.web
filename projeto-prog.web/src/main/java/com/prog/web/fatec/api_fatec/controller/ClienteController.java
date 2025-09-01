package com.prog.web.fatec.api_fatec.controller;

import com.prog.web.fatec.api_fatec.domain.cliente.ClienteRepository;
import com.prog.web.fatec.api_fatec.entities.Cliente;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Opcional: carga inicial na H2
    @PostConstruct
    public void dadosIniciais() {
        if (clienteRepository.count() == 0) {
            clienteRepository.save(new Cliente(null, "Jesus", "Rua Céu"));
            clienteRepository.save(new Cliente(null, "Socorro", "Rua Help"));
            clienteRepository.save(new Cliente(null, "Peido", "Rua Bufa"));
        }
    }

    // CREATE
    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // READ - listar todos
    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // READ - por id
    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente dto) {
        return clienteRepository.findById(id)
                .map(existente -> {
                    existente.setNome(dto.getNome());
                    existente.setEndereco(dto.getEndereco());
                    return clienteRepository.save(existente);
                })
                .orElse(null);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        }
    }

}
