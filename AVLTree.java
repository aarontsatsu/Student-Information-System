/**
 * @author Aaron Tsatsu Tamakloe, Princess Asante, Mercy Chimezie
 */


/**
 * The Node Class represents a node object with fields:
 * student id that will serve as key in the tree,
 * student object that will be the value in the tree,
 * height, left child and right child.
 */
class Node {
        int studID, height;
        Student studObj;
        Node left, right;

    /**
     * Node Constructor
     * @param key unique identifier of a student in the tree
     * @param stud student object associated with the key/ student id
     */
    Node(int key, Student stud) {
            studID = key;
            studObj = stud;
            height = 1;
        }
    }

/**
 * The AVLTree class implements the AVL tree.
 */
class AVLTree extends StackImp {
    Node root;


    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    // Get balance factor of a node
    int getBalanceFactor(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Insert a node
    Node insertNode(Node node, int item, Student studObj) {

        // Find the position and insert the node
        if (node == null)
            return (new Node(item, studObj));
        if (item < node.studID)
            node.left = insertNode(node.left, item, studObj);
        else if (item > node.studID)
            node.right = insertNode(node.right, item, studObj);
        else
            return node;

        // Update the balance factor of each node
        // And, balance the tree
        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (item < node.left.studID) {
                return rightRotate(node);
            } else if (item > node.left.studID) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (item > node.right.studID) {
                return leftRotate(node);
            } else if (item < node.right.studID) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    Node nodeWithMinValue(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete a node
    Node deleteNode(Node root, int item) {

        // Find the node to be deleted and remove it
        if (root == null)
            return root;
        if (item < root.studID)
            root.left = deleteNode(root.left, item);
        else if (item > root.studID)
            root.right = deleteNode(root.right, item);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = nodeWithMinValue(root.right);
                root.studID = temp.studID;
                root.right = deleteNode(root.right, temp.studID);
            }
        }
        if (root == null)
            return root;

        // Update the balance factor of each node and balance the tree
        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (getBalanceFactor(root.left) >= 0) {
                return rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(root.right) <= 0) {
                return leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }

    public Node findNode(int key, Node node) {
        if (node == null) {
            return null;
        }

        if (key == node.studID) {
            return node;
        } else if (key < node.studID) {
            return findNode(key, node.left);
        } else {
            return findNode(key, node.right);
        }
    }

    public boolean ifKeyExists(int key) {
        AVLTree tempTree = new AVLTree();
        if (tempTree.root == null)
            return false;

        if (tempTree.root.studID == key)
            return true;

        // do a recursive call to check the left subtree
        boolean check1 = ifKeyExists(tempTree.root.left.studID);
        if (check1) return true;

        // do a recursive call to check the right subtree
        boolean check2 = ifKeyExists(tempTree.root.right.studID);
        return check2;
    }
}