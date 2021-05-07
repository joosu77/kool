global main
extern printf
section .text
main:
	
	push	kuu
	push	20
	call 	prindikuupaevi

	add	esp, 8
	mov	eax, 0
	ret

prindikuupaevi:
	push 	ebp
	mov	ebp, esp

	mov	ecx, [esp+8]
	mov	esi, [esp+8]
	mov	ebx, [esp+12]
	push	ebx
	sub 	esp, 4
	push 	hello
algus:
	mov	ebx, ecx
	mov	ecx, esi
	inc	ecx
	sub	ecx, ebx
	mov 	[esp+4], ecx
	call 	printf
	mov	ecx, ebx
	loop algus		; vähendab ecx ja hyppab algusesse kuni ecx on 0

	mov	eax, 0		; return val
	mov	esp, ebp	; taasta esp
	pop	ebp		; taasta ebp
	ret			; lopeta funk



section .data
	kuu	db 'Mai', 0
	hello 	db 'Tere %d. %s', 10, 0
