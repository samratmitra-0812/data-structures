import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Tree<T> {
	
	static final String SUBTREE_NODE_COUNT = "SUBTREE_NODE_COUNT";
	
	Node<T> root;
	HashMap<T, Node<T>> nodes;
	
	public Tree() {
		
	}
	
	public Tree(T data) {
		root = new Node<T>(data);
	}
	
	void addEdge(T data1, T data2) {
		Node<T> node1 = new Node<T>(data1);
		Node<T> node2 = new Node<T>(data2);
		
		if(root == null)
			root = node1;
		
		node1.addChild(node2);
		
		if(nodes == null)
			nodes = new HashMap<T, Node<T>>();
		nodes.put(data1, node1);
		nodes.put(data2, node2);
	}
	
	Node<T> getNode(T data){
		return nodes.get(data);
	}
	
	int calculateNodeCountInSubtree(Node<T> node) {
		if(node == null)
			return 0;
		
		if(node.isLeaf())
			node.addProperty(SUBTREE_NODE_COUNT, 1);
		
		int subtree_node_count = 0;
		for(Node<T> child: node.children)
			subtree_node_count += calculateNodeCountInSubtree(child);
		node.addProperty(SUBTREE_NODE_COUNT, subtree_node_count + 1);
		
		return node.getProperty(SUBTREE_NODE_COUNT);
	}
	
	Path getPath(Node<T> node1, Node<T> node2) {
		
		Path<T> path = new Path<T>();
		path.node1_to_lca = new ArrayList<Node<T>>();
		
		Node<T> temp = node1;
		
		return path;
	}
	
	class Node<U>{
		
		U data;
		HashSet<Node<U>> children;
		Node<U> parent;
		HashMap<String, Integer> properties;
		
		public Node(U data) {
			this.data = data;
		}
		
		void addChild(Node<U> child){
			if(children == null)
				children = new HashSet<Node<U>>();
			children.add(child);
			child.parent = this;
		}
		
		boolean isLeaf() {
			return children == null || children.size() == 0;
		}
		
		Integer getProperty(String property) {
			return properties == null ? null :  properties.get(property);
		}
		
		void addProperty(String property, Integer value) {
			if(properties == null)
				properties = new HashMap<String, Integer>();
			properties.put(property, value);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Node))
				return false;
			
			Node<U> node = (Node<U>)obj;
			return this.data.equals(node.data);
			
		}
		
		@Override
		public int hashCode() {
			return data.hashCode();
		}
	}
	
	class Path<U>{
		
		ArrayList<Node<U>> node1_to_lca;
		ArrayList<Node<U>> lca_to_node2;
	}

}
