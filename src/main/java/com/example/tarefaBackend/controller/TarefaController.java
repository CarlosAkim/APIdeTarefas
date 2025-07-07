package com.example.tarefaBackend.controller;

import com.example.tarefaBackend.model.Tarefa;
import com.example.tarefaBackend.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping({"/tarefa"})
public class TarefaController {

    @Autowired
    private TarefaService service;

    @GetMapping
    public List<?> listar(){
        return service.listarTodas();
    }

    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa){
        return service.salvar(tarefa);
    }

    @GetMapping(path = {"/{id}"})
    public Tarefa buscarId(@PathVariable Long id){
        return service.buscarId(id)
                .orElseThrow(()-> new RuntimeException("Pessoa não encontrada"));
    }
    @DeleteMapping(path={"/{id}"})
    public void delete(@PathVariable Long id){
        service.deletar(id);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> AtualizarId(@PathVariable Long id, @RequestBody Tarefa tarefa){
        try{
            Tarefa atualizada = service.atualizar(id, tarefa);
            return ResponseEntity.ok(atualizada);
        }
        catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }



}
