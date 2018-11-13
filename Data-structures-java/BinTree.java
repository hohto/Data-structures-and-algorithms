package binaryTree;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinTree{
	public static class TreeNode {

	    public int val; // 数据域
	    public TreeNode left; // 左子树根节点
	    public TreeNode right; // 右子树根节点
	
	    public TreeNode() {
	
	    }

	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
	
    public static List<TreeNode> createbinTree(int[] array) {
    	List<TreeNode> nodeList = new LinkedList<>();
    	for (int nodeIndex=0;nodeIndex<array.length;nodeIndex++) {
    		nodeList.add(new TreeNode(array[nodeIndex]));
    	}
    
    	for (int parentIndex=0;parentIndex<array.length/2-1;parentIndex++) {
    		nodeList.get(parentIndex).left=nodeList.get(parentIndex*2+1);
    		nodeList.get(parentIndex).right=nodeList.get(parentIndex*2+2);
    	}
    	
    	int lastParentIndex = array.length/2-1;
    	nodeList.get(lastParentIndex).left=nodeList.get(lastParentIndex*2+1);
    	if (array.length%2==1) {
    		nodeList.get(lastParentIndex).right=nodeList.get(lastParentIndex*2+2);
    	}  
    	return nodeList;
    }
    
    /**
     * 先序遍历
     * @param node
     */
    public static void preorderTraverse(TreeNode node){
    	   if (node == null) {
    	       return;
    	   }
    	   System.out.print(node.val + "->");
    	   preorderTraverse(node.left);
    	   preorderTraverse(node.right);
    	}
    
    /**
     * 中序遍历
     * @param node
     */
    public static void inorderTraverse(TreeNode node) {
    	if (node == null) {
    		return;
    	}
    	inorderTraverse(node.left);
    	System.out.print( node.val+ "->");
    	inorderTraverse(node.right);
    }

    /**
     * 后序遍历
     * @param node
     */
    public static void postorderTraverse(TreeNode node) {
    	if (node==null) {
    		return;
    	}
    	postorderTraverse(node.left);
    	postorderTraverse(node.right);
    	System.out.print(node.val + "->");
    }
    
    public static void main(String[] args) {
    	int[] array = {1,2,3,4,5,6,7,8,9};
    	List<TreeNode> testTree = createbinTree(array);
    	preorderTraverse(testTree.get(0));
    	System.out.print("\n");
    	inorderTraverse(testTree.get(0));
    	System.out.print("\n");
    	postorderTraverse(testTree.get(0));
    	//System.out.print("\n");
    	//noEcursionPre(testTree.get(0));
    }
    
    /**
     * 非递归前序遍历
     * @param node
     */
    public static void noEcursionPre(TreeNode node) {
    	if (node==null) {
    		return;
    	}   	
    	
    	//辅助栈
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode cur = node;
    	while(cur!=null || !stack.isEmpty()) {
    		//while+if 判断
    		while(cur!=null) {
    			System.out.print(cur.val + "->");
    			stack.push(cur);// 不断将左子节点入栈，直到cur为空
    			cur=cur.left;
    		}
    		if(!stack.isEmpty()) {
    			cur=stack.pop();// 此时弹出最左边的节点
    			cur=cur.right;
    		}
    	}
    }
}
