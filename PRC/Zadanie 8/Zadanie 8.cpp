#include <iostream>
template<typename T>
class Queue
{
	struct Node
	{
		T data;
		Node* next;
	};
	Node* head;
	Node* tail;
	int qsize;

public:
	Queue()
	{
		head = NULL;
		tail = NULL;
		qsize = 0;
	}

	bool empty()
	{
		if (qsize == 0){ return true; }
		else         { return false; }
	}

	void put(const T& data)
	{
		Node *newNode = new Node;
		if (qsize)
		{
			tail->next = newNode;
			newNode->data = data;
			newNode->next = NULL;
			tail = newNode;
		}
		else
		{
			head = tail = newNode;
			newNode->data = data;
			newNode->next = NULL;
		}
		qsize++;
	}

	T get(){
		T val;
		Node *temp;

		if (empty()){
			std::cout << "queue is empty" << std::endl;
		}
		else{
			val = head->data;
			temp = head;
			head = head->next;
			delete temp;
			qsize--;
			return val;
		}
	}

	void destroyQueue(){
		Node * tmp = head;
		Node * deltmp = NULL;
		while (tmp != NULL) {
			deltmp = tmp;
			tmp = deltmp->next;
			std::cout << "Del: " << deltmp->data << std::endl;
			delete deltmp;
		}
	}

	~Queue(){
		destroyQueue();
	}
};



int main()
{
	int data1, data2;
	Queue<int>* q = new Queue<int>();
	q->put(1);
	data1 = q->get();
	std::cout << " data1=" << data1 << std::endl;
	q->put(1);
	q->put(2);
	data1 = q->get();
	data2 = q->get();
	std::cout << " data1=" << data1
		<< " data2=" << data2 << std::endl;
	q->put(1);
	q->put(2);
	q->put(3);
	q->put(4);
	q->put(5);
	q->put(6);
	while (!q->empty()) std::cout << " " << q->get();
	std::cout << std::endl;
	q->put(1);
	q->put(2);
	q->put(3);
	delete q;
}