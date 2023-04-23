package algorithm;

class TreeNode {
    public int data;
    public TreeNode right;
    public TreeNode left;
    public TreeNode parent;
    public RedBlackTree.Color color;

    public TreeNode(int data) {
        this.left = null;
        this.right = null;
        this.parent = null;
        this.data = data;
        this.color = RedBlackTree.Color.Red;
    }
}

public class RedBlackTree {
    enum Color {
        Red, Black
    }


    TreeNode root;
    TreeNode NIL;

    public RedBlackTree() {
        TreeNode nilNode = new TreeNode(0);
        nilNode.color = Color.Black;
        this.NIL = nilNode;
        this.root = this.NIL;
    }

    public void leftRotate(TreeNode x) {
        TreeNode y = x.right;
        x.right = y.left;
        if (y.left != this.NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == this.NIL) { //x is root
            this.root = y;
        } else if (x == x.parent.left) { //x is left child
            x.parent.left = y;
        } else { //x is right child
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(TreeNode x) {
        TreeNode y = x.left;
        x.left = y.right;
        if (y.right != this.NIL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == this.NIL) { //x is root
            this.root = y;
        } else if (x == x.parent.right) { //x is left child
            x.parent.right = y;
        } else { //x is right child
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insertFixup(TreeNode z) {
        while (z.parent.color == Color.Red) {
            if (z.parent == z.parent.parent.left) { //z.parent is the left child

                TreeNode y = z.parent.parent.right; //uncle of z

                if (y.color == Color.Red) { //case 1
                    z.parent.color = Color.Black;
                    y.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    z = z.parent.parent;
                } else { //case2 or case3
                    if (z == z.parent.right) { //case2
                        z = z.parent; //marked z.parent as new z
                        leftRotate(z);
                    }
                    //case3
                    z.parent.color = Color.Black; //made parent black
                    z.parent.parent.color = Color.Red; //made parent red
                    rightRotate(z.parent.parent);
                }
            } else { //z.parent is the right child
                TreeNode y = z.parent.parent.left; //uncle of z

                if (y.color == Color.Red) {
                    z.parent.color = Color.Black;
                    y.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent; //marked z.parent as new z
                        rightRotate(z);
                    }
                    z.parent.color = Color.Black; //made parent black
                    z.parent.parent.color = Color.Red; //made parent red
                    leftRotate(z.parent.parent);
                }
            }
        }
        this.root.color = Color.Black;
    }

    public void insert(TreeNode z) {
        TreeNode y = this.NIL; //variable for the parent of the added node
        TreeNode temp = this.root;

        while (temp != this.NIL) {
            y = temp;
            if (z.data < temp.data)
                temp = temp.left;
            else
                temp = temp.right;
        }
        z.parent = y;

        if (y == this.NIL) { //newly added node is root
            this.root = z;
        } else if (z.data < y.data) //data of child is less than its parent, left child
            y.left = z;
        else
            y.right = z;

        z.right = this.NIL;
        z.left = this.NIL;

        insertFixup(z);
    }

    public void rbTransplant(TreeNode u, TreeNode v) {
        if (u.parent == this.NIL)
            this.root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;
        v.parent = u.parent;
    }

    public TreeNode minimum(TreeNode x) {
        while (x.left != this.NIL)
            x = x.left;
        return x;
    }

    public void deleteFixup(TreeNode x) {
        while (x != this.root && x.color == Color.Black) {
            if (x == x.parent.left) {
                TreeNode w = x.parent.right;
                if (w.color == Color.Red) {
                    w.color = Color.Black;
                    x.parent.color = Color.Red;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.Black && w.right.color == Color.Black) {
                    w.color = Color.Red;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.Black) {
                        w.left.color = Color.Black;
                        w.color = Color.Red;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.Black;
                    w.right.color = Color.Black;
                    leftRotate(x.parent);
                    x = this.root;
                }
            } else {
                TreeNode w = x.parent.left;
                if (w.color == Color.Red) {
                    w.color = Color.Black;
                    x.parent.color = Color.Red;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == Color.Black && w.left.color == Color.Black) {
                    w.color = Color.Red;
                    x = x.parent;
                } else {
                    if (w.left.color == Color.Black) {
                        w.right.color = Color.Black;
                        w.color = Color.Red;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.Black;
                    w.left.color = Color.Black;
                    rightRotate(x.parent);
                    x = this.root;
                }
            }
        }
        x.color = Color.Black;
    }

    public void rbDelete(TreeNode z) {
        TreeNode y = z;
        TreeNode x;
        Color yOrignalColor = y.color;
        if (z.left == this.NIL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == this.NIL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOrignalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = z;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOrignalColor == Color.Black)
            deleteFixup(x);
    }

    public void inorder(TreeNode n) {
        if (n != this.NIL) {
            inorder(n.left);
            System.out.println(n.data);
            inorder(n.right);
        }
    }

    public static void main(String[] args) {
        RedBlackTree t = new RedBlackTree();

        TreeNode a, b, c, d, e, f, g, h, i, j, k, l, m;
        a = new TreeNode(10);
        b = new TreeNode(20);
        c = new TreeNode(30);
        d = new TreeNode(100);
        e = new TreeNode(90);
        f = new TreeNode(40);
        g = new TreeNode(50);
        h = new TreeNode(60);
        i = new TreeNode(70);
        j = new TreeNode(80);
        k = new TreeNode(150);
        l = new TreeNode(110);
        m = new TreeNode(120);

        t.insert(a);
        t.insert(b);
        t.insert(c);
        t.insert(d);
        t.insert(e);
        t.insert(f);
        t.insert(g);
        t.insert(h);
        t.insert(i);
        t.insert(j);
        t.insert(k);
        t.insert(l);
        t.insert(m);

        t.rbDelete(a);
        t.rbDelete(m);

        t.inorder(t.root);
    }

}

/*
CPP 전체 구현 코드

#include <iostream>
#include <queue>
using namespace std;

enum COLOR { RED, BLACK };

class Node {
public:
int val;
COLOR color;
Node *left, *right, *parent;

Node(int val) : val(val) {
	parent = left = right = NULL;

	// Node is created during insertion
	// Node is red at insertion
	color = RED;
}

// returns pointer to uncle
Node *uncle() {
	// If no parent or grandparent, then no uncle
	if (parent == NULL or parent->parent == NULL)
	return NULL;

	if (parent->isOnLeft())
	// uncle on right
	return parent->parent->right;
	else
	// uncle on left
	return parent->parent->left;
}

// check if node is left child of parent
bool isOnLeft() { return this == parent->left; }

// returns pointer to sibling
Node *sibling() {
	// sibling null if no parent
	if (parent == NULL)
	return NULL;

	if (isOnLeft())
	return parent->right;

	return parent->left;
}

// moves node down and moves given node in its place
void moveDown(Node *nParent) {
	if (parent != NULL) {
	if (isOnLeft()) {
		parent->left = nParent;
	} else {
		parent->right = nParent;
	}
	}
	nParent->parent = parent;
	parent = nParent;
}

bool hasRedChild() {
	return (left != NULL and left->color == RED) or
		(right != NULL and right->color == RED);
}
};

class RBTree {
Node *root;

// left rotates the given node
void leftRotate(Node *x) {
	// new parent will be node's right child
	Node *nParent = x->right;

	// update root if current node is root
	if (x == root)
	root = nParent;

	x->moveDown(nParent);

	// connect x with new parent's left element
	x->right = nParent->left;
	// connect new parent's left element with node
	// if it is not null
	if (nParent->left != NULL)
	nParent->left->parent = x;

	// connect new parent with x
	nParent->left = x;
}

void rightRotate(Node *x) {
	// new parent will be node's left child
	Node *nParent = x->left;

	// update root if current node is root
	if (x == root)
	root = nParent;

	x->moveDown(nParent);

	// connect x with new parent's right element
	x->left = nParent->right;
	// connect new parent's right element with node
	// if it is not null
	if (nParent->right != NULL)
	nParent->right->parent = x;

	// connect new parent with x
	nParent->right = x;
}

void swapColors(Node *x1, Node *x2) {
	COLOR temp;
	temp = x1->color;
	x1->color = x2->color;
	x2->color = temp;
}

void swapValues(Node *u, Node *v) {
	int temp;
	temp = u->val;
	u->val = v->val;
	v->val = temp;
}

// fix red red at given node
void fixRedRed(Node *x) {
	// if x is root color it black and return
	if (x == root) {
	x->color = BLACK;
	return;
	}

	// initialize parent, grandparent, uncle
	Node *parent = x->parent, *grandparent = parent->parent,
		*uncle = x->uncle();

	if (parent->color != BLACK) {
	if (uncle != NULL && uncle->color == RED) {
		// uncle red, perform recoloring and recurse
		parent->color = BLACK;
		uncle->color = BLACK;
		grandparent->color = RED;
		fixRedRed(grandparent);
	} else {
		// Else perform LR, LL, RL, RR
		if (parent->isOnLeft()) {
		if (x->isOnLeft()) {
			// for left right
			swapColors(parent, grandparent);
		} else {
			leftRotate(parent);
			swapColors(x, grandparent);
		}
		// for left left and left right
		rightRotate(grandparent);
		} else {
		if (x->isOnLeft()) {
			// for right left
			rightRotate(parent);
			swapColors(x, grandparent);
		} else {
			swapColors(parent, grandparent);
		}

		// for right right and right left
		leftRotate(grandparent);
		}
	}
	}
}

// find node that do not have a left child
// in the subtree of the given node
Node *successor(Node *x) {
	Node *temp = x;

	while (temp->left != NULL)
	temp = temp->left;

	return temp;
}

// find node that replaces a deleted node in BST
Node *BSTreplace(Node *x) {
	// when node have 2 children
	if (x->left != NULL and x->right != NULL)
	return successor(x->right);

	// when leaf
	if (x->left == NULL and x->right == NULL)
	return NULL;

	// when single child
	if (x->left != NULL)
	return x->left;
	else
	return x->right;
}

// deletes the given node
void deleteNode(Node *v) {
	Node *u = BSTreplace(v);

	// True when u and v are both black
	bool uvBlack = ((u == NULL or u->color == BLACK) and (v->color == BLACK));
	Node *parent = v->parent;

	if (u == NULL) {
	// u is NULL therefore v is leaf
	if (v == root) {
		// v is root, making root null
		root = NULL;
	} else {
		if (uvBlack) {
		// u and v both black
		// v is leaf, fix double black at v
		fixDoubleBlack(v);
		} else {
		// u or v is red
		if (v->sibling() != NULL)
			// sibling is not null, make it red"
			v->sibling()->color = RED;
		}

		// delete v from the tree
		if (v->isOnLeft()) {
		parent->left = NULL;
		} else {
		parent->right = NULL;
		}
	}
	delete v;
	return;
	}

	if (v->left == NULL or v->right == NULL) {
	// v has 1 child
	if (v == root) {
		// v is root, assign the value of u to v, and delete u
		v->val = u->val;
		v->left = v->right = NULL;
		delete u;
	} else {
		// Detach v from tree and move u up
		if (v->isOnLeft()) {
		parent->left = u;
		} else {
		parent->right = u;
		}
		delete v;
		u->parent = parent;
		if (uvBlack) {
		// u and v both black, fix double black at u
		fixDoubleBlack(u);
		} else {
		// u or v red, color u black
		u->color = BLACK;
		}
	}
	return;
	}

	// v has 2 children, swap values with successor and recurse
	swapValues(u, v);
	deleteNode(u);
}

void fixDoubleBlack(Node *x) {
	if (x == root)
	// Reached root
	return;

	Node *sibling = x->sibling(), *parent = x->parent;
	if (sibling == NULL) {
	// No sibling, double black pushed up
	fixDoubleBlack(parent);
	} else {
	if (sibling->color == RED) {
		// Sibling red
		parent->color = RED;
		sibling->color = BLACK;
		if (sibling->isOnLeft()) {
		// left case
		rightRotate(parent);
		} else {
		// right case
		leftRotate(parent);
		}
		fixDoubleBlack(x);
	} else {
		// Sibling black
		if (sibling->hasRedChild()) {
		// at least 1 red children
		if (sibling->left != NULL and sibling->left->color == RED) {
			if (sibling->isOnLeft()) {
			// left left
			sibling->left->color = sibling->color;
			sibling->color = parent->color;
			rightRotate(parent);
			} else {
			// right left
			sibling->left->color = parent->color;
			rightRotate(sibling);
			leftRotate(parent);
			}
		} else {
			if (sibling->isOnLeft()) {
			// left right
			sibling->right->color = parent->color;
			leftRotate(sibling);
			rightRotate(parent);
			} else {
			// right right
			sibling->right->color = sibling->color;
			sibling->color = parent->color;
			leftRotate(parent);
			}
		}
		parent->color = BLACK;
		} else {
		// 2 black children
		sibling->color = RED;
		if (parent->color == BLACK)
			fixDoubleBlack(parent);
		else
			parent->color = BLACK;
		}
	}
	}
}

// prints level order for given node
void levelOrder(Node *x) {
	if (x == NULL)
	// return if node is null
	return;

	// queue for level order
	queue<Node *> q;
	Node *curr;

	// push x
	q.push(x);

	while (!q.empty()) {
	// while q is not empty
	// dequeue
	curr = q.front();
	q.pop();

	// print node value
	cout << curr->val << " ";

	// push children to queue
	if (curr->left != NULL)
		q.push(curr->left);
	if (curr->right != NULL)
		q.push(curr->right);
	}
}

// prints inorder recursively
void inorder(Node *x) {
	if (x == NULL)
	return;
	inorder(x->left);
	cout << x->val << " ";
	inorder(x->right);
}

public:
// constructor
// initialize root
RBTree() { root = NULL; }

Node *getRoot() { return root; }

// searches for given value
// if found returns the node (used for delete)
// else returns the last node while traversing (used in insert)
Node *search(int n) {
	Node *temp = root;
	while (temp != NULL) {
	if (n < temp->val) {
		if (temp->left == NULL)
		break;
		else
		temp = temp->left;
	} else if (n == temp->val) {
		break;
	} else {
		if (temp->right == NULL)
		break;
		else
		temp = temp->right;
	}
	}

	return temp;
}

// inserts the given value to tree
void insert(int n) {
	Node *newNode = new Node(n);
	if (root == NULL) {
	// when root is null
	// simply insert value at root
	newNode->color = BLACK;
	root = newNode;
	} else {
	Node *temp = search(n);

	if (temp->val == n) {
		// return if value already exists
		return;
	}

	// if value is not found, search returns the node
	// where the value is to be inserted

	// connect new node to correct node
	newNode->parent = temp;

	if (n < temp->val)
		temp->left = newNode;
	else
		temp->right = newNode;

	// fix red red violation if exists
	fixRedRed(newNode);
	}
}

// utility function that deletes the node with given value
void deleteByVal(int n) {
	if (root == NULL)
	// Tree is empty
	return;

	Node *v = search(n), *u;

	if (v->val != n) {
	cout << "No node found to delete with value:" << n << endl;
	return;
	}

	deleteNode(v);
}

// prints inorder of the tree
void printInOrder() {
	cout << "Inorder: " << endl;
	if (root == NULL)
	cout << "Tree is empty" << endl;
	else
	inorder(root);
	cout << endl;
}

// prints level order of the tree
void printLevelOrder() {
	cout << "Level order: " << endl;
	if (root == NULL)
	cout << "Tree is empty" << endl;
	else
	levelOrder(root);
	cout << endl;
}
};

int main() {
RBTree tree;

tree.insert(7);
tree.insert(3);
tree.insert(18);
tree.insert(10);
tree.insert(22);
tree.insert(8);
tree.insert(11);
tree.insert(26);
tree.insert(2);
tree.insert(6);
tree.insert(13);

tree.printInOrder();
tree.printLevelOrder();

cout<<endl<<"Deleting 18, 11, 3, 10, 22"<<endl;

tree.deleteByVal(18);
tree.deleteByVal(11);
tree.deleteByVal(3);
tree.deleteByVal(10);
tree.deleteByVal(22);

tree.printInOrder();
tree.printLevelOrder();
return 0;
}

 */
