#include <iostream>
#include <cstdlib>

using namespace std;

enum Banks { PKO, BGZ, BRE, BPH };

struct Account {
	Banks bank;
	int balance;
};

struct Person {
	char name[20];
	Account account;
};

struct Couple {
	Person he;
	Person she;
};

inline int Sum(Couple* cpls){
	return cpls->he.account.balance + cpls->she.account.balance;
}

inline bool CheckBank(Couple* cpls, Banks bank){
	return (cpls->he.account.bank == bank ||
		cpls->she.account.bank == bank);
}

Couple* bestClient(Couple* cpls, int size, Banks bank){
	Couple* maxCpls = 0;
	for (int i = 0; i < size; i++){
		Couple* currentCpls = &cpls[i];
		if (!CheckBank(currentCpls, bank)){
			continue;
		}
		if (!maxCpls){
			maxCpls = currentCpls;
		}
		else {
			if (Sum(maxCpls) <= Sum(currentCpls)){
				maxCpls = currentCpls;
			}
		}
	}
	return maxCpls;
}

int main(){
	Couple cpls[] = { { { "John", { PKO, 1100 } }, { "Mary", { BGZ, 1500 } } },
	{ { "Peter", { BGZ, 1400 } }, { "Suzy", { BRE, 1300 } } },
	{ { "Kevin", { PKO, 1600 } }, { "Katy", { BPH, 1500 } } },
	{ { "Keny", { BPH, 1800 } }, { "Lucy", { BRE, 1700 } } },
	};

	Couple* p = bestClient(cpls, 4, BGZ);

	if (p != NULL)
		cout << p->he.name << " i " << p->she.name << ": " << Sum(p) << endl;
	cin.get();
}