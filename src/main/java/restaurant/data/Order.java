package restaurant.data;

public class Order {
    private int id;
    private int tableid;
    private int costumerid;
    private int statusid;
    private Date time;


    public Order(int id, int tableid, int costumerid, int statusid, Date time) {
        this.id = id;
        this.tableid = tableid;
        this.costumerid = costumerid;
        this.statusid = statusid;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableid() {
        return tableid;
    }

    public void setTableid(int tableid) {
        this.tableid = tableid;
    }

    public int getCostumerid() {
        return costumerid;
    }

    public void setCostumerid(int costumerid) {
        this.costumerid = costumerid;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
