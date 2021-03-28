import java.util.Random;
import java.util.Scanner;

public class PartitaMulti {
	static private int tentativi1=10;
	static private int tentativi2=10;
	private boolean esito1=false;
	private boolean esito2=false;
	private Integer[] sequenza1=new Integer[4];
	private Integer[] sequenza2=new Integer[4];
	
	public PartitaMulti() {
		inizializzaSequenze();
	}

	private void inizializzaSequenze() {
		String[] temp;
		System.out.println("Giocatore 1 inserisci la tua sequenza, cifre una per volta intervallate da un invio: ");
		temp=inserisciSequenza();
		while(!check(temp)) {
			System.out.println("Sequenza non valida, riprova");
			temp=inserisciSequenza();
		}
		for(int i=0;i<4;i++)
			sequenza1[i]=Integer.parseInt(temp[i]);
		for(int i=0;i<20;i++)
			System.out.println("\n");
		System.out.println("Giocatore 2 inserisci la tua sequenza, cifre una per volta intervallate da un invio: ");
		temp=inserisciSequenza();
		while(!check(temp)) {
			System.out.println("Sequenza non valida, riprova");
			temp=inserisciSequenza();
		}
		for(int i=0;i<4;i++)
			sequenza2[i]=Integer.parseInt(temp[i]);
		for(int i=0;i<25;i++)
			System.out.println("\n");
	}

	private boolean check(String[] temp) {
		Integer app;
		for(int i=0;i<4;i++) {
			app=Integer.parseInt(temp[i]);
			if(temp[i].equals("") || app>9)
				return false;
		}
		return true;
	}

	private String[] inserisciSequenza() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String[] seq = new String[4];
		for(int i=0;i<4;i++)
			seq[i]=scannerDiLinee.nextLine();
		return seq;
	}
	
	public void gioca() {
		Integer[] sequenzaProva1= new Integer[4];
		Integer[] sequenzaProva2= new Integer[4];
		while((tentativi1!=0 || tentativi2!=0) && esito1==false && esito2==false) {
			turno(tentativi1, sequenzaProva1, sequenza2, 1, esito1);
			turno(tentativi2, sequenzaProva2, sequenza1, 2, esito2);
		}
	}

	private void turno(int tentativi, Integer[] sequenzaProva, Integer[] sequenza, int i, boolean esito) {
		tentativi--;
		Scanner scannerDiLinee = new Scanner(System.in);
		String[] temp;
		System.out.print("Giocatore "+i);
		System.out.print(" è il tuo turno! Inserisci una sequenza per provare ad indovinare\n");
		temp=inserisciSequenza();
		while(!check(temp)) {
			System.out.println("Sequenza non valida, riprova: ");
			temp=inserisciSequenza();
		}
		for(int j=0;j<4;j++)
			sequenzaProva[j]=Integer.parseInt(temp[j]);
		this.prova(sequenzaProva);
		int strike=contaStrike(sequenza, sequenzaProva);
		int ball=contaBall(sequenza, sequenzaProva);
		if(strike==4) {
			System.out.println("Hai vinto!");
			esito=true;
		}
		else if(tentativi==0 && strike!=4) {
			System.out.println("Hai perso! ");
			System.out.println("La sequenza era: ");
			this.prova(sequenza);
		}
		else {
			System.out.println("Ball: "+ball);
			System.out.println("Strike: "+strike);
			System.out.println("Tentativi rimasti: "+tentativi);
		}
		
	}
	
	private int contaBall(Integer[] sequenza, Integer[] sequenzaProva) {
		int risultato=0;
		for(int i=0; i<4;i++) {
			for(int j=0;j<4;j++) {
				if(sequenzaProva[i]==sequenza[j]&& i!=j)
					risultato++;
			}
		}
		return risultato;
	}

	private int contaStrike(Integer[] sequenza, Integer[] sequenzaProva) {
		int risultato=0;
		for(int i=0;i<4;i++) {
			if(sequenzaProva[i]==sequenza[i])
				risultato++;
		}
		return risultato;
	}
	
	public void prova(Integer[] sequenza) {
		for(int i=0;i<4;i++) {
			System.out.println(sequenza[i]);
		}
	}
	
	
	
	
	/*private void turno1() {
		tentativi1--;
		Scanner scannerDiLinee = new Scanner(System.in);
		String[] temp;
		Integer[] sequenzaProva1= new Integer[4];
		System.out.println("Giocatore 1 è il tuo turno! Inserisci una sequenza per provare ad indovinare ");
		temp=inserisciSequenza();
		while(!check(temp)) 
			temp=inserisciSequenza();
		for(int i=0;i<4;i++)
			sequenzaProva1[i]=Integer.parseInt(temp[i]);
		int strike=contaStrike(sequenza2, sequenzaProva1);
		int ball=contaBall(sequenza2, sequenzaProva1);
		if(strike==4) {
			System.out.println("\nHai vinto!");
			esito1=true;
		}
		else if(tentativi1==0 && strike!=4) {
			System.out.println("\nHai perso! ");
			System.out.println("La sequenza era: ");
			this.prova(sequenza2);
		}
		else {
			System.out.println("Ball: "+ball);
			System.out.println("Strike: "+strike);
			System.out.println("Tentativi rimasti: "+tentativi1);
		}
		
	}
	
	private void turno2() {
		tentativi2--;
		Scanner scannerDiLinee = new Scanner(System.in);
		String[] temp;
		Integer[] sequenzaProva2= new Integer[4];
		System.out.println("Giocatore 2 è il tuo turno! Inserisci una sequenza per provare ad indovinare ");
		temp=inserisciSequenza();
		while(!check(temp)) 
			temp=inserisciSequenza();
		for(int i=0;i<4;i++)
			sequenzaProva2[i]=Integer.parseInt(temp[i]);
		int strike=contaStrike(sequenza1, sequenzaProva2);
		int ball=contaBall(sequenza1, sequenzaProva2);
		if(strike==4) {
			System.out.println("Hai vinto!");
			esito1=true;
		}
		else if(tentativi2==0 && strike!=4) {
			System.out.println("Hai perso! ");
			System.out.println("La sequenza era: ");
			this.prova(sequenza1);
		}
		else {
			System.out.println("Ball: "+ball);
			System.out.println("Strike: "+strike);
			System.out.println("Tentativi rimasti: \n\n"+tentativi1);
		}
		
	}*/

}
	