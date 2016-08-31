package ninhn.app.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ninhn on 5/11/2016.
 */
public class Model implements Serializable {
    public Model() {
        setLastUpTime(new Date());
    }

    @Id
    private String id;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date lastUpTime;
    private boolean deleted = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpTime() {
        return lastUpTime;
    }

    public void setLastUpTime(Date lastUpTime) {
        this.lastUpTime = lastUpTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
