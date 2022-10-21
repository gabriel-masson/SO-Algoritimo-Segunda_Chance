public class App {
    public static void main(String[] args) throws Exception {
        int tamanhoDaMinhaMemoriaVirtual = 10;
        String SUA_ENTRADA = new FabricaDeEntradas(tamanhoDaMinhaMemoriaVirtual).getNewEntrada();
        System.out.println(SUA_ENTRADA);
        String[] entradas = SUA_ENTRADA.split(",");

        Memoria m = new Memoria();
        Processo p = new Processo(m, entradas);

        p.start();

        
    }
}
