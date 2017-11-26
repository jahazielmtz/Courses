class Node {
	constructor(val, left = null, right = null) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

const head = new Node(5, new Node(2, new Node(4), new Node(3)));

function sumBinaryTree(head) {
	if (head == null) return 0;

	if (head != null) {
		return head.val + sumBinaryTree(head.left) + sumBinaryTree(head.right);
	}
}