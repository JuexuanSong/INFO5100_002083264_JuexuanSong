
import java.util.*;

// Singleton Pattern: Logger
class Logger {

    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// Factory Pattern: Transportation
interface Transport {

    void operate();
}

class Bus implements Transport {

    public void operate() {
        Logger.getInstance().log("Bus is operating on city route.");
    }
}

class Train implements Transport {

    public void operate() {
        Logger.getInstance().log("Train is running on rail track.");
    }
}

class Bike implements Transport {

    public void operate() {
        Logger.getInstance().log("Bike is delivering parcels.");
    }
}

class TransportFactory {

    public static Transport getTransport(String type) {
        switch (type.toLowerCase()) {
            case "bus":
                return new Bus();
            case "train":
                return new Train();
            case "bike":
                return new Bike();
            default:
                throw new IllegalArgumentException("Unknown transport type: " + type);
        }
    }
}

// Observer Pattern: Weather Station
interface Observer {

    void update(String weather);
}

class TransportManager implements Observer {

    private String name;

    public TransportManager(String name) {
        this.name = name;
    }

    public void update(String weather) {
        Logger.getInstance().log(name + " received weather update: " + weather);
    }
}

class WeatherStation {

    private List<Observer> observers = new ArrayList<>();
    private String weather;

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void setWeather(String weather) {
        this.weather = weather;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (Observer o : observers) {
            o.update(weather);
        }
    }
}

// Main Class
public class TransportMonitoringSystem {

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.log("System initializing...");

        // Factory usage
        Transport bus = TransportFactory.getTransport("bus");
        Transport train = TransportFactory.getTransport("train");
        Transport bike = TransportFactory.getTransport("bike");

        bus.operate();
        train.operate();
        bike.operate();

        // Observer usage
        WeatherStation station = new WeatherStation();
        Observer manager1 = new TransportManager("Alice - Bus Manager");
        Observer manager2 = new TransportManager("Bob - Train Manager");

        station.addObserver(manager1);
        station.addObserver(manager2);

        station.setWeather("Sunny");
        station.setWeather("Rainy");

        logger.log("System shutting down...");
    }
}
