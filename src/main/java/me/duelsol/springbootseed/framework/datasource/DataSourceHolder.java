package me.duelsol.springbootseed.framework.datasource;

/**
 * @author 冯奕骅
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> DATA_SOURCE = new ThreadLocal<>();

    private DataSourceHolder() {}

    public static void setDataSource(String customerType) {
        DATA_SOURCE.set(customerType);
    }

    public static String getDataSource() {
        return DATA_SOURCE.get();
    }

    public static void clear() {
        DATA_SOURCE.remove();
    }

}
