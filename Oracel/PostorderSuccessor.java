package Oracel;
import java.util.*;
/**
 * 来源：Oracle面经
 * https://www.geeksforgeeks.org/postorder-successor-node-binary-tree/
 * 
 * Examples: Consider the following binary tree
                    20            
                /      \         
                10       26       
                /  \     /   \     
            4     18  24    27   
                    /  \
                14   19
                /  \
                13  15

        Postorder traversal of given tree is 4, 13, 15, 14,
        19, 18, 10, 24, 27, 26, 20.

        Input :  Complexity Analysis:24
        Output : 27

        Input : 4
        Output : 13
 */

class PostorderSuccessor {

    /**
     * 注意它给的node是有parent的
     */
    static class Node { 
        Node left, right, parent; 
        int value; 
    }
     
    // Utility function to create a new node with 
    // given value. 
    static Node newNode(int value) { 
        Node temp = new Node(); 
        temp.left = null;
        temp.right = null;
        temp.parent = null; 
        temp.value = value; 
        return temp; 
    } 

    /**
     * #1: Brute force
     * 把post order直接存到list里然后找呗
     * 
     * 
     * 分类讨论：
     * 如果是n是parent的右子树，或者它parent的右子树是null，那么他的successor就是parent
     * 否则就找到parent右子树的最左节点
     */
    static Node postorderSuccessor(Node root, Node n) { 
        // base case: Root has no successor in postorder traversal 
        if (n == root) 
            return null; 
    
        // If given node is right child of its 
        // parent or parent's right is empty, then 
        // parent is postorder successor. 
        Node parent = n.parent; 
        if (parent.right == null || parent.right == n) 
            return parent; 
    
        // In all other cases, find the leftmost 
        // child in right subtree of parent. 
        Node curr = parent.right; 
        while (curr.left != null) 
            curr = curr.left; 
    
        return curr; 
    } 
    
}