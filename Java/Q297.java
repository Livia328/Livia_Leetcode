import java.util.ArrayList;
import java.util.List;

public class Q297 {
    /*
     * 定义SEP = ","
     * NULL = "#"
     * 
     * tree的前序遍历
     * 遍历完node，append SEP
     */
    public class Codec {
        String SEP = ",";
        String NULL = "#";
        StringBuilder sb = new StringBuilder();
        // Encodes a tree to a single string.
        /*
         *     1
         *   2   3
         *      4  5
         * 
         * 1,2,#,#,3,4,#,#,5,#,#,
         */
        public String serialize(TreeNode root) {
            traverse(root);
            return sb.toString();
        }

        public void traverse(TreeNode root) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            sb.append(root.val).append(SEP);
            traverse(root.left);
            traverse(root.right);
        }
    
        // Decodes your encoded data to tree.
        // 先根据SEP slit
        // 递归重组
        public TreeNode deserialize(String data) {
            List<String> list = new ArrayList<>();
            for (String s : data.split(SEP)) {
                list.add(s);
            }
            return helper(list);
        }

        public TreeNode helper(List<String> data) {
            // base case
            if (data.isEmpty()) {
                return null;
            }
            String first = data.remove(0);
            if (first.equals(NULL)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(first));
            root.left = helper(data);
            root.right = helper(data);
            return root;
        }
    }
}
