package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import hotel.Hospede;

public class BancoDeDados {
	public void salvaTexto(String texto, String path) throws IOException {
		FileWriter fw = new FileWriter(path);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texto);
		bw.close();
	}
	
	public String carregaTexto(String path) throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String texto = "";
		while(br.ready()) {
			texto += br.readLine();
		}
		return texto;
	}
	
	public void salvaHospede(Map<String, Hospede> hospede, String path) {
		
	}
}
