package cn.mldn.vo;

import java.util.Date;

public class News {
    private Long nid;
    private String title;
    private Date pubdate;
    private String note;
    private Double price;
    private Integer readcount;
    

    
    public Integer getReadcount() {
        return readcount;
    }
    
    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }
    
    @Override
    public String toString() {
        return "News{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", pubdate=" + pubdate +
                ", note='" + note + '\'' +
                ", price=" + price +
                ", readcount=" + readcount +
                '}';
    }
    
    public Long getNid() {
        return nid;
    }
    
    public void setNid(Long nid) {
        this.nid = nid;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Date getPubdate() {
        return pubdate;
    }
    
    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
}
