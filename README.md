# Echantillonnage de motifs ensemblistes selon une utilité fondée sur la taille


Ce répertoire contient 4 dossiers. Le dossier *src* contient le code source de la méthode d'échantillonnage en sortie de motifs ensemblistes en deux étapes sous contrainte de norme. Le dossier *ISampling_Datasets* contient quelques jeux de données transactionnels que nous avons utilisés lors de nos expérimentations. Tout jeu de données dans le dossier *Input* sera considéré comme une entrée lors de l'exécution de la méthode. Les motifs échantillonnés seront stocké dans le dossier *Output*.


*Exemple d'exécution

*******************************************************************************
* version 1.0
* Date : 20/12/2018
* Goal : Paper for the National conference CNRIA 2019
* Sample size : 1000
* Utility : freq
* Minimal length constraint : 1 by default
* Maximal length constraint : in {2, 3, 4, 5, 6, 7}
*                          GOOD LUCK!!
*******************************************************************************

auto

	M = 2
	
		Preprocessing time : 0.0 ms
		
		Sampling time : 2.0 ms
		
		Distinct sampled patterns : 858
		
	M = 3
	
		Preprocessing time : 1.0 ms
		
		Sampling time : 2.0 ms
		
		Distinct sampled patterns : 992
		
	M = 4
		Preprocessing time : 1.0 ms
		
		Sampling time : 2.0 ms
		
		Distinct sampled patterns : 1000
		
	M = 5
	
		Preprocessing time : 2.0 ms
		
		Sampling time : 3.0 ms
		
		Distinct sampled patterns : 1000
		
	M = 6
	
		Preprocessing time : 3.0 ms
		
		Sampling time : 3.0 ms
		
		Distinct sampled patterns : 1000
		
	M = 7
	
		Preprocessing time : 1.0 ms
		
		Sampling time : 3.0 ms
		
		Distinct sampled patterns : 1000
		
************************************ END *************************************
