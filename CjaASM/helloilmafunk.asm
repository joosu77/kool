global main
extern printf
section .text
main:
;	mov	ebx, 0
;	sub 	esp, 4
;	push 	hello
;algus:
;	inc 	ebx
;	mov 	[esp+4], ebx
;	call 	printf
;	cmp	ebx, 31
;	jl	algus

	mov	ecx, 31
	sub 	esp, 4
	push 	hello
algus:
	mov	ebx, ecx
	mov	ecx, 32
	sub	ecx, ebx
	mov 	[esp+4], ecx
	call 	printf
	mov	ecx, ebx
	loop algus		; vähendab ecx ja hyppab algusesse kuni ecx on 0

	add	esp, 8
	mov	eax, 0
	ret

section .data
	hello db 'Tere %d. Mai', 10, 0
