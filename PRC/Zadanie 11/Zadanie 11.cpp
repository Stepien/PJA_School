#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
using namespace std;

class Account{
	char *owner;
	int balance;
public:
	Account(const char* owner, int balance = 0);
	Account(const Account& other);
	Account& operator=(const Account& other);
	void withdraw(int ile);
	void deposit(int ile);
	void transferTo(Account& to, int ile);
	int getBalance() const;
	Account& operator+(int ile);
	Account& operator-(int ile);
	~Account();
	friend ostream& operator<<(ostream& str, const Account& acc);
};
Account::Account(const char* owner, int balance){
	int z = strlen(owner);
	this->owner = new char[z + 1];
	strcpy(this->owner, owner);
	this->balance = balance;
}
Account::Account(const Account& other){
	int c = strlen(other.owner);
	this->owner = new char[c + 1];
	strcpy(this->owner, other.owner);
	this->balance = other.balance;
}
Account& Account::operator=(const Account& other){
	delete[] owner;
	int v = strlen(other.owner);
	this->owner = new char[v + 1];
	strcpy(this->owner, other.owner);
	this->balance = other.balance;
	return *this;}

void Account::withdraw(int ile){
	balance -= ile;}

void Account::deposit(int ile){
	balance += ile;}

void Account::transferTo(Account& to, int ile){
	this->balance -= ile;
	to.balance += ile;}

int Account::getBalance() const{
	return balance;}

Account& Account::operator+(int ile){
	this->balance += ile;
	return *this;}

Account& Account::operator-(int ile){
	this->balance -= ile;
	return *this;}

Account::~Account(){
	delete[] owner;}

ostream& operator<<(ostream& str, const Account& acc){
	str << acc.owner << "[" << acc.balance << "]" << " ";
	return str;}


int main(){
	Account jasio("Jasio", 1000);
	Account* pkasia = new Account("Kasia", (jasio + 500 - 700).getBalance() + 300);
	Account basia(*pkasia);
	delete pkasia;
	Account anonym("Anonym");
	basia.transferTo(jasio, 100);
	anonym = jasio;
	cout << anonym << " " << basia << endl;
}