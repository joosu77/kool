CC=gcc

all: helloasm

helloasm: hello.asm
	nasm -f elf hello.asm -o hello.o
	$(CC) -m32 hello.o -o helloasm
run: helloasm
	./helloasm