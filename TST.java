//I used information from the following links to help implement the ternary search tree
//https://www.sanfoundry.com/java-program-ternary-search-tree/#:~:text=A%20ternary%20search%20tree%20is,to%20Implement%20Ternary%20Search%20Tree.
//https://www.geeksforgeeks.org/how-to-implement-text-auto-complete-feature-using-ternary-search-tree/

import java.util.ArrayList;
import java.util.Arrays;

public class TST {
    static TSTNode root;
    static ArrayList<String> theList;

    public static void TST() {
        root = null;
    }

    public boolean isEmpty() {
        if (root == null) return true;
        else return false;
    }

    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }

    public TSTNode insert(TSTNode node, char[] word, int index) {
        if (node == null) {
            node = new TSTNode(word[index]);
        }

        if (word[index] < node.theChar) {
            node.left = insert(node.left, word, index);
        } else if (word[index] > node.theChar) {
            node.right = insert(node.right, word, index);
        } else {
            if (index + 1 < word.length) {
                node.middle = insert(node.middle, word, index + 1);
            } else node.isEnd = true;
        }
        return node;
    }

    public boolean search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    public boolean search(TSTNode node, char[] word, int index) {
        if (node == null) return false;

        if (word[index] < node.theChar) return search(node.left, word, index);
        if (word[index] > node.theChar) return search(node.right, word, index);
        else {
            if (node.isEnd && index == word.length - 1) return true;
            else if (index == word.length - 1) return false;
            else return search(node.middle, word, index + 1);
        }
    }

    public ArrayList<String> traverse(TSTNode node, String theString, ArrayList<String> theList) {
        if (node != null) {
            traverse(node.left, theString, theList);
            theString = theString + node.theChar;
            if (node.isEnd) {
                theList.add(theString);
            }
            traverse(node.middle, theString, theList);
            theString = theString.substring(0, theString.length() - 1);
            traverse(node.right, theString, theList);
        }
        return theList;
    }

    public ArrayList<String> autocompleteWord(String incompleteWord) {
        int index = 0;
        TSTNode tmpRoot = root;
        char[] charArray = incompleteWord.toCharArray();
        ArrayList<String> possibleOptions = new ArrayList<String>();
        if (incompleteWord == "") {
            System.out.println("No results");
            return possibleOptions;
        }

        while (index < incompleteWord.length()) {
            if (tmpRoot.theChar > charArray[index]) {
                tmpRoot = tmpRoot.left;
            } else if (tmpRoot.theChar < charArray[index]) {
                tmpRoot = tmpRoot.right;
            } else if (tmpRoot.theChar == charArray[index]) {
                tmpRoot = tmpRoot.middle;
                index++;
            } else return possibleOptions;
        }
        possibleOptions = traverse(tmpRoot, incompleteWord, possibleOptions);
        return possibleOptions;
    }

    public void print(ArrayList<String> listOfWords)
    {
        System.out.println("Input the corresponding number of the bus stop you want");
        for(int i = 0; i < listOfWords.size(); i++)
        {
            System.out.println(i + ". " + listOfWords.get(i));
        }
    }

}

