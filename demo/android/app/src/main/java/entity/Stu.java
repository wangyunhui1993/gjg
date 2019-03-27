package entity;

public class Stu {
    private int _id;
    private String sname;
    private String snumber;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "_id=" + _id +
                ", sname='" + sname + '\'' +
                ", snumber='" + snumber + '\'' +
                '}';
    }
}
