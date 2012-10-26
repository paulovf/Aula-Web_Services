package br.java.redes.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;

public class Github
{
	private static final long serialVersionUID = -6220830040100209716L;
	public static String usuario;
	public static final String urlSite = "https://api.github.com/users/";
	
	public Github()
	{
		usuario = null;
	}
	
	public DadosUsuario metodoGET(String usuario)
	{
		HttpURLConnection conn;
		BufferedReader buffer;
		try{
			URL url = new URL(urlSite + usuario);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				DadosUsuario dadosUsuario = new Gson().fromJson(buffer, DadosUsuario.class);
				buffer.close();
				return dadosUsuario;
			}
			else
				return null;
		}catch(Exception e){
			System.err.println("Erro ao obter dados do usuário");
			return null;
		}
	}
	
	public static void main(String[] args)
	{
		Github git = new Github();
		DadosUsuario dadosUsuario = null;
		Scanner input = new Scanner(System.in);
		while(usuario == null)
		{
			System.out.println("Forneça o nome do usuário:\n");
			usuario = input.next();
			if(usuario == null)
				System.err.println("Você precisa fornecer um nome de usuário.");
		}
		try {
			dadosUsuario = git.metodoGET(getUsuario());
			if(dadosUsuario.name == null)
			{
				System.out.println("\nUsuário inexistente");
				System.exit(0);
			}
			System.out.println(dadosUsuario.toString());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
			if (usuario == null)
				System.out.println("Usuário Inexistente");
			else
				System.out.println(usuario.toString());
		}
		System.exit(0);
	}//main
	
	public static String getUsuario() 
	{
		return usuario;
	}
	
}//Informacoes

class DadosUsuario 
{
	protected String name;
	protected String email;
	protected int followers;

	public DadosUsuario()
	{
		name = null;
		email = null;
		followers = 0;
	}

	@Override
	public String toString() 
	{
		return "Dados do Usuario:\nNome = " + name +
		"\nE-mail = " + email +	"\nSeguidores = " + followers;
	}
}//DadosUsuario
