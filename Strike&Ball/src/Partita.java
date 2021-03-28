import java.util.Random;

import java.util.Scanner;
public class Partita {
	static private int tentativi=10;
	private int sequenza[] = new int[4];
	private boolean esito=false;
	public Partita() {
		this.generaSequenza();
	}
	
	public void generaSequenza() {
		Random random= new Random();
		for(int i=0;i<4;i++) {
			sequenza[i]= random.nextInt(9);
		}
		if(!check(sequenza)) {
			generaSequenza();
		}
	}
	
	private boolean check(int[] sequenza) {
		for(int i=0; i<4;i++) {
			for(int j=i+1; j<4; j++) {
				if(sequenza[i]==sequenza[j])
					return false;
			}
		}
		return true;
	}

	public void prova() {
		for(int i=0;i<4;i++) {
			System.out.println(sequenza[i]);
		}
	}
	
	public void gioca() {
		String prova;
		Integer sequenzaProva[]= new Integer[4];
		Scanner scannerDiLinee = new Scanner(System.in);
		Integer eletto;
		while(!esito && tentativi!=0) {
			this.tentativi--;
			System.out.println("Fai il tuo tentativo inserendo le cifre una alla volta intervallate da un invio: ");
			for(int i=0;i<4;i++) {
				prova=scannerDiLinee.nextLine();
				eletto= Integer.parseInt(prova);
				while(prova.equals("") || eletto>9) {
					System.out.println("inserimento non valido");
					prova=scannerDiLinee.nextLine();
					eletto= Integer.parseInt(prova);
				}
				sequenzaProva[i]=eletto;
			}
			int strike=contaStrike(this.sequenza, sequenzaProva);
			int ball=contaBall(this.sequenza, sequenzaProva);
			if(strike==4) {
				System.out.println("Hai vinto!");
				esito=true;
			}
			else if(tentativi==0 && strike!=4) {
				System.out.println("Hai perso! ");
				System.out.println("La sequenza era: ");
				this.prova();
			}
			else {
				System.out.println("Ball: "+ball);
				System.out.println("Strike: "+strike);
				System.out.println("Tentativi rimasti: "+tentativi);
			}
		}
	}
	
	private int contaBall(int[] sequenza, Integer[] sequenzaProva) {
		int risultato=0;
		for(int i=0; i<4;i++) {
			for(int j=0;j<4;j++) {
				if(sequenzaProva[i]==sequenza[j]&& i!=j)
					risultato++;
			}
		}
		return risultato;
	}

	private int contaStrike(int[] sequenza, Integer[] sequenzaProva) {
		int risultato=0;
		for(int i=0;i<4;i++) {
			if(sequenzaProva[i]==sequenza[i])
				risultato++;
		}
		return risultato;
	}

	public static void main(String[] argc) {
		String mod;
		Scanner scannerDiLinee = new Scanner(System.in);
		System.out.println("Digita 1 per la modalità facile oppure 2 per la modalità difficile 3 per il multiplayer: ");
		mod=scannerDiLinee.nextLine();
		if(mod.equals("1")) {
			Partita partita = new Partita();
			//partita.prova();
			partita.gioca();
		}
		else if(mod.equals("2")) {
			PartitaReps partita = new PartitaReps();
			//partita.prova();
			partita.gioca();
		}
		else if(mod.equals("3")) {
			PartitaMulti partita = new PartitaMulti();
			//partita.prova();
			partita.gioca();
		}
		else
			System.out.println("Inserimento non valido, riavvia la partita e attento a ciò che scrivi ;)");
	}
}
