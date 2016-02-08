#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>

using namespace std;

class Person {
	char* name;
	friend class Couple;
	friend ostream& operator<<(ostream& str, const Person& os);
public:
	explicit Person(const char* n);
	Person(const Person& os);
	Person& operator=(const Person& os);
	~Person();
};

Person::Person(const char* n) {
	this->name = new char[ strlen(n)+1 ]();
	strcpy(this->name, n);
}

Person::Person(const Person& os) {
	char* n = os.name;
	this->name = new char[ strlen(n)+1 ]();
	strcpy(this->name, n);
}

Person& Person::operator=(const Person& os) {
	delete [] this->name;
	char* n = os.name;
	this->name = new char[ strlen(n)+1 ]();
	strcpy(this->name, n);
	return *this;
}

Person::~Person() {
	cout << "DEL " << this->name << endl;
	delete [] this->name;
}

ostream& operator<<(ostream& str, const Person& os) {
	str << os.name;
	return str;
}

class Couple {
	Person *wife, *husband;
	friend ostream& operator<<(ostream& str, const Couple& p);
public:
	Couple(const Person& she, const Person& he);
	Couple(const Couple& other);
	Couple& operator=(const Couple& other);
	~Couple();
};

Couple::Couple(const Person& she, const Person& he) {
	this->wife = new Person(she);
	this->husband = new Person(he);
}

Couple::Couple(const Couple& other) {
	this->wife = new Person(*other.wife);
	this->husband = new Person(*other.husband);
}

Couple& Couple::operator=(const Couple& other) {
	delete this->wife;
	delete this->husband;
	this->wife = new Person(*other.wife);
	this->husband = new Person(*other.husband);
	return *this;
}

Couple::~Couple() {
	delete this->wife;
	delete this->husband;
}

ostream& operator<<(ostream& str, const Couple& p) {
	str << "Couple: he " << *p.husband << ", she " << *p.wife;
	return str;
}

int main(void) {
	Person *pjohn = new Person("John"),
		*pjane = new Person("Jane");
	Person mary("Mary"), mark("Mark");
	Couple *pcouple1 = new Couple(mary, *pjohn);
	Couple couple2(*pjane,mark);
	delete pjohn;
	delete pjane;
	cout << *pcouple1 << endl;
	cout << couple2 << endl;
	couple2 = *pcouple1;
	delete pcouple1;
	cout << couple2 << endl;
}