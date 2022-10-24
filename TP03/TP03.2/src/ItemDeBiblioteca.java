public interface ItemDeBiblioteca {
    public int diasemprestimo = 7;

    public boolean getEmprestado();
    public String getLocalizacao();
    public void Empresta();
    public void Devolve();

}
