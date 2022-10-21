import java.util.ArrayList;
import java.util.Random;

public class FabricaDeEntradas {
	
	public static final int QTD_ACESSO_ENTRADA = 25;
	private int tamanhoMemoriaVirtual = 0;
	private int seed;
	
	public FabricaDeEntradas(int tamanhoMemoria) {
		this.tamanhoMemoriaVirtual = tamanhoMemoria;
		Random r = new Random();
		this.seed = r.nextInt(tamanhoMemoria);
		System.out.println("Semente = " + seed);
		if(tamanhoMemoria < 5) {
			throw new IllegalArgumentException("Memoria muito pequena - valor minimo 10");
		}
		if(tamanhoMemoria > 40) {
			throw new IllegalArgumentException("Memoria grande - valor maximo 40");
		}
		
		if(QTD_ACESSO_ENTRADA < 25) {
			throw new IllegalArgumentException("Tamanho da entrada nao pode ser menor que 25");
		}
	}
	
	public String getNewEntrada() {
		String value = "";
		Random r = new Random(this.seed);
		Random r1 = new Random();
		
		ArrayList<Integer> reads = new ArrayList<Integer>();
		String[] loop = new String[4];
		int indexLoop = 0;
		
		StringBuffer sb = new StringBuffer();
		int loop1 = QTD_ACESSO_ENTRADA/3 * 1;
		int loop2 = QTD_ACESSO_ENTRADA/3 * 2;
		//System.out.println("Loop1 = "  + loop1 + " - loop2 = " + loop2);
		
		for (int i = 0; i < QTD_ACESSO_ENTRADA; i++) {
			if(i == loop1 || i == loop2) {
				//sb.append("|- ");
				for (int j = 0; j < loop.length; j++) {
					sb.append(loop[indexLoop++]);
					indexLoop = indexLoop % loop.length;
				}
				//sb.append(" -|");
				i = i + loop.length-1;
				continue;
			}
			
			int endereco = r.nextInt(tamanhoMemoriaVirtual);
			
			int index = reads.indexOf(endereco);
			if(index == -1) {
				sb.append(endereco + "-R,");
				loop[indexLoop++] = endereco + "-R,";
				indexLoop = indexLoop % loop.length;
				reads.add(endereco);
			} else {
				boolean tipoAcesso = r.nextBoolean();
				if(tipoAcesso) {
					sb.append(endereco + "-R,");
					loop[indexLoop++] = endereco + "-R,";
					indexLoop = indexLoop % loop.length;
				} else {
					int aux = r1.nextInt(100);
					sb.append(endereco + "-W-" + aux + ",");
					loop[indexLoop++] = endereco + "-W-" + aux + ",";
					indexLoop = indexLoop % loop.length;
				}
			}
		}
		
		value = sb.substring(0, sb.length()-1);
		return value;
	}
}