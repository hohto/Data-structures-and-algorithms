package binaryTree;

import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author symnai
 *
 */
public class BinarySearchTree {
	private TreeNode root = null;
	private List<TreeNode> nodelist = new ArrayList<TreeNode>();
	
	private class TreeNode{
		private int key;
		private TreeNode left;
		private TreeNode right;
		private TreeNode parent;
		
		public TreeNode(int key, TreeNode left,TreeNode right, TreeNode parent) {
			this.key = key;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
		
		public int getKey() {
			return key;
		}

	    
		public String toString() {
			String leftkey = (left==null?"":String.valueOf(left.key));
			String rightkey = (right==null?"":String.valueOf(right.key));
			return "(" + leftkey + "," + key + "," + rightkey +")";
		}
	}
	
    public TreeNode getRoot() {  
        return root;  
    } 
	
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void TreeEmpty() throws Exception{
		if(isEmpty()) {
			throw new Exception("树为空！");
		}
	}
	
	/**
	 * 查找
	 * @param key
	 * @return
	 */
	public TreeNode search(int key) {
		TreeNode pNode = root;
		while(pNode != null && pNode.key != key) {
			if(key < pNode.key) {
				pNode = pNode.left;
			}else {
				pNode = pNode.right;
			}
		}
		return pNode;
	}
	
	/**
	 * 获取二叉查找树中的最小关键字结点
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public TreeNode minElemNode(TreeNode node) throws Exception{
		if(node == null) {
			throw new Exception("树为空！");
		}
		TreeNode pnode = node;
		while(pnode.left!=null) {
			pnode = pnode.left;
		}
		return pnode;
	}
	
	/**
	 * 获取二叉查找树中的最大关键字结点
	 * @param node
	 * @return
	 * @throws Exception
	 */
    public TreeNode maxElemNode(TreeNode node) throws Exception {  
        if (node == null) {  
            throw new Exception("树为空！");  
        }  
        TreeNode pNode = node;  
        while (pNode.right != null) {  
            pNode = pNode.right;  
        }  
        return pNode;  

    }
    
    /**
     * successor: 获取给定结点在中序遍历顺序下的后继结点 
     * @param node
     * @return
     * @throws Exception
     */
    public TreeNode successor(TreeNode node) throws Exception {
    	if(node == null) {
    		//throw new Exception("结点为空！");
    		return null; 
    	}
    	if(node.right!=null) {
    		return minElemNode(node.right);
    	}
		TreeNode parentNode = node.parent;
		while(parentNode != null && node == parentNode.right) {
			node = parentNode;
			parentNode = parentNode.parent;
		}
		return parentNode;
    	
    }
    
    /**
     * precessor: 获取给定结点在中序遍历顺序下的前趋结点
     * @param node
     * @return
     * @throws Exception
     */
    public TreeNode precessor(TreeNode node) throws Exception {  
        if (node == null) {  
            return null;  
        }  
  
        // 若该结点的左子树不为空，则其前趋结点就是左子树中的最大关键字结点  
        if (node.left != null) {  
            return maxElemNode(node.left);  
        }  
        // 若该结点左子树为空  
        TreeNode parentNode = node.parent;  
        while (parentNode != null && node == parentNode.left) {  
            node = parentNode;  
            parentNode = parentNode.parent;  
        }  
        return parentNode;  
    }  

    /**
     * insert
     * @param key
     */
    public void insert(int key) {
    	TreeNode parentNode = null;
    	TreeNode newNode = new TreeNode(key,null,null,null);
    	TreeNode pNode=root;

        if (root == null) {  
            root = newNode;  
            return;  
        } 
        
    	while(pNode!=null) {
    		parentNode=pNode;
    		if(key<pNode.key) {
    			pNode=pNode.left;
    		}else if(key>pNode.key) {
    			pNode=pNode.right;
    		}else {
    			return;
    		}
    	}
    	if(key < parentNode.key) {
    		parentNode.left=newNode;
    		newNode.parent=parentNode;
    	}else{
    		parentNode.right=newNode;
    		newNode.parent=parentNode;
    	}
    }
    

//    public void delete(int key) throws Exception {  
//        TreeNode pNode = search(key);  
//        if (pNode == null) {  
//            throw new Exception("树中不存在要删除的关键字!");  
//        }  
//        delete(pNode);  
//    }
    
    private void delete(TreeNode node) throws Exception {
    	if(node == null) {
    		return;
    	}
    	if(node.left == null && node.right == null) {
    		TreeNode parentNode = node.parent;
    		if(node == parentNode.left) {
    			parentNode.left = null;
    		}else {
    			parentNode.right = null;
    		}
    		node = null;
    		return;
    	}
    	if(node.left == null && node.right != null) {
    		TreeNode parentNode = node.parent;
    		TreeNode rightNode = node.right;
    		if(node == parentNode.left) {
    			parentNode.left = node.right;
    			rightNode.parent = parentNode;
    		}else {
    			parentNode.right = node.right;
    			rightNode.parent = parentNode;
    		}
    		node = null;
    		return;
    	}
    	if(node.left != null && node.right == null) {
    		TreeNode parentNode = node.parent;
    		TreeNode leftNode = node.left;
    		if(node == parentNode.left) {
    			parentNode.left = node.left;
    			leftNode.parent = parentNode;
    		}else {
    			parentNode.right = node.left;
    			leftNode.parent = parentNode;
    		}
    		node = null;
    		return;
    	}
    	
    	if(node.left != null && node.right != null) {
    		TreeNode successorNode = successor(node);
    		node.key=successorNode.key;
    		delete(successorNode);
    	}
    }
    

    public List<TreeNode> inOrderTraverseList() {  
        if (nodelist != null) {  
            nodelist.clear();  
        }  
        inOrderTraverse(root);  
        return nodelist;  
    }  
  
    /** 
     * inOrderTraverse: 对给定二叉查找树进行中序遍历 
     *  
     * @param root 给定二叉查找树的根结点 
     */  
    private void inOrderTraverse(TreeNode root) {  
        if (root != null) {  
            inOrderTraverse(root.left);  
            nodelist.add(root);  
            inOrderTraverse(root.right);  
        }  
    }  


    public String toStringOfOrderList() {  
        StringBuilder sbBuilder = new StringBuilder(" [ ");  
        for (TreeNode p : inOrderTraverseList()) {  
            sbBuilder.append(p.key);  
            sbBuilder.append(" ");  
        }  
        sbBuilder.append("]");  
        return sbBuilder.toString();  
    }  

    public static void testNode(BinarySearchTree bst, TreeNode pNode) throws Exception {  
        System.out.println("本结点: " + pNode);  
        System.out.println("前趋结点: " + bst.precessor(pNode));  
        System.out.println("后继结点: " + bst.successor(pNode));  
    }  

    public static void testTraverse(BinarySearchTree bst) {  
        System.out.println("二叉查找树转换为有序列表: " + bst.toStringOfOrderList());  
    }  

    
    public static void main(String[] args) {
    	try {
    		BinarySearchTree bst = new BinarySearchTree();
    		System.out.println("查找树是否为空？" + (bst.isEmpty()?"是":"否"));
    		int[] keys = new int[] { 52,18,69,32,10,2,7,72,86,98,100,5,1020,789,13,15 }; 
     		for(int key:keys) {
    			bst.insert(key);
    		}
    		System.out.println("查找树是否为空？" + (bst.isEmpty()?"是":"否"));
    		

            //找最小结点
            TreeNode minkeyNode = bst.minElemNode(bst.getRoot());  
            System.out.println("最小关键字： " + minkeyNode.getKey());  
            testNode(bst, minkeyNode);  
            //找最大结点
            TreeNode maxKeyNode = bst.maxElemNode(bst.getRoot());  
            System.out.println("最大关键字： " + maxKeyNode.getKey());  
            testNode(bst, maxKeyNode); 
            
            testTraverse(bst);   
            
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
    		e.printStackTrace();
    	}
    }
}
