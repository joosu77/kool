global eucleides

section .text

; int eucleides(a, b)
eucleides:
	%define a	[esp + 4]
	%define b	[esp + 5]
	
	cmp	a b
	jne	edasi
	
edasi:
	
	push 	b
	div	a b
	push	AH
	call eucleides
