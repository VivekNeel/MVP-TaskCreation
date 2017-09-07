package in.task.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vivek on 07/09/17.
 */

public class Task extends RealmObject {

    private String content;
    @PrimaryKey
    private String id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
