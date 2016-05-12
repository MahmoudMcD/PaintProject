import javafx.scene.shape.Shape;

import java.util.Date;

/**
 * Created by mahmoud on 5/9/2016.
 */
public class Memento {
    private String message;
    private Date date;

    private ShapeLink actionShapeLink;
    private int type;
    private String[] moreInfo;

    private Memento next;
    private Memento prev;


    public Memento()
    {
        message = null;
        date = new Date();
        actionShapeLink = null;
        next = prev = null;
    }

    public Memento(String message, Date date, ShapeLink actionShapeLink, int type,
                   Memento next, Memento prev, String... moreInfo)
    {
        this.message = message;
        this.date = date;
        this.actionShapeLink = actionShapeLink;
        this.type = type;
        this.moreInfo = moreInfo;
        this.next = next;
        this.prev = prev;
    }

    public String getMessage() { return message; }
    public Date getDate() { return date;}
    public Memento getNext() {return next;}
    public Memento getPrev() {return prev;}

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setNext(Memento next) {
        this.next = next;
    }

    public void setPrev(Memento prev)
    {
        this.prev = prev;
    }

    public ShapeLink getActionShapeLink() {
        return actionShapeLink;
    }

    public void setActionShapeLink(ShapeLink actionShapeLink) {
        this.actionShapeLink = actionShapeLink;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String[] moreInfo) {
        this.moreInfo = moreInfo;
    }
}
