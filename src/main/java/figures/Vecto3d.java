package figures;

public class Vecto3d extends Vector{
    private final Double x,y,z;
    public Vecto3d(double x, double y, double z){
        super(x,y);
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public void pr(){
        System.out.println(x.toString() + y.toString() + z.toString());
    }
}
