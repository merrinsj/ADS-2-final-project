public class TSTNode {
    char theChar;
    boolean isEnd;
    TSTNode left;
    TSTNode right;
    TSTNode middle;

    TSTNode(char theChar)
    {
        this.theChar = theChar;
        this.isEnd = false;
        this.left = null;
        this.right = null;
        this.middle = null;
    }
}
