public class BinaryNode<E extends Comparable<E>> {
    private E element;          // an element stored at this node
    private BinaryNode<E> left;       // a reference to the left child (if any)
    private BinaryNode<E> right;      // a reference to the right child (if any)

    /**
     * Constructs a node with the given element and neighbors.
     *
     * @param e          the element to be stored
     * @param above      reference to a parent node
     * @param leftChild  reference to a left child node
     * @param rightChild reference to a right child node
     */
    public BinaryNode(E e, BinaryNode<E> leftChild, BinaryNode<E> rightChild) {
        element = e;
        left = leftChild;
        right = rightChild;
    }

    // accessor methods
    public E getElement() {
        return element;
    }

    // update methods
    public void setElement(E e) {
        element = e;
    }

    public BinaryNode<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<E> leftChild) {
        left = leftChild;
    }

    public BinaryNode<E> getRight() {
        return right;
    }

    public void setRight(BinaryNode<E> rightChild) {
        right = rightChild;
    }

}
