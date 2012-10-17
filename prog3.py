print 'Forneca um nome ou 0 para sair: '
x = '1'
lista = []
while x != '0':
     x = raw_input()
     if x != '0': 
          lista.append(x)

print 'Os nomes digitados pelo usuario sao: '
cont = len(lista)
i = 0
while i < cont:
     print "--> " + lista[i]
     i += 1

