package com.example.tarefaBackend.service;


import com.example.tarefaBackend.model.Tarefa;
import com.example.tarefaBackend.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository repository;

    public Tarefa salvar(Tarefa tarefa){
        return repository.save(tarefa);
    }

    public List<Tarefa> listarTodas(){
        return repository.findAll();
    }

    public Optional<Tarefa> buscarId(Long id){
        return repository.findById(id);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public Tarefa atualizar(Long id, Tarefa tarefaAtualizado){
        Tarefa tarefaExistente = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        tarefaExistente.setNome(tarefaAtualizado.getNome());
        tarefaExistente.setResponsavel(tarefaAtualizado.getResponsavel());
        tarefaExistente.setDataEntrega(tarefaAtualizado.getDataEntrega());

        return repository.save(tarefaExistente);
    }
}
