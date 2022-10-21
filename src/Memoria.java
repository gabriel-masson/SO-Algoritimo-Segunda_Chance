import java.util.ArrayList;

public class Memoria {
    // LinkedList processoItem;
    ArrayList<ProcessoItem> processoItem = new ArrayList<ProcessoItem>();
    private int TAMANHO_LISTA = 2;
    

    public void inserir(ProcessoItem item) {
        synchronized (this) {
            // vamos verificar se o valor do item ja vou referenciado
            System.out.println("----------- ESCRITA ------------");  
            try {
                
                int indice = 0;
                int foiReferenciado = 0;
                for (int i = 0; i < processoItem.size(); i++) {
                    ProcessoItem processo = processoItem.get(i);
                    // 3 - vamos verificar se já exite na memória, caso exista
                    if (processo.valor == item.valor) {
                        processo.modificado = 1; // ganhou mais uma chance
                        foiReferenciado = 1;
                        // Process modificado

                    }
                }

                //4 - Se ele não foi referenciado, significa que um posso adicionar
                if (foiReferenciado == 0) {
                    
                    if (processoItem.size() < TAMANHO_LISTA) {
                        processoItem.add(item);
                        System.out.println("Valor adicionado: " + item.valor);
                    } else {
                        // 5 - Executar o algoritmo de segunda chance
                        System.out.println("------ Page Fault ------"); 
                        while (processoItem.get(indice).modificado == 1) {

                            ProcessoItem processo = processoItem.get(indice);
                            // perde a segunda chance
                            processo.modificado = 0;
                           

                            indice++;
                            // ponteiro voltando ao inicio para começa o processo de eliminação
                            if (indice == TAMANHO_LISTA) {
                                indice = 0;
                            }
                        }

                        // ao sair do laço while o indice terá a referência do item a ser substituido

                        // aqui iniciamos o processo de substituicao
                        // remove a page                       
                        System.out.println("Valor a ser removido: ");
                        System.out.println(processoItem.get(0).valor);
                        processoItem.remove(indice);

                        // coloca o novo valor no quadro
                        processoItem.add(indice, item);
                        ProcessoItem x =  processoItem.get(indice);
                        System.out.println("Novo valor: " + x.valor);
                        indice++;

                        // ponteiro voltando ao inicio
                        if (indice == TAMANHO_LISTA) {
                            indice = 0;
                        }
                        

                    }
                }
                
            } catch (Exception e) {
                System.out.println("Houve um erro inesperado");
                System.out.println(e);
            }
        }
    }

    public void ler(ProcessoItem item) {
        synchronized(this){
            try {
                if(item.moldura >= processoItem.size()){
                    System.out.println("Não podemos ler essa posição!");
                }
                for (int i = 0; i < processoItem.size(); i++) {
                    if (item.valor == i) {
                        System.out.println("----------- LEITURA ------------");  
                        ProcessoItem processo = processoItem.get(i);
                        System.out.println("Processo Valor: " + processo.valor);
                         
                    }
                }
                
                

            } catch (Exception e) {
                System.out.println("Houve um erro inesperado");
                System.out.println(e);
            }
        }
    }
}
