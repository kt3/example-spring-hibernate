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
    private Date createDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public byte[] getDescription() {
        return description;
    }

    public void setDescription(byte[] description) {
        this.description = description;
    }

    public String getDescriptionText() {
        if (description == null) {
            return "";
        }
        return new String(description);
    }
}
