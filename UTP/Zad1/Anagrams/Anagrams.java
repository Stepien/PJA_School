package zad1;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
 
public class Anagrams {
	 
    private Set<List<String>> set = new TreeSet<>(this::toCompare);
    public Anagrams(@NotNull String path) throws FileNotFoundException {
        List<String> list = getListFromFile(path);
        fillSet(list);
    }
    
    private int toCompare(List<String> list1, List<String> list2) {
        if (list1.size() < list2.size())
            return 1;
        if (list1.size() > list2.size())
            return -1;
        return 0;
    }
 
    private List<String> getListFromFile(String path) throws FileNotFoundException {
        List<String> list = new LinkedList<>();
        try (Scanner sc = new Scanner(new FileInputStream(path))) { 
            while (sc.hasNext())
                list.add(sc.next());
        }
        return list;
    }
    private void fillSet(List<String> list) {
        while (!list.isEmpty()) {
            Iterator<String> it = list.iterator();
            List<String> l = new LinkedList<>();
            l.add(it.next());
            it.remove();
            while (it.hasNext()) {
                String el = it.next();
                if (isAnagram(el, l.get(0))) {
                    l.add(el);
                    it.remove();
                }
            }
            set.add(l);
        }
    }
    private boolean isAnagram(String word1, String word2) {
        return getSortListCharacter(word1.toCharArray()).equals(getSortListCharacter(word2.toCharArray()));
    }
 
    private List<Character> getSortListCharacter(char[] chs) {
        Arrays.sort(chs);
        List<Character> list = new ArrayList<>();
        for (char ch : chs)
            list.add(ch);
        return list;
 
    }
    public Set<List<String>> getSortedByAnQty() {
        return set;
    }
 
    public String getAnagramsFor(String word) {
        for (List<String> list : set) {
            if (list.contains(word)) {
                return word+":"+list.toString().replace(word+",","").replace(word,"").replace(" ", "").replace(",", ", ");
            }
        }
        return null;
    }    
}
