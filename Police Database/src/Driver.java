public class Driver{
    String licence;
    String name;
    String street;
    String city;
    String province;
    int current_infractions;
    public Driver(String a, String b, String c, String d, String e){
         licence = a;
         name = b;
         street = c;
         city = d;
         province = e;
        current_infractions =0;
    }
    public Driver(){
        licence = "Uknown";
        name = "Unknown";
        street = "Unknown";
        city = "Unknown";
        province = "Unknown";
        current_infractions =0;
    }
    public String toString(){
        return "#" + licence + " " + name + " living at " + street + ", "  + city + ", " + province;
    }
}
