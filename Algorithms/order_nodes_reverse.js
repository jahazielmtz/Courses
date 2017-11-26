class Node {
	constructor(val, node) {
		this.val = val;
		this.next = node;
	}
}

const node4 = new Node(4, null);
const node3 = new Node(3, node4);
const node2 = new Node(2, node3);
const node1 = new Node(1, node2);
let head = null;

function orderNodesReverse(head) {
	let pre = null;
	let act = null;
	debugger;
	while(head.next != null) {
		act = head;
		head = act.next;
		act.next = pre;
		pre = act;
	}
	head.next = pre;
	return head;
}