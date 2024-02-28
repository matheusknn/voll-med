package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired//instanciando o repositório
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados) { //REQUEST BODY PEGA O CORPO DA REQUISIÇÃO E NÃO UMA STRING JSON
        repository.save(new Medico(dados));//instanciando um Medico usando dto
    }

    @GetMapping
    public Page<DadosListarMedicos> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListarMedicos::new);//find all retorna lista de Medicos

    }

    @PutMapping
    @Transactional//indicar que faz uma escrita no banco, se uma entidade for mudada atualiza automaticamente no banco
    public void atualizarDadosMedico(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }
}
