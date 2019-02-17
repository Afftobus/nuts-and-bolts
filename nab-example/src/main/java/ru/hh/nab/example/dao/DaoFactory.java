package ru.hh.nab.example.dao;

public abstract class DaoFactory {
    public static final int DEFAULT = 0;
    public static final int COLLECTION = 1;

    public abstract TaskDao getTaskDao();

    public abstract void populateTestData();

    public static DaoFactory getDaoFactory(String factoryName) {
        if (factoryName == null || "".equals(factoryName)) {
            return getDaoFactory(DEFAULT);
        }
        switch (factoryName.toLowerCase()) {
            case "collection":
                return getDaoFactory(COLLECTION);
            default:
                throw new IllegalArgumentException("Unknown DAO implementation " + factoryName);
        }
    }

    public static DaoFactory getDaoFactory(int factoryCode) {

        switch (factoryCode) {
            case COLLECTION:
                return new CollectionDAOFactory();
            default:
                return new CollectionDAOFactory();
        }
    }



}
