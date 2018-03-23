# holiday-spots-JavaApp

Aplicatie Java care ajuta oamenii sa aleaga unde sa mearga in vacanta.
Prin intermediul aplicatiei, un utilizator poate cauta prin date,
obtinand urmatoarele informatii despre o anumita locatie X, top 5
locatii din tara/judetul/orasul X ordonate dupa cost sau unde este
cel mai ieftin practicarea unei activitati timp de 10 zile.

Pentru test am folosit 35 de locatii, pe care le-am hardcodat. Fiecare locatie
este scrisa in fisier ("input.txt") pe cate un rand, sub urmatoarea forma:
Nume, Tara, Judet/Regiune, Oras, Pret mediu pe zi, Lista de activitati,
Perioada de inceput, Perioada de sfarsit.

Toate datele de test sunt scrise corespunzator, netratand exceptiile in cazul
in care informatia este scrisa intr-o ordine eronata.
In majoritatea cazurilor, testele includ cel putin 5 locatii caracterizate
de aceeasi tara sau oras. In cazul in care exista mai putin de 5 locatii in
zona cautata, exceptia va fi prinsa de "catch" si va fi explicata la afisare
(exemplu testat si in run-tests, in cazul alegerii Italiei).

In afara acelui IndexOutOfBounds, exceptiile luate in considerare s-au rezumat
la posibilitatea citirii din fisier, preferand sa ma concentrez pe structura
si pe functionalitatea algoritmului pe care se bazeaza aplicatia.

In ceea ce priveste capacitatea de ierarhizare a locatiilor, am construit
clase separate pentru obiectele ce caracterizeaza o locatie (Country, District,
City), intocmai pentru a permite o eventuala dezvoltare a ierarhiei.

Am presupus ca in cazul celei de-a doua cerinte, locatiile ar trebui ordonate
in functie de cat de ieftin este practicarea activitatilor pe intreaga perioada
a acelei locatii. Astfel, le-am ordonat crescator in raport cu pretul total.
