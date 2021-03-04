package glue.pudding.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GUIXu on 2020/6/20.
 */
public class ResponsePageBean implements Serializable{

    private Long total;
    //@JSONField(serialize = false)
    private List<? extends Object> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<? extends Object> getData() {
        return data;
    }

    public void setData(List<? extends Object> data) {
        this.data = data;
    }

}
