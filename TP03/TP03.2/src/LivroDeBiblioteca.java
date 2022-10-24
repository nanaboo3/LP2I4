public class LivroDeBiblioteca extends Livro implements ItemDeBiblioteca {
    private String localizacao;
    private boolean emprestado;

    public LivroDeBiblioteca(String titulo, String autor, String editora, short anoEdicao, String localizacao){
        super(titulo, autor, editora, anoEdicao);
        this.localizacao = localizacao;
        this.emprestado = false;
    }

    @Override
    public boolean getEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    @Override
    public String getLocalizacao() {
        return localizacao;
    }

    @Override
    public void Empresta() {
        this.emprestado = true;
    }

    @Override
    public void Devolve() {
        this.emprestado = false;
    }

    @Override
    public String getDados(){
        return "Título: " + titulo +
                "\nAutor: " +autor+
                "\nEditora: " +editora+
                "\nAno: "+anoEdicao+
                "\nLocalização: "+localizacao;
    }
}
