#include <iostream>
using namespace std;

bool czy_zdanie(char c, const char* zdanie){

	int n = 0;
	bool wynik = false;

	while (zdanie[n] != '\0'){
		if (zdanie[n] == c || zdanie[n] + 32 == c){
		wynik = true;
		break;}
		n++;}
	return wynik;}

void wypisz_wyniki(const char* n1, const char* n2){

	for (char a = 'a'; a <= 'z'; a++){
		if (czy_zdanie(a, n1)){
			if (czy_zdanie(a, n2)){
			cout << a << " ";}
		}
	}
	cout << "\n";
	for (char a = 97; a <= 122; a++){
		if (czy_zdanie(a, n1)){
			if (!czy_zdanie(a, n2)){
				cout << a << " ";}
		}
	}cout << "\n";}

int main() {
	char nap1[] = "Jakis Dlugi Napis";
	char nap2[] = "Zupelnie inny napis";
	wypisz_wyniki(nap1, nap2);
	system("pause");
}