import javafx.scene.shape.Shape;

import java.util.ArrayList;

/**
 * Created by mahmoud on 5/9/2016.
 */
public class HistoryHandler {

    MementoLinkedList mementos;
    Memento prev, current, next;
    ArrayList<ShapeLink> recycleBin;
    DrawApplication drawApplication;

    public HistoryHandler(DrawApplication drawApplication)
    {

        this.drawApplication = drawApplication;
        mementos = new MementoLinkedList();
        recycleBin = new ArrayList<>();
        prev = mementos.getSentinel();
        current = prev;
        next = prev;
    }

    public void addMemento(String message, ShapeLink actionShapeLink,
                           int type, String... moreInfo)
    {
        if (next != mementos.getSentinel())
            mementos.deleteUntil(current);

        next = mementos.getSentinel();

        prev = current;
        current = mementos.addMemento(message, actionShapeLink,  type,
                moreInfo);
    }

    public void undo()
    {
        /* if current is sentinel that means that there's no more actions
         * to undo
         * else set the next to the current and set the current to the
         * current.getNext() to move back in the chain of actions a step
         * if current is now the sentinel set prev also to the sentinel
         * else set the prev to current.getNext() to move prev a step behind
         * the current action
         */

        if (current != mementos.getSentinel())
        {
            next = current;
            current = current.getNext();

            if (current == mementos.getSentinel())
                prev = mementos.getSentinel();
            else
                prev = current.getNext();

            undoAction(next);
            System.out.println("---------");
            mementos.printList();
        }

    }

    public void redo()
    {
        /* if next is sentinel that means that there's no more actions to redo
         * else set the current to the next and the next to next.getPrev() to move
         * back in the chain of actions then set the prev to current.getNext()
         * redo current to redo the action
         */
        if (next != mementos.getSentinel())
        {
            current = next;
            next = next.getPrev();
            prev = current.getNext();

            redoAction(current);
            System.out.println(" - . - . - .");
            mementos.printList();
        }
    }

    private void undoAction(Memento action)
    {
        switch (action.getType())
        {
            case 0:
                drawApplication.deleteWithoutMemento((Shape) action.getActionShapeLink().getShapeFX());
                break;
            case 1:
                drawApplication.drawShape(action.getActionShapeLink());
                break;
        }
    }

    private void redoAction(Memento action)
    {
        switch (action.getType())
        {
            case 0:
                drawApplication.drawShape(action.getActionShapeLink());
                break;
            case 1:
                drawApplication.deleteWithoutMemento((Shape) action.getActionShapeLink().getShapeFX());
                break;
        }
    }

}
