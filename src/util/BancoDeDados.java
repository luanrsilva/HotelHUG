package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.List;
import java.util.Map;

import controller.HotelController;
import hotel.Hospede;
import hotel.Transacao;

import restaurante.Refeicao;

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
		while (br.ready()) {
			texto += br.readLine();
		}
		return texto;
	}
	
	public void salvaHotelController(HotelController hotelController) throws IOException {
		FileOutputStream fos = new FileOutputStream("arquivos_sistemas/hug.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(hotelController);
		oos.close();
	}
	
	public HotelController leHotelController() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("arquivos_sistemas/hug.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object o = ois.readObject();
		ois.close();
		return (HotelController) o;
	}

}
