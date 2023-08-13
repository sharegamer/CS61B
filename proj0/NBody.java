public class NBody {
    public static double readRadius(String s)
    {
        In in=new In(s);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String s)
    {
        In in=new In(s);
        int n = in.readInt();
        Planet[] p=new Planet[n];
        in.readDouble();
        int i=0;
        while (i<n)
        {
            p[i]=new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            i+=1;
        }
        return p;
    }
    public static void main(String[] args)
    {
    double T=Double.parseDouble(args[0]);
    double dt=Double.parseDouble(args[1]);
    String filename=args[2];
    double radius=readRadius(filename);
    Planet[] p=readPlanets(filename);
    StdDraw.setScale(-radius,radius);
    StdDraw.clear();
    StdDraw.picture(0,0,"images/starfield.jpg");
    StdDraw.show();
    for(Planet x:p)
    {
        x.draw();
    }
    StdDraw.enableDoubleBuffering();
    double t=0;
    while (t<T)
    {
        int i=0;
        double xforce,yforce;
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        while (i<p.length)
        {



            xforce=p[i].calcNetForceExertedByX(p);
            yforce=p[i].calcNetForceExertedByY(p);
            p[i].update(dt,xforce,yforce);
            p[i].draw();
            i+=1;
        }
        StdDraw.show();
        StdDraw.pause(10);
        t+=dt;
    }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }
    }

}
