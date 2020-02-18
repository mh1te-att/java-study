
public class PlayGround {
    public static void main( String[] args ) {
        String s = "/1/1/233/9/3/6/";
        String[] str = s.split("/");
        System.out.println(str[0]);
        StringBuilder sb = new StringBuilder("/");
        for (int i = 1; i < 5; i++) {
            sb.append(str[i]);
            sb.append("/");
        }
        System.out.println(sb.toString());
    }
}

