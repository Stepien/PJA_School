#include <cstdlib>
#include <functional>
#include <iostream>
#include <ctime>
using namespace std;

template <typename T, typename FUN>
size_t part(T *arr, size_t size, FUN f){
	int z = 0;
	int w = size - 1;
	while (z < w){
		while (f(arr[z])){
			z++;
		}
		while (!f(arr[w])){
			w--;
		}
		if (z < w){
			swap(arr[z++], arr[w--]);
		}
	}
	return z;
}

template <typename T>
void printTab(const T *t, size_t size)
{
	cout << "[";
	for (size_t i = 0; i < size; i++){
		if (i == 0)cout << " ";
		cout << t[i] << " ";
	}
	cout << "]\n";
}

bool isEven(int e) { return e % 2 == 0;}

int main()
{
	size_t ind = 0;
	int a1[] = { 1, 2, 3, 4, 5, 6 };
	ind = part(a1, 6, isEven);
	cout << "ind = " << ind << " ";
	printTab(a1, 6);
	int a2[] = { 1, 2, 3, 4, 5, 6 };
	// lambda jako argument: predykat sprawdzajacy,
	// czy podana liczba jest nieparzysta
	ind = part(a2, 6, [](int x)
	{
		return x & 1;
	});
	cout << "ind = " << ind << " ";
	printTab(a2, 6);
	double a3[] = { -1.5, 2.5, 3.5, 6.5, 4.5, 0 };
	double mn = 2.0;
	double mx = 5.0;
	// lambda jako argument: predykat sprawdzajacy, czy
	// podana liczba miesci sie w przedziale [mn.mx]
	ind = part(a3, 6, [mn, mx](double x)
	{
		return x <= mx && x >= mn;
	});
	cout << "ind = " << ind << " ";
	printTab(a3, 6);
	const size_t DIM = 500000;
	int *a4 = new int[DIM];
	srand(unsigned(time(0)));
	for (size_t i = 0; i < DIM; ++i) a4[i] = rand() % 21 + 1;
	// lambda jako argument: predykat sprawdzajacy,
	// czy podana liczba dzieli sie przez 7
	ind = part(a4, DIM, [](int x)
	{
		return x % 7 == 0;
	});
	cout << "ind = " << ind << endl;
	delete[] a4;
}

