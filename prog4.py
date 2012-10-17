#coding: utf-8
def operacao(num1, num2, operador):
     if operador == "+":
          return num1+num2
     if operador == "-":
          return num1-num2
     if operador == "*":
          return num1*num2
     if operador == "/":
          return num1/num2
     else:
          printf('Valor(es) incorreto(s)')
          return None

num1 = input('Forneça o primeiro número: ')
operador = raw_input('Forneça o operador: ')
num2 = input('Forneça o segundo número: ')

print 'Resultado: ', operacao(num1, num2, operador)
# lista de exercícios http://www.python.org.br/wiki/EstruturaSequencial

