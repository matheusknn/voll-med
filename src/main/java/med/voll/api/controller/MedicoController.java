package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListarMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DadosListarMedicos> listarMedicos() {
        return repository.findAll().stream().map(DadosListarMedicos::new).toList();//find all retorna lista de Medicos

    }
}
