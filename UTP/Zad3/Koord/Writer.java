public class Writer extends Thread {
       
           private Author autor;
           
            public Writer(Author c) {
               this.autor = c;
            }
 
            public void run() {
                String value = null;
                for (int i = 0; i <autor.getSize(); i++) {
                    value = autor.get();
                    System.out.println(value);
                }
            }
        }
 
