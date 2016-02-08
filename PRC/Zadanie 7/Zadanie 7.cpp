#include <iostream>
#include <functional>
using std::cout; using std::endl;
using std::cerr; using std::function;
struct Node {
	int data;
	Node* next;
}
void printList(const Node* head) {
	for (const Node *wsk = head; wsk != nullptr; wsk = wsk->next){
		cout << wsk->data << " ";
	}
}
void add(Node*& head, int data) {
	Node *toAdd = new Node;
	toAdd->data = data;

	if (!head){
		toAdd->next = nullptr;
		head = toAdd;
		return;
	}

	if (head->data > data){
		toAdd->next = head;
		head = toAdd;
		return;
	}

	Node *wsk;
	for (wsk = head; wsk->next != nullptr; wsk = wsk->next){
		if (wsk->next->data > data){
			Node *tmp = wsk->next;
			wsk->next = toAdd;
			toAdd->next = tmp;
			return;
		}
	}

	toAdd->next = nullptr;
	wsk->next = toAdd;
}
bool any(const Node* head, function<bool(int)> pred) {
	for (const Node *wsk = head; wsk != nullptr; wsk = wsk->next){
		if (pred(wsk->data)) return true;
	}

	return false;
}
bool all(const Node* head, function<bool(int)> pred) {
	for (const Node *wsk = head; wsk != nullptr; wsk = wsk->next){
		if (!pred(wsk->data)) return false;
	}

	return true;
}
void deleteList(Node*& head) {
	for (Node *wsk = head; wsk != nullptr;){
		Node *tmp = wsk->next;
		cout << "Del: " << wsk->data << " ";
		delete wsk;
		wsk = tmp;
	}
	head = nullptr;
}
int main() {
	Node* head = 0;
	add(head, 3);
	add(head, 6);
	add(head, 2);
	add(head, 8);
	add(head, 5);
	printList(head);
	cout << std::boolalpha;
	cout << "\nless than 10 all "
		<< all(head, [](int i) -> bool {return i< 10; })
		<< endl;
	cout << "is even all "
		<< all(head, [](int i) -> bool {return i % 2 == 0; })
		<< endl;
	cout << "is even any "
		<< any(head, [](int i) -> bool {return i % 2 == 0; })
		<< endl;
	deleteList(head);
}