package controller;

import java.util.concurrent.Semaphore;

public class ThreadPrato extends Thread {

	private int idPrato;
	private Semaphore semaforo;

	public ThreadPrato(int idPrato, Semaphore semaforo) {
		this.idPrato = idPrato;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		pratoCozinhando();
		// ----------Início Seção Crítica----------
		try {
			semaforo.acquire();
			pratoEntrega();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			// ----------Fim Seção Crítica----------
		}
	}

	private void pratoCozinhando() {
		int tempo = 100;
		int percent = 0;
		int percentTotal = 0;

		if (idPrato % 2 == 0) {
			System.out.println("O prato #" + idPrato + ": Lasanha a Bolonhesa está sendo preparado!");

			int tempTotal = (int) ((Math.random() * 601) + 600);

			while (percentTotal < 100) {
				percent = tempTotal / 100;
				percentTotal += percent;
				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (percentTotal <= 100) {
					System.out.println("O prato #" + idPrato + ": Lasanha a Bolonhesa cozinhou " + percentTotal + "%");
				} else {
					System.out.println("O prato #" + idPrato + ": Lasanha a Bolonhesa cozinhou 100%");
				}
			}
		} else {
			System.out.println("O prato #" + idPrato + ": Sopa de Cebola está sendo preparado!");

			int tempTotal = (int) ((Math.random() * 301) + 500);

			while (percentTotal < 100) {
				percent = tempTotal / 100;
				percentTotal += percent;
				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (percentTotal <= 100) {
					System.out.println("O prato #" + idPrato + ": Sopa de Cebola cozinhou " + percentTotal + "%");
				} else {
					System.out.println("O prato #" + idPrato + ": Sopa de Cebola cozinhou 100%");
				}

			}
		}
	}

	private void pratoEntrega() {
		if (idPrato % 2 == 0) {
			System.out.println("O prato #" + idPrato + ": Lasanha a Bolonhesa ficou pronto!");
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("O prato #" + idPrato + ": Lasanha a Bolonhesa foi entregue");
		} else {
			System.out.println("O prato #" + idPrato + ": Sopa de Cebola ficou pronto!");
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("O prato #" + idPrato + ": Sopa de Cebola foi entregue");
		}
	}

}