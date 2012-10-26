package br.java.webservices.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;

public class LerJson 
{
	public static Disciplina disciplina;
	public static File arquivo;
	public static FileInputStream input;
	
	public LerJson() 
	{
		disciplina = null;
		arquivo = null;
		input = null;
	}

	public Disciplina lerDadosDisciplina(FileInputStream input)
	{
		try {
			InputStreamReader in = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(in);
			disciplina = new Gson().fromJson(buffer, Disciplina.class);
			in.close();
		}catch (IOException e){
			System.err.println("Erro ao carregar arquivo.");
		}
		return disciplina;
	}
	
	public static void main(String[] args) 
	{
		LerJson lerJson = new LerJson();
		try {
			arquivo = new File("hash.json");
			input = new FileInputStream(arquivo);
		}catch(FileNotFoundException e){
			System.err.println("Erro na abertura do arquivo");
		}
		try{
			disciplina = lerJson.lerDadosDisciplina(input);
		}catch(Exception e){
			System.err.println("Erro ao ler dados arquivo Json");
		}
		
		if(disciplina == null)
			System.out.println("Nenhum dado encontrado.");
		else
			System.out.println(disciplina.toString());
		
		System.exit(0);
	}//main
	
	class Bancada
	{
		protected String nomeAluno;
		protected int nota;
		protected int id;
		
		public Bancada()
		{
			nomeAluno = null;
			nota = -1;
			id = 0;
		}

		public String getNomeAluno() {
			return nomeAluno;
		}

		public void setNomeAluno(String nomeAluno) {
			this.nomeAluno = nomeAluno;
		}

		public int getNota() {
			return nota;
		}

		public void setNota(int nota) {
			this.nota = nota;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public String toString() 
		{
			return "Nome do Aluno = " + nomeAluno + " -- nota = " + nota
					+ " -- ID = " + id + "\n";
		}
	}//Bancada
	
	class Disciplina
	{
		protected String nome; //nome do aluno
		protected int turma; // nome da turma / disciplina
		protected String professor; // nome do professor
		protected Map<String, Bancada> bancada[]; // vetor de objetos da classe Bancada
		
		public Disciplina() 
		{
			nome = null;
			turma = 0;
			professor = null;
			bancada = null;
		}

		@Override
		public String toString() 
		{
			return "nome = " + nome + "\nturma = " + turma
					+ "\nprofessor = " + professor + "\n\nbancadas = " + bancada[0].get("bancada1").toString();
		}
				
	}//Disciplina
	
}//LerJson