public class Q430 {
    /*
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
                 |
                 7 -> 8 -> 9 -> 10
                      |
                      11 -> 12
     * 
     * left有点像左子树，right有点像右子树
     * 
     * 转化为单向链表为
     * 1 -> 2 -> 3 -> 7 -> 8 -> 11 -> 12 -> 9 -> 10 - > 4 -> 5 -> 6
     * 
     * 我们每一层要做的：
     * 如果没有child，那么直接移动到下一个
     * 如果有child
     * （比如说3的child是7），那么先利用flattern将left flatten了
     * （我们要相信flattern函数会给我们7 -> 8 -> 11 -> 12 -> 9 -> 10
     * 
     * 然后用tmp = cur.next
     * 暂时存上cur的next（比如说4）
     * 
     * 然后我们要把三个部分连接起来
     * curNode(3), 7开始的，4开始的
     * 
     * 
     */
    public Node flatten(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        while (head != null) {
            if (head.child == null) {
                head = head.next;
            } else {
                Node right = head.next;
                Node left = flatten(head.child);
                // child已经处理完了，所以设置成空
                head.child = null;
                // 把head和left链接起来
                head.next = left;
                left.prev = head;
                // 把head指针移到left的最后一个
                while (head.next != null) {
                    head = head.next;
                }
                // 把left的最后一个（也就是head）和right的开头链接起来
                head.next = right;
                if (right != null) {
                    right.prev = head;
                }
                head = head.next;
            }
        }
        return dummy.next;

    }
}
