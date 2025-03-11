package ro.mpp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        logger.traceEntry();
        String sql = "select * from masini where manufacturer = ?";
        List<Car> cars = new ArrayList<>();

        try (Connection connection = dbUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, manufacturerN);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cars.add(createEntityFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }

        logger.traceExit(cars);
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        logger.traceEntry();
        String sql = "select * from masini where year between ? and ?";
        List<Car> cars = new ArrayList<>();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, min);
            ps.setInt(2, max);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cars.add(createEntityFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }

        logger.traceExit(cars);
        return cars;
    }

    @Override
    public void add(Car elem) {
        logger.traceEntry("Saving task {} ", elem);
        String sql = "insert into masini (manufacturer, model, year) values (?, ?, ?)";

        try (Connection connection = dbUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, elem.getManufacturer());
            ps.setString(2, elem.getModel());
            ps.setInt(3, elem.getYear());

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }

        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Car elem) {
        logger.traceEntry("Updating task {} ", elem);
        String sql = "update masini set manufacturer = ?, model = ?, year = ? where id = ?";

        try (Connection connection = dbUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, elem.getManufacturer());
            ps.setString(2, elem.getModel());
            ps.setInt(3, elem.getYear());
            ps.setInt(4, integer);

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }

        logger.traceExit();
    }

    @Override
    public Iterable<Car> findAll() {
        logger.traceEntry();
        String sql = "select * from masini";

        List<Car> cars = new ArrayList<>();
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cars.add(createEntityFromResultSet(rs));
            }

        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }

        logger.traceExit(cars);
        return cars;

    }

    private Car createEntityFromResultSet(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String manufacturer = resultSet.getString("manufacturer");
            String model = resultSet.getString("model");
            int year = resultSet.getInt("year");

            Car c = new Car(manufacturer, model, year);
            c.setId(id);
            return c;

        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error createEntity " + e);

            return null;
        }
    }
}
