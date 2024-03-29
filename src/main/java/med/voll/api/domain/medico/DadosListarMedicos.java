package med.voll.api.domain.medico;

public record DadosListarMedicos(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public DadosListarMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
