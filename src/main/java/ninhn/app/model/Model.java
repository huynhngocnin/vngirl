package ninhn.app.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by ninhn on 5/11/2016.
 */
public class Model {
    public Model() {
        setLastUpTime(new Date());
    }

    @Id
    private String id;
    @CreatedDate
    private Date createTime;
    private Date lastUpTime;

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
}
