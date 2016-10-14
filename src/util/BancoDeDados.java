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

/**
 * Classe reponsavel pela logica do uso de arquivos
 * @author ariann
 *
 */
public class BancoDeDados {
	
	/**
	 * Metodo que possui a logica para salvar caracteres em um arquivo texto
	 * @param texto
	 * @param path
	 * @throws IOException
	 */
	public void salvaTexto(String texto, String path) throws IOException {
		FileWriter fw = new FileWriter(path);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texto);
		bw.close();
	}

	/**
	 * Metodo que possui a logica para leitura de caracteres em um arquivo texto
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String carregaTexto(String path) throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String texto = "";
		while (br.ready()) {
			texto += br.readLine();
		}
		return texto;
	}
	
	/**
	 * Metodo para salvar o objeto Hotel por meio de bytes em um arquivo	
	 * @param hotelController
	 * @throws IOException
	 */
	public void salvaHotelController(HotelController hotelController) throws IOException {
		FileOutputStream fos = new FileOutputStream("arquivos_sistemas/hug.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(hotelController);
		oos.close();
	}
	
	/**
	 * Metodo par leitura de um objeto Hotel por meio de bytes de um arquivo
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public HotelController leHotelController() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("arquivos_sistemas/hug.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object o = ois.readObject();
		ois.close();
		return (HotelController) o;
	}

}
