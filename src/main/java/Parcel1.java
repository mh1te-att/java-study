public class Parcel1 {
    class Contents{
        private int i = 120012;
        public int value() {return i;}

        @Override
        public String toString() {
            return "Contents{" +
                    "i=" + i +
                    '}';
        }
    }

    class  Destination{
        private String label;
        Destination(String whereTo){
            label = whereTo;
        }
        String readLable() {return label;}
    }

    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(c.value());
        System.out.println(d.readLable());
    }

    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Zhengy");
    }
}