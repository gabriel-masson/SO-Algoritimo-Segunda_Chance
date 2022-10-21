public class Processo extends Thread {

    public Memoria memoria = null;
    public String[] entrada = null;

    public Processo(Memoria memoria, String[] entrada) {
        this.entrada = entrada;
        this.memoria = memoria;
        // ao criar um processo ele se torna modificado

    }

    public void run() {
        try {
            // 1- vamos organizar a entrada na pos x
            System.out.println("Processo");
            int idx = 0;
            for (String instrucao : this.entrada) {

                // 2 - vamos identificar qual operação devemos executar
                if (instrucao.indexOf("W") >= 0) {
                    // separa as instruções
                    String[] separaInstrucao = instrucao.split("-");

                    // posição memoria, operação, valor
                    // separaInstrucao[0] + separaInstrucao[1]+ separaInstrucao[2]
                    int valor = Integer.parseInt(separaInstrucao[2]);
                    ProcessoItem item = new ProcessoItem(valor);
                    this.memoria.inserir(item);

                }
                if (instrucao.indexOf("R") >= 0) {
                    String[] separaInstrucao = instrucao.split("-");

                    // posição memoria operação
                    // separaInstrucao[0] + separaInstrucao[1]
                    
                    int valor = Integer.parseInt(separaInstrucao[0]);
                    ProcessoItem item = new ProcessoItem(valor);
                    
                    this.memoria.ler(item);
                }

                // System.out.println(idx + "-> " + instrucao);
                // idx++;
            }
            

        } catch (Exception e) {

        }
    }

}
