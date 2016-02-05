#include <iostream>
#include <cmath>
using namespace std;

double avg(double currAvg, double* elements, size_t index) {
	if (index == 1) {
		return elements[index - 1];
	}
	else {
		double prevAvg = avg(currAvg, elements, index - 1);
		return (prevAvg * (index - 1) + elements[index - 1]) / index;
	}
}

double avg(double* elements, size_t size) {
	return avg(0, elements, size);
}

double* findClosestValue(double* elements, size_t size, double average) {
	double* elem = NULL;
	for (size_t i = 0; i < size; i++) {
		if (
			(elem == NULL) 
			|| (abs(average - elements[i]) < abs(average - *elem))) { 
			elem = &elements[i];
		}
	}
	return elem;
}

double* aver(double* arr, size_t size, double& average) {
	average = avg(arr, size);
	return findClosestValue(arr, size, average);
}

int main() {
	double arr[] = { 1, 2, 3, 4, 5, 7 };
	size_t size = sizeof(arr) / sizeof(arr[0]);
	double average = 0;
	double* p = aver(arr, size, average);
	cout << *p << " " << average << endl;
}