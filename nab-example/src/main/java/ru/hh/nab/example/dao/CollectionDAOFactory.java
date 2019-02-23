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

        taskDao.addTask(new SingleTask(1L, "FirstTask", true));
        taskDao.addTask(new SingleTask(2L, "FirstTask", false));
    }
}
