package br.java.redes.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jsonij.json.JSONParser;
import jsonij.json.Value;
import jsonij.parser.ParserException;

public class Github 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Forneça o nome do usuário: ");
		String usuario = input.next();
		String url = "https://api.github.com/users/" + usuario;

		try {
			DadosJson dados = new DadosJson(new URL(url));
			Map<String, String> chavesValores = dados.getPropriedades();
			for (String chave : chavesValores.keySet()){
				System.out.println(String.format("%-20s: %s", chave, (String)chavesValores.get(chave)));
			}
			JFrame frame = new JFrame("AULA 01 - Web Services - Arthur Assuncao");
			JLabel labelImagem = new JLabel(new ImageIcon(new URL(dados.getPropriedade("avatar_url"))));
			frame.getContentPane().add(labelImagem);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (ParserException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e){
			System.err.println("Usuario nao encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class DadosJson{ //nem devia precisar neh
	private String json;
	private Value valoresJson;

	public DadosJson(URL url) throws IOException, ParserException {
		carregaJSON(url);
		carregaValores();
	}

	public DadosJson(String json) throws ParserException, IOException {
		carregaValores();
	}

	private void carregaValores() throws ParserException, IOException {
		JSONParser parser = new JSONParser();
		valoresJson = null;
		valoresJson = parser.parse(json);
	}

	private void carregaJSON(URL url) throws IOException {
		//URL url = new URL(link);
		HttpsURLConnection conexao = (HttpsURLConnection)url.openConnection();

		System.out.println(conexao.getContentType());

		InputStream is = conexao.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String linha = br.readLine();
		StringBuilder texto = new StringBuilder();

		while (linha != null) {
			texto.append(linha);
			linha = br.readLine();
		}
		br.close();

		this.json = texto.toString();
	}

	public String getPropriedade(String propriedade){
		String valor = null;	
		valoresJson = valoresJson.get(propriedade);
		valor = valoresJson.toString();
		return valor;
	}

	public Map<String, String> getPropriedades(){
		Map<String, String> chavesValores = new HashMap<String, String>();

		for (CharSequence chave : ((Object) valoresJson).valueKeySet()){
			chavesValores.put(chave.toString(), valoresJson.get(chave).toString());
		}
		return chavesValores;

	}
}

