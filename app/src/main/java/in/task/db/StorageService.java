package in.task.db;

import java.util.List;

import in.task.data.Task;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by vivek on 07/09/17.
 */

public enum StorageService {
    INSTANCE;

    public static StorageService getInstance() {
        return INSTANCE;
    }

    private Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }

    public <T extends RealmObject> T storeTask(T model) {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
        realm.close();
        return model;
    }

    public void deleteTask(String id, Class<? extends RealmObject> tClass) {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        realm.where(tClass).equalTo("id", id).findFirst().deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public List<Task> getAllTasks() {
        Realm realm = getRealmInstance();
        List<Task> taskList;
        RealmResults<Task> taskRealmResults = realm.where(Task.class).findAll();
        taskList = realm.copyFromRealm(taskRealmResults);
        realm.close();
        return taskList;
    }

    public Task getTaskByID(String id) {
        return getRealmInstance().where(Task.class).equalTo("id", id).findFirst();
    }
}
