public class Combination {
    int first;
    int second;
    int third;
    
    public Combination (int f, int s, int t){
        first = f;
        second = s;
        third = t;
	toString();
    }
    
    boolean equals (Combination c){
        if (this.first == c.first & this.second == c.second & this.third == c.third ) {
            return true;
	} else {
            return false;
        }
    }
    
    public String toString(){
        return ""+first+':'+second+";"+third;
    }
    
    public static void main(String [] ar){
        Combination c1, c2, c3;
        c1 = new Combination(1, 2, 3);
        c2 = new Combination(1, 2, 3);
        c3 = new Combination(3, 2, 1);
        if (c1.equals(c2)){
            System.out.println("True...c1 is equals to c2");
        } else {
            System.out.println("False...c1 is not equal to c2");
        }
        if (c1.equals(c3)){
            System.out.println("True...c1 is equals to c3");
        } else {
            System.out.println("False...c1 is not equal to c3");
        }
        String s;
        s = c1.toString();
        System.out.println(s);
	}
}