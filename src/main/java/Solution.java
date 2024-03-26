import java.util.*;
public class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie();
        int shortestIndex = -1;
        int shortestLength = Integer.MAX_VALUE;
        for (int i = 0; i < wordsContainer.length; i++) {
            trie.insert(wordsContainer[i], i);
            if(wordsContainer[i].length() < shortestLength){
                shortestLength = wordsContainer[i].length();
                shortestIndex = i;
            }
        }
        int[] res = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            int index = trie.endsWith(query) == -1 ? shortestIndex : trie.endsWith(query);
            res[i] = index;
        }
        System.out.println(Arrays.toString(res));
        return res;
    }
}
class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode('0');
    }

    public void insert(String word, int index) {
        int wordLength = word.length();
        TrieNode temp = root;
        for (int i = wordLength - 1; i >= 0; i--) {
            if(temp.children.containsKey(word.charAt(i))){
                temp.indexesGoThrough.offer(new int[]{word.length(), index});
            }
            if(!temp.children.containsKey(word.charAt(i))){
                TrieNode node = new TrieNode(word.charAt(i));
                temp.indexesGoThrough.offer(new int[]{word.length(), index});
                temp.children.put(word.charAt(i), node);
            }
            temp = temp.children.get(word.charAt(i));
            if(i == 0){
                if(!temp.isTerminal){
                    temp.isTerminal = true;
                    temp.index = index;
                }
            }
        }

    }

    public boolean search(String word) {
        TrieNode temp = root;
        for (int i = word.length() - 1; i >=0; i--) {
            if(temp.children.containsKey(word.charAt(i))){
                temp = temp.children.get(word.charAt(i));
            }else return false;
        }
        return temp.isTerminal;
    }

    public int endsWith(String prefix) {
        TrieNode temp = root;
        for (int i = prefix.length() - 1; i >=0; i--) {
            if(temp.children.containsKey(prefix.charAt(i))){
                temp = temp.children.get(prefix.charAt(i));
            }else break;
        }
        if(temp.isTerminal){
            return temp.index;
        }
        return temp.indexesGoThrough.peek()[1];
    }
}

class TrieNode{
    char value;
    Map<Character, TrieNode> children = new HashMap<>();
    PriorityQueue<int[]> indexesGoThrough = new PriorityQueue<>(Comparator.comparingInt((int[]a) -> a[0]).thenComparing((int[]a) -> a[1]));
    boolean isTerminal;
    int index = -1;

    public TrieNode(char value) {
        this.value = value;
        this.isTerminal = false;
    }
}

