#include <iostream>
#define A_PLEC 15
#define A_STAN_CYW 13
#define A_GRUPA_WIEK 11
#define A_EDU 9
#define A_ZAM 7
#define A_REGION 3
#define A_ODP 0

using namespace std;

unsigned short koduj(int plec, int stan_cyw, int grupa_wiek, int edu, int zam, int region, int odp) {

	return (plec << A_PLEC)
		| (stan_cyw << A_STAN_CYW)
		| (grupa_wiek << A_GRUPA_WIEK)
		| (edu << A_EDU)
		| (zam << A_ZAM)
		| (region << A_REGION)
		| (odp << A_ODP);

}

void info(unsigned short kod) {

	cout<<"plec:" << ((kod & (1 << A_PLEC)) >> A_PLEC) << endl
		<< "stan cywilny:" << ((kod & (3 << A_STAN_CYW)) >> A_STAN_CYW) << endl
		<< "grupa wiekowa:" << ((kod & (3 << A_GRUPA_WIEK)) >> A_GRUPA_WIEK) << endl
		<< "wyksztalcenie:" << ((kod & (3 << A_EDU)) >> A_EDU) << endl
		<< "miejsce zam:" << ((kod & (3 << A_ZAM)) >> A_ZAM) << endl
		<< "region:" << ((kod & (15 << A_REGION)) >> A_REGION) << endl
		<< "odpowiedz:" << ((kod & (7 << A_ODP)) >> A_ODP) << endl;

}

int main() {

	info(koduj(0, 0, 0, 0, 0, 0, 0));

}