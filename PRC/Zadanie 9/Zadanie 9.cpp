#include <iostream>
#include <cstring>
#include <cctype>
using namespace std;
class String {
        char* nap;
        size_t size;
public:
        String(const char* n) {
                size = strlen(n);
                nap = new char[size + 1];
                strcpy(nap, n);
        }
 
        String(const String& s) : String(s.nap) { }
 
        String operator+(const String& s) const {
                return operator+(s.nap);
        }
 
        String operator+(const char* n) const {
                char* tmp = new char[length() + strlen(n) + 1];
                strcpy(tmp, nap);
                strcat(tmp, n);
                String res = String(tmp);
                delete[] tmp;
                return res;
        }
 
        String& operator=(const String& s) {
                delete[] nap;
                size = s.size;
                nap = new char[size + 1];
                strcpy(nap, s.nap);
                return *this;
        }
 
        bool operator==(const String& s) const {
                return strcmp(nap, s.nap) == 0;
        }
 
        bool operator!=(const String& s) const {
                return !operator==(s);
        }
 
        String& toLower() {
                for (int i = 0; i < size; ++i) {
                        nap[i] = tolower(nap[i]);
                }
                return *this;
        }
 
        String& toUpper() {
                for (int i = 0; i < size; ++i) {
                        nap[i] = toupper(nap[i]);
                }
                return *this;
        }
 
        size_t length() const {
                return size;
        }
 
        ~String() {
                delete[] nap;
        }
 
        friend String operator+(const char* n, const String& s) {
                char* tmp = new char[s.length() + strlen(n) + 1];
                strcpy(tmp, n);
                strcat(tmp, s.nap);
                String res = String(tmp);
                delete[] tmp;
                return res;
        }
        friend ostream& operator<<(ostream& out, const String& s) {
                out << s.nap;
                return out;
        }
};
int main(void) {
        String s = "To " + String("be ") + "or not to be";
        cout << s << endl;
        if (s == "To be or not to be") s = String(s.toUpper());
        else s = String(s.toLower()); cout << "Length = " << s.length() << endl; cout << s << endl;
}