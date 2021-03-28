import java.util.Random;

import java.util.Scanner;

public class PartitaReps {
	static private int tentativi=15;
	private int sequenza[] = new int[4];
	private boolean esito=false;
	private int strike;
	private int ball;
	
	public PartitaReps() {
		generaSequenza();
	}

	private void generaSequenza() {
		Random rnd=new Random();
		for(int i=0; i<4;i++)
			sequenza[i]= rnd.nextInt(9);
	}
	
	public void gioca() {
		String prova;
		Integer eletto;
		Integer sequenzaProva[]= new Integer[4];
		Scanner scannerDiLinee = new Scanner(System.in);
		while(!esito && tentativi!=0) {
			this.tentativi--;
			this.strike=0;
			this.ball=0;
			System.out.println("Fai il tuo tentativo inserendo le cifre una alla volta intervallate da un invio: ");
			for(int i=0;i<4;i++) {
				prova=scannerDiLinee.nextLine();
				eletto= Integer.parseInt(prova);
				while(prova.equals("") || eletto>9 ) {
					System.out.println("inserimento non valido");
					System.out.println("Fai il tuo tentativo inserendo le cifre una alla volta intervallate da un invio: ");
					prova=scannerDiLinee.nextLine();
				}
				sequenzaProva[i]=eletto;
			}
			//int strike=contaStrike(sequenzaProva);
			//int ball=contaBall(sequenzaProva);
			calcolaRisultato(sequenzaProva);
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
	
	public void prova() {
		for(int i=0;i<4;i++) {
			System.out.println(sequenza[i]);
		}
	}
	
	private void calcolaRisultato(Integer[] sequenzaProva) {
		for(int i=0;i<4;i++) {
			if(sequenzaProva[i]==sequenza[i])
				strike++;
			else
				for(int j=0;j<4;j++) {
					if(sequenzaProva[i]==sequenza[j] && i!=j) { 
						ball++;
						break;
					}
				}
			
		}
	}
	
	
	
	/*private int contaBall(Integer[] sequenzaProva) {
	int risultato=0;
	for(int i=0; i<4;i++) {
		int flag=0;
		for(int j=0;j<4;j++) {
			if(sequenzaProva[i]==sequenza[j]&& i!=j && flag==0)
				risultato++;
			if(sequenzaProva[i]==sequenza[j] && i!=j || sequenzaProva[i]==sequenza[j] && i==j )
				flag=1;
		}
	}
	return risultato;
}*/

/*private int contaStrike(Integer[] sequenzaProva) {
	int risultato=0;
	for(int i=0;i<4;i++) {
		if(sequenzaProva[i]==sequenza[i])
			risultato++;
	}
	return risultato;
}*/

}
