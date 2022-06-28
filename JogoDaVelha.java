package jogoDaVelha;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class JogoDaVelha {
	
	static ArrayList<Integer> posicoesJogadores = new ArrayList<Integer>();
	static ArrayList<Integer> posicoesMaquina = new ArrayList<Integer>();

	public static void main(String[] args) {

		char[][] tabuleiroJogo = {{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', ' '},
				{' ', '|', ' ', '|', ' '}};

		printTabuleiroJogo(tabuleiroJogo);
		
		
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Marque seu posicionamento (1-9):");
			int jogadorPosicao = scan.nextInt();
			while(posicoesJogadores.contains(jogadorPosicao) || posicoesMaquina.contains(posicoesJogadores)) {
				System.out.println("Posição já marcada. Escolha outra posição");
				jogadorPosicao = scan.nextInt();
				
			}
			
			lugarMarcacao(tabuleiroJogo, jogadorPosicao, "jogador");
			
			String resultado = checarVencedor();
			if(resultado.length() > 0) {
				System.out.println(resultado);
				break;
			}
			
			Random aleatorio = new Random();
			int maquinaPosicao = aleatorio.nextInt(9) + 1;
			while(posicoesJogadores.contains(maquinaPosicao) || posicoesMaquina.contains(maquinaPosicao)) {
				maquinaPosicao = aleatorio.nextInt(9) + 1;
				
			}
			lugarMarcacao(tabuleiroJogo, maquinaPosicao, "maquina");
			
			printTabuleiroJogo(tabuleiroJogo);
			
			resultado = checarVencedor();
			if(resultado.length() > 0) {
				System.out.println(resultado);
				break;
			}
			System.out.println(resultado);
		}
		
	}
	
	public static void printTabuleiroJogo(char[][] tabuleiroJogo) {
		for(char[] fileira : tabuleiroJogo) {
			for(char c : fileira) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void lugarMarcacao(char[][] tabuleiroJogo, int posicao, String usuario) {
		
		char simbolo = 'O';
		
		if(usuario.equals("jogador")) {
			simbolo = 'X';
			posicoesJogadores.add(posicao);
		} else if(usuario.equals("cpu")) {
			simbolo = 'O';
			posicoesMaquina.add(posicao);
		}
		
		switch(posicao) {
			case 1:
				tabuleiroJogo[0][0] = simbolo;
				break;
			case 2:
				tabuleiroJogo[0][2] = simbolo;
				break;
			case 3:
				tabuleiroJogo[0][4] = simbolo;
				break;
			case 4:
				tabuleiroJogo[2][0] = simbolo;
				break;
			case 5:
				tabuleiroJogo[2][2] = simbolo;
				break;
			case 6:
				tabuleiroJogo[2][4] = simbolo;
				break;
			case 7:
				tabuleiroJogo[4][0] = simbolo;
				break;
			case 8:
				tabuleiroJogo[4][2] = simbolo;
				break;
			case 9:
				tabuleiroJogo[4][4] = simbolo;
				break;
				default:
					break;
		}
	}
	
	public static String checarVencedor() {
		
		List linhaTopo = Arrays.asList(1, 2, 3);
		List linhaMeio = Arrays.asList(4, 5, 6);
		List linhaFundo = Arrays.asList(7, 8, 9);
		
		List colunaEsquerda = Arrays.asList(1, 4, 7);
		List colunaMeio = Arrays.asList(2, 5, 8);
		List colunaDireita = Arrays.asList(3, 6, 9);
		
		List diagonalUm = Arrays.asList(1, 5, 9);
		List diagonalDois = Arrays.asList(7, 5, 3);
		
		List<List> condicoesVencedor = new ArrayList<List>();
		condicoesVencedor.add(linhaTopo);
		condicoesVencedor.add(linhaMeio);
		condicoesVencedor.add(linhaFundo);
		condicoesVencedor.add(colunaEsquerda);
		condicoesVencedor.add(colunaMeio);
		condicoesVencedor.add(colunaDireita);
		condicoesVencedor.add(diagonalUm);
		condicoesVencedor.add(diagonalDois);
		
		for(List l : condicoesVencedor) {
			if(posicoesJogadores.containsAll(l)) {
				return "Parabéns, você venceu.";
			} else if(posicoesMaquina.contains(l)) {
				return "A máquina venceu a humanidade";
			} else if(posicoesJogadores.size() + posicoesMaquina.size() == 9) {
				return "Deu empate";
			}
		}
		
		return "";
		
	}
	
}