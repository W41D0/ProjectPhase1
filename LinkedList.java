public class LinkedList<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() 
    {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() 
    {
        return head == null;
    }

    public void clear()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public void insertAtFront(T t)
    {
        Node<T> n = new Node<>(t);
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

        size++;
    }

    public void insertAtBack(T t)
    {
        if (isEmpty())
        {
            insertAtFront(t);
        }
        else
        {
            Node<T> n = new Node<>(t);
            tail.setNext(n);
            tail = n;
            size++;
        }
    }

    public void insert(T t, int index)
    {
        if(index < 0 || index > length())
        {
            return;
        }
        if (index == 0)
        {
            insertAtFront(t);
        }
        else
        {
            Node<T> n = new Node<>(t);
            Node<T> temp = get(index - 1);
            n.setNext(temp.getNext());
            temp.setNext(n);

            if(n.getNext() == null)
            {
                tail = n;
            }

            size++;
        }
        
    }

    public void removeAtFront()
    {
        if(isEmpty())
            return;
    
        head = head.getNext();

        if (head == null)
            tail = null;

        size--;
    }

    public void removeAtBack()
    {
        if (length() <= 1)
        {
            clear();
            return;
        }

        Node<T> temp = get(length() - 2);
        tail = temp;
        temp.setNext(null);
        size--;
    }

    public void remove(int index)
    {
        if (index < 0 || index >= length())
            return;
    
        if (index == length() - 1)
        {
            removeAtBack();
            return;
        }

        if (index == 0)
        {
            removeAtFront();
            return;
        }
        
        Node<T> temp = get(index - 1);
        temp.setNext(temp.getNext().getNext());
        size--;
    }

    public Node<T> get(int index)
    {
        if (index < 0 || index >= length())
        {
            return null;
        }
        Node<T> current = head;
        for(int i = 0; i < index; i++)
        {
            current = current.getNext();
        }
        return current;
    }

    public T getData(int index)
    {
        return get(index).getData();
    }

    public Node<T> getFirst()
    {
        return head;
    }

    public Node<T> getLast()
    {
        return tail;
    }

    public int length() 
    {
        return size;
    }
}