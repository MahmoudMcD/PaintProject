import javafx.scene.shape.Shape;

import java.util.Date;

/**
 * Created by mahmoud on 5/9/2016.
 */
public class MementoLinkedList {
    private Memento sentinel;
    private int size = 0;

    public MementoLinkedList()
    {
        sentinel = new Memento();
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }


    public Memento addMemento(String message, ShapeLink actionShapeLink, int type,
                              String... moreInfo)
    {
        Memento newMemento = new Memento(message, new Date(), actionShapeLink, type,
                sentinel.getNext(), sentinel, moreInfo);

        sentinel.getNext().setPrev(newMemento);
        sentinel.setNext(newMemento);

        if (sentinel.getPrev() == sentinel)
            sentinel.setPrev(newMemento);

        size++;
        return newMemento;
    }

    public Memento getFirst()
    {
        return sentinel.getNext();
    }

    public Memento getLast()
    {
        return sentinel.getPrev();
    }

    public void deleteUntil(Memento current)
    {
        Memento temp = sentinel.getNext();

        while (temp != current && temp != sentinel)
        {
            size--;
            temp = temp.getNext();
        }
        sentinel.setNext(temp);
    }

    public int getSize() {
        return size;
    }

    public void printList()
    {
        Memento temp = sentinel.getNext();

        while (temp != sentinel)
        {
            System.out.println(temp.getMessage());
            System.out.println();
            temp = temp.getNext();
        }
    }

    public Memento getSentinel() {return sentinel;}
}
