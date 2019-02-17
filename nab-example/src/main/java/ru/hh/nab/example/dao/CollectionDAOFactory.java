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

        taskDao.addTask(new SingleTask("fbhwaeifg uawigf uoiaw", "FirstTask", true));
        taskDao.addTask(new SingleTask("fbhwaeifg uf uog awiaw", "FirstTask", false));
    }
}
