package ru.hh.nab.example.dao;

import ru.hh.nab.example.dao.impl.TaskDaoImpl;
import ru.hh.nab.example.model.SingleTask;

public class CollectionDAOFactory extends DaoFactory {

    private static final TaskDao taskDao = new TaskDaoImpl();

    @Override
    public TaskDao getTaskDao() {
        return taskDao;
    }

    @Override
    public void populateTestData() {
        ((TaskDaoImpl) taskDao).clean();

        taskDao.addTask(new SingleTask("FirstTask", "First Task Description", "2019-02-16", 1, true));
        taskDao.addTask(new SingleTask("FirstTask", "First Task Description", "2019-02-16", 1, false));
    }
}
