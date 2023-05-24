package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.net.ServerSocket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @Author: Dinuta-Dumitrita Ciofu
 * <p>
 * Java Reflection permite inspectarea claselor, interfetelor, campurilor si metodelor in timpul rularii,
 * fara a cunoaste numele claselor, metodelor etc. la momentul compilarii. De asemenea, este posibil sa se
 * instantieze obiecte noi, sa se invoce metode si sa se obtina/sa se seteze valori ale campului
 * utilizand reflection.
 * <p>
 * In aceasta clasa se foloseste tehnica reflection pentru a crea o clasa generica care contine metode
 * pentru accesarea basei de date: crearea, editarea, stergerea si cautarea unui obiect.
 * Interogarile necesare accesarii bazei de date pentru un anumit obiect care corespunde unui tabel vor fi
 * generate dinamic prin reflection.
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Creeaza si returneaza o lista de obiecte de tip T pe baza rezultatului obtinut dintr-un ResultSet.
     * Metoda utilizeaza informatiile obtinute din constructorii declarate ale tipului T si reflecta asuupra
     * campurilor obiectului pentru a le initializa cu valorile corespunzatoare din ResultSet.
     * @param resultSet Rezultatul obtinut dintr-un ResultSet
     * @return O lista de obiecte de tip T initializate cu valorile din ResultSet.
     */

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();

//        for(Constructor<T> ctor : ctors){
//            System.out.println(ctor);
//        }

        System.out.println(ctors[0].toString()); //nhcejbhvgjk

        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];

          //  System.out.println(ctor);
            //System.out.println(ctor.getGenericParameterTypes());
          //  System.out.println(ctor.getGenericParameterTypes().length);

            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
            //    System.out.println("ajunge aici");
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Creeaza si returneaza un sir de caractere reprezentand o interogare SELECT pe baza numelui campului specificat.
     * Interogarea va selecta toate campurile din tabela corespunzatoare tipului T, unde valoarea campului specificat
     * este egala cu o valoare parametrizata.
     * @param field Numele campului utilizat in clauza WHERE pentru filtrarea rezultatelor.
     * @return Un sir de caractere reprezentand interogarea SELECT.
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Creeaza si returneaza un sir de caractere care reprezinta o interogare DELETE pentru stergerea inregistrarilor
     * dintr-o tabela specificata.
     * Sirul de caractere contine numele tabelei si conditia de stergere bazata pe un camp specificat.
     * @param field Campul utilizat pentru conditia de stergere.
     * @return Un sir de caractere reprezentand interogarea DELETE.
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Returneaza o lista cu toate obiectele de tip T din tabelul corespunzator in baza de date.
     * @return O lista continand toate obiectele de tip T din tabelul corespunzator in baza de date.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM ";
        query += type.getSimpleName(); //numele clasei (tabelei)

        System.out.println(query);


        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            //statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Cauta si returneaza un obiect de tip T pe baza ID-ului specificat.
     * @param id ID-ul obiectului cautat.
     * @return Obiectul de tip T care corespunde ID-ului specificate sau null daca nu a fost gasit niciun obiect.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Sterge inregistrarea corespunzatoare unui anumit id din baza de date.
     * @param id ID-ul inregistrarii care urmeaza sa fie stearsa.
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery("id");
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:detele " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     *
     * @param t obiect de tipul T utilizat pentru a obtine numele clasei
     *          si valorile fiecarui field
     * @return un String care reprezinta o interogare in SQL folosita pentru a insera o noua linie
     *         intr-un tabel din baza de date
     * @throws IllegalAccessException
     */
    private String createInsertQuery(T t) throws IllegalAccessException {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
         values.append(t.toString());

        System.out.println("Values is: "+ values);

        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName());
            columns.append(field.getName()).append(", ");
        }

        columns.deleteCharAt(columns.length() - 1); // șterge ultima virgulă
        columns.deleteCharAt(columns.length() - 1); // șterge ultima virgulă
       // columns.deleteCharAt(columns.length() - 2); // șterge ultima virgulă

        System.out.println("Columns is "+columns);


        //values.deleteCharAt(values.length() - 1); // șterge ultima virgulă

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append("(").append(columns).append(")");
        sb.append(" VALUES ").append(values);

        System.out.println(sb);


        return sb.toString();
    }


    /**
     * @Author: Dinuta-Dumitrita Ciofu
     * Metoda "insert" insereaza un obiect de tipul "T" in tabela corespunzatoare din baza de date.
     * @param t
     * @return t, obiectul inserat in baza de date
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = null;
        try {
            query = createInsertQuery(t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Creeaza si returneaza un sir de caractere care reprezinta interogarea SQL de actualizare pentru un obiect
     * de tip T in baza de date.
     * Interogarea va actualiza valorile campurilor obiectului in baza de date, avand in vedere ID-ul specificat.
     * @param t obiectul de tip T pentru care se creeaza interogarea de actualizare.
     * @param id ID-ul obiectului in baza de date
     * @return sirul de caractere raprezentand interogarea SQL de actualizare
     */
    private String createUpdateQuery(T t, Integer id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(t.getClass().getSimpleName());
        sb.append(" SET ");

        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                sb.append(field.getName()).append("='").append(field.get(t)).append("',");
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.deleteCharAt(sb.length()-1);

        sb.append(" WHERE id=" + id);

        System.out.println(sb);
        return sb.toString();
    }

    /**
     * Actualizeaza un obiect de tip T in baza de date, folosind interogarea SQL generata de metoda createUpdateQuery()
     * Obiectul este actualizat in functie de ID-ul specificate.
     * @param t obiectul de tip T care urmeaza sa fie actualizat in baza de date
     * @param id ID-ul obiectului in baza de date.
     * @return Obiectul actualizat de tip T
     */
    public T update(T t, Integer id) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(t, id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Creeaza si returneaza un obiect JTable care reprezinta o tabela de date pe baza unei liste de obiecte de tip T.
     * Fiecare obiect din lista va fi reprezentat ca o inregistrare in tabela, iar campurile obiectului vor fi coloanele tabelului.
     * @param list Lista de obiecte de tip T pentru care se creeaza tabela.
     * @return Obiectul JTable care reprezinta tabela de date.
     */
    public JTable createTable(List<T> list){
        ArrayList<String> columns = new ArrayList<>();

        for(Field field : type.getDeclaredFields()){
            field.setAccessible(true);
            columns.add(field.getName());
        }

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns.toArray());

        for(T t : list){
            ArrayList<Object> entities = new ArrayList<>();
            try{
                for(Field field : type.getDeclaredFields()){
                    field.setAccessible(true);
                    entities.add(field.get(t));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            tableModel.addRow(entities.toArray());
        }
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        return table;
    }

}
