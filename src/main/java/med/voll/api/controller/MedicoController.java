package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired//instanciando o repositório
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) { //REQUEST BODY PEGA O CORPO DA REQUISIÇÃO E NÃO UMA STRING JSON
        var medico = new Medico(dados);
        repository.save(medico);//instanciando um Medico usando dto
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListarMedicos>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page =  repository.findAllByAtivoTrue(paginacao).map(DadosListarMedicos::new);//find all retorna lista de Medicos
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional//indicar que faz uma escrita no banco, se uma entidade for mudada atualiza automaticamente no banco
    public ResponseEntity atualizarDadosMedico(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativarMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.setarComoInativo();

        return ResponseEntity.noContent().build();//requisições delete não retornam conteúdo
    }


}
