class Author extends Thread {
 
          private String[] nazwa;
          private String contents;
          private boolean available = false;
          public int size = 0;
 
            public Author(String[] n) {
              this.nazwa = n;
              this.size = n.length;
            }


			public void run() {
                for (int i = 0; i <size; i++) {
                    put(nazwa[i]);
                    try {
                        sleep((int)(Math.random() * 100));
                    } catch (InterruptedException e) { }
                }
            }
           
            public synchronized String get() {
                while (available == false) {
                    try {
                        wait();
                    } catch (InterruptedException e) { }
                }
                available = false;
                notifyAll();
                return contents;
            }
 
            public synchronized void put(String value) {
                while (available == true) {
                    try {
                        wait();
                    } catch (InterruptedException e) { }
                }
                contents = value;
                available = true;
                notifyAll();
            }
           
            public int getSize(){
                return size;
            }
        }