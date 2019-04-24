import java.util.ArrayList;

public class linkedBinarySearchTree<E extends Comparable<E>> {
    protected BinaryNode<E> root = null;     // root of the tree

    // constructor
    public linkedBinarySearchTree() {
    }      // constructs an empty binary tree

    protected BinaryNode<E> validate(BinaryNode<E> p) throws IllegalArgumentException {
        if (!(p instanceof BinaryNode))
            throw new IllegalArgumentException("Not valid BinaryNode type");
        BinaryNode<E> node = (BinaryNode<E>) p;       // safe cast
        if (getParent(node, p) == node)     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    public BinaryNode<E> root() {
        return root;
    }

    public BinaryNode<E> left(BinaryNode<E> p) throws IllegalArgumentException {
        BinaryNode<E> node = validate(p);
        return node.getLeft();
    }

    public BinaryNode<E> right(BinaryNode<E> p) throws IllegalArgumentException {
        BinaryNode<E> node = validate(p);
        return node.getRight();
    }

    // update methods supported by this class

    /**
     * Places element e at the root of an empty tree and returns its new BinaryNode.
     *
     * @param e the new element
     * @return the BinaryNode of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public BinaryNode<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = new BinaryNode(e, null, null);
        return root;
    }

    private boolean isEmpty() {
        // TODO Auto-generated method stub
        if (root == null) return true;
        return false;
    }

    /**
     * Creates a new left child of BinaryNode p storing element e and returns its BinaryNode.
     *
     * @param p the BinaryNode to the left of which the new element is inserted
     * @param e the new element
     * @return the BinaryNode of the new element
     * @throws IllegalArgumentException if p is not a valid BinaryNode for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public BinaryNode<E> addLeft(BinaryNode<E> p, E e)
            throws IllegalArgumentException {
        BinaryNode<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        BinaryNode<E> child = new BinaryNode(e, null, null);
        parent.setLeft(child);
        return child;
    }

    /**
     * Creates a new right child of BinaryNode p storing element e and returns its BinaryNode.
     *
     * @param p the BinaryNode to the right of which the new element is inserted
     * @param e the new element
     * @return the BinaryNode of the new element
     * @throws IllegalArgumentException if p is not a valid BinaryNode for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public BinaryNode<E> addRight(BinaryNode<E> p, E e)
            throws IllegalArgumentException {
        BinaryNode<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        BinaryNode<E> child = new BinaryNode(e, null, null);
        parent.setRight(child);
        return child;
    }

    /**
     * Replaces the element at BinaryNode p with element e and returns the replaced element.
     *
     * @param p the relevant BinaryNode
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid BinaryNode for this tree.
     */
    public E set(BinaryNode<E> p, E e) throws IllegalArgumentException {
        BinaryNode<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    private boolean isInternal(BinaryNode<E> p) {
        if (p.getLeft() != null || p.getRight() != null)
            return true;
        return false;
    }

    //************************************/
    //LAB3 coding work starts here
    //************************************/
    //find and return the number of nodes on the tree
    public int size(BinaryNode node) {
        if (root == null) {
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    public BinaryNode<E> getParent(BinaryNode<E> currRoot, BinaryNode<E> p) {
        if (p.getLeft() == currRoot || p.getRight() == currRoot)
            return currRoot;

        if (p == null || p == root) {
            if (currRoot.getLeft() != null)
                return getParent(currRoot.getLeft(), p);
            if (currRoot.getRight() != null)
                return getParent(currRoot.getRight(), p);
        }

        return currRoot;
    }

    private int numChildren(BinaryNode<E> p) {
        if (p.getRight() != null ^ p.getLeft() != null) return 1;
        else if (p.getRight() != null && p.getLeft() != null) return 2;
        else return 0;
    }

    public E remove(BinaryNode<E> p) throws IllegalArgumentException {

        if (root == null) {
            return null;
        }
        if (root.getLeft() == p) {
            remove(root.getLeft());
        } else if (p.getRight() == p) {
            remove(root.getRight());
        } else {
            if (root.getLeft() == null && root.getRight() == null) {
                remove(root);
                root = null;
            } else if (root.getLeft() == null && root.getRight() != null) {
                BinaryNode<E> temp = root;
                root = root.getLeft();
                remove(temp);
            } else if (root.getRight() == null) {
                BinaryNode<E> temp = root;
                root = root.getRight();
                remove(temp);
            } else {
                BinaryNode<E> temp = findMin(root.getRight());
                root = temp;
                remove(root.getRight());
            }
        }
        return root.getElement();
    }

    public boolean search(E e) { // check if element e is on the tree or not// `
        return recursiveSearch(root, e);
    }

    private boolean recursiveSearch(BinaryNode<E> curr, E e) {// check if element e is on the tree or not// `
        if (root == null)
            return false;
        else if (e == curr.getElement())
            return true;
        else if (e.compareTo(curr.getElement()) < 0)
            return recursiveSearch(curr.getLeft(), e);
        else
            return recursiveSearch(curr.getRight(), e);
    }

    public void insert(E e) {//insert element e into the binary search tree, do nothing if found duplicate element
        recursiveInsert(root, e);
    }

    private BinaryNode<E> recursiveInsert(BinaryNode<E> curr, E e) {
        if (curr == null) {
            curr = new BinaryNode<E>(e, null, null);
        }
        if (e.compareTo(curr.getElement()) < 0) {
            recursiveInsert(root.getLeft(), e);
        } else if (e.compareTo(curr.getElement()) > 0) {
            recursiveInsert(root.getRight(), e);
        }
        return root;
    }

    public BinaryNode<E> findMax(BinaryNode<E> p) {//find and return the node contains the maximum element on a binary search tree rooted at p
        if (p == null)
            return null;
        if (p.getRight() != null) {
            return findMax(root.getRight());
        }
        return p;
    }

    public BinaryNode<E> findMin(BinaryNode<E> p) {
        if (root == null) {
            return null;
        }
        if (root.getLeft() != null) {
            return findMin(root.getLeft());
        }
        return root;
    }

    public boolean isIdentical(BinaryNode<E> tree1, BinaryNode<E> tree2) {

        return recursiveIsIdentical(tree1, tree2);
    }

    private boolean recursiveIsIdentical(BinaryNode<E> tree1, BinaryNode<E> tree2) {
        if (tree1 == tree2)
            return true;
        if (tree1 == null || tree2 == null)
            return false;
        return ((tree1.getElement() == tree2.getElement()) &&
                (recursiveIsIdentical(tree1.getLeft(), tree1.getLeft()) &&
                        recursiveIsIdentical(tree1.getRight(), tree1.getRight())));
    }

    public boolean printAncestors(BinaryNode<E> p) {
        return recursivePrintAncestors(root, p);
    }

    private boolean recursivePrintAncestors(BinaryNode<E> curr, BinaryNode<E> p) {
        if (curr == null)
            return false;

        if (curr == p)
            return true;

        if (recursivePrintAncestors(curr.getLeft(), p) || recursivePrintAncestors(curr.getRight(), p)) {
            System.out.print(curr.getElement() + " ");
            return true;
        } else
            return false;
    }

    public ArrayList<E> inorderTraversal(BinaryNode<E> p) {//find and return the inorder travesal for elements stored on the tree rooted at p

        ArrayList<BinaryNode<E>> traversal = new ArrayList<BinaryNode<E>>();

        return recursiveInorderTraversal(traversal, p);

    }

    private ArrayList<E> recursiveInorderTraversal(ArrayList traversal, BinaryNode<E> p) {

        traversal.add(p);

        if (p.getLeft() != null) {
            traversal.add(p.getLeft());
        }
        if (p.getRight() != null) {
            traversal.add(p.getRight());
        }
        return traversal;
    }


}
