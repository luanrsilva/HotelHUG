package facade;

import easyaccept.EasyAccept;

public class RunTest {
	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade",
	    					 "testes_aceitacao/testes_uc1.txt",
	    					 "testes_aceitacao/testes_uc2.txt",
	    					 "testes_aceitacao/testes_uc3.txt",
	    					 "testes_aceitacao/testes_uc4.txt",
	    					 "testes_aceitacao/testes_uc2_exception.txt"}; //separe cada script de teste por virgula.
	    EasyAccept.main(args);	    
	}

}
