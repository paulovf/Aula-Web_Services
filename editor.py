#coding: utf-8
from os import system

def ler():
	system('clear')
	caminho = raw_input('Forneça o caminho do arquivo:\n')
	arquivo = file(caminho, 'r')
	texto = arquivo.readlines()
	print texto
	raw_input('pressione uma tecla para continuar...')
	arquivo.close()

def escrever():
	system('clear')
	print 'Para sair do modo editor basta digitar sair()'
	texto = ''
	while 1:
		aux = raw_input()
		if aux == 'sair()':
			break
		else:
			texto += aux + '\n'
	system('clear')
	caminho = raw_input('Forneça o caminho para salvar o arquivo (com extensão): ')
	arquivo = open(caminho, 'w')
	arquivo.write(texto)
	arquivo.close()
	print 'Arquivo criado com sucesso!'

op = 3
while op != 0:
	system('clear')
	print '\t\t\tEditor de Texto\n\n'
	print '\t1 - Escrever arquivo'
	print '\t2 - Ler arquivo'
	print '\t0 - sair\n'
	print 'Forneça sua opção:'
	op = input()

	if op == 1: # escrever arquivo
		escrever()

	if op == 2: # ler arquivo
		ler()

	if op > 1 or op > 2:
		print 'Opção incorreta!'
	


