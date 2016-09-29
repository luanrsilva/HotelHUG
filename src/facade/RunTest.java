package facade;

import easyaccept.EasyAccept;

/**
 * Classe reponsavel por rodar os testes da plataforma easyaccept.
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class RunTest {
	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade",
	    					 "testes_aceitacao/testes_uc1.txt",
	    					 "testes_aceitacao/testes_uc1_exception.txt",
	    					 "testes_aceitacao/testes_uc2.txt",
	    					 "testes_aceitacao/testes_uc2_exception.txt",
	    					 "testes_aceitacao/testes_uc3.txt",
	    					 "testes_aceitacao/testes_uc3_exception.txt",
	    					 "testes_aceitacao/testes_uc4.txt",
	    					 "testes_aceitacao/testes_uc4_exception.txt",
	    					 "testes_aceitacao/testes_uc5.txt",
	    					 "testes_aceitacao/testes_uc6.txt",
	    					 /*"testes_aceitacao/testes_uc7.txt"*/}; //separe cada script de teste por virgula.
	    EasyAccept.main(args);	    
	}

}
