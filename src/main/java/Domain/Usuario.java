package Domain;

public class Usuario {

    private Long idUsuario;
    private String nmUsuario;
    private String nrCpf;
    private String dsEmail;
    private boolean ieMigracao;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getNrCpf() {
        return nrCpf;
    }

    public void setNrCpf(String nrCpf) {
        this.nrCpf = nrCpf;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public boolean isIeMigracao() {
        return ieMigracao;
    }

    public void setIeMigracao(boolean ieMigracao) {
        this.ieMigracao = ieMigracao;
    }
}
