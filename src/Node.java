import java.util.ArrayList;
import java.util.List;

public class Node<Integer> {
    private List<Node<Integer>> children = new ArrayList<Node<Integer>>();
    private Node<Integer> parent = null;
    private Integer data = null;
    private int[] currentBoard;
    private int action;

    public Node(Integer data) {
        this.data = data;
    }

    public Node(Node<Integer> parent, int[] currentBoard, int action) {
        this.setParent(parent);
        this.currentBoard = currentBoard;
        this.action = action;
    }

    public List<Node<Integer>> getChildren() {
        return children;
    }

    public void setParent(Node<Integer> parent) {
        //parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(Integer data) {
        Node<Integer> child = new Node<Integer>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Node<Integer> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public Integer getData() {
        return this.data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
    public int[] getCurrentBoard()
    {
        return currentBoard;
    }
    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }

    public void setAction(int action)
    {
        this.action = action;
    }
    public int getAction()
    {
        return action;
    }
}