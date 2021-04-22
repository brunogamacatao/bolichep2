package boliche;

import java.util.ArrayList;

public class Boliche {
	private ArrayList<Integer> jogadas;
	
	public Boliche() {
		jogadas = new ArrayList<>();
	}
	
	public void jogar(int pinosDerrubados) {
		// em cada jogada, eu vou registrando o número de pinos derrubados
		jogadas.add(pinosDerrubados);
	}
	
	public int getPontos() {
		int pontos = 0;
		
		// percorrer o arraylist das jogadas de frame em frame (ou seja, de 2 em 2)
		for (int i = 0, frame = 0; i < jogadas.size() && frame < 10; frame++) {
			int pinosDaJogada1 = jogadas.get(i);
			
			// Estou checando se esse frame é um strike, ou seja, 
			// se todos os pinos foram derrubados em 1 jogada
			if (pinosDaJogada1 == 10) {
				// a pontuação do strike precisa das duas próximas jogadas
				int pinosProximaJogada1 = jogadas.get(i + 1);
				int pinosProximaJogada2 = jogadas.get(i + 2);
				
				// a pontuação do strike será 10 + as duas próximas jogadas
				pontos += 10 + pinosProximaJogada1 + pinosProximaJogada2;
				
				// se for um strike, incrementa o i em 1 (porque foi só uma jogada)
				i += 1;
			} else {
				int pinosDaJogada2 = jogadas.get(i + 1);
				
				// se não derrubou os 10 pinos nas duas jogadas do frame ...
				if (pinosDaJogada1 + pinosDaJogada2 < 10) { // é uma jogada simples
					pontos += pinosDaJogada1 + pinosDaJogada2; // os pontos são a soma dos pinos derrubados
				} else if (pinosDaJogada1 + pinosDaJogada2 == 10) {
					// se eu derrubei os 10 pinos nas duas jogadas, é um SPARE !
					// então eu preciso saber quantos pinos foram derrubados na próxima jogada
					int pinosProximaJogada = jogadas.get(i + 2);
					// a pontuação será 10 + os pinos da próxima jogada
					pontos += 10 + pinosProximaJogada;
				}
				// se não foi um strike, incrementa o i em 2 (porque foram 2 jogadas)
				i += 2;
			}
		}
		
		return pontos;
	}
	
	public static void main(String[] args) {
		Boliche b = new Boliche();
		// frame 1 => 2 + 3 (simples) = 5 pontos
		b.jogar(2); // 0
		b.jogar(3); // 1
		// frame 2 => 6 + 4 (spare) = 10 + 5 = 15 pontos
		b.jogar(6); // 2
		b.jogar(4); // 3
		// frame 3 => 5 + 1 (simples) = 6 pontos
		b.jogar(5); // 4
		b.jogar(1); // 5
		// frame 4 => 10 (strike) = 10 + 7 + 2 = 19 pontos
		b.jogar(10); // 6
		// frame 5 => 7 + 2 (simples) = 9 
		b.jogar(7); // 7
		b.jogar(2); // 8
		// total = 5 + 15 + 6 + 19 + 9 = 54
		
		System.out.println("Foi calculado " + b.getPontos() + " - Era esperado 54");
		
		// Vou simular o jogo perfeito
		Boliche b2 = new Boliche();
		b2.jogar(10); // 1
		b2.jogar(10); // 2
		b2.jogar(10); // 3
		b2.jogar(10); // 4
		b2.jogar(10); // 5
		b2.jogar(10); // 6
		b2.jogar(10); // 7
		b2.jogar(10); // 8
		b2.jogar(10); // 9
		b2.jogar(10); // 10
		b2.jogar(10); // 11 - jogada extra porque a última foi um strike
		b2.jogar(10); // 12 - jogada extra porque a última foi um strike
		
		System.out.println("Foi calculado " + b2.getPontos() + " - Era esperado 300");
	}
}
