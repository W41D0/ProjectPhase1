import java.io.Serializable;
public class Node<T> implements Serializable
{
    private Node next;
    private T data;

    public Node(T data)
    {
        this.data = data;
        next = null;
    }

    public T getData() 
    {
        return data;
    }

    public Node getNext() 
    {
        return next;
    }

    public void setData(T data) 
    {
        this.data = data;
    }

    public void setNext(Node next) 
    {
        this.next = next;
    }

    

}