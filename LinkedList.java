public class LinkedList<T>
{
    public Node<T> head;
    public Node<T> tail;

    public LinkedList() 
    {
        head = null;
        tail = null;
    }

    public boolean isEmpty() 
    {
        return head == null;
    }

    public void clear()
    {
        head = null;
        tail = null;
    }

    public void insertAtFront(T s)
    {
        Node n = new Node(s);
        if (isEmpty())
        {
            head = n;
            tail = n;
        }
        else
        {
            n.setNext(head);
            head = n;
        }
    }

    public void insertAtBack(T t)
    {
        if (isEmpty())
        {
            insertAtFront(t);
        }
        else
        {
            Node n = new Node(t);
            tail.setNext(n);
            tail = n;
        }
    }

    public void insert(T t, int index)
    {
        if(index < 0 || index > size())
        {
            return;
        }
        if (index == 0)
        {
            insertAtFront(t);
        }
        else
        {
            Node n = new Node(t);
            Node temp = get(index - 1);
            n.setNext(temp.getNext());
            temp.setNext(n);
        }
        
    }

    public void removeAtFront()
    {
        
    }

    public Node get(int index)
    {
        if (index >= size())
        {
            return null;
        }
        Node current = head;
        for(int i = 0; i < index; i++)
        {
            current = current.getNext();
        }
        return current;
    }

    public Node getFirst()
    {
        return head;
    }

    public Node getLast()
    {
        return tail;
    }

    public int size() {
        if (isEmpty()) 
        {
            return 0;
        }
        int counter = 0;
        Node current = head;
        while (current != null) 
        {
            counter++;
            current = current.getNext();
        }
        return counter;
    }
}