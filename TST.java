//I used information from the following link to help implement the ternary search tree
//https://www.sanfoundry.com/java-program-ternary-search-tree/#:~:text=A%20ternary%20search%20tree%20is,to%20Implement%20Ternary%20Search%20Tree.

import java.util.ArrayList;

public class TST {
    static TSTNode root;
    static ArrayList<String> theList;

    public static void TST()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        if(root == null) return true;
        else return false;
    }

    public void insert(String word)
    {
        root = insert(root, word.toCharArray(), 0);
    }

    public TSTNode insert(TSTNode node, char[] word, int index)
    {
        if(node == null)
        {
            node = new TSTNode(word[index]);
        }

        if(word[index] < node.theChar)
        {
            node.left = insert(node.left, word, index);
        }
        else if(word[index] > node.theChar)
        {
            node.right = insert(node.right, word, index);
        }
        else
        {
            if(index + 1 < word.length)
            {
                node.middle = insert(node.middle, word, index + 1);
            }
            else node.isEnd = true;
        }
        return node;
    }

    public boolean search(String word)
    {
        return search(root, word.toCharArray(), 0);
    }

    public boolean search(TSTNode node, char[] word, int index)
    {
        if(node == null) return false;

        if(word[index] < node.theChar) return search(node.left, word, index);
        if(word[index] > node.theChar) return search(node.right, word, index);
        else
        {
            if(node.isEnd && index == word.length - 1) return true;
            else if (index == word.length -1) return false;
            else return search(node.middle, word, index +1);
        }
    }
}
