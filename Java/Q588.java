import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Trie
 * 因为file system的路径前缀都是一样的
 * 
 * Trie class需要什么？
 * Name： file or directory name
 * boolean: isFile: whether this is a file
 * cotent: the content of file
 * children: map all directory under this path;
 * 
 * 这样每一个node节点就存的是directory的名字
 *       a
 *     b  b1
 *    c
 * 
 */
public class Q588 {
    class TrieNode {
        String name;
        boolean isFile;
        StringBuilder content = new StringBuilder();
        Map<String, TrieNode> children = new HashMap<>();

        TrieNode insert(String path, boolean isFile) {
            TrieNode node = this;
            String[] pathArr = path.split("/");
            // 因为输入的是"/a/b/c/x"
            // 所以index==0不用管，我们从index==1开始遍历
            for (int i = 1; i < pathArr.length; i++) {
                String part = pathArr[i];
                // 如果当前节点还没有这个children的话，我们就创一个
                if (!node.children.containsKey(part)) {
                    node.children.put(part, new TrieNode());
                }
                node = node.children.get(part);
            }
            node.isFile = isFile;
            if (isFile) {
                node.name = pathArr[pathArr.length - 1];
            }
            return node;
        }

        TrieNode search(String path) {
            TrieNode node = this;
            String[] pathArr = path.split("/");
            for (int i = 1; i < pathArr.length; i++) {
                String p = pathArr[i];
                if (!node.children.containsKey(p)) {
                    return null;
                }
                node = node.children.get(p);
            }
            return node;
        }
}

class FileSystem {
    private TrieNode root = new TrieNode();

    public FileSystem() {
    }

    public List<String> ls(String path) {
        List<String> ans = new ArrayList<>();
        TrieNode node = root.search(path);
        if (node == null) {
            return ans;
        }
        if (node.isFile) {
            ans.add(node.name);
            return ans;
        }
        for (String v : node.children.keySet()) {
            ans.add(v);
        }
        Collections.sort(ans);
        return ans;
    }

    public void mkdir(String path) {
        root.insert(path, false);
    }

    public void addContentToFile(String filePath, String content) {
        TrieNode node = root.insert(filePath, true);
        node.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        TrieNode node = root.search(filePath);
        return node.content.toString();
    }
}
}
