package example.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Item {

    @Id
    @GeneratedValue
    private long id;


    @Column
    private String name;

    @Column
    @Lob
    private byte[] description;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getDescription() {
        return description;
    }

    public void setDescription(byte[] description) {
        this.description = description;
    }

    public String getDescriptionText(){
        return new String(description);
    }
}
