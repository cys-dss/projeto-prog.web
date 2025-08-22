package com.prog.web.fatec.api_fatec.controller;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController /* qnd inicia, entende que a classe ira receber uma requisição*/
@RequestMapping("/api/clientes")
public class ClienteController {
 
    @GetMapping("testeCliente1")
    public String testeCliente(){
        return "Teste";
    }    
 
    @GetMapping("/testeCliente2/{nome}")
public String testeCliente2(@PathVariable String nome){
    return nome;
}    
 
    @GetMapping("3/{num}")
public String idade(@PathVariable int num){
    if (num>=18){
        return "Maior de idade";
    }
    else{
        return "Menor de idade";
    }
}
 
 
}
 