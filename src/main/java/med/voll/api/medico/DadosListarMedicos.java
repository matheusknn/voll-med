package med.voll.api.medico;

public record DadosListarMedicos(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListarMedicos(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
