Compilacion:
	javac *.java
Ejecución:
	Pasándole un fichero:
		java DriverControladorTSP < juegoDePrueba.in
	Por consola:
		java DriverControladorTSP
		
Formato de los juegos de prueba:
1er algoritmo:
	Christofides
	NearestNeighbour
	BranchAndBound
2o algoritmo:
	2OPT
	3OPT
	nada

[opcion] [1er algoritmo] [2o algoritmo] [numVertices] [numAristas] [a1origen] [a1destino] [peso1] [a2origen] [a2destino] [peso2] ...

Ejemplo:
1 NearestNeighbour 2OPT 3 3 0 1 2.6 0 2 4.9 1 2 6.3
0 //salir
			