package br.java.webservices.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.google.gson.Gson;

public class LerJson 
{
	//public static Bancada bancada;
	public static Disciplina disciplina;
	public static File arquivo;
	public static FileInputStream input;
	
	public LerJson() 
	{
		//bancada = null;
		disciplina = null;
		arquivo = null;
		input = null;
	}

	public Disciplina lerDadosDisciplina(FileInputStream input)
	{
		InputStreamReader in = new InputStreamReader(input);
		BufferedReader buffer = new BufferedReader(in);
		disciplina.setBancada(lerDadosBancada(input, disciplina));
		disciplina = new Gson().fromJson(buffer, Disciplina.class);
		return disciplina;
	}
	
	public Bancada[] lerDadosBancada(FileInputStream input, Disciplina disciplina)
	{
		Bancada[] b = null;
		try{
			InputStreamReader in = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(in);
			b = new Bancada[4];
			b[0] = new Gson().fromJson(buffer, Bancada.class);
			b[1] = new Gson().fromJson(buffer, Bancada.class);
			b[2] = new Gson().fromJson(buffer, Bancada.class);
			b[3] = new Gson().fromJson(buffer, Bancada.class);
			System.out.println(b[0].toString());	
			System.out.println(b[3].toString());
			return b;
		}catch(Exception e){
			System.err.println("Erro ao ler o arquivo Json!");
			return null;
		}
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
			//bancada = lerJson.lerDadosBancada(input);
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
		protected Bancada bancada[]; // vetor de objetos da classe Bancada
		
		public Disciplina() 
		{
			nome = null;
			turma = 0;
			professor = null;
			bancada = null;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public int getTurma() {
			return turma;
		}

		public void setTurma(int turma) {
			this.turma = turma;
		}

		public String getProfessor() {
			return professor;
		}

		public void setProfessor(String professor) {
			this.professor = professor;
		}

		public Bancada[] getBancada() {
			return bancada;
		}

		public void setBancada(Bancada[] bancada) {
			this.bancada = bancada;
		}

		@Override
		public String toString() 
		{
			return "nome da disciplina = " + nome + "\nturma = " + turma
					+ "\nprofessor = " + professor + "\nbancada = \n"
					+ Arrays.toString(bancada) + "\n";
		}
				
	}//Disciplina
	
}//LerJson
