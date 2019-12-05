import java.util.Date;
public class PoliceDatabase {
    Vehicle[] vehicles;
    int numVehicles = 0;
    Driver[] drivers;
    int numDrivers = 0;
    Infraction[] infractions;
    int numInfractions = 0;
    static final int V_limit = 1000;
    static final int D_limit = 2000;
    static final int I_limit = 800;

    public static PoliceDatabase example() { // Register all drivers and their vehicles
        PoliceDatabase pdb = new PoliceDatabase();

        pdb.registerDriver(new Driver("L1567-34323-84980", "Matt Adore",
                "1323 Kenaston St.", "Gloucester", "ON"));
        pdb.registerDriver(new Driver("L0453-65433-87655", "Bob B. Pins",
                "32 Rideau Rd.", "Greely", "ON"));
        pdb.registerDriver(new Driver("L2333-45645-54354", "Stan Dupp",
                "1355 Louis Lane", "Gloucester", "ON"));
        pdb.registerDriver(new Driver("L1234-35489-99837", "Ben Dover",
                "2348 Walkley Rd.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L8192-87498-27387", "Patty O'Lantern",
                "2338 Carling Ave.", "Nepean", "ON"));
        pdb.registerDriver(new Driver("L2325-45789-35647", "Ilene Dover",
                "287 Bank St.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L1213-92475-03984", "Patty O'Furniture",
                "200 St. Laurant Blvd.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L1948-87265-34782", "Jen Tull",
                "1654 Stonehenge Cres.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L0678-67825-83940", "Jim Class",
                "98 Oak Blvd.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L0122-43643-73286", "Mark Mywords",
                "3 Third St.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L6987-34532-43334", "Bob Upandown",
                "434 Gatineau Way", "Hull", "QC"));
        pdb.registerDriver(new Driver("L3345-32390-23789", "Carrie Meehome",
                "123 Thurston Drive", "Kanata", "ON"));
        pdb.registerDriver(new Driver("L3545-45396-88983", "Sam Pull",
                "22 Colonel By Drive", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L1144-26783-58390", "Neil Down",
                "17 Murray St.", "Nepean", "ON"));
        pdb.registerDriver(new Driver("L5487-16576-38426", "Pete Reedish",
                "3445 Bronson Ave.", "Ottawa", "ON"));
        pdb.registerVehicle(new Vehicle("Honda", "Civic", 2015, "yellow", "W3EW4T"),
                "L0453-65433-87655");
        pdb.registerVehicle(new Vehicle("Pontiac", "Grand Prix", 2007, "dark green", "GO SENS"),
                "L0453-65433-87655");
        pdb.registerVehicle(new Vehicle("Mazda", "RX-8", 2004, "white", "OH YEAH"),
                "L2333-45645-54354");
        pdb.registerVehicle(new Vehicle("Nissan", "Altima", 2017, "bergundy", "Y6P9O7"),
                "L1234-35489-99837");
        pdb.registerVehicle(new Vehicle("Saturn", "Vue", 2002, "white", "9R6P2P"),
                "L2325-45789-35647");
        pdb.registerVehicle(new Vehicle("Honda", "Accord", 2018, "gray", "7U3H5E"),
                "L2325-45789-35647");
        pdb.registerVehicle(new Vehicle("Chrysler", "PT-Cruiser", 2006, "gold", "OLDIE"),
                "L2325-45789-35647");
        pdb.registerVehicle(new Vehicle("Nissan", "Cube", 2010, "white", "5Y6K8V"),
                "L1948-87265-34782");
        pdb.registerVehicle(new Vehicle("Porsche", "959", 1989, "silver", "CATCHME"),
                "L0678-67825-83940");
        pdb.registerVehicle(new Vehicle("Kia", "Soul", 2018, "red", "J8JG2Z"),
                "L0122-43643-73286");
        pdb.registerVehicle(new Vehicle("Porsche", "Cayenne", 2014, "black", "EXPNSV"),
                "L6987-34532-43334");
        pdb.registerVehicle(new Vehicle("Nissan", "Murano", 2010, "silver", "Q2WF6H"),
                "L3345-32390-23789");
        pdb.registerVehicle(new Vehicle("Honda", "Element", 2008, "black", "N7MB5C"),
                "L3545-45396-88983");
        pdb.registerVehicle(new Vehicle("Toyota", "RAV-4", 2010, "green", "R3W5Y7"),
                "L3545-45396-88983");
        pdb.registerVehicle(new Vehicle("Toyota", "Celica", 2006, "red", "FUNFUN"),
                "L5487-16576-38426");

        return pdb;
    }


    public PoliceDatabase() { // set the limits for the lists

        vehicles = new Vehicle[V_limit];
        drivers = new Driver[D_limit];
        infractions = new Infraction[I_limit];
    }

    public void registerDriver(Driver aDriver) {// log the drivers into the driver array
        if (numDrivers < D_limit) {
            drivers[numDrivers] = aDriver;
            numDrivers++;
        } else
            System.out.println("DRIVER LIMIT EXCEEDED");
    }

    public void registerVehicle(Vehicle aVehicle, String license) {// log the vehicles into the array
        if (numVehicles < V_limit) {
            vehicles[numVehicles] = aVehicle;
            ++numVehicles;
            for (int i = 0; i < numDrivers; i++) {
                if (drivers[i].licence == license) {
                    aVehicle.owner = drivers[i]; // apply the owner to the vehicle
                    break;
                }
            }

        } else
            System.out.println("Vehicle LIMIT EXCEEDED"); // if the array hits the limit give them a error
    }

    public void unregisterVehicle(String plate) {// put the wehicles into the array
        for (int i = 0; i < numVehicles; i = i + 1) {
            if (vehicles[i].plate == plate) {
                for (; i < numVehicles - 1; i = i + 1) {
                    vehicles[i] = vehicles[i + 1];
                }


            }

        }
        numVehicles -= 1;
    }

    public void reportStolen(String plate) {// go through the wehicle array find the one with the licence plate and report it stolen
        for (int i = 0; i < numVehicles; i = i + 1) {
            if (vehicles[i].plate == plate) {
                vehicles[i].reportedStolen = true;
                break;
            }

        }

    }

    public void changeOwner(String plate, String license) {// go through the wehicle array find the one with the licence plate find the wehicle with the plate
        Vehicle current_vehicle;
        for (int l = 0; l < numVehicles; l = l + 1) {
            if (vehicles[l].plate == plate) {
                current_vehicle = vehicles[l];
                for (int i = 0; i < numVehicles; i = i + 1) {// go through the list again and find the licence
                    if (vehicles[i].owner.licence == license) {
                        current_vehicle.owner = vehicles[i].owner;// set the current vehicles owner to the wehicles owner with he given licence
                        break;
                    }
                }
                break;

            }

        }

    }

    public Infraction issueInfraction(String license, float amount, String description, Date date) {// create a infraction for a driver
        if (numInfractions < I_limit) {
            for (int i = 0; i < numDrivers; i = i + 1) {
                if (drivers[i].licence == license) {
                    infractions[numInfractions] = new Infraction(amount, description, date);;
                    infractions[numInfractions].driver = drivers[i];
                    numInfractions = numInfractions + 1;

                    return infractions[numInfractions - 1 ];
                }


            }

        }
        return new Infraction();
    }

    public boolean hasOutstandingInfractions(Driver d) {// if the driver has an outstanding infraction then return true

        for (int i = 0; i < numInfractions; i = i + 1) {
            if (infractions[i].driver == d) {
                if (infractions[i].outstanding)
                    return true;
            }
        }
        return false;
    }

    public boolean shouldStopVehicle(String plate) {// checks if the licence plate of a vehicle should be stopped by seeing if it has any infractions or was stolen
        for (int l = 0; l < numVehicles; l = l + 1) {
            if (vehicles[l].plate == plate) {
                if (vehicles[l].reportedStolen || hasOutstandingInfractions(vehicles[l].owner)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void showInfractionsFor(String license) {//goes through the infraction array and finds how many time the driver has gotten an infraction
        byte tracker = 0;
        for (int i = 0; i < numInfractions; i = i + 1) {
            if (infractions[i].driver.licence == license) {
                System.out.println(infractions[i]);
                if (infractions[i].outstanding){
                    tracker += 1;
                }
            }
        }
        System.out.println("Total outstanding infractions = " + tracker);
    }
    public Driver[] cleanDrivers() {
        int driver_count = 0;
        int counter = 0;
        Driver[] clean_driver = new Driver[numDrivers];// creates a open array meant for only clean drivers
        for (int l = 0; l < numDrivers; l = l + 1) {
            driver_count = 0;

            for (int i = 0; i < numInfractions; i = i + 1) {
                if (infractions[i].driver == drivers[l]) {// fills array with clean drivers
                    driver_count +=1;
                }

            }
            if (driver_count == 0) {
                clean_driver[counter] = drivers[l];// puts driver in array
                counter += 1;
            }
        }
        Driver[] actual_clean_driver = new Driver[counter];// inputs all the clean drivers within the clean drivers array to a array with an exact amount of clean drivers
        for (int i = 0; i < counter; i = i + 1) {
                actual_clean_driver[i] = clean_driver[i];
            }
        return actual_clean_driver;
    }
    public void showInfractionReport() {//show all the bad drivers
        int driver_count = 0;
        Driver[] cleans_driver = cleanDrivers(); // get clean drivers list
        int c = numDrivers - cleans_driver.length; // get length of the bad drivers array
        for (int l = 0; l < numDrivers; l = l + 1) {
            driver_count = 0;
            for (int i = 0; i < cleans_driver.length; i = i + 1) {
                if (drivers[l] == cleans_driver[i]){
                    driver_count+=1;// means it is a clean driver
                 }
            }
            float amount = 0.0f;
            if (driver_count == 0){// if its not a clean driver
                for (int i = 0; i < numInfractions; i = i + 1){//
                    if (drivers[l] == infractions[i].driver){
                        drivers[l].current_infractions +=1;
                        if (!infractions[i].outstanding){// if the infraction was paid ie. false, then add the amount
                            amount += infractions[i].amount;
                        }
                    }
                }
                System.out.println(String.format("%20s", drivers[l].name) +": "+  drivers[l].current_infractions + " infractions, total paid = $"  + String.format("%6.2f", amount));
            }
        }
    }
}


