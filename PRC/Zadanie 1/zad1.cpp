#define POL

#if   defined(POL) && defined(ENG)
#error Please define only one country
#elif !(defined(POL) || defined(ENG))
#error Please define a country
#endif

#ifdef POL
#define pytanie "Podaj liczbe dodatnia czalkowita. 0 - Konczy program!"

#elif defined(ENG)
#define pytanie "Give me positive absoulute number. 0 - End program!"
#endif

#include <iostream>
#include <cstdio>
using namespace std;


void rekur() {
	int d{};
	cout << "Enter positive int (0 to end) ";
	cin >> d;
	if (d > 0) {
		rekur();
		cout << d << " ";}
	}
}

int main() {

	rekur();
	return 0;

}