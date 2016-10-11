package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import hotel.Hospede;

public class BancoDeDados {
	public void salvaTexto(String texto, String path) throws IOException {
		FileWriter fw = new FileWriter(path);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(this.carregaTexto(path));
		bw.close();
	}

	public String carregaTexto(String path) throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String texto = "";
		while (br.ready()) {
			texto += br.readLine();
		}
		return texto;
	}

	public void salvaHospede(Map<String, Hospede> hospede) throws IOException {
		FileOutputStream fos = new FileOutputStream("arquivos_sistemas/hug.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(hospede);
		oos.close();
	}

	private Map<String, Hospede> leHospede() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("arquivos_sistemas/hug.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object o = ois.readObject();
		ois.close();
		return (Map<String, Hospede>) o;
	}

	
}
