public class ProcessoItem {
    public int referenciado = 0; //se existe uma valor igual a ele na memoria
    public int modificado = 0; // foi acessado recentemente
    public int moldura = -1;  // posição na memoria
    public int valor = 0;

    public ProcessoItem(int valor){
        this.valor = valor;
        //ao ser criado automaticamente é modificado e referenciado
        this.referenciado = 1;
        this.modificado = 1;
    }
}
